package link.infra.ecmacraft.emu.apis;

import java.util.function.Function;

public class UrlApi {
	
	public Parse parse;

	// TODO implement boolean parseQueryString, boolean slashesDenoteHost
	
	public class Parse implements Function<String, LegacyUrl> {
		@Override
		public LegacyUrl apply(String url) {
			return new LegacyUrl(url, false, false);
		}
	}
	
	public UrlApi() {
		parse = new Parse();
	}

	public class LegacyUrl {

		public LegacyUrl(String url, boolean parseQueryString, boolean slashesDenoteHost) {
			// TODO Auto-generated constructor stub
		}

	}

}
