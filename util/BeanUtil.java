package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanUtil {


    public static <T> T changeToBean(T t, Map<String, String[]> map) {

        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Class fieldType = field.getType();
                if (map.containsKey(fieldName)) {
                    Method method = t.getClass().getMethod(AnalyzeAnnotation.setMethodName(fieldName), fieldType);
                    if (fieldType==Integer.class)
                    {
                        method.invoke(t, Integer.parseInt(map.get(fieldName)[0]));
                    }else if(fieldType==Float.class){
                        method.invoke(t, Float.parseFloat(map.get(fieldName)[0]));
                    }else if(fieldType==Double.class){
                        method.invoke(t, Double.parseDouble(map.get(fieldName)[0]));
                    }else{
                        method.invoke(t, map.get(fieldName)[0]);
                    }

                }
            }
            return t;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
