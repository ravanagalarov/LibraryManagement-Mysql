package MiniProjects.LibraryManagement;

import MiniProjects.LibraryManagement.Entity.Account;
import MiniProjects.LibraryManagement.Repository.CRUD;
import MiniProjects.LibraryManagement.Repository.UserOperations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CRUD crud = new CRUD();
        UserOperations uo = new UserOperations();


        while (true) {
            System.out.println("Welcome to the Application of MyBook");
            System.out.println("------------------------------------------------");
            System.out.println("Select option 1 for sign up");
            System.out.println("Already have account? Select option 2");
            System.out.println("Exit? Select option 3");


            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    uo.signUp();
                    break;

                case 2:
                    Account account = uo.login();
                    if (account != null) {
                        while (true) {
                            System.out.println(" Book Management System ");
                            System.out.println("--------------------------");
                            System.out.println("1 -> Add book");
                            System.out.println("2 -> Delete book");
                            System.out.println("3 -> Update book");
                            System.out.println("4 -> Show All");
                            System.out.println("5 -> Logout");

                            int choice = sc.nextInt();
                            sc.nextLine();

                            switch (choice) {
                                case 1:
                                    crud.addBook(account.getId());
                                    break;
                                case 2:
                                    crud.deleteBook(account.getId());
                                    break;
                                case 3:
                                    crud.update(account.getId());
                                    break;
                                case 4:
                                    crud.showAll(account.getId());
                                    break;
                                case 5:
                                    System.out.println("Logged out from Book Management.");
                                    break;
                                default:
                                    System.out.println("Invalid option! Try again.");
                            }

                            if (choice == 5) {
                                break;
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exited from app.");
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }


    }
}





