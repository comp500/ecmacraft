package link.infra.ecmacraft.emu;

import java.util.concurrent.Executors;

import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import link.infra.ecmacraft.emu.apis.Console;
import link.infra.ecmacraft.emu.apis.Require;

public class SandboxRunner {
	
	private NashornSandbox sandbox;
	
	public SandboxRunner() {
		sandbox = NashornSandboxes.create();
		sandbox.setMaxCPUTime(100); // prevent while(true)
		sandbox.setExecutor(Executors.newSingleThreadExecutor());
	}
	
	public void init() {
		// inject "native" functions into Nashorn
		sandbox.inject("console", new Console());
		sandbox.inject("require", new Require(this));
	}
	
	public void injectBootloader() {
		Require scriptLoader = new Require(this);
		scriptLoader.apply("init"); // init script from ROM
	}
	
	public void evalCode(String code) {
		sandbox.eval(code);
	}
	
}
