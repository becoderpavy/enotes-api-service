package com.becoder.util;

public class Constants {

	public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String MOBNO_REGEX = "^[7-9][0-9]{9}$";

	public static final String ROLE_ADMIN="hasRole('ADMIN')";
	public static final String ROLE_ADMIN_USER="hasAnyRole('USER','ADMIN')";
	public static final String ROLE_USER="hasRole('USER')";
	
	public static final String DEFAULT_PAGE_NO="0";
	public static final String DEFAULT_PAGE_SIZE="10";
	
	
}
