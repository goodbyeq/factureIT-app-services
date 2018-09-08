package com.beatus.factureIT.app.services.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beatus.factureIT.app.services.exception.FactureITServiceException;
import com.beatus.factureIT.app.services.model.MailVO;
import com.beatus.factureIT.app.services.repository.EmailRepository;
import com.beatus.factureIT.app.services.utils.Constants;
import com.beatus.factureIT.app.services.utils.Utils;

@Service("emailService")
public class EmailService {

	@Resource(name = "emailRepository")
	private EmailRepository emailRepository;

	@Resource(name = "awsSimpleMailService")
	AWSSimpleMailService awsSimpleMailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	public String addEmail(HttpServletRequest request, HttpServletResponse response, MailVO mailVO)
			throws FactureITServiceException, SQLException {
		if (mailVO == null || mailVO.getUsername() == null) {
			throw new FactureITServiceException("Mail cant be sent.");
		}
		try {
			String mailId = Utils.generateRandomKey(50);
			mailVO.setMailId(mailId);
			return emailRepository.addEmailWithoutAttachments(mailVO);
		} catch (Exception exception) {
			LOGGER.error("Email Service Exception in the addMail() {} ", exception.getMessage());
			throw new FactureITServiceException("Exception while adding mailvo");
		}
	}

	public List<MailVO> getMailVOsByUID(String username) throws FactureITServiceException {
		LOGGER.info("In getMailVOsByUID method of Email Service");
		try {
			if (StringUtils.isNotBlank(username)) {
				List<MailVO> mailVOs = emailRepository.getMailVOsByUID(username);
				if (mailVOs != null && mailVOs.size() > 0) {
					return mailVOs;
				} else
					return null;
			} else {
				LOGGER.error(" Exception in the getMailVOsByUID() {},  uid passed cant be null or empty string");
				throw new FactureITServiceException("Mail Id passed cant be null or empty string");
			}
		} catch (Exception e) {
			throw new FactureITServiceException("Exception in getMailVOsByUID " + e);
		}

	}

	public MailVO getMailVOByUIDAndSMSType(String username) throws FactureITServiceException {
		LOGGER.info("In getMailVOByUIDAndSMSType method of Email Service");
		try {
			if (StringUtils.isNotBlank(username)) {
				List<MailVO> mailVOs = emailRepository.getMailVOByUIDAndSMSType(username, Constants.EMAIL_VERIFICATION_TYPE);
				if (mailVOs != null && mailVOs.size() > 0) {
					return mailVOs.get(0);
				} else
					return null;
			} else {
				LOGGER.error(" Exception in the getMailVOsByUID() {},  uid passed cant be null or empty string");
				throw new FactureITServiceException("Mail Id passed cant be null or empty string");
			}
		} catch (Exception e) {
			throw new FactureITServiceException("Exception in getMailVOsByUID " + e);
		}

	}

	public String sendEmail(HttpServletRequest request, HttpServletResponse response, MailVO mailVO)
			throws FactureITServiceException {

		if (mailVO == null || mailVO.getUsername() == null) {
			throw new FactureITServiceException("Mail cant be sent.");
		}
		try {
			/*awsSimpleMailService.sendEmailThroughAWSSES(mailVO.getToAddress(), mailVO.getSubject(), mailVO.getBody(),
					mailVO.getHtmlBody());*/
			addEmail(request, response, mailVO);
		} catch (Exception e) {
			throw new FactureITServiceException("Error seding email through AWS " + e.getMessage());
		}
		return Constants.SUCCESS;
	}

	public String verifySendCode(String code, String uid) throws FactureITServiceException {
		if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(code)) {
			MailVO mailVO = getMailVOByUIDAndSMSType(uid);
			if (mailVO != null && mailVO.getSendCode() != null) {
				if (mailVO.getSendCode().equals(code)) {
					return Constants.SUCCESS;
				}
			}
		}
		return Constants.FAILURE;
	}
}
