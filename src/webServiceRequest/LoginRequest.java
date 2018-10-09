package webServiceRequest;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.ImageIcon;

public class LoginRequest {
	public static String loginRequest(String username,String password,boolean crypted) {
		if(!crypted) {
		password=LicenceFileUtils.crypt(password);
		}
		String request = "http://www.retopall.com/styles/connectionPages/request.php?username="+username+"&password="+password;
		URL url;
		try {
			url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("charset", "utf-8");
			connection.connect();
			
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	        String get="",line="";
	        int c=0;
	        int bool=0;
	        while((line=in.readLine())!=null){
	        	get+=line;
	        	
	        }
	   
	        System.out.println(get.trim());
	        bool=Integer.parseInt(get.trim());
	        if(bool==1) {
	        	return "1";
	        }else {
	        	return "User and password dont match";
	        }
	     
				
	       
	        				
	        	

		} catch (MalformedURLException e) {
			System.out.println("URL not valid");
			return "URL no valida";
		} catch (ProtocolException e) {
			System.out.println("Protocol exception");
			return "Protocol exception";
		} catch (IOException e) {
			System.out.println("Network exception");
			return "We couldnt connect to the servers";
		}
		

	}
}
