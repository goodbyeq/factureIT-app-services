package com.beatus.factureIT.app.services.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beatus.factureIT.app.services.utils.Constants;

@Controller
@RequestMapping(Constants.WEB_DISTRIBUTOR_REQUEST)
public class DistributorsController {

	/*private static final Logger LOGGER = LoggerFactory.getLogger(DistributorsController.class);

	@Resource(name = "distributorService")
    private DistributorService distributorService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String distributorHome(HttpServletRequest request, ModelMap model) {
        return "distributor/home";
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_DISTRIBUTOR,
            method = RequestMethod.GET)
    public String addDistributorGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	List<Location> locations = distributorService.getLocations(companyId);
        model.addAttribute("locations", locations);
        return "distributor/request-add";
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_ADD_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String addDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.addDistributor(distributor, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_EDIT_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String editDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.editDistributor(distributor, companyId, uid);
    	return resp;
    }
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_DELETE_DISTRIBUTOR,
            method = RequestMethod.POST)
    public String deleteDistributorPost(HttpServletRequest request, Distributor distributor, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
		String uid = (String) request.getAttribute(Constants.USERNAME);
    	String resp = distributorService.deleteDistributor(distributor.getDistributorId(), companyId, uid);
    	return resp;
    }
    
    
    @RequestMapping(value = Constants.WEB_DISTRIBUTOR_GET_DISTRIBUTORS,
            method = RequestMethod.GET)
    public String getDistributorsGet(HttpServletRequest request, ModelMap model) throws ClassNotFoundException, SQLException {
    	String companyId = (String) request.getAttribute(Constants.COMPANY_ID);
    	List<Distributor> distributors = distributorService.getDistributors(companyId);
    	LOGGER.info("After the get call and the distributors are "  + distributors != null? distributors.size() > 0 ? distributors.get(0).getDistributorName() : "No Distributor data" : "No Distributor data");
        DistributorResponse resp = new DistributorResponse();
        resp.setDistributors(distributors);
    	model.addAttribute("distributors", distributors);
        return "distributor/request-get";
    }*/
}
