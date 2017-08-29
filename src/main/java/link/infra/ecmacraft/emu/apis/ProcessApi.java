package link.infra.ecmacraft.emu.apis;

import java.util.concurrent.Callable;

public class ProcessApi {
	
	public void nextTick(Callable<Void> callback) {
		/*new Thread(new Runnable() {
			public void run() {
				callback.run();
			}
		}).start();*/
		try {
			callback.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
