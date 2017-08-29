package link.infra.ecmacraft.emu.apis;

import java.util.function.Function;

public class ProcessApi {
	
	public void nextTick(Function<Void, Void> callback) {
		/*new Thread(new Runnable() {
			public void run() {
				callback.run();
			}
		}).start();*/
		callback.apply(null);
	}

}
