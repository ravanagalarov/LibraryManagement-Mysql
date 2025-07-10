package MiniProjects.LibraryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CRUD {

    public void addBook(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Book's name: ");
        String title = scanner.nextLine();


        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Publish Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(title,author,year,genre);

        String sql = "INSERT INTO library(title, author, publish_year, genre, users_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1,book.getTitle());
            ps.setString(2,book.getAuthor());
            ps.setInt(3,book.getPublishYear());
            ps.setString(4,book.getGenre());
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("Book added.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int userID ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID of the book that you wanna delete");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM library WHERE id = ? AND users_id = ?";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1,id);
            ps.setInt(2,userID);

            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("Book deleted.");
            } else {
                System.out.println("Book not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int idUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ID of the book that you wanna update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String title = scanner.nextLine();

        System.out.print("Enter new author: ");
        String author = scanner.nextLine();

        System.out.print("Define new publish year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Define new genre: ");
        String genre = scanner.nextLine();

        String sql = "UPDATE library SET title = ?, author = ?, publish_year = ?, genre = ? WHERE id = ? AND users_id = ?";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1,title);
            ps.setString(2,author);
            ps.setInt(3,year);
            ps.setString(4,genre);
            ps.setInt(5,id);
            ps.setInt(6,idUser);

            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Book updated.");
            } else {
                System.out.println("Not found the book with this ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAll(int UserId) {
        String sql = "SELECT * FROM library WHERE users_id = ?";

        try {
            Connection cn = DbConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1,UserId);

            ResultSet rs = ps.executeQuery();

            boolean checkBook = false;

            while (rs.next()) {
                checkBook = true;
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("publish_year");
                String genre = rs.getString("genre");
                int code = rs.getInt("users_id");

                System.out.println("ID: " + id +
                        ", Title: " + title +
                        ", Author: " + author +
                        ", Year: " + year +
                        ", Genre: " + genre);
            }

            if (!checkBook) {
                System.out.println("You don't have any book yet.2");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
