package com.beatus.factureIT.app.services.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.beatus.factureIT.app.services.model.Notification;

public class NotificationHelper {
	
	public static ResourceBundle loadProperties(String localeValue) {		
		Locale locale = new Locale(localeValue);
		ResourceBundle bundle1 = ResourceBundle.getBundle("TestBundle", locale);		
		return bundle1;
	}

	public static List<Notification> getMessagesForNotifications(ResourceBundle bundle,
			List<Notification> notifications) {
		List<Notification> modifiedWithMessageValue = new ArrayList<Notification>();
		if(notifications != null && notifications.size() > 0) {
			for(Notification notif : notifications) {
				String message = bundle.getString(notif.getMessage());
				notif.setMessage(message);
				modifiedWithMessageValue.add(notif);
			}
		}
		return modifiedWithMessageValue;
	}
}
