
import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;


public class CardTest extends JFrame {

	public CardTest() {
		setTitle("BusnissCard");
		setSize(400,200);

		
		ImageIcon icon = new ImageIcon("다운1.jpg");
		JLabel label1 = new JLabel(icon);

		
		JPanel panel = new JPanel();
		JPanel pane2 = new JPanel();
		JPanel pane3 = new JPanel();

		panel.add(new JLabel("이XX"));
		pane2.add(new JLabel("프로젝트 매니저"));
		pane3.add(new JLabel("XX주식회사"));

		

		add(label1);
        add(panel);
        add(pane2);
        add(pane3);

       
		setLayout(null); 

	    label1.setBounds(20, 20, 100, 120); 
	    panel.setBounds(130, 20, 100, 30);
        pane2.setBounds(130, 60, 100, 30); 
        pane3.setBounds(130, 100, 100, 30); 

        
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public static void main(String[] args) {

		CardTest c = new CardTest();
	}

}