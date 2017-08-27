package link.infra.ecmacraft.emu;

import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import link.infra.ecmacraft.emu.apis.Console;
import link.infra.ecmacraft.emu.apis.Require;

public class SandboxRunner {
	
	private NashornSandbox sandbox;

	public void run() {
		sandbox = NashornSandboxes.create();
		sandbox.inject("console", new Console());
		sandbox.inject("require", new Require(this));
		sandbox.eval("var fs = require(\"fs\");");
		sandbox.eval("console.log(fs.stat(\"./src\"))");
	}

	public void evalScript(String script) {
		System.out.println("eval " + script);
		sandbox.eval("console.log(\"Welcome from script \" + \"" + script + "\")");
	}
	
}
