package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Product;

public class ManufacturerProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getString("productId"));
		product.setProductCategoryId(rs.getString("productCategoryId"));
		product.setProductName(rs.getString("productName"));
		product.setProductDesc(rs.getString("productDesc"));
		product.setProductImageString(rs.getString("productImage"));
		product.setBrandName(rs.getString("brandName"));
		product.setHsnCode(rs.getString("hsnCode"));
		return product;
	}
}

