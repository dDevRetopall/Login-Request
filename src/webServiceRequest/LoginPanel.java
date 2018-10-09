package webServiceRequest;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;

public class LoginPanel extends JPanel {
	JLabel l = new JLabel("LOG IN");
	JLabel l2 = new JLabel("Username   ");
	JLabel l3 = new JLabel("Password   ");
	JLabel l4 = new JLabel("Dont have an account? Click here to register");
	JLabel l5 = new JLabel("Error");
	JLabel l6 = new JLabel("Did you forget password?");
	JButton b;
	JButton b2;
	JTextField campUser = new JTextField();
	JPasswordField campPwd = new JPasswordField();
	JPanel user = new JPanel(new FlowLayout());
	JPanel pwd = new JPanel(new FlowLayout());
	JPanel south = new JPanel(new FlowLayout());
	JPanel souther = new JPanel(new FlowLayout(FlowLayout.CENTER, 170, 20));
	JPanel southest = new JPanel(new FlowLayout());
	JPanel moreSouther = new JPanel(new FlowLayout());
	JPanel north = new JPanel(new FlowLayout());
	JLabel gif = new JLabel();
	JCheckBox check = new JCheckBox("Remember account", true);
	ImageIcon icon = new ImageIcon(getClass().getResource("loading.gif"));
	private LoginWindow lw;
	boolean gettingData = false;
	Thread t ;
	boolean exception=false;
	public LoginPanel(LoginWindow lw) {
		campUser.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					startLoginRequest();
				}
			}
		});
		campPwd.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					startLoginRequest();
				}
			}
		});
		b = new JButton("Log in");
		b2 = new JButton("Log out");
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
		user.setOpaque(false);
		pwd.setOpaque(false);
		south.setOpaque(false);
		souther.setOpaque(false);

		north.setOpaque(false);
		b.setOpaque(false);
		user.add(l2);
		user.add(campUser);
		pwd.add(l3);
		pwd.add(campPwd);
		south.add(b);
		String[] datos = SaveSettings.read();

		southest.add(l5);
		moreSouther.add(l4);
		moreSouther.setOpaque(false);
		souther.add(l6);
		souther.add(check);
		add(north, gbc);
		gbc.gridy = 1;
		add(user, gbc);
		gbc.gridy = 2;
		add(pwd, gbc);
		gbc.gridy = 3;
		add(souther, gbc);

		gbc.gridy = 4;
		add(south, gbc);
		gbc.gridy = 5;
		add(moreSouther, gbc);
		gbc.gridy = 6;
		if (datos[0] != null && datos[1] != null) {
			if (datos.length > 0) {
				add(b2, gbc);
			}else {
				add(southest, gbc);
			}
		}else {
		add(southest, gbc);
		}
		Component.updateLabel(check, 16);
		check.setOpaque(false);
		check.setFocusable(false);
		Component.updateLabel(l, 20);
		Component.updateLabel(l2, 16);
		Component.updateLabel(l3, 16);
		Component.updateLabel(l4, 16);
		Component.updateLabel(l5, 16);
		Component.updateLabel(l6, 16);
		Component.updateLabel(b, 18);
		Component.updateLabel(b2, 18);
		Component.updateButton(b, this);
		Component.updateCancelButton(b2, this);

		Component.createLink(l6);
		Component.createLink(l4);
		Component.createLink(check);
		Component.updateLabel(campUser, 14);
		Component.updateLabel(campPwd, 14);
		Component.updateField(campUser);
		campUser.setHorizontalAlignment(JTextField.CENTER);
		campPwd.setHorizontalAlignment(JTextField.CENTER);

		Component.updateField(campPwd);

		southest.setBackground(new Color(164, 0, 0, 153));
		l5.setForeground(new Color(200, 0, 0));

		southest.setVisible(false);
		southest.setBorder(new MatteBorder(2, 2, 2, 2, new Color(123, 20, 20)));

		if (datos[0] != null && datos[1] != null) {
			if (datos.length > 0) {
				b.setIcon(icon);
				b.updateUI();
				b.setBackground(Color.BLACK);
				enableLabels(false);
				campUser.setText(datos[0]);
				campPwd.setText(datos[1]);
				gettingData = true;
				
			t= new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							exception=true;
							
						
						} 
						if(!exception) {
						login(datos[0], datos[1]);
						lw.validate();
						lw.repaint();
						b.setIcon(null);
						b.setBackground(new Color(10, 175, 241));
						b.updateUI();

						gettingData = false;
						enableLabels(true);
						}

					}
				});
				t.start();

			}
		}
		l4.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lw.setState(JFrame.ICONIFIED);

				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("http://www.retopall.com/styles/connectionPages/register.php"));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					l5.setText(
							"We couldnt open your browser sorry. Go to: http://www.retopall.com/styles/connectionPages/register.php");
					southest.setVisible(true);
				}
				super.mouseClicked(e);
			}

		});
		l6.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lw.setState(JFrame.ICONIFIED);
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("http://www.retopall.com/styles/connectionPages/forgottenPwd.php"));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					l5.setText(
							"We couldnt open your browser sorry. Go to: http://www.retopall.com/styles/connectionPages/forgottenPwd.php");
					southest.setVisible(true);
				}
				super.mouseClicked(e);
			}

		});
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startLoginRequest();

			}
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveSettings.removeFile();
				lw.setContentPane(new LoginPanel(lw));
				lw.repaint();
				lw.validate();
				exception=true;
				
				
			}
		});

	}

	public void startLoginRequest() {
		b.setIcon(icon);
		b.updateUI();
		gettingData = true;
		enableLabels(false);

		b.setBackground(Color.BLACK);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				login();
				gettingData = false;
				lw.validate();
				lw.repaint();
				b.setIcon(null);
				b.updateUI();
				b.setBackground(new Color(10, 175, 241));
				enableLabels(true);

			}
		});
		t.start();
	}

	public void login() {
		String user = campUser.getText();
		char[] pwd = campPwd.getPassword();
		if (!user.isEmpty() && pwd.length != 0) {
			String password = "";
			for (int i = 0; i < pwd.length; i++) {
				password = password + pwd[i];
			}

			String result = LoginRequest.loginRequest(user, password, false);
			if (result.equals("1")) {
				lw.user = user;
				if (check.isSelected()) {
					String cryptedPassword = LicenceFileUtils.crypt(password);
					SaveSettings.save(user, cryptedPassword);
				}
				if (lw.requireLicense) {
					lw.setContentPane(new LicensePanel(lw));
				} else {
					lw.dispose();
					lw.onLogin();
				}
			} else {
				l5.setText(result);
				southest.setVisible(true);
			}
		} else {
			l5.setText("Fields user and password required");
			southest.setVisible(true);
		}

	}

	public void login(String user, String password) {

		String result = LoginRequest.loginRequest(user, password, true);
		if (result.equals("1")) {
			lw.user = user;
			SaveSettings.save(user, password);
			if (lw.requireLicense) {
				lw.setContentPane(new LicensePanel(lw));
			} else {
				lw.dispose();
				lw.onLogin();
			}
		} else {
			l5.setText(result);
			southest.setVisible(true);
		}

	}

	public void enableLabels(boolean action) {

		l.setEnabled(action);
		l2.setEnabled(action);
		l3.setEnabled(action);
		l4.setEnabled(action);
		l6.setEnabled(action);

		campUser.setEnabled(action);
		campPwd.setEnabled(action);

	}
}
