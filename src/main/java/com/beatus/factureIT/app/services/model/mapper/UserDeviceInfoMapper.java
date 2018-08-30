package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.UserDeviceInfo;

public class UserDeviceInfoMapper implements RowMapper<UserDeviceInfo> {

	@Override
	public UserDeviceInfo mapRow(ResultSet result, int rowNum) throws SQLException {
		UserDeviceInfo userDeviceInfo = new UserDeviceInfo();
		userDeviceInfo.setDeviceId(result.getString("device_id"));
		userDeviceInfo.setFirebaseId(result.getString("firebase_id"));
		userDeviceInfo.setGcmId(result.getString("gcm_id"));
		userDeviceInfo.setVisionId(result.getString("vision_id"));
		userDeviceInfo.setUid(result.getString("uid"));
		userDeviceInfo.setUserDeviceInfoId(result.getString("users_device_info_id"));
		return userDeviceInfo;
	}

}
