import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter; //импорт библиотек

public class Main extends JFrame {
	static float profit; 
	static int clearhelp = 0;
	static int col1;
	static int ger=0;

	public Main() throws ParseException {
        JFrame frame1 = new JFrame("Зарплатный калькулятор"); //создание главного окна
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из программы при закрытии окна
        
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int vert = sSize.height;
		int hor  = sSize.width;
		int locationX = (sSize.width - hor/2) / 2;
		int locationY = (sSize.height - vert/2) / 3;
        frame1.setResizable(true);
        frame1.setBounds(locationX, locationY, hor/3+20, vert/2+50);
        Dimension minSize = new Dimension(hor/3, vert/2+20-50);
        frame1.setMinimumSize(minSize); //установка размеров и расположения главного окна
        
        frame1.setVisible(true); //видимость главного окна
        
        GridLayout content_5column = new GridLayout(0, 5, 0, 0);
        GridLayout content_4column = new GridLayout(0, 4, 0, 0);
        GridLayout content_3column = new GridLayout(0, 3, 7, 0);
        GridLayout content_2column = new GridLayout(0, 2, 0, 0);
        GridLayout content_1column = new GridLayout(0, 1, 0, 0); //создание табличных менеджеров расположения
        BorderLayout centermen = new BorderLayout();
        
        frame1.setLayout(centermen); //установка менеджера расположения главного окна
        
        BorderLayout layout1 = new BorderLayout();
        
        JPanel panel = new JPanel();
        panel.setLayout(layout1);
        
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(100, 17));
        GridLayout contentn = new GridLayout(0, 1, 0, 0);
        north.setLayout(contentn);
        JLabel currentuser = new JLabel();
        currentuser.setText("  Текущий пользователь: " + Auto.user);
        north.add(currentuser);
        panel.add(north, BorderLayout.NORTH);
        
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(15, 10));
        panel.add(east, BorderLayout.EAST);

        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(15, 10));
        panel.add(west, BorderLayout.WEST);
        
        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(10, 35));
        GridLayout content_buttons = new GridLayout(0, 3, 0, 0);
        south.setLayout(content_buttons);
        JButton clear = new JButton("Очистить");
        south.add(clear);
        JPanel infopanel = new JPanel();
        JLabel info = new JLabel("Karimov");
        Font infofont = new Font("Century Gothic", Font.PLAIN, 16);
		info.setFont(infofont);
		info.setHorizontalAlignment(JLabel.CENTER);
        info.setFocusable(false);
        infopanel.add(info);
        infopanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        infopanel.setBackground(Color.LIGHT_GRAY);
        south.add(infopanel);
        JButton enter = new JButton("Расчитать");
        south.add(enter);
        south.setBackground(Color.LIGHT_GRAY);
        south.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(south, BorderLayout.SOUTH);

        
        JTextField center = new JTextField("center (PAGE_center)");
        
        JPanel mainpanel1 = new JPanel();
        mainpanel1.setLayout(content_1column);
        JPanel mainpanel2 = new JPanel();
        mainpanel2.setLayout(content_1column);
        
        Container cont1 = new Container();
        JPanel panel2 = new JPanel();
        panel2.setLayout(content_3column);
        //cont1.add(panel2);
        //panel2.setLayout(content);
        cont1.setLayout(content_1column);
        
        
        panel2.add(new JLabel("Период расчета"));
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel("Оклад, руб."));

        JFormattedTextField firstdate = new JFormattedTextField("");
        
        try {
    		firstdate.setFormatterFactory(new DefaultFormatterFactory(
			        new MaskFormatter("")));
    		firstdate.setText("");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        firstdate.addFocusListener(new FocusAdapter() { //создание слушателя для текстового поля
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (firstdate.getText().equals("")) {
	            	try {
	            		firstdate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("##.##.####"))); //установка маски при задании значений
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        }
	        
	        @Override
	        public void focusLost(FocusEvent e) { //действие при потере фокуса
	            if (firstdate.getText().contains(" ")) {
	            	try {
	            		firstdate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("")));
	            		firstdate.setText(""); //установка пустого значения
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	firstdate.setText(""); //установка пустого значения
	            }
	        }
        });
        
        panel2.add(firstdate); //добавить текстовое поле для ввода первой даты
        
        JFormattedTextField seconddate = new JFormattedTextField(""); //создание текстового поля для ввода второй даты
        
        try {
        	seconddate.setFormatterFactory(new DefaultFormatterFactory(
			        new MaskFormatter("")));
        	seconddate.setText("");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        seconddate.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (seconddate.getText().equals("")) {
	            	try {
	            		seconddate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("##.##.####")));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        }
	        
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (seconddate.getText().contains(" ")) {
	            	try {
	            		seconddate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("")));
	            		seconddate.setText("");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	seconddate.setText("");
	            }
	        }
        });
        
        panel2.add(seconddate);
        JTextField salary = new JTextField("");
        panel2.add(salary);
        
        mainpanel1.add(panel2);
        
        JPanel panel4 = new JPanel();
        panel4.setLayout(content_1column);
        
        //JPanel panel4_0 = new JPanel();
        //panel4_0.setLayout(content_1column);
        //panel4_0.add(new JLabel(" "));
        //panel4.add(panel4_0);
        
        JPanel panel4_1 = new JPanel();
        panel4_1.setLayout(content_1column);
        panel4_1.add(new JLabel("<html>Выберите один из вариантов ниже, если были невыходы на работу</html>"));
        panel4.add(panel4_1);
        
        JPanel panel4_2 = new JPanel();
        panel4_2.setLayout(content_4column);
        
        ButtonGroup radgroup = new ButtonGroup();
        JRadioButton rad0 = new JRadioButton("Нет", true);
        radgroup.add(rad0);
        JRadioButton rad1 = new JRadioButton("Больничный", false);
        radgroup.add(rad1);
        JRadioButton rad2 = new JRadioButton("Отпуск", false);
        radgroup.add(rad2);
        JRadioButton rad3 = new JRadioButton("Прочее", false);
        radgroup.add(rad3);
        
        panel4_2.add(rad1);
        panel4_2.add(rad2);
        panel4_2.add(rad3);

        JTextField days = new JTextField("Кол-во дней");
        Font daysfont = new Font("Calibri", Font.PLAIN, 12);
        days.setFont(daysfont);
        days.disable();
        panel4_2.add(days);
        
        rad1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	days.enable();
            	days.transferFocus(); 
            	days.grabFocus();	
            }
        });
        rad2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	days.enable();
            	days.transferFocus(); 
            	days.grabFocus();
            }
        });
        rad3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	days.enable();
            	days.transferFocus(); 
            	days.grabFocus();
            }
        });
       
        panel4.add(panel4_2);

        mainpanel1.add(panel4);
        
        JPanel panel4_3 = new JPanel();
        GridLayout content5_esp = new GridLayout(0, 2, 20, 0);
        panel4_3.setLayout(content5_esp);

        panel4_3.add(new JLabel("<html>Расчет зарплаты</html>"));
        panel4_3.add(new JLabel("<html>Отчисления работодателя</html>"));
     
        mainpanel1.add(panel4_3);
        
        JPanel panel5 = new JPanel();  
        panel5.setLayout(content_1column);
        
        JPanel panel5_1 = new JPanel();
        
        panel5_1.setLayout(content5_esp);
    
        JPanel panel5_1_1 = new JPanel();
        panel5_1_1.setLayout(content_1column);

        JPanel panel5left = new JPanel();
        BorderLayout BL1 = new BorderLayout();
        panel5left.setLayout(BL1);
        
        JPanel left1panel = new JPanel();
        left1panel.setLayout(content_1column);
        
        left1panel.add(new JLabel("<html>Сумма всего</html>"));
        left1panel.add(new JLabel("<html>Сумма зарплаты на руки</html>"));
        left1panel.add(new JLabel("Сумма НДФЛ"));
        left1panel.add(new JLabel(" "));
        panel5left.add(left1panel, BorderLayout.CENTER);    
        
        JPanel left2panel = new JPanel();
        left2panel.setLayout(content_1column);
        JLabel lsum0 = new JLabel("");
        lsum0.setHorizontalAlignment(JTextField.RIGHT);
        left2panel.add(lsum0);
        JLabel lsum1 = new JLabel("");
        lsum1.setHorizontalAlignment(JTextField.RIGHT);
        left2panel.add(lsum1); 
        JLabel lsum2 = new JLabel("");
        lsum2.setHorizontalAlignment(JTextField.RIGHT);
        left2panel.add(lsum2);
        left2panel.add(new JLabel(" "));
        panel5left.add(left2panel, BorderLayout.EAST);
        
        panel5_1_1.add(panel5left);
        
        panel5_1.add(panel5_1_1);
      
        JPanel panel5_1_2 = new JPanel();
        panel5_1_2.setLayout(content_1column);
  
        JPanel panel5right = new JPanel();
        BorderLayout BL2 = new BorderLayout();
        panel5right.setLayout(BL2);
        
        JPanel right1panel = new JPanel();
        right1panel.setLayout(content_1column);
        
        right1panel.add(new JLabel("<html>Пенсионное (22%)</html>"));
        right1panel.add(new JLabel("<html>Медицинское (5,1%)</html>"));
        right1panel.add(new JLabel("<html>Социальное (2,9%)</html>"));
        right1panel.add(new JLabel("<html>Отчисления в ФСС (0,2%)</html>"));
        
        panel5right.add(right1panel, BorderLayout.CENTER);
        
        JPanel right2panel = new JPanel();
        right2panel.setLayout(content_1column);
        
        JLabel rsum1 = new JLabel("");
        rsum1.setHorizontalAlignment(JTextField.RIGHT);
        right2panel.add(rsum1);
        JLabel rsum2 = new JLabel("");
        rsum2.setHorizontalAlignment(JTextField.RIGHT);
        right2panel.add(rsum2);
        JLabel rsum3 = new JLabel("");
        rsum3.setHorizontalAlignment(JTextField.RIGHT);
        right2panel.add(rsum3);
        JLabel rsum4 = new JLabel("");
        rsum4.setHorizontalAlignment(JTextField.RIGHT);
        right2panel.add(rsum4);
        
        panel5right.add(right2panel, BorderLayout.EAST);
        
        panel5_1_2.add(panel5right);

        panel5_1.add(panel5_1_2);
      
        panel5.add(panel5_1);
      
        mainpanel2.add(panel5);
        
        cont1.add(mainpanel1);
        cont1.add(mainpanel2);
        
        panel.add(cont1, BorderLayout.CENTER);
        frame1.add(panel);
        
        JLabel empty3 = new JLabel("");
        JLabel empty4 = new JLabel("");
        JLabel empty5 = new JLabel("");
        
        JLabel starttext = new JLabel("<html><p style=\"margin-left: 7px;\">Дата начала</p></html>");
        JLabel endtext = new JLabel("<html><p style=\"margin-left: 7px;\">Дата окончания</p></html>");
        JLabel salarytext = new JLabel("<html><p style=\"margin-left: 7px;\">Размер оклада</p></html>");
        JTextField start = new JTextField("");
        JTextField end = new JTextField("");
        
        JLabel result = new JLabel("");

        enter.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) {
				
				if (!isDate(firstdate.getText(), seconddate.getText())) {
					JOptionPane.showMessageDialog(panel, "Введите корректные даты", "Ошибка", JOptionPane.ERROR_MESSAGE);
				} else
				
				if (isGood(salary.getText())) {
				if (rad0.isSelected() || isNumericPlus(days.getText(), firstdate.getText(), seconddate.getText())) {
				try {
				String firstdates = new String(firstdate.getText());
				String seconddates = new String(seconddate.getText());
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate startDate = LocalDate.parse(firstdates, formatter);
				LocalDate endDate = LocalDate.parse(seconddates, formatter);
				Period period = Period.between(startDate, endDate);
				
				long p2 = ChronoUnit.DAYS.between(startDate, endDate);
				profit = Float.parseFloat(salary.getText())/30*p2;
				DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
				result.setText(String.valueOf( Math.ceil(profit*Math.pow(10, 2))/Math.pow(10, 2)) );
				
				lsum0.setText(String.valueOf((Math.ceil(profit*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
				lsum2.setText(String.valueOf((Math.ceil(profit/100*13*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
				lsum1.setText(String.valueOf((Math.ceil((profit-(profit/100*13))*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
				
				rsum1.setText(String.valueOf(Math.ceil(profit/100*22*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
				rsum2.setText(String.valueOf(Math.ceil(profit/100*5.1*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
				rsum3.setText(String.valueOf(Math.ceil(profit/100*2.9*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
				rsum4.setText(String.valueOf(Math.ceil(profit/100*0.2*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
				
				if (rad1.isSelected() || rad2.isSelected() || rad3.isSelected()) {
					profit = profit - Float.parseFloat(salary.getText())/30*Float.parseFloat(days.getText());
					lsum0.setText(String.valueOf((Math.ceil(profit*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
					lsum2.setText(String.valueOf((Math.ceil(profit/100*13*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
					lsum1.setText(String.valueOf((Math.ceil((profit-(profit/100*13))*Math.pow(10, 2)))/Math.pow(10, 2)) + "р.");
					
					rsum1.setText(String.valueOf(Math.ceil(profit/100*22*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
					rsum2.setText(String.valueOf(Math.ceil(profit/100*5.1*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
					rsum3.setText(String.valueOf(Math.ceil(profit/100*2.9*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
					rsum4.setText(String.valueOf(Math.ceil(profit/100*0.2*Math.pow(10, 2))/Math.pow(10, 2)) + "р.");
				}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(panel, "Введите корректные даты", "Ошибка", JOptionPane.ERROR_MESSAGE);
			    }
				
				}
				else {
					JOptionPane.showMessageDialog(panel, "Введите корректное число пропущенных дней", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
				}
				else {
					JOptionPane.showMessageDialog(panel, "Введите корректный оклад", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}                 
		}); 
        
        
        
        String daystext = new String("Кол-во дней");
        days.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (days.getText().equals(daystext)) {
	            	days.setText("");
	            	days.setCaretColor(Color.BLACK);
	            }
	        }
	        @Override
	        public void focusLost(FocusEvent e) {
	            if (days.getText().isEmpty()) {
	            	days.setText(daystext);
	            	days.setCaretColor(Color.LIGHT_GRAY);
	            }
	        }
	    });
        
        
        ClearBar ClearBar=new ClearBar();
        clear.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) { 
				System.out.println("clearhelp: " + clearhelp);
				if (!ClearBar.isAlive())
					ClearBar.start();
					else {
						if (col1 > 5) {
							col1 = 0;
						}
					}

				if (clearhelp==1) {

					salary.setText("");
					days.setText(daystext);
	            	days.setCaretColor(Color.LIGHT_GRAY);
	            	rad0.setSelected(true);
	            	days.disable();
	            	
	            	lsum0.setText("");
	            	lsum1.setText("");
	            	lsum2.setText("");
	            	rsum1.setText("");
	            	rsum2.setText("");
	            	rsum3.setText("");
	            	rsum4.setText("");
	            	
	            	try {
	            		firstdate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("")));
	            		firstdate.setText("");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	
	            	try {
	            		seconddate.setFormatterFactory(new DefaultFormatterFactory(
						        new MaskFormatter("")));
	            		seconddate.setText("");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	clearhelp=0;		
				}
				if (clearhelp==0) {
					rad0.setSelected(true);
					days.setText(daystext);
					days.disable();
					
					
				}
			} 	    	                             
		}); 
        
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static boolean isDate(String str1, String str2) {
	    try {
	    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	    	LocalDate startDate = LocalDate.parse(str1, formatter1);
	    	LocalDate endDate = LocalDate.parse(str2, formatter1);
	    	if (ChronoUnit.DAYS.between(startDate, endDate)>0) {
	    		return true;  
	    	}
	    	return false;  
	    } catch (DateTimeParseException e) {
        return false;
    }
}
	
	public static boolean isGood(String str) {
	    try {
	        if (Float.parseFloat(str)>=100) {
	        	return true;
	        }
	        return false;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static boolean isNumericPlus(String str, String str1, String str2) {
	    try {
	        Integer.parseInt(str);
	        if (Integer.parseInt(str)>0) {
	        	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	    	LocalDate startDate = LocalDate.parse(str1, formatter1);
	    	LocalDate endDate = LocalDate.parse(str2, formatter1);
	    	if (ChronoUnit.DAYS.between(startDate, endDate)-Integer.parseInt(str)>1) {
	    		return true;}
	    	}
	        return false;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}

class ClearBar extends Thread {
	boolean showProgress = true;
	public void run() {
	Boolean isVisible = true;
		while (showProgress) {
			isVisible=!isVisible;
			if (Main.col1 <=5) { Main.col1++; 
				Main.clearhelp=1;
			} else {
				Main.clearhelp=0;
			}
            try { Thread.sleep(70); }
            catch (Exception e) {};
		}
	}
		
}


