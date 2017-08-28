package link.infra.ecmacraft.emu.apis;
import jdk.nashorn.api.scripting.AbstractJSObject;

public class StreamApi {
	
	// This is far too hacky for my liking. This is because Nashorn doesn't support proper initialisation of inner classes for some reason.
	// You have to change the access rules for eclipse to compile this without errors. It seems to think extending Nashorn is a bad thing - I agree.
	
	public PassThroughInit PassThrough;
	
	public StreamApi() {
		PassThrough = new PassThroughInit();
	}
	
	public class PassThroughInit extends AbstractJSObject {
		@Override
		public PassThrough newObject(Object... test) {
			return new PassThrough(test[0]);
		}
		
		@Override
		public PassThrough call(Object thiz, Object... test) {
			return new PassThrough(test[0]);
		}
	}
	
	public class PassThrough {
		private Object options;
		
		public PassThrough(Object test) {
			options = test;
			System.out.println(options);
		}
	}

}
