package link.infra.ecmacraft.emu.apis;

import java.io.IOException;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require {
	
	private IRunEnv env;
	private FilesApi filesapi;
	
	public Require(IRunEnv environment) {
		env = environment;
		filesapi = new FilesApi(env);
	}

	public Object getnative(String msg) {
		if (msg == "fs") {
			return filesapi;
		}
		return null;
	}
	
	public String get(String msg) { // todo fix to require algorithm
		try {
			return filesapi.readFileSync(msg);
		} catch (IOException e) {
			return null;
		}
	}

}
