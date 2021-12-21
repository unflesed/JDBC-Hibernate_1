package practise.task_1_2_3;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/cofeehouse";
    private static final String LOGIN = "root123";
    private static final String PASSWORD = "root123";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL,LOGIN, PASSWORD);
            statement = connection.createStatement();

            statement.execute("INSERT INTO menu(category_id, Name, Price) VALUES(2,'Сок', 50)");
            statement.execute("INSERT INTO personnels(Position_id, Fullname, Phone, Email) VALUES(1,'Пупкин', '+5053030', 'test@test.com')");
            statement.execute("UPDATE menu SET price = 100 WHERE Name = 'Лате'");
            statement.execute("UPDATE personnels SET phone = '+100' WHERE FullName = 'Пупкин'");
            statement.execute("DELETE FROM clients WHERE FullName = 'Попов Иван Иванович'");
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
}
