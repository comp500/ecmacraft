package link.infra.ecmacraft.emu.apis;

import java.io.File;

import link.infra.ecmacraft.emu.IRunEnv;

public class Files {
	
	private IRunEnv env;
	
	public Files(IRunEnv environment) {
		env = environment;
	}
	
	public boolean stat(String path) {
		File f = new File(env.getComputerDir(), path);
		return f.exists();
	}

}
