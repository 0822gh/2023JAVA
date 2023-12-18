package boards_02;

import java.sql.*;
import java.util.Scanner;

public class BoardExample2 {
    private Connection connection;
    private Statement statement;

    public BoardExample2() {
        connection = makeConnection();
        statement = createStatement();
    }

    // 메인 메뉴 표시 및 처리
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
            System.out.print("메뉴선택: ");

            String menuNo = scanner.nextLine();

            switch (menuNo) {
                case "1" -> create();
                case "2" -> read();
                case "3" -> clear();
                case "4" -> exit();
                default -> System.out.println("잘못된 메뉴 번호입니다. 다시 입력하세요.");
            }
        }
    }

    // 게시물 생성
    public void create() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("작성자: ");
        String writer = scanner.nextLine();

        System.out.print("제목: ");
        String title = scanner.nextLine();

        try {
            String insertQuery = "INSERT INTO boards (writer, title) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, writer);
                preparedStatement.setString(2, title);
                preparedStatement.executeUpdate();

                System.out.println("게시물이 성공적으로 생성되었습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시물 조회
    public void read() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("게시물 번호(bno): ");
        int bno = scanner.nextInt();

        try {
            String selectQuery = "SELECT * FROM boards WHERE bno = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, bno);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.printf("%-6s%-12s%-16s%-40s \n",
                            resultSet.getInt("bno"), resultSet.getString("writer"),
                            resultSet.getString("regdate"), resultSet.getString("title"));
                } else {
                    System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시물 삭제
    public void clear() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("게시물 번호(bno): ");
        int bno = scanner.nextInt();

        try {
            String deleteQuery = "DELETE FROM boards WHERE bno = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, bno);
                int result = preparedStatement.executeUpdate();

                if (result > 0) {
                    System.out.println("게시물이 성공적으로 삭제되었습니다.");
                } else {
                    System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시판 종료
    public void exit() {
        System.out.println("게시판을 종료합니다.");
        System.exit(0);
    }

    // DB 연결
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

    // Statement 생성
    private Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Statement 생성에 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        BoardExample2 boardExample = new BoardExample2();
        boardExample.mainMenu();
    }
}

