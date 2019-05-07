package daoUtil;

import util.IPage;
import util.ReflectForJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

public class Query {
	/**
	 * 条件查询一条数据
	 *
	 * @param c   传入需要查询的实体类的class
	 * @param sql 传入SQL语句
	 * @param obj 如果条件查询则传入相应的参数
	 * @param     <T> 泛型传入什么类型返回什么类型
	 * @return 返回一个实体类
	 */
	public <T> T uniqueResult(Class<T> c, String sql, Object... obj) {
		return ReflectForJDBC.singleResultByPrimaryKey(c,
				JdbcManager.executeQuery(ReflectForJDBC.changeSQL(sql, c), obj));
	}

	/**
	 * 条件（非条件）查询一个记录
	 * 
	 * @param sql 传入SQL语句
	 * @param obj 如果需要条件查询则传入需要查询的条件
	 * @return 如果查询到有数据则返回true 如果没查询到返回false
	 */
	public boolean uniqueResult(String sql, Object... obj) {
		return ReflectForJDBC.selectObject(JdbcManager.executeQuery(sql, obj));
	}

	/**
	 * 查询所有
	 *
	 * @param c 需要查询的对象的class
	 * @param   <T> 泛型传入什么类型返回什么类型
	 * @return 返回一个实体类集合
	 */
	public <T> List<T> list(Class<T> c) {

		return ReflectForJDBC.getAllDLL(c);
	}

	/**
	 * 分页查询所有
	 *
	 * @param             <T> 传入什么类型返回什么类型
	 * @param c           传入需要查询的实体类字节码
	 * @param firstResult 分页的起始条数（显示的最大条数*（当前页数-1））
	 * @param maxResult   显示的最大条数
	 * @return 返回一个实体类集合
	 */
	public <T> List<T> list(Class<T> c, int firstResult, int maxResult) {

		return ReflectForJDBC.list(c,
				JdbcManager.executeQuery(ReflectForJDBC.getAllByPageDDL(c, firstResult, maxResult)));
	}

	/**
	 * 条件查询多条数据
	 *
	 * @param c   传入实体类class
	 * @param sql 传入sql语句
	 * @param obj 如果需要条件查询则传入条件参数否则可以不写
	 * @param     <T> 泛型传入什么类型返回什么类型
	 * @return 返回对象集合
	 */
	public <T> List<T> list(Class<T> c, String sql, Object... obj) {
		return ReflectForJDBC.list(c, JdbcManager.executeQuery(ReflectForJDBC.changeSQL(sql, c), obj));
	}

	/**
	 * 分页查询
	 *
	 * @param c           需要查询的实体类的class
	 * @param sql         sql语句
	 * @param firstResult 分页查询的开头
	 * @param maxResult   分页查询的最大显示条数
	 * @param obj         如果需要条件分页查询则传入条件否则可以不写
	 * @param             <T>
	 * @return
	 */
	public <T> List<T> list(Class<T> c, String sql, int firstResult, int maxResult, Object... obj) {
		return ReflectForJDBC.list(c, JdbcManager
				.executeQuery(ReflectForJDBC.byPageSQL(ReflectForJDBC.changeSQL(sql, c), firstResult, maxResult), obj));
	}

	/**
	 * 通过主键查询单个对象
	 *
	 * @param c   需要查询的实体类的字节码
	 * @param obj 传入主键的值
	 * @param     <T> 泛型传入什么类型返回什么类型
	 * @return 返回一个实体
	 */
	public <T> T get(Class<T> c, Object obj) {
		return ReflectForJDBC.getSingleDDL(c, obj);
	}

	/**
	 * 更新
	 *
	 * @param t 传入需要更新的对象
	 * @param   <T> 泛型传入什么类型返回什么类型
	 * @return 返回影响数据库行数如果小于0则更新失败
	 */
	public <T> int update(T t) {
		return ReflectForJDBC.updateDDL(t);
	}

	/**
	 * 实体类对象删除
	 *
	 * @param t 需要删除的对象
	 * @param   <T> 泛型传入什么类型返回什么类型
	 * @return 返回影响数据库行数如果小于0则更新失败
	 */
	public <T> int delete(T t) {
		return ReflectForJDBC.deleteDDL(t);
	}

	/**
	 * 通过主键删除
	 *
	 * @param c   传入需要删除的实体类的class
	 * @param obj 传入主键的值
	 * @param     <T> 泛型传入什么类型返回什么类型
	 * @return 返回影响数据库行数如果小于0则更新失败
	 */
	public <T> int delete(Class<T> c, Object obj) {
		return ReflectForJDBC.deletePrimaryDDL(c, obj);
	}

	/**
	 * 插入操作
	 *
	 * @param t 需要插入的实体
	 * @param   <T>
	 * @return
	 */
	public <T> int insert(T t) {
		return ReflectForJDBC.insertDDL(t);
	}

	/**
	 * 有无条件查询数据库的记录数的条数
	 *
	 * @param sql   sql语句
	 * @param param 传入需要的参数
	 * @return 返回总条数
	 */
	public int count(String sql, Object... param) {
		return JdbcManager.getCount(sql, param);
	}

	/**
	 * 查询当前数据库的所有表名
	 *
	 * @return 返回一个数据库表名的集合
	 */
	public List<String> getTables() {
		return ReflectForJDBC.getTables();
	}

	/**
	 * 查询当前表的所有数据
	 *
	 * @param table 传入当前表名
	 * @return 返回一个json数据
	 */
	public JSONObject getCurrentTableValue(String table) {

		return ReflectForJDBC.getCurrentTableValue(table);
	}

	/**
	 * 
	 * @param table
	 * @param primaryKey
	 * @param value
	 * @return
	 */
	public int deleteTableData(String table, String primaryKey, String[] value) {
		return ReflectForJDBC.deleteTableData(table, primaryKey, value);
	}

	/**
	 * 
	 * @param update
	 * @param insert
	 * @return
	 */
	public int updateOrInsert(String update, String insert) {

		return ReflectForJDBC.updateOrInsert(update, insert);
	}

	/**
	 * 
	 * @param table
	 * @param order
	 * @param condition
	 * @return
	 */
	public JSONObject getCurrentTableValuesByOrder(String table, String order, String condition) {

		return ReflectForJDBC.getCurrentTableValuesByOrder(table, order, condition);
	}

	/**
	 * 执行
	 * 
	 * @param sql
	 * @param object
	 * @return
	 */
	public int execute(String sql, Object[] object) {
		return ReflectForJDBC.execute(sql, object);
	}

	/**
	 * 设置事务是否自动提交默认自动提交
	 * 
	 * @param autoCommit
	 * @throws SQLException
	 */
	public void autoConmit(Boolean autoCommit) throws SQLException {
		ReflectForJDBC.autoConmit(autoCommit);
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public void conmit() throws SQLException {
		ReflectForJDBC.conmit();
	}

	/**
	 * 事务回滚
	 * 
	 * @throws SQLException
	 */
	public void rollBack() throws SQLException {
		ReflectForJDBC.rollBack();
	}

	/**
	 * 拿到原生JDBC的Connection
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return ReflectForJDBC.getConnection();
	}
	
	public <T> IPage<T>  findPage(String sql,Integer start,Integer limit,Object[] param,Class<T> clazz){
		
		return ReflectForJDBC.findPage(sql,start,limit,param,clazz);
	}
	
	public IPage findPage(String sql,Integer start,Integer limit,Object[] param) {
		
		return ReflectForJDBC.findPage(sql,start,limit,param);
	}

}
