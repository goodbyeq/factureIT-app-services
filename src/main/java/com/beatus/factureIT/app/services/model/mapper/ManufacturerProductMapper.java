package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.utils.Constants;

public class ManufacturerProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getString("manufacturer_product_id"));
		product.setProductCategoryId(rs.getString("product_category_id"));
		product.setUserType(Constants.MANUFACTURER_TYPE);
		product.setUserTypeID(rs.getString("manufacturer_id"));
		product.setProductName(rs.getString("product_name"));
		product.setProductDesc(rs.getString("product_desc"));
		product.setProductImageString(rs.getString("product_image"));
		product.setBrandName(rs.getString("brand_name"));
		product.setHsnCode(rs.getString("hsn_code"));
		product.setPrice(rs.getString("product_price"));
		product.setUnit(rs.getString("product_unit"));
		product.setTotalQuantityAvailable(rs.getString("product_total_quantity_available"));
		return product;
	}
}