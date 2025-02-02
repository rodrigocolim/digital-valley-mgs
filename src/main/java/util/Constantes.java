package util;
import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constantes {

	private static String APP_URL;
	private static String APP_GUARDIAO_URL;
	private static String APP_ASSETS_URL;
	private static String APP_JS_URL;
	private static String APP_IMG_URL;
	private static String APP_CSS_URL;
	private static Integer NUMBER_OF_ROWS_PER_PAGE;
	private static String SESSION_MSG;
	private static String DATABASE_CONF_DIR;
	private static String PRE_URL;
	private static String LOGO_UFC;
	private static String LOGO_UFC_ICON;
	private static String LOGO_DARWIN;
	private static String LOGO_N2S;
	private static String DOCUMENTS_DIR;
	private static String TEMP_DIR;
	private Constantes() {
		//
	}

	static {
		Map<String, String> dotEnv = DotEnv.load();
		DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
		APP_URL = dotEnv.get("APP_URL");
		APP_GUARDIAO_URL = dotEnv.get("APP_GUARDIAO_URL");
		APP_ASSETS_URL = dotEnv.get("APP_ASSETS_URL");
		APP_JS_URL = dotEnv.get("APP_JS_URL");
		APP_IMG_URL = dotEnv.get("APP_IMG_URL");
		APP_CSS_URL = dotEnv.get("APP_CSS_URL");
		NUMBER_OF_ROWS_PER_PAGE = Integer.valueOf(dotEnv.get("NUMBER_OF_ROWS_PER_PAGE"));
		SESSION_MSG = dotEnv.get("SESSION_MSG");
		PRE_URL = dotEnv.get("PRE_URL");
		LOGO_UFC = dotEnv.get("LOGO_UFC");
		LOGO_UFC_ICON = dotEnv.get("LOGO_UFC_ICON");
		LOGO_DARWIN = dotEnv.get("LOGO_DARWIN");
		LOGO_N2S = dotEnv.get("LOGO_N2S");
		DOCUMENTS_DIR = dotEnv.get("DOCUMENTS_DIR");
		TEMP_DIR = dotEnv.get("TEMP_DIR");
	}

	/**
	 * @return the appUrl
	 */
	public static String getAppUrl() {
		return APP_URL;
	}
	
	/**
	 * @return the appUrl
	 */
	public static String getAppGuardiaoUrl() {
		return APP_GUARDIAO_URL;
	}
	
	
	/**
	 * @return the appUrl
	 */
	public static String getPreUrl() {
		return PRE_URL;
	}
	/**
	 * @return the appAssetsUrl
	 */
	public static String getAppAssetsUrl() {
		return APP_ASSETS_URL;
	}

	/**
	 * @return the appJsUrl
	 */
	public static String getAppJsUrl() {
		return APP_JS_URL;
	}

	/**
	 * @return the appImgUrl
	 */
	public static String getAppImgUrl() {
		return APP_IMG_URL;
	}

	/**
	 * @return the appCssUrl
	 */
	public static String getAppCssUrl() {
		return APP_CSS_URL;
	}

	/**
	 * @return the numberOfRowsPerPage
	 */
	public static Integer getNumberOfRowsPerPage() {
		return NUMBER_OF_ROWS_PER_PAGE;
	}

	/**
	 * @return the sessionMsg
	 */
	public static String getSessionMsg() {
		return SESSION_MSG;
	}

	/**
	 * @return the databaseConfDir
	 */
	public static String getDatabaseConfDir() {
		return DATABASE_CONF_DIR;
	}
	/**
	 * @return the logoUFC
	 */
	public static String getLOGO_UFC() {
		return LOGO_UFC;
	}
	
	public static String getLOGO_UFC_ICON() {
		return LOGO_UFC_ICON;
	}
	
	public static String getLOGO_DARWIN() {
		return LOGO_DARWIN;
	}
	
	public static String getLOGO_N2S() {
		return LOGO_N2S;
	}
	
	/**
	 * @return the documentsDir
	 */
	public static String getDocumentsDir() {
		return DOCUMENTS_DIR;
	}
	/**
	 * @return the TempsDir
	 */
	public static String getTemp() {
		return TEMP_DIR;
	}
	
}
