package llcweb.com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @author ouzhb
 */
public class StringUtil {

	/**
	 * 判断字符串是否为null、“ ”、“null”
	 * @param obj
	 * @return
	 */
	public static boolean isNull(String obj) {
		if (obj == null){
			return true;
		}else if (obj.trim().equals("")){ //String.tirm() 去除字符串首尾空格
			return true;
		}else if(obj.trim().toLowerCase().equals("null")){
			return true;
		}
		
		return false;
	}

	/**
	 * 正则验证是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
		Matcher match = pattern.matcher(str);
		
		return match.matches();
	}
    /** 
     * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端 
     *  
     * @param l 
     *            长整数 
     * @return 代表长整数的字节数组 
     */  
    public static byte[] longToBytes(long l) {  
        byte[] b = new byte[8];  
        b[0] = (byte) (l >>> 56);  
        b[1] = (byte) (l >>> 48);  
        b[2] = (byte) (l >>> 40);  
        b[3] = (byte) (l >>> 32);  
        b[4] = (byte) (l >>> 24);  
        b[5] = (byte) (l >>> 16);  
        b[6] = (byte) (l >>> 8);  
        b[7] = (byte) (l);  
        return b;  
    }
	/**
	 * 判断字符串是否为正整数纯数字，不为空
	 * @param str 长整数
	 * @return 代表长整数的字节数组
	 */
	public static boolean isPositiveInt(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}
	/**
	 * 正则验证用户名是否合格
	 * @param userName
	 * @return
	 */
	public static boolean isUserName(String userName) {
		Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5\\w]{1,}$");
		Matcher match = pattern.matcher(userName);

		return match.matches();
	}

	/**
	 * 正则验证密码是否合格
	 * @param password
	 * @return
	 */
	public static boolean isPassword(String password) {
		Pattern pattern = Pattern.compile("^\\w+$");
		Matcher match = pattern.matcher(password);

		return match.matches();
	}

}
