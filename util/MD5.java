		package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: 戴虎
 * Date: 2018/08/17
 * Description:
 * Version: V1.0
 */
public class MD5 {


    /**
     * 加密
     * @param password
     * @return
     */
    public static String encoder(String password){
        String str ="";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            str = new BigInteger(1,messageDigest.digest()).toString(16);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            str=password;
        }


        return str;
    }
}
