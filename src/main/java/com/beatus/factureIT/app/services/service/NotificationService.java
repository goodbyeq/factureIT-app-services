package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.helper.NotificationHelper;
import com.beatus.factureIT.app.services.model.Customer;
import com.beatus.factureIT.app.services.model.Notification;
import com.beatus.factureIT.app.services.model.User;
import com.beatus.factureIT.app.services.repository.NotificationRepository;

@Service
@Component("notificationService")
public class NotificationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
	
	@Resource(name = "notificationRepository")
	private NotificationRepository notificationRepository;
	
	public ResourceBundle loadProperties(String localeValue) {
		return  NotificationHelper.loadProperties(localeValue);

		}
	public String getProopertyValue(ResourceBundle bundle, String labelValue) {
		return bundle.getString(labelValue);
	}
	
	public String addNotification(List<Notification> notifications) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addNotification");
		String response = notificationRepository.addNotification(notifications);
		return response;
	}
	
	public List<Notification> getNotificationBySender(String sender, String locale) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getNotificationBySender");
		List<Notification> notifications = notificationRepository.getNotificationBySender(sender);
		ResourceBundle bundle = NotificationHelper.loadProperties(locale);
		notifications = NotificationHelper.getMessagesForNotifications(bundle, notifications);
		return notifications;
	}
	
	public List<Notification> getNotificationByReceiver(String receiver, String locale) throws ClassNotFoundException, SQLException {
		LOGGER.info("In getNotificationByReceiver");
		List<Notification> notifications = notificationRepository.getNotificationByReceiver(receiver);
		ResourceBundle bundle = NotificationHelper.loadProperties(locale);
		notifications = NotificationHelper.getMessagesForNotifications(bundle, notifications);
		return notifications;
	}

}

