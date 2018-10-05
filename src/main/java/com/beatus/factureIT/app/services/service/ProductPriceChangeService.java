package com.beatus.factureIT.app.services.service;

import java.util.List;

import javax.annotation.Resource;

import com.beatus.factureIT.app.services.model.ProductPriceChange;
import com.beatus.factureIT.app.services.repository.ProductPriceChangeRepository;

public class ProductPriceChangeService {
	
	@Resource(name ="productPriceChangeRepository")
	ProductPriceChangeRepository productPriceChangeRepository;
	
	public String updatePricesOfProductsAndSendNotification(List<ProductPriceChange> productPriceChanges, String senderUserType, String sendUserTypeId) {
		try {
			return productPriceChangeRepository.updatePricesOfProducts(productPriceChanges, sendUserTypeId, sendUserTypeId);
		}catch(Exception e) {
			
		}
		return sendUserTypeId;
	}

}
