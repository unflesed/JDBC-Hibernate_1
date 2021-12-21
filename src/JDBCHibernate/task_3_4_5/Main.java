package JDBCHibernate.task_3_4_5;

import java.sql.*;
import java.util.Date;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root123";
    private static final String PASSWORD = "root123";

    private static final String GET_CONTACTS = "SELECT * from employees JOIN info on employees.id = info.Empl_id";
    private static final String GET_BIRTH_DATE = "SELECT * from employees JOIN info on employees.id = info.Empl_id " +
                                                    "where family = false";
    private static final String GET_MANAGERS = "SELECT * from employees JOIN info on employees.id = info.Empl_id " +
                                                "JOIN salaries on employees.id = salaries.Empl_id " +
                                                "where post = 'manager'";


    public static void main(String[] args) {
        regDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET_CONTACTS);

            ResultSet resultSet1 = statement.executeQuery();
            System.out.println("Контактные данные сотрудников:");
            while(resultSet1.next()){
                String name = resultSet1.getString("name");
                String phone = resultSet1.getString("phone");
                String address = resultSet1.getString("address");
                System.out.println(name + " " + phone + " " + address);
            }
            statement = connection.prepareStatement(GET_BIRTH_DATE);

            ResultSet resultSet2 = statement.executeQuery();
            System.out.println("Дата рождения всех холостых сотрудников:");
            while(resultSet2.next()){
                String name = resultSet2.getString("name");
                String phone = resultSet2.getString("phone");
                Date date = resultSet2.getDate("dateOfBirth");
                System.out.println(name + " " + phone + " " + date);
            }
            statement = connection.prepareStatement(GET_MANAGERS);

            ResultSet resultSet3 = statement.executeQuery();
            System.out.println("Информация о менеджерах компании:");
            while(resultSet3.next()){
                String name = resultSet3.getString("name");
                String phone = resultSet3.getString("phone");
                Date date = resultSet3.getDate("dateOfBirth");
                System.out.println(name + " " + phone + " " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
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
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
