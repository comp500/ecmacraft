package link.infra.ecmacraft.emu.apis;

import java.io.IOException;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require {
	
	private IRunEnv env;
	public FilesApi filesapi;
	public UrlApi urlapi;
	public StreamApi streamapi;
	public ProcessApi processapi;
	public HttpApi httpapi;
	
	public Require(IRunEnv environment) {
		env = environment;
		filesapi = new FilesApi(env);
		urlapi = new UrlApi();
		streamapi = new StreamApi();
		processapi = new ProcessApi();
		httpapi = new HttpApi();
	}

	public Object getnative(String msg) {
		if (msg == "fs") {
			return filesapi;
		}
		if (msg == "url") {
			return urlapi;
		}
		if (msg == "stream") {
			return streamapi;
		}
		if (msg == "process") {
			return processapi;
		}
		if (msg == "http") {
			return httpapi;
		}
		return null;
	}
	
	public String get(String msg) { // todo fix to require algorithm
		try {
			return filesapi.readFileSync(msg);
		} catch (IOException e) {
			System.out.println("Missing module " + msg);
			return null;
		}
	}

}
