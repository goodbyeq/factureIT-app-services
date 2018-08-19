package com.beatus.factureIT.app.services.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.encryption.KeyChain;
import com.beatus.factureIT.app.services.encryption.KeyChainEntries;
import com.beatus.factureIT.app.services.exception.CryptoException;;

/**
 * Root class for the application's Spring configuration. This class is
 * annotated with {@link ComponentScan} which will look for other classes in
 * this package with the {@link Configuration} annotation. It will not component
 * scan for any other Spring Stereotypes.
 *
 * @author Abhinav Akey
 * @since 1.0
 */
@Configuration
@ComponentScan(
        basePackages = "com.beatus.factureIT",
        useDefaultFilters = false,
        includeFilters = {
            @ComponentScan.Filter(Configuration.class),
            @ComponentScan.Filter(Controller.class),
            @ComponentScan.Filter(Component.class),
        	@ComponentScan.Filter(Service.class)
        })
@PropertySource(
        name = "applicationProperties",
        value = "classpath:application.properties", 
        ignoreResourceNotFound = false)
/*@PropertySource(
        name = "buildProperties", 
        value = "classpath:build.properties", 
        ignoreResourceNotFound = true)*/
public class ApplicationConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    
    // ******************************************************************//
    // Properities Configuration
    // ******************************************************************//

	@Value("${keychain.file:factureIT_key.xml}")
	private String keyChainFile = "factureIT_key.xml";
	
	@Resource(name = "keyChain")
	private KeyChain keyChain;
	
    @Bean
    @Profile("!test")
    public static PropertySourcesPlaceholderConfigurer pspc() {
                
    	LOGGER.info("Loading the properties");
        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();

        pspc.setLocalOverride(true);

        return pspc;
    }
    
    /**
     * Property Placeholder configuration for non-test profiles.
     * 
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    @Profile("test")
    public static PropertySourcesPlaceholderConfigurer pspcTest() {

        PropertySourcesPlaceholderConfigurer pspc
                = new PropertySourcesPlaceholderConfigurer();

        pspc.setLocalOverride(true);

        return pspc;
    }
    
    @Bean
    public DriverManagerDataSource driverManagerDataSource() throws ClassNotFoundException {

	    //String dbURL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String dbURL = "jdbc:postgresql://localhost:5432/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "postgres";
		String password = "postgres";
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(dbURL);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		LOGGER.info("In connection");
		return dataSource;
	}
    
    @Bean
	public KeyChainEntries keyChainEntries() {
    	fixKeyLength();
		KeyChainEntries keyChainEntries = null;
		try {
			// ENC
			// Load the googleKMS encrypted file.
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL resource = loader.getResource(keyChainFile);
			// File billliveFile = new File(resource.toURI());
			// InputStream encryptedStream = new FileInputStream(billliveFile);
			StringBuilder contentBuilder = new StringBuilder();

			try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()), StandardCharsets.UTF_8)) {
				stream.forEach(s -> contentBuilder.append(s).append("\n"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] fileInBytes = contentBuilder.toString().getBytes();
			InputStream plainTextStream = new ByteArrayInputStream(fileInBytes);
			keyChainEntries = keyChain.loadKeyChain(plainTextStream);
		} catch (URISyntaxException | CryptoException e) {
			LOGGER.info("Error while encrypting");
		}
		return keyChainEntries;
	}
    
    private void fixKeyLength() {
	    String errorString = "Failed manually overriding key-length permissions.";
	    int newMaxKeyLength;
	    try {
	        if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
	            Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
	            Constructor con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissionCollection = con.newInstance();
	            Field f = c.getDeclaredField("all_allowed");
	            f.setAccessible(true);
	            f.setBoolean(allPermissionCollection, true);

	            c = Class.forName("javax.crypto.CryptoPermissions");
	            con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissions = con.newInstance();
	            f = c.getDeclaredField("perms");
	            f.setAccessible(true);
	            ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

	            c = Class.forName("javax.crypto.JceSecurityManager");
	            f = c.getDeclaredField("defaultPolicy");
	            f.setAccessible(true);
	            Field mf = Field.class.getDeclaredField("modifiers");
	            mf.setAccessible(true);
	            mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	            f.set(null, allPermissions);

	            newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException(errorString, e);
	    }
	    if (newMaxKeyLength < 256)
	        throw new RuntimeException(errorString); // hack failed
	}
}
