import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MoveCar extends JFrame {

    private JLabel carLabel;
    private int img_x, img_y;
    private JButton button_x, button_y;

    public MoveCar() {
        this.setSize(1000, 1000);
        this.setTitle("자동차 움직이기");

        JPanel panel = new JPanel();

        ImageIcon icon = new ImageIcon("다운.jpg");
        carLabel = new JLabel(icon);
        panel.add(carLabel);

        img_x = (this.getWidth() - icon.getIconWidth()) / 2;
        img_y = (this.getHeight() - icon.getIconHeight()) / 2;
        carLabel.setBounds(img_x, img_y, icon.getIconWidth(), icon.getIconHeight());

        button_x = new JButton("LEFT");
        button_x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carLeft();
            }
        });
        panel.add(button_x);

        button_y = new JButton("RIGHT");
        button_y.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carRight();
            }
        });
        panel.add(button_y);

        this.add(panel);
        
        
        // 윈도우 크기가 변경될 때마다 이미지 위치 조정
        // 맨 처음 버튼을 눌렀을 때 이미지가 엉뚱한 위치로 이동하는 경우가 발생하여 추가한 코드
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            
                img_x = (getWidth() - icon.getIconWidth()) / 2;
                img_y = (getHeight() - icon.getIconHeight()) / 2;
                carLabel.setLocation(img_x, img_y);
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void carLeft() {
        img_x -= 10;
        carLabel.setLocation(img_x, img_y);
    }

    public void carRight() {
        img_x += 10;
        carLabel.setLocation(img_x, img_y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MoveCar();
            }
        });
    }
}


