package link.infra.ecmacraft.emu.apis;

public class UrlApi {
	
	public LegacyUrl parse(String url, boolean parseQueryString, boolean slashesDenoteHost) {
		return new LegacyUrl(url, parseQueryString, slashesDenoteHost);
	}
	
	public LegacyUrl parse(String url, boolean parseQueryString) {
		return new LegacyUrl(url, parseQueryString, false);
	}
	
	public LegacyUrl parse(String url) {
		return new LegacyUrl(url, false, false);
	}
	
	public class LegacyUrl {

		public LegacyUrl(String url, boolean parseQueryString, boolean slashesDenoteHost) {
			// TODO Auto-generated constructor stub
		}
		
	}

}
