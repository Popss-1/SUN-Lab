import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    public static String URL = "jdbc:mysql://localhost:3306/lab_db?useUnicode=true&characterEncoding=utf8";
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String USER = "root";
    public static String PWD = "root";

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
