package link.infra.ecmacraft.emu.apis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	}
	
	private Path sanitisePath(String path) {
		return Paths.get(env.getComputerDir(), path);
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
	
	public void readFile(String path, BiFunction<Exception, String, Void> callback) throws IOException {
		new Thread(new Runnable() {
			public void run() {
				byte[] encoded;
				try {
					encoded = Files.readAllBytes(sanitisePath(path));
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
