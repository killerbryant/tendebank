package tw.com.tendebank.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 常用工具
 * @author Edison
 *
 */
public class CommonUtils {

	/**
	 * 清除特殊字符
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	@SuppressWarnings("unused")
	private static String stringFilter(String str) throws PatternSyntaxException {
		// 字母和數字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static final Pattern TWPID_PATTERN = Pattern.compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");

	/**
	 * 身分證字號檢查程式，身分證字號規則： 字母(ABCDEFGHJKLMNPQRSTUVXYWZIO)對應一組數(10~35)，
	 * 令其十位數為X1，個位數為X2；( 如Ａ：X1=1 , X2=0 )；D表示2~9數字 Y = X1 + 9*X2 + 8*D1 + 7*D2 +
	 * 6*D3 + 5*D4 + 4*D5 + 3*D6 + 2*D7+ 1*D8 + D9 如Y能被10整除，則表示該身分證號碼為正確，否則為錯誤。
	 * 臺北市(A)、臺中市(B)、基隆市(C)、臺南市(D)、高雄市(E)、臺北縣(F)、
	 * 宜蘭縣(G)、桃園縣(H)、嘉義市(I)、新竹縣(J)、苗栗縣(K)、臺中縣(L)、
	 * 南投縣(M)、彰化縣(N)、新竹市(O)、雲林縣(P)、嘉義縣(Q)、臺南縣(R)、
	 * 高雄縣(S)、屏東縣(T)、花蓮縣(U)、臺東縣(V)、金門縣(W)、澎湖縣(X)、 陽明山(Y)、連江縣(Z)
	 * 
	 * @since 2015/08/17
	 */
	public static boolean isValidTWPID(String twpid) {
		boolean result = false;
		String pattern = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		if (TWPID_PATTERN.matcher(twpid.toUpperCase()).matches()) {
			int code = pattern.indexOf(twpid.toUpperCase().charAt(0)) + 10;
			System.out.println("code = " + code);
			int sum = 0;
			sum = (int)(code / 10) + 9 * (code % 10) + 8 * (twpid.charAt(1) - '0')
			          + 7 * (twpid.charAt(2) - '0') + 6 * (twpid.charAt(3) - '0')
			          + 5 * (twpid.charAt(4) - '0') + 4 * (twpid.charAt(5) - '0')
			          + 3 * (twpid.charAt(6) - '0') + 2 * (twpid.charAt(7) - '0')
			          + 1 * (twpid.charAt(8) - '0') + (twpid.charAt(9) - '0');
			if ((sum % 10) == 0) {
				result = true;
			}
		}
		return result;
	}
	
	/** 
	 * 不足碼補0
	 * 
	 */
	public static String addZeroForStr(String str, int strLength) {
	    int strLen = str.length();
	    if (strLen < strLength) {
	        while (strLen < strLength) {
	            StringBuffer sb = new StringBuffer();
	            sb.append("0").append(str);// 左補0
	            // sb.append(str).append("0");//右補0
	            str = sb.toString();
	            strLen = str.length();
	        }
	    }
	    return str;
	}
	
	/** 
	 * 產生隨機字串 
	 */
	public static String randomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	public static void main(String[] args) throws IOException {
//		System.out.println(isValidTWPID("A121777777"));
//		System.out.println(isValidTWPID("F227474299"));
//		System.out.println(isValidTWPID("F227474299"));
//		System.out.println(isValidTWPID(""));
//		System.out.println(addZeroForNum("123456",10));
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'hh:mm:ss");
        Date newDate = null;
		try {
			newDate = sdf.parse("2015-01-10T11:39:31-05:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("newDate = " + sdf.format(newDate));*/
		
        /*String wrkExpYearDesc = null;
        
        StringBuffer sb = new StringBuffer();
        sb.append(wrkExpYearDesc);
        System.out.println("123"+sb.toString());*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'hh:mm:ss");
        Date newDate = null;
		try {
			newDate = sdf.parse("2015-02-04T03:03:49-05:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(newDate));
		
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String monthAndDate = String.valueOf(sdf.format(date));
        System.out.println("monthAndDate = " + monthAndDate);*/
		/*
		 * String year = stringFilter("10~50 years"); System.out.println(year);
		 * File f = new File("D:/10~50.pdf"); if(f.createNewFile()){
		 * System.out.println("建立檔案成功"); }else{ System.out.println("檔案已存在"); }
		 * if(f.exists()){ System.out.println("準備刪除"); f.delete(); }
		 */
		// String date = "2014/2/17~2015/1/30";
		/*try {
			String date = "入伍時間 2010年11月30日退伍時間 2011年10月31日";
			System.out.println(date.split("/")[0]);
			System.out.println(date.split("/")[1]);
			System.out.println(date.split("/")[2].split("~")[1]);
			System.out.println(date.split("/")[3]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}*/

		/*
		 * List<String> strList = new ArrayList<String>(); strList.add("5");
		 * strList.add("Edison"); strList.add("6"); for (int i = 0; i <
		 * strList.size(); i++) { try{ int count =
		 * Integer.parseInt(strList.get(i)); System.out.println("Count = " +
		 * count); }catch(Exception e1){ System.out.println("Count = " + e1); }
		 * }
		 */
		/*
		 * String number = "文化傳播與科技 Communic ations Culture AND Tec hnology";
		 * System.out.println(number.length());
		 */
	}

}

