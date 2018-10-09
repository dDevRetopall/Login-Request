package webServiceRequest;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;



public class LicensePanel extends JPanel {

	PassTextField p;
	JLabel l = new JLabel("Enter licence ");
	JButton b ;
	JLabel l6 = new JLabel("STEP 2");
	JLabel l2 = new JLabel("Username   ");
	JLabel l3 = new JLabel("Password   ");
	JLabel l4 = new JLabel("Dont have a license? Contacts us to apply for a license");
	JLabel l5 = new JLabel("Error");
	JPanel step = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel south = new JPanel(new FlowLayout());
	JPanel souther = new JPanel(new FlowLayout(FlowLayout.CENTER,170,20));
	JPanel southest = new JPanel(new FlowLayout());
	JPanel moreSouther = new JPanel(new FlowLayout());
	JPanel north = new JPanel(new FlowLayout());

	
	ImageIcon icon = new ImageIcon(getClass().getResource("loading.gif"));
	LoginWindow lw;

	boolean gettingData=false;


	public LicensePanel(LoginWindow lw) {

		step.add(l6);
		step.setOpaque(false);
		b= new JButton("Validate");
		this.lw = lw;
		setBackground(new Color(30, 30, 30));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 20;
		gbc.ipady = 20;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		north.add(l);
		
		south.setOpaque(false);
		souther.setOpaque(false);

		north.setOpaque(false);
		b.setOpaque(false);
	
		south.add(b);
		
		
		southest.add(l5);
		moreSouther.add(l4);
		moreSouther.setOpaque(false);
		
		
		
		MaskFormatter mf1;
		try {
			mf1 = new MaskFormatter("AAAAAA-AAAAAA-AAAAAA-AAAAAA");
			
			mf1.setPlaceholderCharacter('_');
			p = new PassTextField(20,mf1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gbc.gridy = 1;
		add(north, gbc);
		gbc.gridy = 2;
		add(p, gbc);
		gbc.gridy = 3;
		add(souther, gbc);
	
		gbc.gridy = 4;
		add(south, gbc);
		gbc.gridy = 5;
		add(moreSouther, gbc);
		gbc.gridy = 6;
		add(southest, gbc);
		gbc.gridy = 7;
		
		Component.updateLabel(l, 20);
		Component.updateLabel(l2, 16);
		Component.updateLabel(l3, 16);
		Component.updateLabel(l4, 16);
		Component.updateLabel(l5, 16);
		Component.updateLabel(l6, 16);


		Component.updateLabel(b, 18);
		Component.updateButton(b,this);
	
	
		Component.createLink(l4);
		
	

	
		
		southest.setBackground(new Color(164, 0, 0, 153));
		l5.setForeground(new Color(200, 0, 0));

		southest.setVisible(false);
		southest.setBorder(new MatteBorder(2, 2, 2, 2, new Color(123, 20, 20)));
		String myLicense=LicenceFileUtils.getMyLicence();
		if(myLicense!=null) {
		b.setIcon(icon);
		b.updateUI();
		b.setBackground(Color.BLACK);
		gettingData=true;
		p.setEnabled(false);
		b.setText("Validating");
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String result=LicenseRequest.licenseRequest(lw.user, myLicense,lw.idApp, true);
				
				if(result.equals("1")) {
					LicenceFileUtils.addLicence(myLicense,true);
					lw.onLogin();
					lw.dispose();
					l.setVisible(true);
				}else {
					LicenceFileUtils.clear(LicenceFileUtils.f2);
					l5.setText("Invalid license or another user is using the license");
					southest.setVisible(true);
				
				}
				b.setIcon(null);
				b.setBackground(new Color(10,175,241));
				b.updateUI();
				b.setText("Validate");
				p.setEnabled(true);
				gettingData=false;
				
			
				
			}
		});
		t.start();
		}	
		
		l4.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				lw.setState(JFrame.ICONIFIED);
				
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("http://www.retopall.com/webPages/contact.php"));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					l5.setText(
							"We couldnt open your browser sorry. Go to: http://www.retopall.com/webPages/contact.php");
					southest.setVisible(true);
				}
				super.mouseClicked(e);
			}

		});
	
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setIcon(icon);
				b.updateUI();
				gettingData=true;
				b.setText("Validating");
				b.setBackground(Color.BLACK);
				p.setEnabled(false);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						checkLicense();
						gettingData=false;
						lw.validate();
						lw.repaint();
						b.setIcon(null);
						b.updateUI();
						b.setBackground(new Color(10,175,241));
						b.setText("Validate");
						p.setEnabled(true);
					

					}
				});
				t.start();
				
				
				
			}
		});
		
		
		
	}
	public void checkLicense() {
		String license=p.getText().replace("-", "").replace("_", "");
		
		
		if(!license.equals("")) {
		String result=LicenseRequest.licenseRequest(lw.user, license,lw.idApp, false);
		
		if(result.equals("1")) {
			LicenceFileUtils.addLicence(license,false);
			lw.onLogin();
			lw.dispose();
			l.setVisible(true);
		}else {
			l5.setText("Invalid license or another user is using the license");
			southest.setVisible(true);
		}
		}else {
			l5.setText("Please add a license to use this product");
			southest.setVisible(true);
		}
	}
}
