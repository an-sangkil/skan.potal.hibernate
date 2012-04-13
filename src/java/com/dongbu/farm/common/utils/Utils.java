package com.dongbu.farm.common.utils;


import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {
   public static String formatDouble(int value, String fd) { return formatDouble((double)value, fd); }
   public static String formatDouble(long value, String fd) { return formatDouble((double)value, fd); }
   public static String formatDouble(float value, String fd) { return formatDouble((double)value, fd); }
   public static String formatDouble(String value, String fd) {
      if (value == null || value.equals("")) return "";
      else return formatDouble(Double.parseDouble(value), fd);
   }
   public static String formatDouble(Object value, String fd) {
    if (value == null) return "";
    else return formatDouble(value.toString().trim(), fd);
   }

   public static String formatDouble(double value, String fd) {
      DecimalFormat formatDouble = null;
      if ("".equals(fd)) {
         formatDouble =new DecimalFormat("#,##0.000");
      } else {
         formatDouble =new DecimalFormat(fd);
      }
      return formatDouble.format(value);
   }


  /**
   * String stringValue = "1234567";
   * out.println(Utils.formatFloat(stringValue)); // ��°� = 1,234,567.00
   * double doubleValue = 12345.78d;
   * out.println(Utils.formatFloat(doubleValue)); // ��°� = 12,345.78
   * @param value
   * @return
   */
   public static String formatFloat(int value) { return formatFloat((double)value); }
   public static String formatFloat(long value) { return formatFloat((double)value); }
   public static String formatFloat(float value) { return formatFloat((double)value); }
   public static String formatFloat(Object value) {
      if (value == null) return "0";
      else return formatFloat(value.toString().trim());
   }

   public static String formatFloat(String value) {
      if (value == null || value.equals("")) return "0";
      else return formatFloat(Double.parseDouble(value.trim()));
   }

   public static String formatFloat(double value) {
      return formatFloat(value, 2);
   }
   
   /**
    * String stringValue = "1234567";
    * out.println(Utils.formatFloat(stringValue),2); // ��°� = 1,234,567
    * double doubleValue = 12345.783d;
    * out.println(Utils.formatFloat(doubleValue),1); // ��°� = 12,345.7
    * @param value
    * @param d
    * @return
    */
   public static String formatFloat(int value, int d) { return formatFloat((double)value, d); }
   public static String formatFloat(long value, int d) { return formatFloat((double)value, d); }
   public static String formatFloat(float value, int d) { return formatFloat((double)value, d); }

   public static String formatFloat(Object value, int d) {
      if (value == null) return "0";
      else return formatFloat(value.toString().trim(), d);
   }

   public static String formatFloat(String value, int d) {
      if (value == null || value.equals("")) return "0";
      else return formatFloat(Double.parseDouble(value.trim()), d);
   }

   public static String formatFloat(double value, int d) {
      StringBuffer f = new StringBuffer("#,##0");
      for (int i=0; i<d; i++) {
        if (i==0) f.append(".0");
        else f.append('0');
      }
      DecimalFormat formatFloat =new DecimalFormat(f.toString());
      return formatFloat.format(value);
   }

  /**
   * String stringValue = "1234567";
   * out.println(Utils.formatInt(stringValue));  // ��°� = 1,234,567
   * double doubleValue = 12345.78d;
   * out.println(Utils.formatInt(doubleValue)); // ��°� = 12,345
   * @param value
   * @return
   */
   public static String formatInt(long value) { return formatInt((int)value); }
   public static String formatInt(float value) { return formatInt((int)value); }
   public static String formatInt(double value) { return formatInt((int)value); }
   public static String formatInt(Object value) {
    if (value == null) return "0";
    else return formatInt(value.toString().trim());
   }

   public static String formatInt(String value) {
      if (value == null || value.equals("")) return "0";
      else return formatInt(Integer.parseInt(value));
   }

   public static String formatInt(int value) {
      DecimalFormat formatInt=new DecimalFormat("#,###");
      return formatInt.format(value);
   }

   /**
    * @param src
    * @param stripChar
    * stripChar�� �ش��ϴ� ���ڸ� src���� ã�Ƽ� ������ String�� return
    * @return
    */
   public static String stripCharacter(String src, char stripChar) {
      return stripCharacter(src,String.valueOf(stripChar));
   }

   public static String stripCharacter(String src, String stripChar) {

      for(int i=0;i<stripChar.length();i++) {
         String s = stripChar.substring(i,i+1);
         src = replace(src,s,"");
      }

      return src;
   }

   /**
    * @param String str
    * @return String
    */
   public static String strValue(String str) {
      if (str == null)	return "";
      else return str;
   }

   /**
    * @param String str
    * @return int
    */
   public static int intValue(String str) {
      if (str == null || str.equals(""))	return 0;
      try {
         if (str.indexOf(".") >= 0)
            return (int)Float.parseFloat(str);
         else
            return Integer.parseInt(str);
      } catch (Exception e) {
         return 0;
      }
   }

   /**
    * @param String str, int ret
    * @return int
    */
   public static int intValue(String str, int ret) {
      if (str == null || str.equals(""))	return ret;
      try {
         if (str.indexOf(".") >= 0)
            return (int)Float.parseFloat(str);
         else
            return Integer.parseInt(str);
      } catch (Exception e) {
         return ret;
      }
   }

   /**
    * @param String str
    * @return long
    */
   public static long longValue(String str) {
      if (str == null || str.equals(""))	return 0;
      try {
         if (str.indexOf(".") >= 0)
            return (long)Double.parseDouble(str);
         else
            return Long.parseLong(str);
      } catch (Exception e) {
         return 0;
      }
   }

   /**
    * @param String str
    * @return double
    */
   public static double doubleValue(String str) {
      if (str == null || str.equals(""))	return 0;
      try {
         return Double.parseDouble(str);
      } catch (Exception e) {
         return 0;
      }
   }

   /**
    * @param String str, double
    * @return double
    */
   public static double doubleValue(String str, double ret) {
      if (str == null || str.equals(""))	return ret;
      try {
         return Double.parseDouble(str);
      } catch (Exception e) {
         return ret;
      }
   }

   /**
    * @param String uri
    * uri���� ���ϸ? ��ȯ�Ѵ�
    * ��)getFilename("http://ESH/doc/test.jsp") -> "test"
    */
   public static String getFilename (String uri) {
      int i= uri.lastIndexOf("/")+1;
      int j= uri.lastIndexOf(".");
      if (i == 1||i > j)
         return uri.substring(i);
      else
         return uri.substring(i,j);
   }

   /**
    * @param double d
    * �Ǽ��� ��ȯ (1->1, 1.0-> 1, 1.1->2, 1.9->2)
    * ��) up(1.23) -> 2
    */
   public static int up (double d) {
      int i = (int)d;
      return (d > (double)i) ? i + 1 : i;
   }

   /**
    * @param Object o
    * @param String str
    * @return Object
    * null�� Object�� Ư�������� ��ȯ�ؼ� ��ȯ
    * ��) nvl(null,"").toString() -> ""
    */
   public static Object nvl(Object o, String str) {
      if (o==null) return (Object)str;
      return o;
   }

   /**
    * @param String temp
    * @param String str
    * @return String
    * null�� String�� Ư�������� ��ȯ�ؼ� ��ȯ
    * ��) nvl(null,"") -> ""
    */
   public static String nvl(String temp, String str) {
      if (temp==null) return str;
      return temp;
   }

   /**
    * @param mainString
    * @param oldString
    * @param newString
    * @return String
    * ���ڿ������� Ư�� ���ڿ��� �ٸ� ���ڿ��� ��ȯ�ؼ� ��ȯ
    * ��) replace("abcdef","cde","CDE") -> "abCDEf"
    */
   public static String replace(
         String mainString,
         String oldString,
         String newString) {
      if (mainString == null) {
         return null;
      }
      if (oldString == null || oldString.length() == 0) {
         return mainString;
      }
      if (newString == null) {
         newString = "";
      }

      int i = mainString.lastIndexOf(oldString);
      if (i < 0)
         return mainString;

      StringBuffer mainSb = new StringBuffer(mainString);

      while (i >= 0) {
         mainSb.replace(i, (i + oldString.length()), newString);
         i = mainString.lastIndexOf(oldString, i - 1);
      }
      return mainSb.toString();
   }



   /**
    * @param String src
    * @param String trgt
    * @param int icnt
    * @return String
    * ���ڿ������� Ư������ ������ ���ڿ��� ã��
    * ��) cfind("abc-def-gh", "-", 1) -> "abc"
    * ��) cfind("abc-def-gh", "-", 2) -> "def"
    * ��) cfind("abc-def-gh", "-", 3) -> "gh"
    * (��, Ư�����ڴ� ��� �����ؾ� ��)
    */
   public static String cfind(String src, String trgt, int icnt) {
      int pos = 0;
      String temp = "";
      src = nvl(src,"");

      for ( int i = 1; i <= icnt ; ++i){
         pos = src.indexOf(trgt);

         if ( pos < 0 ) {
            temp = src;
         }
         else {
            temp = src.substring(0, pos);
            src = src.substring(pos + trgt.length() , src.length());
         }
      }

      return temp;
   }

   /**
    * @param String token
    * @param String strings
    * @return String
    * join�� split�� �ݴ밳���̴�
    */
   public static String join( String token, String[] strings ) {
      StringBuffer sb = new StringBuffer();

      for( int x = 0; x < ( strings.length - 1 ); x++ ) {
         sb.append( strings[x] );
         sb.append( token );
      }
      sb.append( strings[ strings.length - 1 ] );

      return( sb.toString() );
   }
   /**
    * �ð��� �ùٸ��� Ȯ���Ѵ�.
    * 
    * @param token : �Է��� �ð�.
    * @return �Էõ� �ùٸ� �ð� ����
    */
   @SuppressWarnings("deprecation")
public static String timeCheck(String token){
	   String input = token.replaceAll(":", "");
	   String inputHours = input.substring(0, 2);
	   String inputMinutes = input.substring(2, 2);
	Date date = new Date(0 , 0 , 0 , Integer.parseInt(inputHours) , Integer.parseInt(inputMinutes) , 0);
	   String result = "";
	   if(date.getHours() != Integer.parseInt(inputHours) || 
			   date.getMinutes() != Integer.parseInt(inputMinutes)){
	   } else {
		   result = inputHours + ":" + inputMinutes;
	   }
	   return result;
   }
   
    /**
     * web -> Servlet ���� parameter �ѱ�� �ѱ� ����, �ٽ� euc-kr�� ���ڵ� 
     * @param str
     * @return
     */
    public static String toKSC5601(String str){
    	if(str==null){
    		return null;
    	}
    	try{
    		return new String(str.getBytes("8859_1"),"KSC5601");
    	}catch(Exception e){
    		e.printStackTrace();
    		return str;
    	}
    	
    }
    
    /**
     * utf-8 인코더
     * @param str
     * @return
     */
    public static String encoding(String str){
    	if(str == null){
    		return null;
    	}
    	try {
    		return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
    }
    
    /**
     * utf-8 디코더
     * @param str
     * @return
     */
    public static String decoding(String str){
    	if(str == null){
    		return null;
    	}
    	try {
    		return URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
    }
    
    /**
	 * 10보다 작은 숫자는 앞에 0을 붙여준다. 단 -Number 인경우는 제외
	 * @param dayParameter
	 * @return
	 */
	public static String numberType(int number){
		
		String returnStr = "";
		
		try {
			if(number > 0 && number < 10){
				returnStr = "0" + String.valueOf(number);
			}else {
				returnStr = String.valueOf(number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnStr;
	}
	
	/**
	 * month 값이 -1 인경우와 12 인경우 년도와 월의 값 재셋팅
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> govtMove(int year, int month) throws Exception{
		
		String yearStr = "";
		String monthStr = "";
		
		Map<String,String> getMoveMap = new HashMap<String,String>();
		
		if (month == 12) {
			year++;
			month = 0;
			
			yearStr = String.valueOf(year);
			monthStr = String.valueOf(month);
		} else if (month < 0) {
			year--;
			month = 11;
			
			yearStr = String.valueOf(year);
			monthStr = String.valueOf(month);
		} else {
			
			yearStr = String.valueOf(year);
			monthStr = String.valueOf(month);
		}
		
		getMoveMap.put("yearStr", yearStr);
		getMoveMap.put("monthStr", monthStr);
		
		return getMoveMap;
	}
    
    /**
     * RANDOM UUID Create
     * @return
     */
    public static String getRandomUUID(){
    	return UUID.randomUUID().toString();
    }
    
    
    
    
}
