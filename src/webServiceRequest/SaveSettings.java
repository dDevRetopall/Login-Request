package webServiceRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveSettings {
	static File f= new File("data.log");
	public static void save(String user,String pwd) {
	
		try {
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw,true);
			pw.println(user);
			pw.println(pwd);
			pw.close();
		} catch (IOException e) {
			System.out.println("Save error");
			e.printStackTrace();
		}
		
	}
	public static void removeFile() {
		System.out.println("aa");
		f.delete();
	}
	public static String[] read() {
		String[] values = new String[2];
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line="";
			int c=0;
			while((line=br.readLine())!=null) {
				values[c]=line;
				c++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Read error");
			e.printStackTrace();
		}
		return values;
	}
	public static void createAsNeeded() {
		
	}
}
