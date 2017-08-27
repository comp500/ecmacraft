package link.infra.ecmacraft.emu.apis;

public class Console {
	
	public void log(String str) {
		System.out.println(str);
	}
	
	public void error(String str) {
		log(str);
	}
	
	public void info(String str) {
		log(str);
	}
	
	public void warn(String str) {
		log(str);
	}

}
