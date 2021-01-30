import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

public class Main extends JFrame {
	static float profit;

	public Main() {
        JFrame frame1 = new JFrame("Зарплатный калькулятор"); 
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int vert = sSize.height;
		int hor  = sSize.width;
		int locationX = (sSize.width - hor/2) / 2;
		int locationY = (sSize.height - vert/2) / 3;
        frame1.setResizable(true);
        frame1.setBounds(locationX, locationY, hor/3, vert/2+20);
        frame1.setVisible(true); 
        
        GridLayout content = new GridLayout(0, 3, 0, 0);
        frame1.setLayout(content);
        
        JLabel empty3 = new JLabel("");
        JLabel empty4 = new JLabel("");
        JLabel empty5 = new JLabel("");
        
        JLabel starttext = new JLabel("<html><p style=\"margin-left: 7px;\">Дата начала</p></html>");
        JLabel endtext = new JLabel("<html><p style=\"margin-left: 7px;\">Дата окончания</p></html>");
        JLabel salarytext = new JLabel("<html><p style=\"margin-left: 7px;\">Размер оклада</p></html>");
        JTextField start = new JTextField("");
        JTextField end = new JTextField("");
        JTextField salary = new JTextField("");
        
        JLabel empty6 = new JLabel("");
        JLabel empty7 = new JLabel("");
        JLabel empty8 = new JLabel("");
        JLabel empty9 = new JLabel("");
        JLabel empty10 = new JLabel("");
        JLabel empty11 = new JLabel("");
        
        JLabel resulttext = new JLabel("<html><p style=\"margin-left: 7px;\">Результат</p></html>");
        resulttext.setHorizontalAlignment(JLabel.CENTER);
        JLabel result = new JLabel("");
        JLabel empty1 = new JLabel("");
        JButton clear = new JButton("Очистить");  
        JButton enter = new JButton("Расчитать");
        JLabel empty2 = new JLabel("");
        		
        frame1.add(empty3);frame1.add(empty4);frame1.add(empty5);
        frame1.add(starttext);frame1.add(endtext);frame1.add(salarytext);
        frame1.add(start);frame1.add(end);frame1.add(salary);
        frame1.add(empty6);frame1.add(empty7);frame1.add(empty8);
        frame1.add(empty9);frame1.add(empty10);frame1.add(empty11);
        frame1.add(resulttext);frame1.add(result);frame1.add(empty1);
        frame1.add(clear);frame1.add(enter);frame1.add(empty2);

        enter.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) {
				String startstr = new String(start.getText());
				String endstr = new String(end.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				LocalDate startDate = LocalDate.parse(startstr, formatter);
				LocalDate endDate = LocalDate.parse(endstr, formatter);
				Period period = Period.between(startDate, endDate);
				long p2 = ChronoUnit.DAYS.between(startDate, endDate);
				profit = Float.parseFloat(salary.getText())/30*p2;
				DecimalFormat decimalFormat = new DecimalFormat( "#.###" );
				result.setText(String.valueOf( Math.ceil(profit*Math.pow(10, 2))/Math.pow(10, 2)) );
			}                 
		}); 
        
        clear.addActionListener(new ActionListener() {                                                         
			public void actionPerformed(ActionEvent e) { 
				start.setText("");
				end.setText("");
				salary.setText("");
				result.setText("");
			} 	    	                             
		}); 

	}
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
}


