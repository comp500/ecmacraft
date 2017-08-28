package link.infra.ecmacraft.emu.apis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
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
	
	private Charset convertEncoding(String charset) {
		switch (charset) {
			case "utf8":
				return StandardCharsets.UTF_8;
			case "utf-8":
				return StandardCharsets.UTF_8;
			case "ascii":
				return StandardCharsets.US_ASCII;
			case "utf16le":
				return StandardCharsets.UTF_16LE;
			case "utf-16le":
				return StandardCharsets.UTF_16LE;
			case "ucs2":
				return StandardCharsets.UTF_16LE;
			case "ucs-2":
				return StandardCharsets.UTF_16LE;
			case "latin1":
				return StandardCharsets.ISO_8859_1;
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

	public void access(String path, Function<Exception, Void> callback) {
		access(path, constants.get("F_OK"), callback);
	}

	public void access(String path, int mode, Function<Exception, Void> callback) {
		new Thread(new Runnable() {
			public void run() {
				File f = sanitisePath(path).toFile();
				if (!f.exists()) {
					callback.apply(new IOException()); // TODO fix to allow EEXIST checking etc
					return;
				}
				if (((mode & constants.get("X_OK")) == constants.get("X_OK")) && !f.canExecute()) {
					callback.apply(new AccessDeniedException("EPERM"));
					return;
				}
				if (((mode & constants.get("W_OK")) == constants.get("W_OK")) && !f.canWrite()) {
					callback.apply(new AccessDeniedException("EPERM"));
					return;
				}
				if (((mode & constants.get("R_OK")) == constants.get("R_OK")) && !f.canRead()) {
					callback.apply(new AccessDeniedException("EPERM"));
					return;
				}
				callback.apply(null);
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
	// TODO support binary, base64, hex
	// TODO support multiple options

	public void appendFile(String path, String data, Function<Exception, Void> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Files.write(sanitisePath(path), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
					callback.apply(null);
				} catch (IOException e) {
					callback.apply(e);
				}
			}
		}).start();
	}
	
	public void appendFile(String path, String data, String charset, Function<Exception, Void> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Files.write(sanitisePath(path), data.getBytes(convertEncoding(charset)), StandardOpenOption.APPEND);
					callback.apply(null);
				} catch (IOException e) {
					callback.apply(e);
				}
			}
		}).start();
	}

	public void appendFileSync(String path, String data, Function<Exception, Void> callback) throws IOException {
		Files.write(sanitisePath(path), data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
	}
	
	public void appendFileSync(String path, String data, String charset, Function<Exception, Void> callback) throws IOException {
		Files.write(sanitisePath(path), data.getBytes(convertEncoding(charset)), StandardOpenOption.APPEND);
	}

	// TODO add encoding for readFile(Sync)

	public void readFile(String path, BiFunction<Exception, String, Void> callback) {
		new Thread(new Runnable() {
			public void run() {
				try {
					byte[] encoded = Files.readAllBytes(sanitisePath(path));
					callback.apply(null, new String(encoded, StandardCharsets.UTF_8));
				} catch (IOException e) {
					callback.apply(e, null);
				}
			}
		}).start();
	}

	public String readFileSync(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(sanitisePath(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
