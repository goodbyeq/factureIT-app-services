package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Notification;

public class NotificationMapper implements RowMapper<Notification> {

	@Override
	public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
		Notification notification = new Notification();
		notification.setNotificationId(rs.getString("notification_id"));
		notification.setReceiver(rs.getString("receiver"));
		notification.setSender(rs.getString("sender"));
		notification.setMessage(rs.getString("message"));
		notification.setNotificationTimestamp(rs.getTimestamp("notification_timestamp"));
		return notification;
	}
}
