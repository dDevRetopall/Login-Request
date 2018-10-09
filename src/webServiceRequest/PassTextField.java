package webServiceRequest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;


public class PassTextField extends JFormattedTextField{
	
	public PassTextField(int lenght,MaskFormatter f) {
		super(f);
		
		setColumns(lenght);
		
		
		setOpaque(false);
		setSelectionColor(Color.gray);
		setSelectedTextColor(Color.LIGHT_GRAY);
		setCaretColor(Color.GRAY);
		Component.updateLabel(this, 20);
		setForeground(new Color(200, 200, 200, 200));
		setBorder(new LineBorder(new Color(120, 120, 120, 120)));
		setPreferredSize(new Dimension(980, 35));
		setHorizontalAlignment(JTextField.CENTER);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(new Color(120, 120, 120, 120)));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(120, 120, 120, 200)));

			}

		});
	}
	public PassTextField(int width,boolean yes) {
		setPreferredSize(new Dimension(width, 35));
		

		setOpaque(false);
		setSelectionColor(Color.gray);
		setSelectedTextColor(Color.LIGHT_GRAY);
		setCaretColor(Color.GRAY);
		setForeground(new Color(200, 200, 200, 200));
		setBorder(new LineBorder(new Color(120, 120, 120, 120)));
	
		setHorizontalAlignment(JTextField.CENTER);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(new LineBorder(new Color(120, 120, 120, 120)));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(new LineBorder(new Color(120, 120, 120, 200)));

			}

		});
	}
	
}

