/*
 * COPPE / UFRJ - Universidade Federal do Rio de Janeiro
 * PESC - Programa de Engenharia de Sistemas e Computacao
 *
 * Disciplina: CPS730 - Internet das Coisas
 * Professor: Claudio Miceli
 *
 * Trabalho Final - Emergency Sound Detection
 * Data: 25/AGO/2022
 *
 * Nome: Luiz Marcio Faria de Aquino Viana, M.Sc.
 * Formacao: Engenheiro Eletricista com Enfase em Engenharia de Sistemas e Computação
 * DRE: 120048833
 * CPF: 024.723.347-10
 * RG: 08855128-8 IFP-RJ
 * Registro: 2000103581 CREA-RJ
 * E-mail: luiz.marcio.viana@gmail.com
 *         lmarcio@cos.ufrj.br
 * Tel.: +55-21-99983-7207
 *
 * StringUtil.java
 */

package br.com.tlmv.emsdapp.utils;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.Normalizer;

import br.com.tlmv.emsdapp.AppDefs;

public class StringUtil 
{
//Public

	public static String replaceAll(String str, String oldStr, String newStr)
	{
		String result = str.replace(oldStr, newStr);
		return result;
	}
	
	public static String stripAccents(String s, boolean bToUpperCase) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    if( bToUpperCase )
	    	s = s.toUpperCase();
	    return s;
	}	
	
	public static String removeLines(String src)
	{
    	String dst = src;  
        if( (src != null) && ( !"".equals(src) ) ) { 
        	dst = dst.replaceAll("\\r", "");  
        	dst = dst.replaceAll("\\n", "");  
        }
        return dst;
	}
	
	public static String substr(String src, int maxlen)
	{
		if( maxlen > src.length() )
			return src;
		String dst = src.substring(0, maxlen);
		return dst;
	}
	
	public static String getOnlyValidFileNameChars(String inStr)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < inStr.length(); i++) {
			char c = inStr.charAt(i);
			if( ( (c >= '0')&&(c <= '9') ) || 
				( (c >= 'A')&&(c <= 'Z') ) || 
				( (c >= 'a')&&(c <= 'z') ) || 
				(c == '_') || 
				(c == '-') || 
				(c == '[') || 
				(c == ']') )
				sb.append(c);
			else
				sb.append('_');				
		}		
		return sb.toString();
	}
	
	public static boolean isEmpty(String s)
	{
		return ( (s == null) || ( "".equals(s) ) );
	}
	
	public static String getHead(String s, char c)
	{
		StringBuffer result = new StringBuffer();
		int pos = 0;
		while(pos < s.length()) {
			char ch = s.charAt(pos);
			if(ch == c) break;
			result.append(ch);
			pos++;
		}
		return result.toString();
	}

	public static String getTail(String s, char c)
	{
		int pos = 0;
		while(pos < s.length()) {
			char ch = s.charAt(pos);
			if(ch == c) 
				return s.substring(pos);
			pos++;
		}
		return "";
	}

	public static String trimAll(String str)
	{		
		return str.trim();
	}
	
	public static String getOnlyNumbers(String s)
	{
		StringBuilder result = new StringBuilder();
		if(s != null) {
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if( (c >= '0') && (c <= '9') )
					result.append(c);
			}
		}
		return result.toString();
	}
	
	public static String getOnlyNumbers(String s, char[] spetialChars)
	{
		StringBuilder result = new StringBuilder();
		if(s != null) {
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if( (c >= '0') && (c <= '9') ) {
					result.append(c);
				}
				else {
					for(char cX : spetialChars) {
						if(c == cX)
							result.append(c);							
					}
				}
			}
		}
		return result.toString();
	}

	public static String[] split(String s, char c)
	{
		ArrayList<String> result = new ArrayList<String>();
		String parseString = new String(s);
		boolean test = (parseString.length() > 0);
		while( test ) {
			String tk = StringUtil.getHead(parseString, c);
			result.add(tk);
			parseString = StringUtil.getTail(parseString, c);
			if(parseString.length() > 0)
				parseString = parseString.substring(1);
			else
				test = false;
		}
		return result.toArray(new String[result.size()]);						
	}

	public static String arrayToString(String arr[])
	{
		StringBuffer sbuf = new StringBuffer();
		for(int i = 0; i < arr.length; i++) {
			if(sbuf.length() == 0)
				sbuf.append(arr[i]);
			else
				sbuf.append(",").append(arr[i]);
		}
		return sbuf.toString();
	}

	public static int arrayFind(String arr[], String s)
	{
		for(int i = 0; i < arr.length; i++) {
			if( s.equals(arr[i]) )
				return i;
		}
		return -1;
	}
	
	public static String fillLeft(String str, int len, char c)
	{
		StringBuilder result = new StringBuilder();
		result.append(str);
		while(result.length() < len)
			result.insert(0, c);
		return result.toString();
	}

	public static String fillRight(String str, int len, char c)
	{
		StringBuilder result = new StringBuilder();
		result.append(str);
		while(result.length() < len)
			result.append(c);
		return result.toString();
	}

	// FORMAT_FUNCTIONS
	
	public static String timeFormat(Double hourWithFrac)
	{
		double hour = Math.floor(hourWithFrac);
		double min = Math.floor((hourWithFrac - hour) * 60);
		return String.format("%02.0f:%02.0f", hour, min);
	}

	// PARSE_FUNCTIONS
	
	public static Double parseTime(String timeHourMin)
	{
		String[] arr = StringUtil.split(timeHourMin, ':');

		double hour = 0.0;
		double min = 0.0;

		if(arr.length > 1) {
			try {
				hour = Double.parseDouble(arr[0]);
			}
			catch(Exception e) { }

			try {
				min = Double.parseDouble(arr[1]);
			}
			catch(Exception e) { }
		}
		
		double result = (hour + min / 60.0);		
		return result;
	}
	
    public static Date parseDate(String s, String fmt)
    {
		DateFormat df = new SimpleDateFormat(fmt);
		
		Date d = null;			
		try {
			d = df.parse(s);
		}
		catch(Exception e) {
			
		}
		return d;
    }
    
    public static int parseInt(String s)
    {
		Integer i = 0;
		try {
			i = Integer.parseInt(s); 
		}
		catch(Exception e)
		{
			
		}
		return i;
    }
	
    public static double parseDouble(NumberFormat nf, String s)
    {
		Double d = 0.0;
		try {
			d = nf.parse(s).doubleValue(); 
		}
		catch(Exception e)
		{
			
		}
		return d;
    }
	
	// SAFE_FUNCTIONS
	
	public static String safeStr(String s)
	{
		if(s != null)
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if(ch > 255) 
					ch += ' ';
				sb.append(ch);
			}
			return sb.toString();
		}
		return "";
	}
	
	public static Date safeDate(DateFormat df, String strIn)
	{
		Date result = new Date(1900, 0, 1);
		
		try {
			result = df.parse(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static double safeDbl(NumberFormat nf, String strIn)
	{
		double result = 0.0;
		
		try {
			result = nf.parse(strIn).doubleValue();
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static int safeInt(String strIn)
	{
		int result = 0;
		
		try {
			result = Integer.parseInt(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static long safeLng(String strIn)
	{
		long result = 0;
		
		try {
			result = Long.parseLong(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
		
	public static String safeStr2(String s)
	{
		if(s != null)
			return s;
		return "";
	}
	
	// FORMAT_FIELD_FUNCTIONS
	
	public static String strField(String str, int len)
	{
		StringBuilder result = new StringBuilder();
		
		str = str.toUpperCase();		
		for(int i = 0; i < str.length(); i++) {
			if(i >= len) break;
			
			char c_src = str.charAt(i);
			if( ( (c_src >= '0') && (c_src <= '9') ) ||
				( (c_src >= 'A') && (c_src <= 'Z') ) )
				result.append(c_src);
			else
				result.append(' ');
		}

		while(result.length() < len) {
			result.append(' ');
		}
		return result.toString();
	}
	
	public static String valField(String str, int len)
	{
		while(str.length() < len)
			str = "0" + str;
		return str;
	}
	
	public static String valField(Double val, int len, int dec)
	{
		long val_res = (long)Math.floor(val * Math.pow(10.0, dec)); 		
		String str = Long.toString(val_res);
		while(str.length() < len)
			str = "0" + str;
		return str;
	}
	
	public static String valField(Long val, int len)
	{
		String str = Long.toString(val);
		while(str.length() < len)
			str = "0" + str;
		return str;
	}
		
	public static String valField(Integer val, int len)
	{
		String str = Integer.toString(val);
		while(str.length() < len)
			str = "0" + str;
		return str;
	}

	// HASH_FUNCTIONS
	
	public static String strToMD5(String str)
    {
    	String result = "";
    	
    	try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(str.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}	    	
	    	result = hexString.toString();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
	
	// STRING_CONVERSION

	public static String valToStr(Date val)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat hf = new SimpleDateFormat("HH:mm:ss");

		if(val == null)
			return "";
		return df.format(val) + "T" + hf.format(val);
	}

	public static String valToStr(DateFormat df, Date val)
	{
		if(val == null)
			return "";
		return df.format(val);
	}
    
	public static String valToStr(Integer val)
	{
		if(val == null)
			return "";
		return Integer.toString(val);
	}
    
	public static String valToStr(Integer val, Integer len)
	{
		String strVal = valToStr(val);
		return valToStr(strVal, len);
	}
    
	public static String valToStr(Long val)
	{
		if(val == null)
			return "";
		return Long.toString(val);
	}
    
	public static String valToStr(Long val, Integer len)
	{
		String strVal = valToStr(val);
		return valToStr(strVal, len);
	}
    
	public static String valToStr(String val, Integer len)
	{
		if(val == null)
			return "";
		return val.substring(0, Math.min(len, val.length()));
	}
    
	public static String valToStr(Double val, Integer prec)
	{
		if(val == null)
			return "";
		
		NumberFormat nf = FormatUtil.newNumberFormatPtBr(prec);
		nf.setGroupingUsed(false);
		return nf.format(val);
	}
    
	public static String valToStr(Double val, Integer len, Integer prec)
	{
		if(val == null)
			return "0";

		long lVal = (long)Math.floor(val * Math.pow(10.0, prec));		
		return valToStr(lVal, len);
	}
    
	public static String valToStr(NumberFormat nf, Double val)
	{
		if(val == null)
			return "";
		return nf.format(val);
	}
	
	// IFNULL_FUNCTIONS

	public static Double zDblIfNull(Double val)
	{
		if(val == null)
			return 0.0;
		return val;
	}
	
	public static Integer zIntIfNull(Integer val)
	{
		if(val == null)
			return 0;
		return val;
	}	
	
	public static Long zLongIfNull(Long val)
	{
		if(val == null)
			return 0L;
		return val;
	}	
	
	public static String ifNull(String str1, String str2)
	{
		if( (str1 == null) || ("".equals(str1)) )
			return str2;
		return str1;
	}	
		
}
