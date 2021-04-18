
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; //������ ���������

public class Auto extends JFrame {
	
	public JLabel tname;
	static public JTextField login;
	static public JPasswordField pass;
	public JButton enter;
	public JPanel panel;
	static int col;
	private Scanner sc;
	static String user;
	
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
		
		BorderLayout border1 = new BorderLayout();
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4,1,20,20));
		panel.setVisible(true);
		
		Font mainfont = new Font("Calibri", Font.PLAIN, 36);
		Font secondfont = new Font("Calibri", Font.PLAIN, 24);
		
		tname = new JLabel("Isk Prog");
		tname.setFont(mainfont);
		tname.setHorizontalAlignment(JLabel.CENTER);
		
		login = new JTextField("�����");
		login.setHorizontalAlignment(JTextField.CENTER);
		login.setFont(secondfont);
		
		sc = new Scanner(new File("src/main/resources/pass.txt"));
		String slogin, spass;
		slogin = sc.nextLine();
		spass = sc.nextLine();
		
		String hintlogin = "�����";
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
		
		String hintpass = "������";
		pass = new JPasswordField(hintpass);
		pass.setHorizontalAlignment(JTextField.CENTER);
		pass.setFont(secondfont);
		pass.setEchoChar((char)0);
		
		pass.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (pass.getText().equals(hintpass)) {
	            	pass.setText("");
	            	pass.setEchoChar('�');
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
		
		ProgressBarRotating progressBarRotating=new ProgressBarRotating();
		pass.addKeyListener(new KeyListener() {
			 
		    public void keyPressed(KeyEvent e) {
		    	if(e.getKeyCode() == KeyEvent.VK_ENTER ) {
		    		int count1 = 0;
					String password = new String(pass.getPassword());
					try {
	//���������� ����� �� �������� ����������� ������
						File file = new File("src/main/resources/pass.txt");
	// ������� ������ FileReader ��� ������� File
						FileReader fr = new FileReader(file);
	// ������� BufferedReader � ������������� FileReader ��� ����������� ����������
						BufferedReader reader = new BufferedReader(fr);
	// ������� ������� ������ ������
						String line = reader.readLine();
						int id = 0;
						String l = "";
						for (int i = 0; i < 10; i++) {
							if (line.startsWith("L:")) {
								String[] logi = line.split(":");
								l = logi[1];
							} 
								
							// ����� ���������� ������ ����� ������������
							if (l.equalsIgnoreCase(login.getText())) {
								id = i;
								break;
							}
							else {
								line = reader.readLine();
							}
						}
						reader.close();

	//���������� ����� �� �������� ����������� ������
						File file1 = new File("src/main/resources/pass.txt");
	// ������� ������ FileReader ��� ������� File
						FileReader fr1 = new FileReader(file1);
	// ������� BufferedReader � ������������� FileReader ��� ����������� ����������
						BufferedReader reader1 = new BufferedReader(fr1);
	// ������� ������� ������ ������
						String line1 = reader1.readLine();

						String p = "";
						int svyaz = 0;
						for (int j = 0; j < 10; j++) {
							if (line1.startsWith("P:")) {
								String[] parol = line1.split(":");
								p = parol[1];
								svyaz = j;
							}
							if ((p.equalsIgnoreCase(password)) && (svyaz == (id + 1))) {
								count1 = 1;
							} else {
	// ��������� ��������� ������ � �����
								line1 = reader1.readLine();
							}
							// ����� ���������� ������ ����� ������������

						}

						if (count1 == 1) {
							dispose();//��������� ����
							user = login.getText();
							new Main();
							
						} else {
							login.setBackground(Color.PINK);
								pass.setBackground(Color.PINK);
								pass.setText(hintpass);
								pass.setEchoChar((char)0);	
								if (!progressBarRotating.isAlive())
							    progressBarRotating.start();
								else {
									if (col > 5) {
										col = 0;
									}
								}

						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
		    	    }
		    }
		    public void keyReleased(KeyEvent e) {}
		    public void keyTyped(KeyEvent e) {}
		     
		});
		
		enter = new JButton("�����");
		enter.setFont(secondfont);
			
		panel.setLayout(border1);
		
		GridLayout content = new GridLayout(0, 1, 20, 20);
		JPanel main = new JPanel();
		main.setLayout(content);
		JLabel tag = new JLabel("�����������");
		tag.setHorizontalAlignment(JLabel.CENTER);
		tag.setFont(mainfont);
		main.add(tag);
		main.add(login);
		main.add(pass);
		main.add(enter);
		
		JPanel north = new JPanel();
		north.setPreferredSize(new Dimension(15, 10));
        panel.add(north, BorderLayout.NORTH);
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(25, 10));
        panel.add(west, BorderLayout.WEST);
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(25, 10));
        panel.add(east, BorderLayout.EAST);
        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(15, 10));
        panel.add(south, BorderLayout.SOUTH);
		
		panel.add(main, BorderLayout.CENTER);

		
		enter.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) {  
					int count1 = 0;
					String password = new String(pass.getPassword());
					try {
	//���������� ����� �� �������� ����������� ������
						File file = new File("src/main/resources/pass.txt");
	// ������� ������ FileReader ��� ������� File
						FileReader fr = new FileReader(file);
	// ������� BufferedReader � ������������� FileReader ��� ����������� ����������
						BufferedReader reader = new BufferedReader(fr);
	// ������� ������� ������ ������
						String line = reader.readLine();
						int id = 0;
						String l = "";
						for (int i = 0; i < 10; i++) {
							if (line.startsWith("L:")) {
								String[] logi = line.split(":");
								l = logi[1];
							} 
								
							// ����� ���������� ������ ����� ������������
							if (l.equalsIgnoreCase(login.getText())) {
								id = i;
								break;
							}
							else {
								line = reader.readLine();
							}
						}
						reader.close();

	//���������� ����� �� �������� ����������� ������
						File file1 = new File("src/main/resources/pass.txt");
	// ������� ������ FileReader ��� ������� File
						FileReader fr1 = new FileReader(file1);
	// ������� BufferedReader � ������������� FileReader ��� ����������� ����������
						BufferedReader reader1 = new BufferedReader(fr1);
	// ������� ������� ������ ������
						String line1 = reader1.readLine();

						String p = "";
						int svyaz = 0;
						for (int j = 0; j < 10; j++) {
							if (line1.startsWith("P:")) {
								String[] parol = line1.split(":");
								p = parol[1];
								svyaz = j;
							}
							if ((p.equalsIgnoreCase(password)) && (svyaz == (id + 1)) && (!login.getText().equals("�����"))) {
								count1 = 1;
							} else {
	// ��������� ��������� ������ � �����
								line1 = reader1.readLine();
							}
							// ����� ���������� ������ ����� ������������

						}

						if (count1 == 1) {
							dispose();//��������� ����
							user = login.getText();
							new Main();
							
						} else {
							login.setBackground(Color.PINK);
								pass.setBackground(Color.PINK);
								pass.setText(hintpass);
								pass.setEchoChar((char)0);	
								if (!progressBarRotating.isAlive())
							    progressBarRotating.start();
								else {
									if (col > 5) {
										col = 0;
									}
								}

						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
					Auto.login.setBackground(Color.WHITE);
					Auto.pass.setBackground(Color.WHITE);
				}
			} else {
				Auto.login.setBackground(Color.WHITE);
				Auto.pass.setBackground(Color.WHITE);
			}
            try { Thread.sleep(70); }
            catch (Exception e) {};
		}
	}
		
}
	
