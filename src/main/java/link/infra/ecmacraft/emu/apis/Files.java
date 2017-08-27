package link.infra.ecmacraft.emu.apis;

import java.io.File;

public class Files {
	
	public boolean stat(String path) {
		File f = new File(path);
		return f.exists();
	}

}
