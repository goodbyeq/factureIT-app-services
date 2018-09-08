package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.SmsVO;

public class SmsVOMapper implements RowMapper<SmsVO> {

	@Override
	public SmsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SmsVO smsVO = new SmsVO();
		smsVO.setSmsId(rs.getString("users_sms_id"));
		smsVO.setDestPhone(rs.getString("dest_phone"));
		smsVO.setMessage(rs.getString("message"));
		smsVO.setSendCode(rs.getString("send_code"));
		smsVO.setUsername(rs.getString("username"));
		smsVO.setSmsType(rs.getString("sms_type"));
		return smsVO;
	}
}
