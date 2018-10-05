package com.beatus.factureIT.app.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.factureIT.app.services.model.Notification;
import com.beatus.factureIT.app.services.model.NoticationResponse;
import com.beatus.factureIT.app.services.model.JSendResponse;
import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.model.ProductCategory;
import com.beatus.factureIT.app.services.model.Retailer;
import com.beatus.factureIT.app.services.service.NotificationService;
import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_NOTIFICATION_REQUEST)
public class NotificationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

	@Resource(name = "notificationService")
	private NotificationService notificationService;

	public static JSendResponse<NoticationResponse> jsend(NoticationResponse noticationResponse) {
		if (noticationResponse == null || noticationResponse.getNotifications() == null
				|| noticationResponse.getNotifications().get(0) == null) {
			return new JSendResponse<NoticationResponse>(Constants.FAILURE, noticationResponse);
		} else {
			return new JSendResponse<NoticationResponse>(Constants.SUCCESS, noticationResponse);
		}
	}
	
	public static JSendResponse<Notification> jsend(Notification notification) {
		if (notification == null || notification == null) {
			return new JSendResponse<Notification>(Constants.FAILURE, notification);
		} else {
			return new JSendResponse<Notification>(Constants.SUCCESS, notification);
		}
	}
	
	public static JSendResponse<String> jsend(boolean response) {
		if (response) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<String> jsend(String response) {
		if (response != null) {
			return new JSendResponse<String>(Constants.SUCCESS, "Request processed Successfully");
		} else {
			return new JSendResponse<String>(Constants.FAILURE, "Request Processing failed");
		}
	}
	
	public static JSendResponse<List<?>> jsend(List<?> response) {
		if (response == null || response == null) {
			return new JSendResponse<List<?>>(Constants.FAILURE, response);
		} else {
			return new JSendResponse<List<?>>(Constants.SUCCESS, response);
		}
	}

/*	@RequestMapping(value = Constants.WEB_NOTIFICATION_ADD_NOTIFICATION, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<String> addNotificationPost(HttpServletRequest request,
			ModelMap model, @RequestParam Notification notification) throws ClassNotFoundException, SQLException {
		String id = notificationService.addNotification(notification);
		LOGGER.info("After the add call and the notification " + notification != null
				? notification.getNotificationId() : "No notification data" + " got added successfully");
		return jsend(id);
	}*/

	@RequestMapping(value = Constants.WEB_NOTIFICATION_GET_NOTIFICATION_SENDER, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getNotificationBySenderGet(HttpServletRequest request,
			ModelMap model, @RequestParam String senderId, @RequestParam String locale) throws ClassNotFoundException, SQLException {
		List<Notification> notifications = notificationService.getNotificationBySender(senderId, locale);
		LOGGER.info("After the get call and the notification is " + notifications != null
				? " Notification List" : "No Notification data");
		return jsend(notifications);
	}
	
	@RequestMapping(value = Constants.WEB_NOTIFICATION_GET_NOTIFICATION_RECEIVER, method = RequestMethod.GET)
	public @ResponseBody JSendResponse<List<?>> getNotificationByReceiverGet(HttpServletRequest request,
			ModelMap model, @RequestParam String receiverId, @RequestParam String locale) throws ClassNotFoundException, SQLException {
		List<Notification> notifications = notificationService.getNotificationByReceiver(receiverId, locale);
		LOGGER.info("After the get call and the notification is " + notifications != null
				? " Notification List" : "No Notification data");
		return jsend(notifications);
	}
	
	
}
