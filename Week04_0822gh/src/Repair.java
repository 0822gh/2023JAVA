import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Repair extends JPanel implements ItemListener {
    ArrayList<JCheckBox> checkboxes = new ArrayList<>();
    String[] items = {"엔진오일 교환", "자동변속기오일교환", "에어컨필터교환", "타이어 교환"};
    int[] prices = {45000, 8000, 3000, 100000};
    int money = 0;
    JLabel label;

    public Repair() {
        super();
        setLayout(null);

        label = new JLabel("현재까지의 가격은 0원 입니다.");
        label.setBounds(10, 10, 200, 20);
        add(label);

        int yPos = 40; //체크박스 Y좌표 초기값
        for (String item : items) {
            JCheckBox checkbox = new JCheckBox(item);
            checkbox.addItemListener(this);
            checkbox.setBounds(10, yPos, 150, 20); //체크박스 위치 및 크기를 설정하고
            checkboxes.add(checkbox); //추가한다음
            add(checkbox);
            yPos += 20; // 다음 체크박스 위치 조정
        }
    }

    public void itemStateChanged(ItemEvent e) {
        money = 0;
        for (int i = 0; i < checkboxes.size(); i++) {
            if (checkboxes.get(i).isSelected()) { // 체크박스가 클릭 되었디면~
                money += prices[i];
            }
        }
        label.setText("현재까지의 가격은 " + money + "원 입니다");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new Repair();
        newContentPane.setVisible(true);
        frame.setContentPane(newContentPane);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}

