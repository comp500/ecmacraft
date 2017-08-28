package link.infra.ecmacraft.emu.apis;

import java.io.IOException;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require {
	
	private IRunEnv env;
	
	public Require(IRunEnv environment) {
		env = environment;
	}

	public Object getnative(String msg) {
		if (msg == "fs") {
			return new FilesApi(env);
		}
		return null;
	}
	
	public String get(String msg) {
		FilesApi files = new FilesApi(env);
		try {
			return files.readFileSync(msg);
		} catch (IOException e) {
			return null;
		}
	}

}
