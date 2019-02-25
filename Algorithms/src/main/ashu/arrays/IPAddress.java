package main.ashu.arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class IPAddress {
	  private static Pattern VALID_IPV4_PATTERN = null;
	  private static Pattern VALID_IPV6_PATTERN = null;
	  private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
	  private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

	  static {
	    try {
	      VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
	      VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
	    } catch (PatternSyntaxException e) {
	      //logger.severe("Unable to compile pattern", e);
	    }
	  }

	  public static boolean isIpAddress(String ipAddress) {

	    Matcher m1 = IPAddress.VALID_IPV4_PATTERN.matcher(ipAddress);
	    if (m1.matches()) {
	      return true;
	    }
	    Matcher m2 = IPAddress.VALID_IPV6_PATTERN.matcher(ipAddress);
	    return m2.matches();
	  }
	  
	  public static void main(String args []) {
		  String s = "2001:0db8:85a3:0:0:8A2E:0370:7334";
		  IPAddress ut = new IPAddress();
		  boolean ans = ut.isIpAddress(s);
		  System.out.println(ans);
	  }


}
