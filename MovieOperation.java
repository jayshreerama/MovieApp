package MovieOperation;

import java.sql.*;
import java.util.Scanner;

public class MovieOperation {

    static Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    Scanner sc=new Scanner(System.in);
    static {
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","sql@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    String q1="Insert into movie_info values(?,?,?,?,?)";
    String q2="select * from movie_info";
    String q3="delete from movie_info where movie_id= ?";
    String q4="select * from movie_info where movie_name= ?";
    String q5="select * from movie_info where movie_name=?";
    public void addMovies(String name, String theater, double cost, int tickets) {
        try {
            pstmt=con.prepareStatement(q1);
            pstmt.setString(1,"0");
            pstmt.setString(2,name);
            pstmt.setString(3,theater);
            pstmt.setDouble(4,cost);
            pstmt.setInt(5,tickets);
            pstmt.executeUpdate();
            System.out.println("Movie Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void movieDetails() {
        try {
            pstmt=con.prepareStatement(q2);
            System.out.println("Movie Id \t\t\t Movie Name \t\t\t Theater Name\t\t\t Ticket Cost \t\t\t Available Tickets ");
            boolean status = true;
            while(rs.next()){
                System.out.println(rs.getInt(1) + "\t\t\t\t\t" + rs.getString(2) + "\t\t\t\t\t" + rs.getString(3) + "\t\t\t\t\t" + rs.getDouble(4) + "\t\t\t\t\t" + rs.getInt(5));
                System.out.println("-------------------------------------------------------------------");
            }
            System.out.println("Movie Info Display Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void daleteMovie(int id) {
        try {
            pstmt=con.prepareStatement(q3);
            pstmt.setInt(1,id);
            int count = pstmt.executeUpdate();
            if (count==0){
                System.out.println("Movie Not Found");
            }else {
                System.out.println(count+" Movie Deleted Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void movieDatails(String movieName) {
        try {
            pstmt=con.prepareStatement(q4);
            pstmt.setString(1,movieName);
            rs=pstmt.executeQuery();
            System.out.println("Movie Id \t\t\t Movie Name \t\t\t Theater Name \t\t\t Ticket Cost \t\t\t Available Tickets ");
            while (rs.next()){
                System.out.println(rs.getInt(1) + "\t\t\t\t\t" + rs.getString(2) + "\t\t\t\t\t" + rs.getString(3) + "\t\t\t\t\t" + rs.getDouble(4) + "\t\t\t\t\t" + rs.getInt(5));

            }
            System.out.println("Movie Info Display Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookTickets(String name, String theater, int tickets) {
        try {
            pstmt=con.prepareStatement(q5);
            pstmt.setString(1,name);
            rs=pstmt.executeQuery();
            if(rs.next()){
                String query = "update movie_info set available_tickets =? where movie_name =? and theater=?";
                pstmt=con.prepareStatement(query);
                int i=rs.getInt(5);
                if (i>=tickets){
                    double cost = rs.getInt(4)*tickets;
                    System.out.println("You Have To pay For Your ticket booking is : "+cost);
                    System.out.println("Continue : press 1\nCancle : 2 ");
                    int opt=sc.nextInt();
                    switch (opt){
                        case 1:
                            int j = i-tickets;
                            pstmt.setInt(1,j);
                            pstmt.setString(2,name);
                            pstmt.setString(3,theater);
                            pstmt.executeUpdate();
                            System.out.println(tickets+" Ticket Booked Successfully");
                            break;

                        case 2:
                            System.out.println("Ok Thank You ! ");
                            break;
                    }
                }
                else {
                    System.out.println("Ticket Not Available");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResources() {
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
