package webServiceRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LicenseRequest {
	public static String licenseRequest(String username,String license,int idApp,boolean crypted) {
		if(!crypted) {
			license=LicenceFileUtils.crypt(license);
		}
		
		String request = "http://www.retopall.com/styles/connectionPages/checkLicense.php?license="+license+"&username="+username+"&app="+idApp;
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
	    
	        int bool=0;
	        while((line=in.readLine())!=null){
	        	get+=line;
	        	
	        }
	   
	        System.out.println("License response: "+get.trim());
	        bool=Integer.parseInt(get.trim());
	        if(bool==1) {
	        	return "1";
	        }else {
	        	return "License doesnt exist";
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
