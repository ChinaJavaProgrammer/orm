package util;


import daoUtil.JdbcManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * 封装结果集
 */
public class ReflectForJDBC {
	private static final Log log = LogFactory.getLog(ReflectForJDBC.class);

    /**
     * 将结果集封装成实体类对象集合
     *
     * @param c   实体类字节码
     * @param rs  结果集
     * @param <T>
     * @return 返回一个实体类的集合
     */
    public static <T> List<T> list(Class<T> c, ResultSet rs) {
        List<T> beanList = new ArrayList<>();
        try {
            ResultSetMetaData rsm = rs.getMetaData();
            Map<String, String> mapping = AnalyzeAnnotation.getEntityMappingDB(c);
            int count = rsm.getColumnCount();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = c.newInstance();
                for (int i = 0; i < count; i++) {
                    String fieldName = rsm.getColumnName(i + 1);
                    Object value = rs.getObject(fieldName);
                    if (mapping.containsKey(fieldName))
                        fieldName = mapping.get(fieldName);
                    setValue(t, AnalyzeAnnotation.setMethodName(fieldName),
                            c.getDeclaredField(fieldName).getType(), value);

                }
                beanList.add(t);
            }
            return beanList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Map<String,Object>> listMap( ResultSet rs) {
        try {
            ResultSetMetaData rsm = rs.getMetaData();
            int count = rsm.getColumnCount();
            List<Map<String,Object>> list = new ArrayList<>();
            while (rs.next()) {
            	Map<String, Object> temp = new HashMap<String, Object>();
                for (int i = 0; i < count; i++) {
                    String fieldName = rsm.getColumnName(i + 1);
                    Object value = rs.getObject(fieldName);
                    temp.put(fieldName, value);
                }
                list.add(temp);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }
    /**
     * 通过实体类的setter方法给实体类字段赋值
     *
     * @param t          传入需要封装的对象
     * @param methodName settter的方法名
     * @param type       方法参数类型
     * @param param      需要赋值的参数
     * @param <T>
     */
    public static <T> void setValue(T t, String methodName, Class type, Object param) {

        try {
            Method method = t.getClass().getMethod(methodName, type);
            method.invoke(t, param);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 排除空的字段对应的值
     *
     * @param paramMap
     * @return
     */
    public static Map<String, Object> rejectNullParam(Map<String, Object> paramMap) {
        Map<String, Object> param = new HashMap<>();
        for (Map.Entry<String, Object> enty : paramMap.entrySet()) {
            if (enty.getValue() != null) {
                param.put(enty.getKey(), enty.getValue());
            }
        }
        return param;
    }

    /**
     * 生成insertddl语句并执行插入操作
     *
     * @param t 传入需要插入的对象
     * @return
     */
    public static <T> int insertDDL(T t) {
        //拿到当前实体类的非空字段
        Map<String, Object> map = rejectNullParam(AnalyzeAnnotation.getFieldParam(t));
        //拿到当前实体类的主键非空字段
        Map<String, Object> primaryKey = AnalyzeAnnotation.getPrimaryKey(t);
        String primery = "";
        //拼接SQL语句
        StringBuilder prex = new StringBuilder("insert into " + AnalyzeAnnotation.getTableName(t.getClass()) + " ");
        //如果主键不为空的情况
        if (!primaryKey.containsValue(null)) {
            prex.append(" (");
            for (Map.Entry<String, Object> enty : primaryKey.entrySet()) {
            	primery = enty.getKey();
                prex.append(enty.getKey() + ",");
            }
            for (Map.Entry<String, Object> enty : map.entrySet()) {
                prex.append(enty.getKey() + ",");
            }
            prex = new StringBuilder(prex.substring(0, prex.length() - 1));
            prex.append(")");
            prex.append(" values(");
            int count = map.size() + primaryKey.size();
            for (int i = 0; i < count; i++) {
                prex.append("?,");
            }
            prex = new StringBuilder(prex.substring(0, prex.length() - 1));
            prex.append(")");
            return JdbcManager.update(prex.toString(),primaryKey , map);
        } else {        //主键为空的情况（也就是主键自增长）
        	
        	  for (Map.Entry<String, Object> enty : primaryKey.entrySet()) {
              	primery = enty.getKey();
              }
            prex.append("(");
            int count = map.size();
            for (Map.Entry<String, Object> enty : map.entrySet()) {
                prex.append(enty.getKey() + ",");
            }
            prex = new StringBuilder(prex.substring(0, prex.length() - 1));
            prex.append(") values(");
            for (int i = 0; i < count; i++) {
                prex.append("?,");
            }
            prex = new StringBuilder(prex.substring(0, prex.length() - 1));
            prex.append(")");
            int result = JdbcManager.update(prex.toString(), map);
            List<Map<String,Object>> listMap =listMap(JdbcManager.executeQuery("SELECT LAST_INSERT_ID()", null));
           int id =new BigInteger(listMap.get(0).get("LAST_INSERT_ID()")+"").intValue() ;
           try {
			t.getClass().getMethod("set"+Character.toUpperCase(primery.charAt(0))+primery.substring(1), t.getClass().getDeclaredField(primery).getType()).invoke(t, id);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            return result;
        }

    }

    /**
     * 通过对象删除生成DDL语句执行删除操作
     *
     * @param t   传入一个需要删除的实体
     * @param <T>
     * @return
     */
    public static <T> int deleteDDL(T t) {
        //拿到当前实体类中的非空字段的键值对
        Map<String, Object> map = rejectNullParam(AnalyzeAnnotation.getFieldParam(t));
        //拿到当前实体类的主键的键值对
        Map<String, Object> primaryKey = AnalyzeAnnotation.getPrimaryKey(t);
        //拼接SQL语句
        StringBuilder sb = new StringBuilder("delete from " + AnalyzeAnnotation.getTableName(t.getClass()) + " where ");

        for (Map.Entry<String, Object> enty : primaryKey.entrySet()) {
            sb.append(enty.getKey() + "=? and ");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        for (Map.Entry<String, Object> enty : map.entrySet()) {
            sb.append(" " + enty.getKey() + "=? and ");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 4));
        return JdbcManager.update(sb.toString(), map, primaryKey);
    }

    /**
     * 通过主键删除生成DDL语句
     *
     * @param c      需要删除的实体的字节码
     * @param object 需要删除的主键的值
     * @param <T>
     * @return
     */
    public static <T> int deletePrimaryDDL(Class<T> c, Object object) {
        try {
            Map<String, Object> primaryKey = AnalyzeAnnotation.getPrimaryKey(c.newInstance());
            StringBuilder sb = new StringBuilder("delete from " + AnalyzeAnnotation.getTableName(c) + " where ");
            for (Map.Entry<String, Object> enty : primaryKey.entrySet()) {
                sb.append(enty.getKey() + "=?");
                primaryKey.put(enty.getKey(), object);
            }
            return JdbcManager.update(sb.toString(), primaryKey);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 更新操作生成DDL语句
     *
     * @param t   传入需要更新的对象
     * @param <T>
     * @return
     */
    public static <T> int updateDDL(T t) {
        Map<String, Object> map = rejectNullParam(AnalyzeAnnotation.getFieldParam(t));
        Map<String, Object> primaryKey = AnalyzeAnnotation.getPrimaryKey(t);
        Map<String, Object> temp = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder("update " + AnalyzeAnnotation.getTableName(t.getClass()) + " set ");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=?,");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1) + " where ");
        String id = "";
        Object value = "";
        for (Map.Entry<String, Object> entry : primaryKey.entrySet()) {
            id = entry.getKey();
            value = entry.getValue();
            sb.append(entry.getKey() + "=?");
        }
        temp.putAll(map);
        temp.putAll(primaryKey);
        return JdbcManager.update(sb.toString(), temp);
    }

    /**
     * 通过主键查询生成DDL语句
     *
     * @param c      传入需要查询的实体类的字节码
     * @param object 传入主键的值
     * @param <T>
     * @return
     */
    public static <T> T getSingleDDL(Class<T> c, Object object) {
        try {
            Map<String, Object> primaryKey = AnalyzeAnnotation.getPrimaryKey(c.newInstance());
            String sql = new String("select * from " + AnalyzeAnnotation.getTableName(c) + " where ");
            for(Map.Entry<String,Object> entry:primaryKey.entrySet()){
                sql+=entry.getKey()+"=?";
            }
            sql =changeSQL(sql,c);
            return singleResultByPrimaryKey(c, JdbcManager.executeQuery(sql, object));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 将结果集封装成一个对象
     *
     * @param c   需要封装的对象的字节码
     * @param rs  结果集
     * @param <T>
     * @return
     */
    public static <T> T singleResultByPrimaryKey(Class<T> c, ResultSet rs) {
        try {
            ResultSetMetaData rsm = rs.getMetaData();
            Map<String, String> mappingField = AnalyzeAnnotation.getEntityMappingDB(c);
            int count = rsm.getColumnCount();
            T t = null;
            if (rs.next()) {
                t = c.newInstance();
                for (int i = 0; i < count; i++) {
                    String fieldName = rsm.getColumnName(i + 1);
                    Object value = rs.getObject(fieldName);
                    if (mappingField.containsKey(fieldName))
                        fieldName = mappingField.get(fieldName);
                    Field field = c.getDeclaredField(fieldName);
                    Class fieldType = field.getType();
                    Method method = c.getMethod(AnalyzeAnnotation.setMethodName(fieldName), fieldType);
                    method.invoke(t, value);
                }
            }
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean  selectObject(ResultSet resultSet){
        try {
            if(resultSet.next()){
                return true;
            }else{
              return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 为PreparedStatement 赋值
     *
     * @param preparedStatement
     * @param map
     * @return
     */
    public static PreparedStatement fullParamIntoPreparedStatement(PreparedStatement preparedStatement, String sHOWString,Map<String, Object>... map) {
    	
    	if("true".equals(sHOWString)) {
    		log.info("params:"+map);
    	}
    	
        if (map == null)
            return preparedStatement;
        int count = 0;
        try {
            if (map.length == 1) {
                for (Map.Entry<String, Object> enty : map[0].entrySet()) {
                    preparedStatement.setObject(count + 1, enty.getValue());
                    count++;
                }
            } else if (map.length == 2) {
                if (map[0].size() == 1) {
                    for (Map.Entry<String, Object> enty : map[0].entrySet()) {
                        preparedStatement.setObject(count + 1, enty.getValue());
                        count++;
                    }
                    for (Map.Entry<String, Object> enty : map[1].entrySet()) {
                        preparedStatement.setObject(count + 1, enty.getValue());
                        count++;
                    }
                } else {
                    for (Map.Entry<String, Object> enty : map[1].entrySet()) {
                        preparedStatement.setObject(count + 1, enty.getValue());
                        count++;
                    }
                    for (Map.Entry<String, Object> enty : map[0].entrySet()) {
                        preparedStatement.setObject(count + 1, enty.getValue());
                        count++;
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


        return preparedStatement;
    }

    /**
     * 查询全部的DDL语句
     *
     * @param c   需要查询的实体类的字节码
     * @param <T>
     * @return
     */
    public static <T> List<T> getAllDLL(Class<T> c) {
        String sql = "select * from " + AnalyzeAnnotation.getTableName(c);
        return list(c, JdbcManager.executeQuery(ReflectForJDBC.changeSQL(sql,c)));
    }

    /***
     * get查询语句赋值
     * @param psd   传入预编译语句
     * @param param   赋值的参数
     * @return
     */
    public static PreparedStatement fullParamForExecuteQuery(PreparedStatement psd, Object... param) {
    	log.info("parmas:"+param);
        if (param == null || param.length == 0)
            return psd;
        try {
            for (int i = 0; i < param.length; i++) {
                psd.setObject(i + 1, param[i]);
            }
            return psd;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询拼装SQL DDL语句
     *
     * @param sql         传入SQL语句
     * @param firstResult 分页开始的位置
     * @param maxResult   每一页最多显示多少条
     * @return
     */
    public static String byPageSQL(String sql, int firstResult, int maxResult) {
        sql += " limit " + firstResult + "," + maxResult;
        return sql;
    }

    /**
     * 查询所有分页查询DDL
     *
     * @param c           传入需要查询的实体类字节码
     * @param firsrResult 分页的其实位置
     * @param maxResult   每页最大显示条数
     * @param <T>
     * @return
     */
    public static <T> String getAllByPageDDL(Class<T> c, int firsrResult, int maxResult) {
        String sql = " select * from " + AnalyzeAnnotation.getTableName(c) + " limit " + firsrResult + "," + maxResult;
        return ReflectForJDBC.changeSQL(sql,c);
    }


    /**
     * 拿到当前数据库的所有表结构
     *
     * @return
     */
    public static List<String> getTables() {
        String sql = " show tables ";
        return handleResultSetForAllTable(JdbcManager.executeQuery(sql));
    }


    /**
     * 处理数据库查出来的所有表结构的名称集合
     *
     * @param rs 传入已经查询出来的结果集
     * @return 返回一个表结构名称的集合
     */
    public static List<String> handleResultSetForAllTable(ResultSet rs) {

        List<String> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到当前表的json数据（不排序）
     *
     * @param table 当前操作的表
     * @return 返回一个完整表的json数据
     */
    public static JSONObject getCurrentTableValue(String table) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        String sql = " select * from " + table;
        ResultSet rs = JdbcManager.executeQuery(sql);
        return getCurrentTableValuesJSON(json, array, table, rs);
    }


    /**
     * 通过json删除数据
     *
     * @param table      需要操作的表
     * @param primaryKey 主键
     * @param num        主键值（可一个可多个）
     * @return
     */
    public static int deleteTableData(String table, String primaryKey, String[] num) {
        String sql = "delete from " + table + " where " + primaryKey;
        if (num.length == 1) {
            sql += " ='" + num[0] + "'";
        } else {
            sql += " in(";
            for (int i = 0; i < num.length; i++) {
                sql += " '" + num[i] + "',";
            }
            sql = sql.substring(0, sql.length() - 1) + ")";
        }

        return JdbcManager.update(sql);
    }


    /**
     * 更新或者插入操作
     *
     * @param update 传入json转换成字符串的更新操作的相关json数据
     * @param insert 传入json转换成字符串的插入操作的相关json数据
     * @return
     */
    public static int updateOrInsert(String update, String insert) {
        JSONObject json = new JSONObject();
        int result = 0;
        if (!update.equals("[]")) {
            JSONArray array = new JSONArray(update);
            for (int i = 0; i < array.length(); i++) {
                String sql = "update ";
                Map<String, Object> param = new LinkedHashMap<>();
                JSONObject tempJson = array.getJSONObject(i);
                String primaryKey = tempJson.getString("primarykey");
                String table = tempJson.getString("table");
                sql += table + " set ";
                Map<String, Object> tempMap = tempJson.toMap();
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if (!entry.getKey().equals(primaryKey) && !entry.getKey().equals("table") && !entry.getKey().equals("primarykey")) {
                        param.put(entry.getKey(), entry.getValue());
                        sql += entry.getKey() + "=?,";
                    }
                }
                sql = sql.substring(0, sql.length() - 1) + " where " + primaryKey + "=?";
                param.put(primaryKey, tempMap.get(primaryKey));
                result += JdbcManager.update(sql, param);
            }
        }
        if (!insert.equals("[]")) {
            JSONArray array = new JSONArray(insert);
            for (int i = 0; i < array.length(); i++) {
                Map<String, Object> param = new LinkedHashMap<>();
                JSONObject tempJson = array.getJSONObject(i);
                String table = tempJson.getString("table");
                String sql = "insert into " + table + " (";
                String sub = " values(";
                Map<String, Object> tempMap = tempJson.toMap();
                for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    if (!entry.getKey().equals("table")) {
                        param.put(entry.getKey(), entry.getValue());
                        sql += entry.getKey() + ",";
                        sub += "?,";
                    }
                }
                sql = sql.substring(0, sql.length() - 1) + ")";
                sub = sub.substring(0, sub.length() - 1) + ")";
                sql = sql + sub;
                result += JdbcManager.update(sql, param);
            }
        }
        return result;
    }


    /**
     * 通过排序拿到一个数据库当前表的json的数据格式
     *
     * @param table     需要操作的表
     * @param order     需要进行排序的字段
     * @param condition 需要排序的规则
     * @return
     */
    public static JSONObject getCurrentTableValuesByOrder(String table, String order, String condition) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        String rule = "";
        if (condition.equals("up")) {
            rule = "asc";
        } else {
            rule = "desc";
        }
        String sql = " select * from " + table + " order by " + order + " " + rule;
        ResultSet rs = JdbcManager.executeQuery(sql);

        return getCurrentTableValuesJSON(json, array, table, rs);

    }



    /*
         {
         field:{"id","name","value","test"} //数据库表的表头
          data:[{               //数据库每一行的字段所对应的值
                 id:"",
              name:"",
              value:"",
              test:"",
                },
                {
                 id:"",
              name:"",
              value:"",
              test:"",
                }
                ],
             primarykey:"",         //主键
             primarykeytype:""      //主键策略
         }

     */

    /**
     * 处理 从数据库拿出来的数据转换成json结构
     *
     * @param json  传入一个json
     * @param array 传入一个Json数组
     * @param table 传入需要操作的表
     * @param rs    传入执行完SQL得到的结果集ResultSet
     * @return
     */
    public static JSONObject getCurrentTableValuesJSON(JSONObject json, JSONArray array, String table, ResultSet rs) {
        try {
            //拿到当前表的主键
            ResultSet rs1 = JdbcManager.executeQuery("desc " + table);
            while (rs1.next()) {
                if (rs1.getObject(4).toString().equals("PRI")) {
                    json.put("primarykey", rs1.getObject(1));
                    if (rs1.getObject(6).toString().equals("auto_increment")) {
                        json.put("primarykeytype", 1);
                    } else {
                        json.put("primarykeytype", 0);
                    }
                    break;
                }
            }
            ResultSetMetaData rsd = rs.getMetaData();
            int count = rsd.getColumnCount();
            JSONObject json1 = new JSONObject();
            for (int i = 0; i < count; i++) {
                json1.put(rsd.getColumnName(i + 1), "");
            }
            json.put("field", json1);
            while (rs.next()) {
                JSONObject tempJson = new JSONObject();
                for (int i = 0; i < count; i++) {
                    tempJson.put(rsd.getColumnName(i + 1), rs.getObject(rsd.getColumnName(i + 1)));
                }
                array.put(tempJson);
            }
            json.put("data", array);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将写入的SQL语句转换成数据库对应字段对应表的SQL语句
     * @param sql
     * @param c
     * @return
     */
    public static String changeSQL(String sql,Class c){


        return sql;
    }
    
    public static void autoConmit(Boolean autoCommit) throws SQLException {
			getConnection().setAutoCommit(autoCommit);
    }
    
    
    public static Connection getConnection() {
    	return JdbcManager.getConn();
    }
    
    public static void conmit() throws SQLException {
    	
    	getConnection().commit();
    }
    
    public static int execute(String sql,Object[] object) {
    	return JdbcManager.execution(sql, object);
    }
    
    public static void rollBack() throws SQLException {
    	getConnection().rollback();
    	}

	public  static <T> IPage<T> findPage(String sql, Integer start, Integer limit, Object[] param, Class<T> clazz) {
		String se="select count(*) ";
		se+=sql.substring(sql.indexOf("from"));
		int count=JdbcManager.getCount(se, param);
		sql+=" limit ?,?";
		param=Arrays.copyOfRange(param, 0,param.length+2);
		param[param.length-2]=start*limit;
		param[param.length-1]=limit;
		ResultSet resultSet = JdbcManager.executeQuery(sql, param);
	
		if(clazz!=null) {
			
			IPage<T>  page= new IPage<T>();
			page.setData(list(clazz,resultSet));
			page.setCurrentPage(start+1);
			page.setDataNum(limit);
			page.setTotalCount(count);
			page.setTotalPage((count+limit-1)/limit);
			return page;
		}else {
			IPage page = new IPage<>();
			page.setData(listMap(resultSet));
			page.setCurrentPage(start+1);
			page.setDataNum(limit);
			page.setTotalCount(count);
			page.setTotalPage((count+limit-1)/limit);
			return page;
		}
	}
	
	public  static <T> IPage<T> findPage(String sql, Integer start, Integer limit, Object[] param) {
		String se="select count(*) ";
		se+=sql.substring(sql.indexOf("from"));
		int count=JdbcManager.getCount(se, param);
		sql+=" limit ?,?";
		param=Arrays.copyOfRange(param, 0,param.length+2);
		param[param.length-2]=start;
		param[param.length-1]=limit;
		ResultSet resultSet = JdbcManager.executeQuery(sql, param);
		IPage page = new IPage<>();
		page.setData(listMap(resultSet));
		page.setCurrentPage(start+1);
		page.setDataNum(limit);
		page.setTotalCount(count);
		page.setTotalPage((count+limit-1)/limit);
		return page;
	}
}
