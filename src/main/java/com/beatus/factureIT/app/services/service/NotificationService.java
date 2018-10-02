package com.beatus.factureIT.app.services.service;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.helper.NotificationHelper;

@Service
@Component("customerService")
public class NotificationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
	
	public ResourceBundle loadProperties(String localeValue) {
		return  NotificationHelper.loadProperties(localeValue);

		}
	public String getProopertyValue(ResourceBundle bundle, String labelValue) {
		return bundle.getString(labelValue);
	}

}

