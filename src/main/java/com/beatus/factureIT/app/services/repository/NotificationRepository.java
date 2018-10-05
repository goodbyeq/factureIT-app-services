package com.beatus.factureIT.app.services.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.beatus.factureIT.app.services.model.Notification;
import com.beatus.factureIT.app.services.model.SmsVO;
import com.beatus.factureIT.app.services.model.mapper.NotificationMapper;
import com.beatus.factureIT.app.services.model.mapper.SmsVOMapper;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Component("notificationRepository")
public class NotificationRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationRepository.class);

	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public NotificationRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String addNotification(List<Notification> notifications) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addNotification ");
		if (notifications != null && notifications.size() > 0) {
			try {
				String sql = "INSERT INTO notification (notification_id, sender, receiver, message, timestamp) VALUES (?,?,?,?,?)";
				int[] rowsInserted = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, Utils.generateRandomKey(50));
						ps.setString(2, notifications.get(i).getSender());
						ps.setString(3, notifications.get(i).getReceiver());
						ps.setString(4, notifications.get(i).getMessage());
					}

					@Override
					public int getBatchSize() {
						return notifications.size();
					}
				});
				if (rowsInserted.length > 0) {
					LOGGER.info("A new notification was inserted successfully!");
					return Constants.SUCCESS;
				} else {
					return Constants.FAILURE;
				}
			} finally {

			}
		}
		return Constants.FAILURE;
	}

	public List<Notification> getNotificationBySender(String sender) throws SQLException {
		LOGGER.info("In getNotificationBySender " + sender);
		try {
			String sql = "SELECT * FROM notification WHERE sender = ? and notification_timstamp > sysdate - numtodsinterval(5,'MINUTE')";
			List<Notification> notifications = jdbcTemplate.query(sql, new Object[] { sender },
					new NotificationMapper());
			LOGGER.info("The mailVOs returned " + notifications != null ? notifications.toString() : null);
			return notifications;
		} finally {

		}
	}

	public List<Notification> getNotificationByReceiver(String reveiver) throws SQLException {
		LOGGER.info("In getNotificationByReceiver " + reveiver);
		try {
			String sql = "SELECT * FROM notification WHERE sender = ? and notification_timstamp > sysdate - numtodsinterval(5,'MINUTE')";
			List<Notification> notifications = jdbcTemplate.query(sql, new Object[] { reveiver },
					new NotificationMapper());
			LOGGER.info("The mailVOs returned " + notifications != null ? notifications.toString() : null);
			return notifications;
		} finally {

		}
	}

	/*
	 * public String updateMailVO(MailVO mailVO) throws SQLException {
	 * LOGGER.info("In updateStore " + mailVO.getMailId()); String sql =
	 * "UPDATE EMAIL SET company_id,store_name=?, address_line1=?, address_line2=?, city=?, state=?,zipcode=?, email=?,phone_number=?) WHERE store_id = ?"
	 * ; PreparedStatement statement = conn.prepareStatement(sql);
	 * statement.setString(1, mailVO.getMailId());
	 * 
	 * int rowsUpdated = statement.executeUpdate(); if (rowsUpdated > 0) {
	 * LOGGER.info("An existing store updated successfully!"); return
	 * Constants.SUCCESS; } return Constants.FAILURE; } public void
	 * addSMSConfiguration(SMSConfiguration config) throws ClassNotFoundException,
	 * SQLException {
	 * 
	 * LOGGER.info("In addSMS"); SMSConfiguration smsConfigurationFromDB =
	 * getSMSConfiguration(config.getCompanyId()); if (smsConfigurationFromDB !=
	 * null && StringUtils.isNotBlank(smsConfigurationFromDB.getSmsUrl())) {
	 * config.setConfigurationId(smsConfigurationFromDB.getConfigurationId());
	 * editSMSConfiguration(config); } else { String sql =
	 * "INSERT INTO sms_configuration (sms_url, parameter_username, parameter_password, "
	 * +
	 * "send_code, message_header, message_footer, company_id, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
	 * ;
	 * 
	 * PreparedStatement statement = conn.prepareStatement(sql);
	 * statement.setString(1, config.getSmsUrl()); statement.setString(2,
	 * config.getParameterUsername()); statement.setString(3,
	 * config.getParameterPassword()); statement.setString(4, config.getSendCode());
	 * statement.setString(5, config.getMessageHeader()); statement.setString(6,
	 * config.getMessageFooter()); statement.setString(7, config.getCompanyId());
	 * statement.setString(8, config.getUid());
	 * 
	 * int rowsInserted = statement.executeUpdate(); if (rowsInserted > 0) {
	 * LOGGER.info("A new sms was inserted successfully!"); } }
	 * 
	 * }
	 * 
	 * public SMSConfiguration getSMSConfiguration(String companyId) throws
	 * ClassNotFoundException, SQLException { SMSConfiguration config = null; String
	 * sql = "SELECT * FROM sms_configuration WHERE company_id =?";
	 * 
	 * PreparedStatement statement = conn.prepareStatement(sql);
	 * statement.setString(1, companyId); ResultSet result =
	 * statement.executeQuery(); while (result.next()) { config = new
	 * SMSConfiguration();
	 * config.setConfigurationId(result.getString("configuration_id"));
	 * config.setSmsUrl(result.getString("sms_url"));
	 * config.setParameterUsername(result.getString("parameter_username"));
	 * config.setParameterPassword(result.getString("parameter_password"));
	 * config.setSendCode(result.getString("send_code"));
	 * config.setMessageHeader(result.getString("message_header"));
	 * config.setMessageFooter(result.getString("message_footer"));
	 * config.setCompanyId(result.getString("company_id"));
	 * config.setUid(result.getString("username")); break; } return config; }
	 * 
	 * public void editSMSConfiguration(SMSConfiguration config) throws SQLException
	 * { String sql =
	 * "UPDATE sms_configuration SET sms_url = ?, parameter_username = ?, parameter_password = ?, "
	 * +
	 * "send_code = ?, message_header = ?, message_footer = ?, company_id = ?, username = ? WHERE configuration_id = ?"
	 * ; PreparedStatement statement = conn.prepareStatement(sql);
	 * statement.setString(1, config.getSmsUrl()); statement.setString(2,
	 * config.getParameterUsername()); statement.setString(3,
	 * config.getParameterPassword()); statement.setString(4, config.getSendCode());
	 * statement.setString(5, config.getMessageHeader()); statement.setString(6,
	 * config.getMessageFooter()); statement.setString(7, config.getCompanyId());
	 * statement.setString(8, config.getUid()); statement.setString(9,
	 * config.getConfigurationId());
	 * 
	 * int rowsInserted = statement.executeUpdate(); if (rowsInserted > 0) {
	 * LOGGER.info("A new sms was updated successfully!"); } }
	 */

}