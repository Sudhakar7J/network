import java.io.*; 
import java.net.URL; 
import java.net.MalformedURLException; 
public class downHTTP{
	public static void main(String[] args) {
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		try {
			FileWriter ipFile = new FileWriter("downpage.html");
			BufferedWriter ipStream = new BufferedWriter(ipFile);
			url = new URL("http://www.ssn.net/twiki/bin/view/SsnIntranet/SsnLibrary");
			is = url.openStream();  // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				ipStream.write(line);
			}
			ipStream.close();
			System.out.println("SuccessFully Wrote into downpage.html");
		} catch (MalformedURLException mue) {
			 mue.printStackTrace();
		} catch (IOException ioe) {
			 ioe.printStackTrace();
		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
	}
}