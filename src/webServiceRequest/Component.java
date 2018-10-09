package webServiceRequest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class Component {
	public  static void updateLabel(JComponent l, int size) {

		l.setFont(new Font("Arial", Font.PLAIN, size));
	
		l.setForeground(Color.WHITE);
		
		
	}
	
	public static void updateButton(JButton b,LoginPanel lp) {
		b.setOpaque(true);
		b.setBorderPainted(true);

		b.setPreferredSize(new Dimension(400, 60));
		b.setForeground(Color.WHITE);
		b.setBorder(new LineBorder(Color.decode("#0893ca"), 2, true));
		b.setFocusable(false);
		b.setBackground(new Color(10,175,241));
		b.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(lp.gettingData==false) {
				b.setBackground(new Color(10,175,241));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(lp.gettingData==false) {
				b.setBackground(Color.decode("#0893ca"));
				}
				

			}

		});

	}
	public static void updateButton(JButton b,LicensePanel lp) {
	
		b.setBorderPainted(true);

		b.setPreferredSize(new Dimension(400, 60));
		b.setForeground(Color.WHITE);
		b.setBorder(new LineBorder(Color.decode("#0893ca"), 2, true));
		b.setFocusable(false);
		b.setContentAreaFilled(false);
		b.setBackground(new Color(10,175,241));
		b.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(lp.gettingData==false) {
				b.setBackground(new Color(10,175,241));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(lp.gettingData==false) {
				b.setBackground(Color.decode("#0893ca"));
				}
				

			}

		});
		b.setOpaque(true);
	}


	public static void updateField(JTextComponent t) {
		t.setPreferredSize(new Dimension(400, 40));
		t.setForeground(Color.gray);
		t.setOpaque(false);
		t.setCaretColor(new Color(10,175,241));
		t.setSelectionColor(new Color(10,175,241));
		t.setBorder(new LineBorder(new Color(10,175,241), 2, true));
	   

		t.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				t.setBorder(new LineBorder(new Color(10,175,241), 2, true));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			
				t.setBorder(new LineBorder(Color.decode("#0893ca"), 2, true));


			}

		});
	}
	public static void updateCancelButton(JButton b,LoginPanel lp) {
		
		b.setBorderPainted(true);

		b.setPreferredSize(new Dimension(250, 40));
		b.setForeground(Color.WHITE);
		b.setBorder(new LineBorder(Color.decode("#be1d1d"), 2, true));
		b.setFocusable(false);
		b.setContentAreaFilled(false);
		b.setBackground(Color.decode("#b61414"));
		b.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				b.setBackground(Color.decode("#b61414"));
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				b.setBackground(new Color(154, 0, 0));
				
				

			}

		});
		b.setOpaque(true);
	}
	public static void createLink(JComponent l) {
		
	   

		l.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				l.setForeground(Color.white);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			
				l.setForeground(Color.decode("#0893ca"));


			}

		});
	}

}
