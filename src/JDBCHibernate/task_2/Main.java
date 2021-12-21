package JDBCHibernate.task_2;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String LOGIN = "root123";
    private static final String PASSWORD = "root123";

    public static void main(String[] args) {
        regDriver();

        Connection connection = null;
        Statement statement = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\user\\IdeaProjects\\Hibernate_1\\queries.txt")))) {

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            String tmp = null;

            while((tmp = br.readLine()) !=null){
                statement.execute(tmp);
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void regDriver(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
