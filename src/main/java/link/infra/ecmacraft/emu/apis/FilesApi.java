package link.infra.ecmacraft.emu.apis;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import link.infra.ecmacraft.emu.IRunEnv;

public class FilesApi {

	private IRunEnv env;

	public FilesApi(IRunEnv environment) {
		env = environment;
	}

	public boolean stat(String path) {
		File f = new File(env.getComputerDir(), path);
		return f.exists();
	}

	public String readFileSync(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
