package com.beatus.factureIT.app.services.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.beatus.factureIT.app.services.model.ProductPriceChange;
import com.beatus.factureIT.app.services.utils.Constants;

@Repository("productPriceChangeRepository")
public class ProductPriceChangeRepository {
	
	@Resource(name = "manufacturerRepository")
	private ManufacturerRepository manufacturerRepository;
	
	@Resource(name = "distributorRepository")
	private DistributorRepository distributorRepository;

	public String updatePricesOfProducts(List<ProductPriceChange> productPriceChanges, String senderUserType, String senderUserTypeId) {
		if(StringUtils.isNotBlank(senderUserType) && Constants.MANUFACTURER_TYPE.equals(senderUserType)) {
			
		}
		return null;
	}

}
