package com.team.app.constant;

/**
 * 
 * @author Vikky
 *
 */
public class AppConstants {

	// REST API Mapping
	public static final String CONSUMER_API = "rest/consumer";
	public static final String ADMIN_API = "rest/admin";
	
	
	// Indicator
	public static final String IND_Y = "Y";
	public static final String IND_N = "N";
	public static final String IND_A = "A";
	public static final String IND_D = "D";
	public static final String LED_ON = "ON";
	public static final String LED_OFF = "OFF";
	
	// App key Config
	public static final String HTTP_HEADER_JWT_TOKEN = "jwt";
	
	public static final String HTTP_HEADER_TOKEN_NAME = "X-ACCESS-TOKEN";
	public static final String HTTP_HEADER_BASE_TOKEN_NAME = "X-BASE-TOKEN";
		
	public static final String TTL_LOGIN_KEY = "app.token.login.ttlmillis";
	
	public static final String KEY_UNIZEN_MOBILE = "UNIZEN_MOBILE";
	public static final String KEY_UNIZEN_THRIDPARTY = "UNIZEN_THIRDPARTY";
	public static final String KEY_UNIZEN_MOBILE_VAL = "TrustSecret";
		
	public static final String SUBJECT_SECURE = "MOBILE_SECURE";
	public static final String SUBJECT_NON_SECURE = "MOBILE_NON_SECURE";
	public static final String TOKEN_LOGN_ID = "MOBILE_LOGIN";
	
	public static final String Organisation = "Unizen";
	
	public static final String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJsb3JhLWFwcC1zZXJ2ZXIiLCJhdWQiOiJsb3JhLWFwcC1zZXJ2ZXIiLCJuYmYiOjE1MDk5NjE1NzIsInN1YiI6InVzZXIiLCJ1c2VybmFtZSI6ImFkbWluIn0.NDZGFGPDQNs7AgmGRzQk1WL5Y1tLjyRbw-n_TwHPZsY";
	public static final String domain ="https://139.59.14.31:8080";
	public static final String appURL ="http://139.59.14.31:8081/USWMDEMO";
	public static final String org = domain+"/api/organizations/";
	public static final String org_url = domain+"/api/organizations?limit=500";
	public static final String app_url =domain+"/api/applications?limit=500";
	public static final String user_url=domain+"/api/users?limit=500";
	public static final String status="Y";
	
	public static final String statusLog="Failed";
	public static final String noportdomain ="139.59.14.31";
	
	public static final String timeZone="Asia/Kolkata";
	
	public static final String superAdmin="su";
	public static final String admin="admin";
	public static final String user="usr";
	
	// App token Config
	
	public static final String DAYS = "DAYS";
	public static final String HOURS = "HOURS";
	public static final String MINUTES = "MINUTES";
	public static final long TIME = 15;
	
	public static final String orgName = "organisation";
	public static final String appName = "application";
	public static final String devName = "device";
	
	
	/*Device Downlink Command*/
	public static final String MPDU = "32";
	public static final String date = "64";
	public static final String retry = "48";
	public static final int missing = 1;
	
	
	
	/*Payment Configuration*/
	
	public static final String merchant_key = "gtKFFx";
	public static final String salt = "eCwWELxi";
	//public static final String base_url = "https://sandboxsecure.payu.in";
	public static final String base_url = "https://test.payu.in";
	public static final String surl = appURL+"/paymentStatus";
	public static final String furl = appURL+"/paymentFailure";
	public static final String txnMode = "Web";
	
	
	
	//Feedback status
	
	public static final String open = "Open";
	public static final String close = "Close";
	public static final String pending = "Pending";
	public static final String reopen = "ReOpen";
	
	
	
	
}
