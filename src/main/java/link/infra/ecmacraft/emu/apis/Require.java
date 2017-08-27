package link.infra.ecmacraft.emu.apis;

import java.util.function.Function;

import link.infra.ecmacraft.emu.ProcessRunner;

public class Require implements Function<String, Object> {
	
	private ProcessRunner run;
	
	public Require(ProcessRunner runner) {
		run = runner;
	}

	@Override
	public Object apply(String msg) {
		if (msg == "fs") {
			return new Files();
		}
		run.evalScript(msg);
		return null;
	}

}
