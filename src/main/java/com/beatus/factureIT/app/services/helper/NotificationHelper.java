package com.beatus.factureIT.app.services.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotificationHelper {
	
	public static ResourceBundle loadProperties(String localeValue) {
		
		Locale locale = new Locale(localeValue);
		ResourceBundle bundle1 = ResourceBundle.getBundle("TestBundle", locale);
		
		return bundle1;
	}

}
