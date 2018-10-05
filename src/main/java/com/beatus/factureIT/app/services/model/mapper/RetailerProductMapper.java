package com.beatus.factureIT.app.services.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beatus.factureIT.app.services.model.Product;
import com.beatus.factureIT.app.services.utils.Constants;

public class RetailerProductMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getString("retailer_product_id"));
		product.setProductCategoryId(rs.getString("product_category_id"));
		product.setUserType(Constants.RETAILER_TYPE);
		product.setUserTypeID(rs.getString("retailer_id"));
		product.setProductName(rs.getString("product_name"));
		product.setProductDesc(rs.getString("product_desc"));
		product.setProductImageString(rs.getString("product_image"));
		product.setBrandName(rs.getString("brand_name"));
		product.setHsnCode(rs.getString("hsn_code"));
		product.setCostPrice(rs.getString("product_cost_price"));
		product.setMarginAmount(rs.getString("product_margin_amount"));
		product.setSellingPrice(rs.getString("product_selling_price"));
		product.setUnit(rs.getString("product_unit"));
		product.setTotalQuantityAvailable(rs.getString("product_total_quantity_available"));
		product.setGstTax(rs.getString("product_gst_tax"));
		product.setCgstTax(rs.getString("product_cgst_tax"));
		product.setSgstTax(rs.getString("product_sgst_tax"));
		product.setIgstTax(rs.getString("product_igst_tax"));
		product.setGstNumber(rs.getString("product_gst_number"));
		return product;
	}
}