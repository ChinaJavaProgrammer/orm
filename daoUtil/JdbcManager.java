package daoUtil;


import util.ReflectForJDBC;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcManager {

    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    private static Connection conn;
    private static final String SHOWSQL;
    private static final Log log = LogFactory.getLog(JdbcManager.class);

    private JdbcManager() {
    }

    static {
        Properties pop = new Properties();

        try {
            pop.load(JdbcManager.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            Class.forName(pop.getProperty("jdbc.driver"));
            conn = DriverManager.getConnection(pop.getProperty("jdbc.url"), pop.getProperty("jdbc.user"), pop.getProperty("jdbc.password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SHOWSQL = pop.getProperty("jdbc.showsql");
    }

    /**
     * 得到一个数据库连接
     *
     * @return
     */
    public static Connection getConn() {
        if (tl.get() == null) {
            tl.set(conn);
        }

        return tl.get();
    }

    /**
     * 拿到预编译对象
     *
     * @param sql
     * @return
     */
    public static PreparedStatement getPreparedStatement(String sql) {
        if (getConn() != null) {
            try {
                PreparedStatement psd = getConn().prepareStatement(sql);
                if (SHOWSQL.equals("true"))
                	log.info("sql:"+sql);
                return psd;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 更新操作
     *
     * @param sql
     * @param map
     * @return
     */
    public static int update(String sql, Map<String, Object>... map) {
        PreparedStatement psd = getPreparedStatement(sql);
        psd = ReflectForJDBC.fullParamIntoPreparedStatement(psd,SHOWSQL,map);
        if (psd != null) {
            try {
                int rs = psd.executeUpdate();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /**
     * 得到一个结果集
     *
     * @param sql
     * @return
     */
    public static ResultSet executeQuery(String sql, Object... param) {
        PreparedStatement psd = getPreparedStatement(sql);
        psd = ReflectForJDBC.fullParamForExecuteQuery(psd, param);
        if (psd != null) {
            try {
                ResultSet rs = psd.executeQuery();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


    public static int getCount(String sql, Object... object) {
        ResultSet rs = executeQuery(sql, object);
        int count = 0;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info("count:"+count);
        return count;

    }
    
    public static int execution(String sql, Object... object) {
    	PreparedStatement psd = getPreparedStatement(sql);
        psd = ReflectForJDBC.fullParamForExecuteQuery(psd, object);
        int result=0;
        try {
			if(psd.execute()) {
				result=1;
			}else {
				result=0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }
    
    
}
