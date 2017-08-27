package link.infra.ecmacraft.emu;

import java.util.concurrent.Executors;

import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import link.infra.ecmacraft.emu.apis.Console;
import link.infra.ecmacraft.emu.apis.Require;

public class SandboxRunner {
	
	private NashornSandbox sandbox;
	private IRunEnv env;
	
	public SandboxRunner(IRunEnv environment) {
		env = environment;
		sandbox = NashornSandboxes.create();
		sandbox.setMaxCPUTime(100); // prevent while(true)
		sandbox.setExecutor(Executors.newSingleThreadExecutor());
		sandbox.allowLoadFunctions(true);
		sandbox.setDebug(true);
	}
	
	public void init() {
		// inject "native" functions into Nashorn
		sandbox.inject("console", new Console());
		sandbox.inject("require", new Require(env));
	}
	
	public void injectRequireFix() {
		// load require override with eval() and fs
	}
	
	public void injectBootloader() {
		// load init script
	}
	
	public Object evalCode(String code) {
		return sandbox.eval(code);
	}
	
}
