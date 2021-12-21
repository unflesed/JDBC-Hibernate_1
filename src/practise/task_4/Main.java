package practise.task_4;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/cofeehouse";
    private static final String LOGIN = "root123";
    private static final String PASSWORD = "root123";

    private static final String GET = "SELECT * from personnels " +
                                        "JOIN positions on positions.id = personnels.position_id" +
                                        " where positions.name = 'Официант'";

    public static void main(String[] args) {
        regDriver();

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(GET);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("Id");
                int position_id = resultSet.getInt("Position_id");
                String fullName = resultSet.getString("Fullname");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");

                System.out.println(id + " " + position_id + " " + fullName + " " +
                        phone + " " + email);
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
