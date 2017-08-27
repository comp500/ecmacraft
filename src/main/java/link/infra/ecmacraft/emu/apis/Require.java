package link.infra.ecmacraft.emu.apis;

import java.util.function.Function;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require implements Function<String, Object> {
	
	private IRunEnv env;
	
	public Require(IRunEnv environment) {
		env = environment;
	}

	@Override
	public Object apply(String msg) {
		if (msg == "fs") {
			return new Files(env);
		}
		return null;
	}

}
