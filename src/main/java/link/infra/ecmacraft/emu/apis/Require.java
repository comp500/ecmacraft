package link.infra.ecmacraft.emu.apis;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require {
	
	private IRunEnv env;
	
	public Require(IRunEnv environment) {
		env = environment;
	}

	public Object require(String msg) {
		if (msg == "fs") {
			return new FilesApi(env);
		}
		return null;
	}
	
	public String resolve(String msg) {
		return msg;
	}

}
