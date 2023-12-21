import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CatchFly extends JFrame {
    private JButton flyButton;
    private JLabel scoreLabel;
    private JLabel lifeLabel;
    private Timer timer;
    private int score;
    private int life;
    private long startTime;
    private long endTime;

    public CatchFly() {
        setTitle("파리 잡기 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

       //배경
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("background.jpg"));
        setContentPane(backgroundLabel);
        setLayout(null);

       //파리
        ImageIcon flyIcon = new ImageIcon("fly.jpg"); 
        flyButton = new JButton(flyIcon);
        flyButton.setSize(flyIcon.getIconWidth(), flyIcon.getIconHeight());
        flyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickfly();
            }
        });
        
        
        //점수와 생명
        
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 30, 300, 30);
        scoreLabel.setFont(labelFont);
        

        lifeLabel = new JLabel("Life: 3");
        lifeLabel.setBounds(10, 70, 300, 30);
        lifeLabel.setFont(labelFont);
        

        add(flyButton);
        add(scoreLabel);
        add(lifeLabel);

        score = 0;
        life = 3;

        //소요시간
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidefly();
                showfly();
            }
        });

        //배경 누르면...
        backgroundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backgroundClicked();
            }
        });

        startGame();
    }

    private void startGame() {
        startTime = System.currentTimeMillis();
        timer.start();
    }

    private void hidefly() {
        flyButton.setVisible(false);
    }
    
    //파리가 랜덤한 위치에서 등장
    private void showfly() {
        int x = (int) (Math.random() * (getWidth() - flyButton.getWidth()));
        int y = (int) (Math.random() * (getHeight() - flyButton.getHeight()));

        flyButton.setLocation(x, y);
        flyButton.setVisible(true);
    }
    //파리 누르면 점수 증가
    private void clickfly() {
        score++;
        scoreLabel.setText("Score: " + score);

        if (score == 10) {
            endGame();
        } else {
            hidefly();
            showfly();
        }
    }
    //배경 누르면 생명 감소
    private void backgroundClicked() {
        if (life > 0) {
            life--;
            lifeLabel.setText("Life: " + life);
        }

        if (life == 0) {
            endGame();
        }
    }

    private void endGame() {
        endTime = System.currentTimeMillis(); // 게임 종료 시간 기록
        double realtime = (endTime - startTime) / 1000.0; // 초 단위로 변환

        timer.stop();
        
        if (life == 0) {
            JOptionPane.showMessageDialog(this, "게임 오버!");
        } else {
            JOptionPane.showMessageDialog(this, "게임 종료! 최종 점수: " + score + "\n소요 시간: " + realtime + "초");
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CatchFly().setVisible(true);
            }
        });
    }
}


