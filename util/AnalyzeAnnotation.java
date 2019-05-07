package util;

import annotation.*;
import exception.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 解析注解
 */
public class AnalyzeAnnotation {


    private static String id;
    private static Id mappingId;
    private static Map<String, String> enttyMappingDB = new HashMap<>();

    /**
     * 获取主键对应数据库的字段和其值
     *
     * @param
     * @return
     */
    public static <T> Map<String, Object> getPrimaryKey(T t) {
        return findPrimaryKey(t);
    }

    /**
     * 返回除主键外的实体类字段对应数据库字段和其值
     *
     * @param
     * @return
     */
    public static Map<String, Object> getFieldParam(Object object) {
        return findBaseFieldParam(object);
    }

    /**
     * 返回数据库字段和实体类字段的对应集合格式为{"数据库字段","实体类字段"}
     *
     * @param c
     * @return
     */
    public static Map<String, String> getEntityMappingDB(Class c) {
        return findEntityMappingDB(c);
    }

    /**
     * 返回数据库字段和实体类字段的对应集合格式为{"实体类字段",""数据库字段}
     *
     * @param c
     * @return
     */
    public static Map<String, String> getEntityMappingDBInvers(Class c) {
        return findEntityMappingDBInverse(c);
    }


    /**
     * 拿到JoinTable注解的相关属性
     *
     * @param c 传入需要解析的class
     * @return 如果没有则返回null，如果包含属性则返回map{"实体类字段"={"tableName"="","ownColumnJoinTable"="","inverseColumnJoinTable"=""}}
     */
    public static Map<String, Map<String, String>> findJoinTableInfo(Class c) {
        return getJoinTable(c);
    }

    /**
     * 拿到JoinColumn注解的相关属性
     *
     * @param c 传入需要解析的class
     * @return 如果没有则返回null，如果包含属性则返回map{"实体类字段"={"ownColumn"="","targetColumn"=""}}
     */
    public static Map<String, Map<String, String>> findJoinColumnInfo(Class c) {
        return getJoinColumn(c);
    }

    /**
     * 拿到ManyToMany注解的相关属性
     *
     * @param c 传入需要解析的class
     * @return 如果没有则返回null，如果包含属性则返回map{"实体类字段"={Class}
     */
    public static Map<String, Class> findManyToManyInfo(Class c) {
        return getManyToMany(c);
    }

    /**
     * 拿到ManyToOne注解的相关属性
     *
     * @param c 传入需要解析的class
     * @return 如果没有则返回null，如果包含属性则返回map{"实体类字段"={Class}
     */
    public static Map<String, Class> findManyToOne(Class c) {
        return getManyToOne(c);
    }

    /**
     * 拿到OneToMany注解的相关属性
     *
     * @param c 传入需要解析的class
     * @return 如果没有则返回null，如果包含属性则返回map{"实体类字段"={Class}
     */
    public static Map<String, Class> findOneToMany(Class c) {
        return getOneToMany(c);
    }

    /**
     * 判断当前实体类是否使用@Entity注解
     *
     * @param c
     * @return
     */
    private static boolean isEntity(Class c) {

        try {
            if (!c.isAnnotationPresent(Entity.class)) {
                throw new NotEntityException(c + " is not a Entity");
            }
            return true;
        } catch (NotEntityException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断当前实体类是否有@Table注解
     *
     * @param c
     * @return
     */
    private static boolean hasTable(Class c) {
        try {
            if (isEntity(c)) {
                if (!c.isAnnotationPresent(Table.class))
                    throw new NoTableException(c + " has no @Table");
                return true;
            } else {
                return false;
            }

        } catch (NoTableException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断当前实体类是否使用@Id注解
     *
     * @param c
     * @return
     */
    private static boolean hasId(Class c) {
        Field[] fields = c.getDeclaredFields();
        if (hasTable(c)) {
            boolean flag = false;
            for (Field field : fields) {
                if (field.getAnnotation(Id.class) != null) {
                    flag = true;
                    id = field.getName();
                    mappingId = field.getAnnotation(Id.class);
                    break;
                }
            }
            try {
                if (!flag) {
                    throw new NoIdException(c + " properties has no @Id");
                }
                return true;
            } catch (NoIdException e) {
                e.printStackTrace();
                return false;

            }
        } else {
            return false;
        }

    }

    /**
     * 判断当前实体类是否使用了@Column注解
     *
     * @param c
     * @return
     */
    private static boolean hasColumn(Class c) {
        Field[] fields = c.getDeclaredFields();
        if (hasId(c)) {
            try {
                for (Field field : fields) {
                    if (field.getAnnotation(Column.class) == null && !isOneToMany(field) && !isManyToOne(field) && !isManyToMany(field) && !field.getName().equals("serialVersionUID")  &&field.getAnnotation(NoMapping.class) == null) {
                        throw new NoColumnException(c + " " + field.getName() + " has no @Column");
                    }
                }
                return true;
            } catch (NoColumnException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

    }
    

    /**
     * 找到主键及其值
     *
     * @param
     * @return
     */
    public static <T> Map<String, Object> findPrimaryKey(T t) {
        Class c = t.getClass();
        try {
            if (hasColumn(c)) {
                Map<String, Object> idParam = new LinkedHashMap<>();
                Field field = c.getDeclaredField(id);
                Column column = field.getAnnotation(Column.class);
                Method method = c.getMethod(getMethodName(id));
                if (column.name().equals("")) {
                    idParam.put(id, method.invoke(t));
                } else {
                    idParam.put(column.name(), method.invoke(t));
                }
                return idParam;
            } else {
                return null;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 拿到实体类对应数据库的字段及其值
     *
     * @param
     * @return
     */
    public static Map<String, Object> findBaseFieldParam(Object object) {
        Class c = object.getClass();
        if (hasColumn(c)) {
            Field[] fields = c.getDeclaredFields();
            Map<String, Object> fieldParam = new LinkedHashMap<>();
            try {
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Column column = field.getAnnotation(Column.class);
                    if (field.getAnnotation(Id.class) == null && !field.getName().equals("serialVersionUID") &&field.getAnnotation(Column.class) != null) {
                        String getMethodName = getMethodName(fieldName);
                        Method method = c.getMethod(getMethodName);
                        if (column != null) {
                            if (!column.name().equals("")) {
                                fieldParam.put(column.name(), method.invoke(object));
                            } else {
                                fieldParam.put(fieldName, method.invoke(object));
                            }
                        }

                    } else {

                    }
                }
                return fieldParam;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            return null;
        }
        return null;
    }


    /**
     * 解析实体类字段和数据库字段的对应集合
     *
     * @param c
     * @return
     */
    public static Map<String, String> findEntityMappingDB(Class c) {

        return commonsHandlerMappingField(c, "in");
    }

    /**
     * 解析实体类字段和数据库字段的对应集合
     *
     * @param c
     * @return
     */
    public static Map<String, String> findEntityMappingDBInverse(Class c) {

        return commonsHandlerMappingField(c, "invers");
    }

    /**
     * 共同处理实体类与数据库字段对应的map集合
     *
     * @param c     传入需要处理的class
     * @param order 传入处理方式（invers/in）
     * @return 如果传入的处理方式为inverse则返回Map<"   数据库字段   "   ,   "   实体类字段   ">如果传入的方式不是inverse则返回Map<"实体类字段","数据库字段"></>
     */
    public static Map<String, String> commonsHandlerMappingField(Class c, String order) {
        if (hasColumn(c)) {
            Field[] fields = c.getDeclaredFields();
            Map<String, String> fieldMapping = new HashMap<>();
            try {
                for (Field field : fields) {
                	if(!field.getName().equals("serialVersionUID") && field.getAnnotation(NoMapping.class)==null) {
                    String fieldName = field.getName();
                    Column column = field.getAnnotation(Column.class);

                    String getMethodName = getMethodName(fieldName);
                    Method method = c.getMethod(getMethodName);
                    if (!column.name().equals("")) {
                        if (order.equals("invers"))
                            fieldMapping.put(fieldName, column.name());
                        else
                            fieldMapping.put(column.name(), fieldName);
                    }
                	}
                }
                return fieldMapping;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        } else {
            return null;
        }
        return null;
    }

    /**
     * 拿到实体类getter的方法名
     *
     * @param fieldName
     * @return
     */
    public static String getMethodName(String fieldName) {
        fieldName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        return fieldName;
    }

    /**
     * 拿到实体类setter的方法名
     *
     * @param fieldName
     * @return
     */
    public static String setMethodName(String fieldName) {
        fieldName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        return fieldName;
    }

    /**
     * 获取实体类对应数据库的表名
     *
     * @param c 需要实体类的字节码
     * @return
     */
    public static String getTableName(Class c) {

        if (hasTable(c)) {
            Table table = (Table) c.getAnnotation(Table.class);
            if (!table.name().equals("")) {
                return table.name();
            } else {

                return c.getName().substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
            }

        } else {
            return null;
        }
    }


    /**
     * 判断是否有ManyToMany注解
     *
     * @param field 传入需要判断的字段
     * @return
     */
    public static boolean isManyToMany(Field field) {
        if (field.getAnnotation(ManyToMany.class) == null)
            return false;
        else
            return true;
    }

    /**
     * 判断是否有ManyToOne注解
     *
     * @param field 传入需要判断的字段
     * @return
     */
    public static boolean isManyToOne(Field field) {
        if (field.getAnnotation(ManyToOne.class) == null)
            return false;
        else
            return true;
    }

    /**
     * 判断是否有OneToMany注解
     *
     * @param field 传入需要判断的字段
     * @return
     */
    public static boolean isOneToMany(Field field) {
        if (field.getAnnotation(OneToMany.class) == null)
            return false;
        else
            return true;
    }

    /**
     * 判断是否有JoinTable注解
     *
     * @param field 传入需要判断的字段
     * @return
     */
    public static boolean isJoinTable(Field field) {
        try {
            if (!isManyToMany(field))
                throw new NoManyToManyException("the property " + field.getName() + " has no ManyToMany");
            if (field.getAnnotation(JoinTable.class) == null)
                return false;
            else
                return true;
        } catch (NoManyToManyException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否有JoinColumn注解
     *
     * @param field 传入需要判断的字段
     * @return
     */
    public static boolean isJoinColumn(Field field) {
        try {
            if (!isManyToOne(field) || !isOneToMany(field))
                throw new NoManyToManyException("the property " + field.getName() + " has no ManyToOne or OneToMany");
            if (field.getAnnotation(JoinColumn.class) == null)
                return false;
            else
                return true;
        } catch (NoManyToManyException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析JoinColumn注解获取JoinColumn注解的属性及其值
     *
     * @param c 传入需要获取的实体类的class
     * @return 返回格式为{"field"={"ownColumn"=""}{"targerColumn"="xxxxx"}}通过
     */
    public static Map<String, Map<String, String>> getJoinColumn(Class c) {
        Map<String, Map<String, String>> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (isManyToOne(field) || isOneToMany(field)) {
                    JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                    if (joinColumn.ownColumn().equals(""))
                        throw new Exception("the JoinColumn must have ownColumn");
                    if (joinColumn.targetColumn().equals(""))
                        throw new Exception("the JoinColumn must have targetColumn");
                    Map<String, String> temp = new HashMap<>();
                    temp.put("ownColumn", joinColumn.ownColumn());
                    temp.put("targetColumn", joinColumn.targetColumn());
                    map.put(field.getName(), temp);

                }
            }
            if (map.size() == 0)
                return null;
            else
                return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到ManyToOne注解的相关属性信息
     *
     * @param c 传入需要获取的class
     * @return 返回格式{"field",Class}
     */
    public static Map<String, Class> getManyToOne(Class c) {
        Map<String, Map<String, String>> joinColum = getJoinColumn(c);
        Map<String, Class> param = new HashMap<>();
        try {
            if (joinColum != null) {
                Set<String> key = joinColum.keySet();
                Iterator<String> it = key.iterator();
                while (it.hasNext()) {
                    String mapKey = it.next();
                    Field field = c.getDeclaredField(mapKey);
                    if (field.getAnnotation(ManyToOne.class).targer() == Object.class) {
                        param.put(field.getName(), null);
                    } else {
                        param.put(field.getName(), field.getAnnotation(ManyToOne.class).targer());
                    }
                }
                return param;
            } else {
                return null;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到OneToMany注解
     *
     * @param c 传入需要处理的class
     * @return 返回{"field"=CLass}
     */
    public static Map<String, Class> getOneToMany(Class c) {
        Map<String, Map<String, String>> joinColum = getJoinColumn(c);
        Map<String, Class> param = new HashMap<>();
        try {
            if (joinColum != null) {
                Set<String> key = joinColum.keySet();
                Iterator<String> it = key.iterator();
                while (it.hasNext()) {
                    String mapKey = it.next();
                    Field field = c.getDeclaredField(mapKey);
                    if (field.getAnnotation(OneToMany.class).targer() == Object.class) {
                        param.put(field.getName(), null);
                    } else {
                        param.put(field.getName(), field.getAnnotation(OneToMany.class).targer());
                    }
                }
                return param;
            } else {
                return null;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到ManyToMany注解的相关属性信息
     *
     * @param c 传入需要解析的class
     * @return 返回结果为{"field"=Class}
     */
    public static Map<String, Class> getManyToMany(Class c) {
        Map<String, Map<String, String>> joinTable = getJoinTable(c);
        Map<String, Class> param = new HashMap<>();
        try {
            if (joinTable != null) {
                Set<String> key = joinTable.keySet();
                Iterator<String> it = key.iterator();
                while (it.hasNext()) {
                    String mapKey = it.next();
                    Field field = c.getDeclaredField(mapKey);
                    if (field.getAnnotation(ManyToMany.class).targer() == Object.class) {
                        param.put(field.getName(), null);
                    } else {
                        param.put(field.getName(), field.getAnnotation(ManyToMany.class).targer());
                    }
                }
                return param;
            } else {
                return null;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到JoinTable注解的相关属性信息
     *
     * @param c 传入需要解析的class
     * @return 返回{"field"={"tableName"="","ownColumnJoinTable"="","inverseColumnJoinTable"=""}
     */
    public static Map<String, Map<String, String>> getJoinTable(Class c) {
        Map<String, Map<String, String>> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (isManyToMany(field)) {
                    JoinTable joinTable = field.getAnnotation(JoinTable.class);
                    if (joinTable.tableName().equals(""))
                        throw new Exception("the JoinTable must have tableName");
                    if (joinTable.ownColumnJoinTable().equals(""))
                        throw new Exception("the JoinTable must have ownColumnJoinTable");
                    if (joinTable.inverseColumnJoinTable().equals(""))
                        throw new Exception("the JoinTable must have inverseColumnJoinTable");
                    Map<String, String> temp = new HashMap<>();
                    temp.put("tableName", joinTable.tableName());
                    temp.put("ownColumnJoinTable", joinTable.ownColumnJoinTable());
                    temp.put("inverseColumnJoinTable", joinTable.inverseColumnJoinTable());
                    map.put(field.getName(), temp);

                }
            }
            if (map.size() == 0)
                return null;
            else
                return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
