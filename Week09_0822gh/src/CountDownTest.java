import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CountDownTest extends JFrame {
	
	private JLabel label;
	
	class MyThread extends Thread {
		public void run() {
			for (int i=0; i<=10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				label.setText(i+"");
			}
		}
	}	


	public CountDownTest() {
		
		setTitle("카운트다운");
		setSize(400,150);
		label = new JLabel("0");
		label.setFont(new Font("Serif",Font.BOLD,100));
		add(label);
		setVisible(true);
		
		(new MyThread()).start();
		
		
	}

	public static void main(String[] args) {
		CountDownTest t = new CountDownTest();
	}

}
