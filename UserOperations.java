package MiniProjects.LibraryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UserOperations {
    public static boolean checkIfUserExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Define username");
        String userName = sc.nextLine();
        System.out.println("Define password");
        String password = sc.nextLine();

        Account account = new Account(userName, password);

        String sql = "INSERT INTO users (username,password) VALUES(?, ?)";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();

            System.out.println("Created Account");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Username already exists!");
                while (true) {
                    System.out.println("Enter your username");
                    userName = sc.nextLine();
                    boolean check = checkIfUserExists(userName);

                    if (!check) {
                        Account account1 = new Account(userName,password);
                        try {
                            Connection connection = DbConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1, account1.getUsername());
                            preparedStatement.setString(2, account1.getPassword());

                            preparedStatement.executeUpdate();
                            System.out.println("Created Account.");
                        }catch (SQLException exception) {
                            exception.printStackTrace();
                        }

                        break;
                    } else {
                        System.out.println("Username already exists! Please try another.");
                    }
                }
            } else {
                e.printStackTrace();
            }
        }
    }

    public  Account login() {
        int attempt = 0;
        Scanner sc = new Scanner(System.in);
        while (attempt < 3) {
            System.out.println("Enter your username");
            String username = sc.nextLine();
            System.out.println("Enter your password");
            String password = sc.nextLine();

            String sql = "SELECT * FROM users WHERE username =  ? AND password = ?";

            try {
                Connection cn = DbConnection.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password1 = rs.getString("password");

                    System.out.println("Logged in succsessfully.");
                    System.out.println();

                    return new Account(id, name, password1);


                } else {
                    System.out.println("Wrong username or password! Try again.");
                    System.out.println();
                    attempt++;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Too many attempts. Returning previous menyo...");
        return null;
    }
}

