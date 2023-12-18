import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class StudentFrame extends JFrame {
    JTextField nameField, numberField, gradeField, searchField;
    JButton searchButton, addButton, deleteButton;
    ResultSet rs;
    Statement stmt;
    Connection connection;

    public StudentFrame() throws SQLException {
        super("Student Database Viewer");
        connection = makeConnection();
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery("SELECT * FROM students");

        setLayout(new GridLayout(0, 2));
        add(new JLabel("이름", JLabel.CENTER));
        add(nameField = new JTextField());

        add(new JLabel("학번", JLabel.CENTER));
        add(numberField = new JTextField());

        add(new JLabel("성적", JLabel.CENTER));
        add(gradeField = new JTextField());

        add(new JLabel("학생 검색", JLabel.CENTER));
        add(searchField = new JTextField());

        searchButton = new JButton("검색");
        addButton = new JButton("추가");
        deleteButton = new JButton("삭제");

        add(searchButton);
        add(addButton);
        add(deleteButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String searchKeyword = searchField.getText();
                try {
                    String query = "SELECT * FROM students WHERE name LIKE '%" + searchKeyword + "%'";
                    ResultSet searchResult = stmt.executeQuery(query);

                    if (searchResult.next()) {
                        nameField.setText(searchResult.getString("name"));
                        numberField.setText(searchResult.getString("number"));
                        gradeField.setText(searchResult.getString("grade"));
                    } else {
                        System.out.println("검색 결과가 없습니다.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String name = nameField.getText();
                String number = numberField.getText();
                String grade = gradeField.getText();

                try {
                    String query = "INSERT INTO students (name, number, grade) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, number);
                    preparedStatement.setString(3, grade);
                    preparedStatement.executeUpdate();

                    System.out.println(name + " 학생이 추가되었습니다.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String number = numberField.getText();

                try {
                    String query = "DELETE FROM students WHERE number = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, number);
                    preparedStatement.executeUpdate();

                    System.out.println("학번이 " + number + "인 학생이 삭제되었습니다.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
    }

    public static Connection makeConnection() {	
		String url = "jdbc:mysql://localhost:3306/student_db";
		String id = "root";
		String password = "";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}
}

