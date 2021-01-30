
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Auto extends JFrame {
	
	public JLabel tname;
	static public JTextField login;
	static public JPasswordField pass;
	public JButton enter;
	public JPanel panel;
	static int col;
	private Scanner sc;
	
	public static void main(String[] args) throws FileNotFoundException {
		new Auto();
	}

	public Auto() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int vert = sSize.height;
		int hor  = sSize.width;
		int locationX = (sSize.width - hor/2) / 2;
		int locationY = (sSize.height - vert/2) / 3;
		setBounds(locationX, locationY, hor/4, vert/2);
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4,1));
		panel.setVisible(true);
		
		Font mainfont = new Font("Century Gothic", Font.PLAIN, 32);
		Font secondfont = new Font("Century Gothic", Font.PLAIN, 24);
		
		tname = new JLabel("Isk Prog");
		tname.setFont(mainfont);
		tname.setHorizontalAlignment(JLabel.CENTER);
		
		login = new JTextField("Логин");
		login.setHorizontalAlignment(JTextField.CENTER);
		login.setFont(secondfont);
		
		sc = new Scanner(new File("src/pass.txt"));
		String slogin, spass;
		slogin = sc.nextLine();
		spass = sc.nextLine();
		
		String hintlogin = "Логин";
		login.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (login.getText().equals(hintlogin)) {
	            	login.setText("");
	            }
	        }
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (login.getText().isEmpty()) {
	            	login.setText(hintlogin);
	            }
	        }
	    });
		
		String hintpass = "Пароль";
		pass = new JPasswordField(hintpass);
		pass.setHorizontalAlignment(JTextField.CENTER);
		pass.setFont(secondfont);
		pass.setEchoChar((char)0);
		
		pass.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (pass.getText().equals(hintpass)) {
	            	pass.setText("");
	            	pass.setEchoChar('•');
	            }
	        }
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (pass.getText().isEmpty()) {
	            	pass.setText(hintpass);
	            	pass.setEchoChar((char)0);
	            }
	        }
	    });
		
		enter = new JButton("Войти");
		enter.setFont(secondfont);
		enter.setBackground(new Color(173, 214, 254));
		panel.setBackground(new Color(230, 235, 235));
		login.setBackground(new Color(250, 250, 250));
		pass.setBackground(new Color(250, 250, 250));
			
		panel.add(tname);
		panel.add(login);
		panel.add(pass);
		panel.add(enter);

		ProgressBarRotating progressBarRotating=new ProgressBarRotating();
		enter.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) {  
				if (login.getText().equals(slogin) && pass.getText().equals(spass)) {		
						dispose();	
						new Main();	
				} else {
					login.setBackground(Color.PINK);
					pass.setBackground(Color.PINK);
					pass.setText("Пароль");
					pass.setEchoChar((char)0);	
					if (!progressBarRotating.isAlive())
				    progressBarRotating.start();
					else {
						if (col > 5) {
							col = 0;
						}
					}
				}
			} 	    	                             
		}); 
		
		setVisible(true);
		enter.transferFocus(); 
		enter.grabFocus();	
	}
}

class ProgressBarRotating extends Thread {
	boolean showProgress = true;
	public void run() {
	Boolean isVisible = true;
		while (showProgress) {
			isVisible=!isVisible;
			if (Auto.col <=5) { Auto.col++;
				if(isVisible) {
					Auto.login.setBackground(Color.PINK);
					Auto.pass.setBackground(Color.PINK);
				}
				else
				{
					Auto.login.setBackground(new Color(250, 250, 250));
					Auto.pass.setBackground(new Color(250, 250, 250));
				}
			} else {
				Auto.login.setBackground(new Color(250, 250, 250));
				Auto.pass.setBackground(new Color(250, 250, 250));
			}
            try { Thread.sleep(70); }
            catch (Exception e) {};
		}
	}
		
}
	
