package link.infra.ecmacraft.emu.apis;

import java.util.function.Function;

import link.infra.ecmacraft.emu.SandboxRunner;

public class Require implements Function<String, Object> {
	
	private SandboxRunner run;
	
	public Require(SandboxRunner runner) {
		run = runner;
	}

	@Override
	public Object apply(String msg) {
		if (msg == "fs") {
			return new Files();
		}
		run.evalCode(msg);
		return null;
	}

}
