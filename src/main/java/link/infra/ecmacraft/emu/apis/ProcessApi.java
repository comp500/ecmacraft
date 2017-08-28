package link.infra.ecmacraft.emu.apis;

public class ProcessApi {
	
	public void nextTick(Runnable callback) {
		new Thread(new Runnable() {
			public void run() {
				callback.run();
			}
		}).start();
	}

}
