package JDBCHibernate.additional;

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

        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            statement.execute("INSERT INTO testTable(name, phone) VALUES ('Vasya','+123456789')," +
                                                                        "('Valya', '+7654321')," +
                                                                        "('Ivan', '+54321')");
            statement.execute("UPDATE testTable SET  phone = '+111111111' where name = 'Ivan'");
            statement.execute("DELETE from testTable where name = 'Valya'");
        } catch (SQLException e) {
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
