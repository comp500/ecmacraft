package link.infra.ecmacraft.emu.apis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import link.infra.ecmacraft.emu.IRunEnv;

public class FilesApi {

	private IRunEnv env;
	public final Map<String, Integer> constants;

	public FilesApi(IRunEnv environment) {
		env = environment;
		constants = new HashMap<String, Integer>();
		constants.put("F_OK", 0);
		constants.put("X_OK", 1);
		constants.put("W_OK", 2);
		constants.put("R_OK", 4);
	}

	private Path sanitisePath(String path) {
		return Paths.get(env.getComputerDir(), path);
	}
	
	private byte[] convertEncoding(String data, String charset) {
		switch (charset) {
			case "utf8":
				return data.getBytes(StandardCharsets.UTF_8);
			case "utf-8":
				return data.getBytes(StandardCharsets.UTF_8);
			case "ascii":
				return data.getBytes(StandardCharsets.US_ASCII);
			case "base64":
				return Base64.getDecoder().decode(new String(data).getBytes(StandardCharsets.UTF_8));
			case "hex":
				int len = data.length();
			    byte[] out = new byte[len / 2];
			    for (int i = 0; i < len; i += 2) {
			        out[i / 2] = (byte) ((Character.digit(data.charAt(i), 16) << 4)
			                             + Character.digit(data.charAt(i+1), 16));
			    }
			    return out;
			case "binary":
				return data.getBytes(StandardCharsets.ISO_8859_1);
			case "latin1":
				return data.getBytes(StandardCharsets.ISO_8859_1);
			case "utf16le":
				return data.getBytes(StandardCharsets.UTF_16LE);
			case "utf-16le":
				return data.getBytes(StandardCharsets.UTF_16LE);
			case "ucs2":
				return data.getBytes(StandardCharsets.UTF_16LE);
			case "ucs-2":
				return data.getBytes(StandardCharsets.UTF_16LE);
		}
		throw new UnsupportedCharsetException(charset + " not supported");
	}

	@Deprecated
	public void exists(String path, Function<Boolean, Void> callback) {
		new Thread(new Runnable() {
			public void run() {
				File f = new File(env.getComputerDir(), path);
				callback.apply(f.exists());
			}
		}).start();
	}

	@Deprecated
	public boolean existsSync(String path) {
		File f = new File(env.getComputerDir(), path);
		return f.exists();
	}

	// TODO fix for Buffers

	public void access(String path, Consumer<Exception> callback) {
		access(path, constants.get("F_OK"), callback);
	}

	public void access(String path, int mode, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				File f = sanitisePath(path).toFile();
				if (!f.exists()) {
					callback.accept(new IOException()); // TODO fix to allow EEXIST checking etc
					return;
				}
				if (((mode & constants.get("X_OK")) == constants.get("X_OK")) && !f.canExecute()) {
					callback.accept(new AccessDeniedException("EPERM"));
					return;
				}
				if (((mode & constants.get("W_OK")) == constants.get("W_OK")) && !f.canWrite()) {
					callback.accept(new AccessDeniedException("EPERM"));
					return;
				}
				if (((mode & constants.get("R_OK")) == constants.get("R_OK")) && !f.canRead()) {
					callback.accept(new AccessDeniedException("EPERM"));
					return;
				}
				callback.accept(null);
			}
		}).start();
	}

	public void accessSync(String path) throws IOException {
		accessSync(path, constants.get("F_OK"));
	}

	public void accessSync(String path, int mode) throws IOException {
		File f = sanitisePath(path).toFile();
		if (!f.exists()) {
			throw new IOException(); // TODO fix to allow EEXIST checking etc
		}
		if (((mode & constants.get("X_OK")) == constants.get("X_OK")) && !f.canExecute()) {
			throw new AccessDeniedException("EPERM");
		}
		if (((mode & constants.get("W_OK")) == constants.get("W_OK")) && !f.canWrite()) {
			throw new AccessDeniedException("EPERM");
		}
		if (((mode & constants.get("R_OK")) == constants.get("R_OK")) && !f.canRead()) {
			throw new AccessDeniedException("EPERM");
		}
	}

	// TODO fix for file descriptor ints and Buffers
	// TODO support multiple options

	public void appendFile(String path, String data, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Files.write(sanitisePath(path), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
					callback.accept(null);
				} catch (IOException e) {
					callback.accept(e);
				}
			}
		}).start();
	}
	
	public void appendFile(String path, String data, String charset, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Files.write(sanitisePath(path), convertEncoding(data, charset), StandardOpenOption.APPEND);
					callback.accept(null);
				} catch (IOException e) {
					callback.accept(e);
				}
			}
		}).start();
	}

	public void appendFileSync(String path, String data) throws IOException {
		Files.write(sanitisePath(path), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
	}
	
	public void appendFileSync(String path, String data, String charset) throws IOException {
		Files.write(sanitisePath(path), convertEncoding(data, charset), StandardOpenOption.APPEND);
	}
	
	// pretend we implement chmod, but do nothing
	
	public void chmod(String path, int mode, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				callback.accept(null);
			}
		}).start();
	}
	
	public void chmodSync(String path, int mode) {
		// nothing to see here
	}
	
	// pretend we implement chown, but do nothing
	
	public void chown(String path, int uid, int gid, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				callback.accept(null);
			}
		}).start();
	}
	
	public void chownSync(String path, int uid, int gid) {
		// nothing to see here
	}
	
	// TODO implement file descriptors
	
	public void close(int fd, Consumer<Exception> callback) {
		new Thread(new Runnable() {
			public void run() {
				callback.accept(null);
			}
		}).start();
	}
	
	public void closeSync(int fd) {
		// nothing to see here
	}

	// TODO add encoding for readFile(Sync)

	public void readFile(String path, BiConsumer<Exception, String> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					byte[] encoded = Files.readAllBytes(sanitisePath(path));
					callback.accept(null, new String(encoded, StandardCharsets.UTF_8));
				} catch (IOException e) {
					callback.accept(e, null);
				}
			}
		}).start();
	}

	public String readFileSync(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(sanitisePath(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
