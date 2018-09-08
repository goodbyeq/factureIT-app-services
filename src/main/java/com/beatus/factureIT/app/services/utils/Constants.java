package com.beatus.factureIT.app.services.utils;

public class Constants {

	public static final String X_FRAME_OPTIONS = "X-Frame-Options";
	public static final String X_FRAME_OPTIONS_VALUE = "DENY";
	public static final String X_XSS_PROTECTION = "X-XSS-Protection";
	public static final String X_XSS_PROTECTION_VALUE = "1; mode=block";
	public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
	public static final String STRICT_TRANSPORT_SECURITY_VALUE = "max-age=16070400; includeSubDomains";
    public static final String URL_PARAM_ACTION = "action";
    public static final String URL_PARAM_ERROR_CODE = "errorCode";
    
    public static final String WEB_PRODUCTS_REQUEST = "/product";
    public static final String WEB_DISTRIBUTOR_REQUEST = "/distributor";
    public static final String WEB_SMS_REQUEST = "/sms";
	public static final String WEB_PRODUCTS_ADD_PRODUCT = "/addProduct";
	public static final String WEB_DISTRIBUTOR_ADD_DISTRIBUTOR = "/addDistributor";
	public static final String WEB_PRODUCTS_GET_PRODUCTS = "/getProducts";
	public static final String WEB_DISTRIBUTOR_GET_DISTRIBUTORS = "/getDistributors";
    public static final String WEB_SMS_SEND_SMS_SCREEN = "/sendsmsScreen";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_CATEGORY = "productCategory";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String DISTRIBUTOR = "distributor";
	public static final String DISTRIBUTOR_NAME = "distributorName";
	public static final String DISTRIBUTOR_PHONE = "distributorPhone";
	public static final String DISTRIBUTOR_LOCATION = "distributorLocation";
	public static final String DISTRIBUTOR_DISTRICT = "distributorDistrict";
	public static final String PRODUCTS = "Products";
	public static final String REDIRECT = "redirect:";
	public static final String WEB_LOCATION_REQUEST = "/location";
	public static final String WEB_LOCATION_ADD_LOCATION = "/addLocation";
	public static final String WEB_LOCATION_GET_LOCATIONS = "/getLocations";
	public static final String LOCATION = "location";
	public static final String LOCATION_NAME = "locationName";
	public static final String LOCATION_CITY = "locationCity";
	public static final String LOCATION_DISTRICT = "locationDistrict";
	public static final String LOCATION_STATE = "locationState";
	public static final String PRODUCT_LOCATION = "productLocation";
	public static final String WEB_PRODUCTS_ADD_PRODUCT_AND_LOCATION = "/addProductAndLocation";
	public static final String WEB_PRODUCTS_GET_PRODUCTS_AND_LOCATIONS = "/getProductsAndLocations";
	public static final String PRODUCT_LOCATION_NAME = "productandlocation";
	public static final String WEB_LOCATION_EDIT_LOCATION = "/editLocation";
	public static final String WEB_PRODUCTS_EDIT_PRODUCT = "/editProduct";
	public static final String WEB_PRODUCTS_EDIT_PRODUCT_AND_LOCATION = "/editProductsAndLocations";
	public static final String WEB_DISTRIBUTOR_EDIT_DISTRIBUTOR = "/editDistributor";
	public static final String WEB_SMS_SEND_SMS_ADD_SCREEN_CONFIGURATION = "/addSmsConfiguration";
	public static final String WEB_SMS_SCREEN_GET_CONFIGURATION = "/getSmsConfiguration";
	public static final String WEB_SMS_SEND_SMS_EDIT_SCREEN_CONFIGURATION = "/editSmsConfiguration";
	public static final String WEB_USER_REQUEST = "/user";
	public static final String WEB_LOGIN = "/login";
	public static final String WEB_USER_SIGNUP = "/signup";
	public static final String AUTHENTICATED = "authenticated";
	public static final String USER_CREATED = "userCreated";
	public static final String ERROR_LOGIN = "Username & or password doesn't match. Please try again";
	public static final String CHAR_SET = "UTF-8";
	public static final String AES = "AES";
	public static final String HMACSHA256 = "HmacSHA256";
	public static final String BILLLIVE_SENDSMS = "billlive-sendsms";
	public static final String ERROR_USER_CREATION = "There is some problem creating the user. Please try again.";
	public static final String USERNAME = "username";
	public static final String WEB_LOGOUT = "/logout";
	public static final String USER = "user";
	public static final String USER_ADMIN = "admin";
	public static final String COMPANY_ID = "companyId";
	public static final String USER_TYPE = "userType";
	public static final String WEB_USER_ADD_COMPANY_USER = "/addCompanyUser";
	public static final String OK = "OK";
	public static final String WEB_DISTRIBUTOR_DELETE_DISTRIBUTOR = "/deleteDistributor";
	public static final String WEB_LOCATION_DELETE_LOCATION = "/deleteLocation";
	public static final String WEB_PRODUCTS_DELETE_PRODUCTS_AND_LOCATIONS = "/deleteProductsAndLocations";
	public static final String WEB_PRODUCTS_DELETE_PRODUCTS = "/deleteProduct";
    public static final String WEB_GET_BILLS = "/bills";
    public static final String WEB_PRODUCTS_GET_PRODUCT_BY_ID = "/getProductById";
	public static final String ERROR_USER_WITH_SAME_EMAIL_EXISTS = "Account with this email already exists";
	public static final String ERROR_USER_WITH_SAME_PHONE_EXISTS = "Account with this phone number already exists";
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String DISTRIBUTOR_TYPE = "DISTRIBUTOR";
	public static final String RETAILER_TYPE = "RETAILER";
	public static final String MANUFACTURER_TYPE = "MANUFACTURER";
	public static final String CUSTOMER_TYPE = "CUSTOMER";
	public static final String COLLECTION_AGENT_TYPE = "COLLECTION_AGENT";
	public static final String WEB_DISTRIBUTOR_GET_ALL_DISTRIBUTORS = "/getAllDistributors";
	public static final String WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_UID = "/getDistributorByUID";
	public static final String WEB_DISTRIBUTOR_GET_DISTRIBUTOR_BY_ID = "/getDistributorById";	
	public static final String WEB_CUSTOMER_GET_ALL_CUSTOMERS = "/getAllCustomers";
	public static final String WEB_CUSTOMER_GET_CUSTOMER_BY_UID = "/getCustomerByUID";
	public static final String WEB_CUSTOMER_GET_CUSTOMER_BY_ID = "/getCustomerById";	
	public static final String WEB_MANUFACTURER_GET_ALL_MANUFACTURERS = "/getAllManufacturers";
	public static final String WEB_MANUFACTURER_GET_MANUFACTURER_BY_UID = "/getManufacturerByUID";
	public static final String WEB_MANUFACTURER_GET_MANUFACTURER_BY_ID = "/getManufacturerById";	
	public static final String WEB_RETAILER_GET_ALL_RETAILERS = "/getAllRetailers";
	public static final String WEB_RETAILER_GET_RETAILER_BY_UID = "/getRetailerByUID";
	public static final String WEB_RETAILER_GET_RETAILER_BY_ID = "/getRetailerById";
	public static final String WEB_MANUFACTURER_ADD_MANUFACTURER = "/addManufacturer";
	public static final String WEB_MANUFACTURER_EDIT_MANUFACTURER = "/editManufacturer";
	public static final String WEB_MANUFACTURER_ADD_PRODUCTS = "/addProducts";
	public static final String WEB_MANUFACTURER_ADD_CATEGORIES = "/addCategories";
	public static final String WEB_MANUFACTURER_GET_PRODUCTS = "/getProductsByManufacturerID";
	public static final String WEB_MANUFACTURER_GET_PRODUCT_CATEGORIES = "/getProductCategoriesByManufacturerID";
	public static final String WEB_MANUFACTURER_ADD_RELATED_DISTRIBUTORS = "/addRelatedDistributors";
	public static final String WEB_MANUFACTURER_GET_RELATED_DISTRIBUTORS = "/getRelatedDistributors";
	public static final String WEB_MANUFACTURER_ADD_ROUTE = "/addRoute";
	public static final String WEB_MANUFACTURER_DELETE_MANUFACTURER = "/deleteManufacturer";
	
	public static final String WEB_DISTRIBUTOR_ADD_RELATED_RETAILER = "/addRelatedDistributors";
	public static final String WEB_DISTRIBUTOR_GET_RELATED_RETAILER = "/getRelatedDistributors";
	
	
	public static final String WEB_RETAILER_ADD_RETAILER = "/addRetailer";
	public static final String WEB_RETAILER_EDIT_RETAILER = "/editRetailer";
	public static final String WEB_RETAILER_ADD_RELATED_DISTRIBUTORS = "/addRelatedDistributors";
	public static final String WEB_RETAILER_GET_RELATED_DISTRIBUTORS = "/getRelatedDistributors";
	
	
    public static final String WEB_MANUFACTURER_REQUEST = "/manufacturer";
    public static final String WEB_RETAILER_REQUEST = "/retailer";
    public static final String WEB_CUSTOMER_REQUEST = "/customer";
	public static final String WEB_USER_ADD_PROFILE = "/addProfile";
	public static final String WEB_DISTRIBUTOR_GET_ALL_DISTRIBUTORS_BY_AREA = "/getDistributorsByArea";
	public static final String WEB_MANUFACTURER_GET_ALL_MANUFACTURERS_BY_AREA = "/getManufacturersByArea";
	public static final String WEB_RETAILER_GET_ALL_RETAILERS_BY_AREA = "/getRetailersByArea";
	public static final String ERROR_CONVERTING_IDS_STRING = "Error Converting Ids into JSON.";
	public static final String USER_DEVICE_INFO_CREATED = "User Device info created/updated";
	public static final String ERROR_USER_DEVICE_INFO_CREATION = "Error creating user device info created/updated";
	public static final String ZERO_DOUBLE_AMOUNT = "0.00";
	public static final String NO = "No";
	public static final String YES = "Yes";
	public static final String COLLECTION_AGENT_ROUTES_CREATED_OR_UPDATED = "Collection Agent Routes Created";
	public static final String ERROR_CREATING_OR_UPDATING_COLLECTION_AGENT_ROUTES = "Error creating collection agent routes";
	
    public static final String USER_ID_TYPE_EMAIL="EMAIL";
	
	public static final String USER_ID_TYPE_PHONE="PHONE";

	public static final String APPLICATION_JSON = "application/json";

	public static final String VERIFY_CODE_PAGE = "verify";
	
	public static final int SECURE_RANDOM_LIMIT = 999999;

	public static final String SECURE_VERIFICATION_CODE = "secure_verification_code";
	
	public static final String LOGIN_PAGE = "login";
	public static final String SMS_VERIFICATION_TYPE = "SMS_VERIFICATION";
	public static final String EMAIL_VERIFICATION_TYPE = "EMAIL_VERIFICATION";
	public static final String WEB_SEND_CODE = "/sendcode";
	public static final String WEB_VERIFY_SEND_CODE = "/sendcode/verify";
	public static final String EMAIL_TYPE = "email";
	public static final String SMS_TYPE = "sms";
	public static final String MAIL_SUBJECT = "Email Verification Code";
	public static final String VERIFICATION_CODE_BODY = " is your FactureIT verification code.";

	
}
