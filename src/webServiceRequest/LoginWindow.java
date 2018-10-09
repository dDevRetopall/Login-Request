package webServiceRequest;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public abstract class LoginWindow extends JFrame {

	public boolean requireLicense;
	String user="";
	 int idApp;
	public LoginWindow(String appName,int idApp,boolean requireLicense) {
	
		this.idApp = idApp;
		this.requireLicense = requireLicense;
		setSize(1000, 670);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setContentPane(new LoginPanel(this));
		setTitle("Launcher - "+appName);
		setVisible(true);
		String imagePath = "/webServiceRequest/favicon.png";
		InputStream imgStream =getClass().getResourceAsStream(imagePath );
		BufferedImage myImg;
		try {
			myImg = ImageIO.read(imgStream);
			setIconImage(myImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public abstract void onLogin();

	public abstract void onCancel();
	public int getIdApp() {
		return idApp;
		
	}
	public String getUser() {
		return user;
	}
}
