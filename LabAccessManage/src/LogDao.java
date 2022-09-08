import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogDao {

    public boolean saveLog(AccessLog log) {

        Connection con = null;
        String sql = "insert into accesslog(uid,uname,time) values(?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, log.getUid());
            pstmt.setString(2, log.getName());
            Date date = new Date();
            pstmt.setTimestamp(3, new Timestamp(date.getTime()));
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(con, pstmt, null);
        }
        return false;

    }

    public List<AccessLog> queryLog(String id){
        List<AccessLog> list = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from accesslog ");
            if(id != null && !"".equals(id)){
                sb.append("where uid = ? ");
                params.add(id);
            }
            sb.append("order by time desc");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                AccessLog log = new AccessLog();
                log.setId(rs.getInt("id"));
                log.setName(rs.getString("uname"));
                log.setUid(rs.getInt("uid"));
                log.setTime(rs.getTimestamp("time"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con, pstmt, rs);
        }
        return list;


    }
    public List<AccessLog> queryLogByDate(String date){
        List<AccessLog> list = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            StringBuffer sb = new StringBuffer("select * from accesslog WHERE DATE_FORMAT(time,'%Y/%m/%d')= ? ");
            sb.append("order by time desc");
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,date);
            rs = pstmt.executeQuery();
            while(rs.next()){
                AccessLog log = new AccessLog();
                log.setId(rs.getInt("id"));
                log.setName(rs.getString("uname"));
                log.setUid(rs.getInt("uid"));
                log.setTime(rs.getTimestamp("time"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con, pstmt, rs);
        }
        return list;
    }

    public List<AccessLog> queryLogByTimeRange(String start,String end){
        List<AccessLog> list = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            StringBuffer sb = new StringBuffer("select * from accesslog WHERE time between ? and ? ");
            sb.append("order by time desc");
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setString(1,start);
            pstmt.setString(2,end);
            rs = pstmt.executeQuery();
            while(rs.next()){
                AccessLog log = new AccessLog();
                log.setId(rs.getInt("id"));
                log.setName(rs.getString("uname"));
                log.setUid(rs.getInt("uid"));
                log.setTime(rs.getTimestamp("time"));
                list.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(con, pstmt, rs);
        }
        return list;
    }
}
