/**
 * Created by Wilson Wong
 */
import java.sql.*;
public class JavaDBExample {
    public static void main(String[] args) throws SQLException {
        System.out.println("-------Embedded Java DB Connection Testing --------");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Java DB Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the Java JDK is installed");
            System.out.println("Select the folder java/jdk1.8.xxx/db/lib where xxx is the version.");
            System.out.println("Click OK, compile the code and run it.");
            e.printStackTrace();
            return;
        }

        System.out.println("Java DB driver registered!");
        Connection connection = null;

        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }
        System.out.println("Java DB connection established!");
        Statement stmt = connection.createStatement();
        // This line inserts things into the database
        String str = "INSERT INTO THIS_IS_A_TEST VALUES(6,'Sherri',2897)";
        stmt.execute(str);
        String str2 = "SELECT * FROM THIS_IS_A_TEST WHERE ID = 6";
        ResultSet rset = stmt.executeQuery(str2);
        int ID = 0, cats = 0;
        String testerName = "";
        while (rset.next()){
            ID = rset.getInt("ID");
            testerName = rset.getString("NAME");
            cats = rset.getInt("CATS");
            System.out.println("ID: " + ID + " Name: " + testerName + " Cats: " + cats);
        }



    }
}
