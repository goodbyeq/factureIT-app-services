package com.beatus.factureIT.app.services.repository;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beatus.factureIT.app.services.model.MailVO;
import com.beatus.factureIT.app.services.model.mapper.MailVOMapper;
import com.beatus.factureIT.app.services.utils.Constants;

@Repository("emailRepository")
public class EmailRepository {

	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailRepository.class);

	@Autowired
	@Qualifier(value = "driverManagerDataSource")
	private DataSource dataSource;

	@Autowired
	public EmailRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String addEmailWithoutAttachments(MailVO mailVO) throws ClassNotFoundException, SQLException {
		LOGGER.info("In addEmailWithoutAttachments " + mailVO.getMailId());
		try {
			String sql = "INSERT INTO users_email (users_email_id, from_address, to_address, subject, body, send_code, ccAddress, bccAddress, html_body, username, mail_type) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			int rowsInserted = jdbcTemplate.update(sql, mailVO.getMailId(), mailVO.getFromAddress(),
					mailVO.getToAddress(), mailVO.getSubject(), mailVO.getBody(), mailVO.getSendCode(), mailVO.getCcAddress() != null ? mailVO.getCcAddress().toString() : null,
					mailVO.getBccAddress() != null ? mailVO.getBccAddress().toString() : null, mailVO.getHtmlBody(), mailVO.getUsername(), mailVO.getMailType());

			if (rowsInserted > 0) {
				LOGGER.info("A new email was inserted successfully!");
				return Constants.SUCCESS;
			} else {
				return Constants.FAILURE;
			}
		} finally {

		}
	}

	public List<MailVO> getMailVOsByUID(String username) throws SQLException {
		LOGGER.info("In getMailVOsByUID " + username);
		try {
			String sql = "SELECT * FROM users_email WHERE username = ?";
			List<MailVO> mailVOs = jdbcTemplate.query(sql, new Object[] { username }, new MailVOMapper());
			LOGGER.info("The mailVOs returned " + mailVOs != null ? mailVOs.toString() : null);
			return mailVOs;
		} finally {

		}
	}

	public List<MailVO> getMailVOByUIDAndSMSType(String username, String emailVerificationType) {
		LOGGER.info("In getMailVOsByUID " + username);
		try {
			String sql = "SELECT * FROM users_email WHERE username = ? AND mail_type = ? AND create_dt > current_timestamp - interval '5 minutes' order by create_dt";
			List<MailVO> mailVOs = jdbcTemplate.query(sql, new Object[] { username, emailVerificationType }, new MailVOMapper());
			LOGGER.info("The mailVOs returned " + mailVOs != null ? mailVOs.toString() : null);
			return mailVOs;
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
	}*/
}
