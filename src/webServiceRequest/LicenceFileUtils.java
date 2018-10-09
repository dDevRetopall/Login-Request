package webServiceRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class LicenceFileUtils {
	private static MessageDigest digester;
	static File f = new File("searchLicense.license");
	static File f2 = new File("myLicense.license");

	public static void createLicence(String licence) {

		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String s = crypt(licence);
		try {
			Writer output;
			output = new BufferedWriter(new FileWriter(f, true));
			output.append(s + "\n");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addLicence(String licence,boolean crypted) {
		if(!crypted) {
		licence=crypt(licence);
		}
		if (!f2.exists()) {
			try {
				f2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String s = licence;
		try {
			Writer output;
			output = new BufferedWriter(new FileWriter(f2, false));
			output.append(s + "\n");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getMyLicence() {
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(f2));
			String sCurrentLine;

			while ((sCurrentLine = input.readLine()) != null) {
				return sCurrentLine;

			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No licences");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean checkCryptedLicence() {
		String cryptedLicence=getMyLicence();
		if ( cryptedLicence!= null) {
			String q = cryptedLicence;
			System.out.println("Mi licencia actual "+cryptedLicence);
			BufferedReader input;
			try {
				input = new BufferedReader(new FileReader(f));
				String sCurrentLine;

				while ((sCurrentLine = input.readLine()) != null) {
				
					if (q.equals(sCurrentLine)) {
						System.out.println("Licencia aceptada");
						return true;
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				System.out.println("No licences");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(f2.exists()) {
		JOptionPane.showMessageDialog(null, "Your license has expired");
		}
		clear(f2);
		boolean r=f2.delete();
		System.out.println("File deleted: "+r);
		return false;
	}
	public static void clear(File f) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(f);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static boolean checkLicence(String licence) {
		String q = crypt(licence);
		System.out.println("Posible licencia: "+licence);
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(f));
			String sCurrentLine;

			while ((sCurrentLine = input.readLine()) != null) {
		
				
				if (q.equals(sCurrentLine)) {
					System.out.println("Licencia aceptada");
					addLicence(q,true);
					return true;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No licences");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static String crypt(String str) {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}

		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}
}
