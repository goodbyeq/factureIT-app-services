package com.beatus.factureIT.app.services.repository;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.beatus.factureIT.app.services.model.SmsVO;
import com.beatus.factureIT.app.services.model.mapper.SmsVOMapper;
import com.beatus.factureIT.app.services.utils.Constants;

@Component("smsRepository")
public class SMSRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSRepository.class);

	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public SMSRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String addSMSInfo(SmsVO smsVO) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addSMSInfo " + smsVO.getDestPhone());
		try {
			String sql = "INSERT INTO users_sms (users_sms_id, dest_phone, message, send_code, username, sms_type) VALUES (?,?,?,?,?,?)";

			int rowsInserted = jdbcTemplate.update(sql, smsVO.getSmsId(), smsVO.getDestPhone(),
					smsVO.getMessage(), smsVO.getSendCode(), smsVO.getUsername(), smsVO.getSmsType());

			if (rowsInserted > 0) {
				LOGGER.info("A new sms was inserted successfully!");
				return Constants.SUCCESS;
			} else {
				return Constants.FAILURE;
			}
		} finally {

		}
	}

	public List<SmsVO> getSMSVOsByUID(String username) throws SQLException {
		LOGGER.info("In getSMSVOsByUID " + username);
		try {
			String sql = "SELECT * FROM users_sms WHERE username = ?";
			List<SmsVO> smsVOs = jdbcTemplate.query(sql, new Object[] { username }, new SmsVOMapper());
			LOGGER.info("The mailVOs returned " + smsVOs != null ? smsVOs.toString() : null);
			return smsVOs;
		} finally {

		}
	}

	public List<SmsVO> getSMSVOByUIDAndSMSType(String username, String smsVerificationType) {
		LOGGER.info("In getSMSVOByUIDAndSMSType " + username);
		try {
			String sql = "SELECT * FROM users_sms WHERE username = ? AND sms_type = ? AND create_dt > current_timestamp - interval '5 minutes' order by create_dt";
			List<SmsVO> smsVOs = jdbcTemplate.query(sql, new Object[] { username, smsVerificationType }, new SmsVOMapper());
			LOGGER.info("The mailVOs returned " + smsVOs != null ? smsVOs.toString() : null);
			return smsVOs;
		} finally {

		}
	}

	/*public String updateMailVO(MailVO mailVO) throws SQLException {
		LOGGER.info("In updateStore " + mailVO.getMailId());
		String sql = "UPDATE EMAIL SET company_id,store_name=?, address_line1=?, address_line2=?, city=?, state=?,zipcode=?, email=?,phone_number=?) WHERE store_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, mailVO.getMailId());
	
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
			LOGGER.info("An existing store updated successfully!");
			return Constants.SUCCESS;
		}
		return Constants.FAILURE;
	}
	public void addSMSConfiguration(SMSConfiguration config) throws ClassNotFoundException, SQLException {

		LOGGER.info("In addSMS");
		SMSConfiguration smsConfigurationFromDB = getSMSConfiguration(config.getCompanyId());
		if (smsConfigurationFromDB != null && StringUtils.isNotBlank(smsConfigurationFromDB.getSmsUrl())) {
			config.setConfigurationId(smsConfigurationFromDB.getConfigurationId());
			editSMSConfiguration(config);
		} else {
			String sql = "INSERT INTO sms_configuration (sms_url, parameter_username, parameter_password, "
					+ "send_code, message_header, message_footer, company_id, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, config.getSmsUrl());
			statement.setString(2, config.getParameterUsername());
			statement.setString(3, config.getParameterPassword());
			statement.setString(4, config.getSendCode());
			statement.setString(5, config.getMessageHeader());
			statement.setString(6, config.getMessageFooter());
			statement.setString(7, config.getCompanyId());
			statement.setString(8, config.getUid());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				LOGGER.info("A new sms was inserted successfully!");
			}
		}

	}

	public SMSConfiguration getSMSConfiguration(String companyId) throws ClassNotFoundException, SQLException {
		SMSConfiguration config = null;
		String sql = "SELECT * FROM sms_configuration WHERE company_id =?";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, companyId);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			config = new SMSConfiguration();
			config.setConfigurationId(result.getString("configuration_id"));
			config.setSmsUrl(result.getString("sms_url"));
			config.setParameterUsername(result.getString("parameter_username"));
			config.setParameterPassword(result.getString("parameter_password"));
			config.setSendCode(result.getString("send_code"));
			config.setMessageHeader(result.getString("message_header"));
			config.setMessageFooter(result.getString("message_footer"));
			config.setCompanyId(result.getString("company_id"));
			config.setUid(result.getString("username"));
			break;
		}
		return config;
	}

	public void editSMSConfiguration(SMSConfiguration config) throws SQLException {
		String sql = "UPDATE sms_configuration SET sms_url = ?, parameter_username = ?, parameter_password = ?, "
				+ "send_code = ?, message_header = ?, message_footer = ?, company_id = ?, username = ? WHERE configuration_id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, config.getSmsUrl());
		statement.setString(2, config.getParameterUsername());
		statement.setString(3, config.getParameterPassword());
		statement.setString(4, config.getSendCode());
		statement.setString(5, config.getMessageHeader());
		statement.setString(6, config.getMessageFooter());
		statement.setString(7, config.getCompanyId());
		statement.setString(8, config.getUid());
		statement.setString(9, config.getConfigurationId());

		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			LOGGER.info("A new sms was updated successfully!");
		}
	}*/

}