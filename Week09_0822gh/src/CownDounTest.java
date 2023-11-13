import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CownDounTest extends JFrame {
    private JLabel label;
    private Thread t;

    class Counter extends Thread {
        public void run() {
            for (int i = 0; i <= 10; i++) {
                try {
                    Thread.sleep(1000);
                    label.setText(i + "");
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    public CownDounTest() {
        setTitle("카운트다운");
        setSize(400, 150);
        getContentPane().setLayout(null);
        label = new JLabel("0");
        label.setBounds(0, 0, 384, 111);
        label.setFont(new Font("Serif", Font.BOLD, 100));
        getContentPane().add(label);

        JButton stopButton = new JButton("카운터 중지");
        stopButton.setBounds(247, 25, 125, 23);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (t != null && t.isAlive()) {
                    t.interrupt();
                }
            }
        });
        getContentPane().add(stopButton);

        JButton startButton = new JButton("카운터 다시 시작");
        startButton.setBounds(247, 50, 125, 23);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (t != null && t.isAlive()) {
                    t.interrupt(); // 현재 실행 중인 스레드를 중지
                }
                t = new Counter(); // 새로운 카운터 스레드 생성
                t.start(); // 새로운 카운터 스레드 시작
            }
        });
        getContentPane().add(startButton);

        setVisible(true);
        t = new Counter();
        t.start();
    }

    public static void main(String[] args) {
        CownDounTest t = new CownDounTest();
    }
}

