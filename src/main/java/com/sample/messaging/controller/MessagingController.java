/**
 * Rest Controller, provides API details
 */
package com.sample.messaging.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.messaging.entity.MessageEntity;
import com.sample.messaging.exception.ApplicationException;
import com.sample.messaging.model.Message;
import com.sample.messaging.service.MessagingService;

@Controller
@RestController
@RequestMapping("/api")
public class MessagingController {

	private Logger logger = LoggerFactory.getLogger(MessagingController.class);

	@Autowired
	private MessagingService messagingService;

	/**
	 * Controller method to post a new message
	 * 
	 * @param message
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/v1/messages", method = RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody Message message) throws ApplicationException {
		logger.debug("Request received to post a new message: {}", message);
		return new ResponseEntity<>(messagingService.addMessage(message), HttpStatus.OK);
	}

	/**
	 * Controller method to get messages of a user
	 * 
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/v1/messages", method = RequestMethod.GET)
	public ResponseEntity<List<MessageEntity>> getMessages(@RequestParam String userId) throws ApplicationException {
		logger.debug("Request received to get messages of user:{}", userId);
		return new ResponseEntity<>(messagingService.getMessages(userId), HttpStatus.OK);
	}

	/**
	 * Controller method to update message by messageId
	 * 
	 * @param messageId
	 * @param message
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/v1/messages", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateMessage(@RequestParam String messageId, @RequestBody Message message)
			throws ApplicationException {
		logger.debug("Request received to update messages of id :{} and content:{}", messageId, message);
		messagingService.updateMessage(message, messageId);
	}

	/**
	 * Controller method to delete all messages of a user
	 * 
	 * @param userId
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/v1/messages", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMessage(@RequestParam String userId) throws ApplicationException {
		logger.debug("Request received to delete messages of user: {}", userId);
		messagingService.deleteMessage(userId);
	}

	/**
	 * Controller method to delete multiple messages of a user, providing number
	 * 
	 * @param userId
	 * @param number
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/v1/messages/multi", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteMessageMulti(@RequestParam String userId, @RequestParam Integer number)
			throws ApplicationException {
		logger.debug("Request received to delete messages of user: {} and number:{}", userId, number);
		messagingService.deleteMultiMessage(userId, number);
	}

}
