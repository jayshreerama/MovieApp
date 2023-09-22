package MovieOperation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MovieOperation mv=new MovieOperation();
        Scanner sc=new Scanner(System.in);
        System.out.println("Select User");
        System.out.println("1: Admin\n2: Customer");
        int choice=sc.nextInt();
        if(choice==1){
            System.out.println("Select mode of operation");
            System.out.println("1: Add movie details");
            System.out.println("2: View movie details");
            System.out.println("3: Delete movie details");
            int mode=sc.nextInt();
            switch (mode){
                case 1:
                    System.out.println("Add movie name");
                    String name=sc.next();
                    System.out.println("Add Theater name");
                    String theater=sc.next();
                    System.out.println("Add Ticket Cost");
                    double cost=sc.nextDouble();
                    System.out.println("Add No Of Tickets");
                    int tickets=sc.nextInt();
                    mv.addMovies(name,theater,cost,tickets);
                    break;

                case 2:
                    mv.movieDetails();
                    break;

                case 3:
                    System.out.println("Enter movie Id");
                    int id=sc.nextInt();
                    mv.daleteMovie(id);
                    break;
                default:
                    System.out.println("Exited");
            }
        }
        else if (choice==2){
            System.out.println("Select mode Of operation");
            System.out.println("1: search movie information");
            System.out.println("2: Book Tickets");
            int mode=sc.nextInt();
            switch (mode){
                case 1:
                    System.out.println("Enter the movie Name");
                    String movieName=sc.next();
                    mv.movieDatails(movieName);
                    break;

                case 2:
                    System.out.println("Add movie Name");
                    String name=sc.next();
                    System.out.println("Add Theater Name");
                    String theater=sc.next();
                    System.out.println("Add no of tickets");
                    int tickets=sc.nextInt();
                    mv.bookTickets(name,theater,tickets);
                    break;
                default:
                    mv.closeResources();
                    System.out.println("Thank You For Visited");
            }
        }
        else {
            System.out.println("Invalid Option");
        }
    }

}
