package link.infra.ecmacraft.emu.apis;

import java.net.MalformedURLException;
import java.net.URL;
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
		
		public String auth;
		public String hash;
		public String host;
		public String hostname;
		public String href;
		public String path;
		public String pathname;
		public int port;
		public String protocol;
		public String query;
		public String search;
		public boolean slashes = true;

		public LegacyUrl(String url, boolean parseQueryString, boolean slashesDenoteHost) {
			 try {
				URL aURL = new URL(url);
				
				auth = aURL.getUserInfo();
				hash = aURL.toString();
				host = aURL.getAuthority();
				hostname = aURL.getHost();
				href = "#" + aURL.getRef();
				path = aURL.getFile();
				pathname = aURL.getPath();
				port = aURL.getPort();
				protocol = aURL.getProtocol();
				query = aURL.getQuery(); // parse if parseQueryString
				search = "?" + aURL.getQuery();
			} catch (MalformedURLException e) {
				// ignore
			}
		}

	}

}
