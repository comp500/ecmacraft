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
		sandbox.setDebug(true);
	}

	public void init() {
		// inject "native" functions into Nashorn
		sandbox.inject("console", new Console());
		sandbox.inject("require_native", new Require(env));
		injectRequireFix();
	}

	/*
       var module = { exports: {} };
	   var exports = module.exports;
	   var require = (function(require_native){
			return function (str) {
				var req = require_native(str);
				if (req == null) {
					try {
						var oldModule = module;
						var oldExports = exports;
						module = { exports: {} };
						exports = module.exports;
						eval(require_native("fs").readFileSync(str));
						var fin = module.exports;
						module = oldModule;
						exports = oldExports;
						return fin;
					} catch (e) {
						return null;
					}
				} else {return require_native(str);}
			}
		})(require_native);
		delete require_native;
	 */

	public void injectRequireFix() {
		// load require override with eval() and fs
		sandbox.eval("var module={exports:{}},exports=module.exports,require=function(a){return function(b){if(null==a(b))try{var c=module,d=exports;module={exports:{}};exports=module.exports;eval(a(\"fs\").readFileSync(b));var e=module.exports;module=c;exports=d;return e}catch(f){return null}else return a(b)}}(require_native);delete require_native;");
	}

	public void injectBootloader() {
		// load init script
	}

	public Object evalCode(String code) {
		return sandbox.eval(code);
	}

}
