import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User getById(int id){
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            String sql = "select * from user where id = ? and type =2 and status =1";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con, pstmt, rs);
        }
        return user;


    }

    public User getUserByNameAndPwd(String name,String password){
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            String sql = "select * from user where name = ? and pwd =? and status =1";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, name);
            pstmt.setObject(2, password);
            rs = pstmt.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setType(rs.getInt("type"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con, pstmt, rs);
        }
        return user;


    }
}
