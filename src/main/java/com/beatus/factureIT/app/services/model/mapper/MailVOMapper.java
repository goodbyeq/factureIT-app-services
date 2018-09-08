package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.MailVO;

public class MailVOMapper implements RowMapper<MailVO> {

	@Override
	public MailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MailVO mailVO = new MailVO();
		mailVO.setMailId(rs.getString("users_email_id"));
		mailVO.setFromAddress(rs.getString("from_address"));
		mailVO.setToAddress(rs.getString("to_address"));
		mailVO.setSubject(rs.getString("subject"));
		mailVO.setBody(rs.getString("body"));
		mailVO.setSendCode(rs.getString("send_code"));
		mailVO.setHtmlBody(rs.getString("html_body"));
		mailVO.setUsername(rs.getString("username"));
		mailVO.setMailType(rs.getString("mail_type"));
		return mailVO;
	}
}
