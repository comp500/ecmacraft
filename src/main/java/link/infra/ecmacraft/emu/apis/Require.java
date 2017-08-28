package link.infra.ecmacraft.emu.apis;

import java.io.IOException;

import link.infra.ecmacraft.emu.IRunEnv;

public class Require {
	
	private IRunEnv env;
	private FilesApi filesapi;
	private UrlApi urlapi;
	private StreamApi streamapi;
	
	public Require(IRunEnv environment) {
		env = environment;
		filesapi = new FilesApi(env);
		urlapi = new UrlApi();
		streamapi = new StreamApi();
		//streamapi.PassThrough = StreamApi.PassThroughClass.class;
	}

	public Object getnative(String msg) {
		if (msg == "fs") {
			return filesapi;
		}
		if (msg == "url") {
			return urlapi;
		}
		if (msg == "stream") {
			//StreamApi.PassThrough test = streamapi.new PassThrough(null);
			return streamapi;
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
