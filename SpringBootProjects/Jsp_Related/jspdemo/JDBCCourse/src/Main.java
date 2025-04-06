import java.sql.*;

public class Main {

    public static void main(String[] args) {
        // Replace these with your own database connection details
        String url = "jdbc:mysql://localhost:3306/imdb";
        String user = "root";
        String password = "";

        // Connect to the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database!");

            // You can now execute SQL statements using the connection object
            // For example, to select data from a table:
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM movie LIMIT 5";
            ResultSet resultSet = statement.executeQuery(query);
//
//            // Process the results
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");

                System.out.println("ID: " + id + ", Title: " + title);
            }
//            System.out.println("result: "+resultSet);
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error: Connection failed!");
            e.printStackTrace();
        }
    }
}