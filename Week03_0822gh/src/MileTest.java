import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MileTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mile -> KM");
        frame.setSize(400, 150);
        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel mileLabel = new JLabel("마일 값을 입력하세요 : ");
        JTextField mileTextField = new JTextField(10);
        JButton Button = new JButton("변환하기");
        JLabel kmLabel = new JLabel("킬로미터 값은 : ");

        panel.add(mileLabel);
        panel.add(mileTextField);
        panel.add(Button);
        panel.add(kmLabel);

        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    double miles = Double.parseDouble(mileTextField.getText());
                    double kilometers = miles * 1.609344;
                    kmLabel.setText("킬로미터 값은 : " + kilometers);
            }
        });

        frame.setVisible(true);
    }
}