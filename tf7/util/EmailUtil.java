package com.tr.util;

public class EmailUtil {

	 public static String formatInterviewEmail(String candidateName, String jobTitle) {
	        return "Dear " + candidateName + ",\nYour interview for " + jobTitle + " is confirmed.";
	    }
	    
	    public static boolean isValidEmail(String email) {
	        return email.contains("@") && email.contains(".");
	    }
}
