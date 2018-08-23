package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.ProductCategory;

public class ManufacturerProductCategoryMapper implements RowMapper<ProductCategory> {

	@Override
	public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(rs.getString("productCategoryId"));
		productCategory.setProductCategoryName(rs.getString("productCategoryName"));
		productCategory.setProductCategoryDesc(rs.getString("productCategoryDesc"));
		return productCategory;
	}
}
