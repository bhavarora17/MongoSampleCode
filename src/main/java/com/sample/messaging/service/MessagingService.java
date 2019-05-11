/**
 * service class,provides actual business logic of controller
 * 
 * 
 */
package com.sample.messaging.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sample.messaging.constants.MessageDirection;
import com.sample.messaging.constants.MessageType;
import com.sample.messaging.entity.MessageEntity;
import com.sample.messaging.exception.ApplicationException;
import com.sample.messaging.model.Message;
import com.sample.messaging.repo.MessagingRepo;

@Service
public class MessagingService {

	private Logger logger = LoggerFactory.getLogger(MessagingService.class);

	@Autowired
	private MessagingRepo messagingRepo;

	/**
	 * service method to add a new message
	 * 
	 * @param message
	 * @return
	 * @throws ApplicationException
	 */
	public String addMessage(Message message) throws ApplicationException {

		logger.debug("Request recieved to post a new message: {}", message);
		MessageEntity entity = new MessageEntity();
		String messageId = UUID.randomUUID().toString();

		validateMessage(message);

		BeanUtils.copyProperties(message, entity);
		entity.setMessageId(messageId);
		messagingRepo.save(entity);
		logger.debug("Message having id: {} saved sucessfully", messageId);

		return messageId;
	}

	/**
	 * service method to get list of messages
	 * 
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 */
	public List<MessageEntity> getMessages(String userId) throws ApplicationException {
		logger.debug("Request recived to get message for userId: {}", userId);

		List<MessageEntity> entities = messagingRepo.findByUserIdOrderByTimestampDesc(userId);

		if (entities == null || entities.size() == 0) {
			logger.error("no data available for userId: {}", userId);
			throw new ApplicationException("no data available for userId: " + userId);
		}

		logger.debug("Messages fetched sucessfully, having size: {}", entities.size());
		return entities;
	}

	/**
	 * service method to update a message
	 * 
	 * @param message
	 * @param messageId
	 * @throws ApplicationException
	 */
	public void updateMessage(Message message, String messageId) throws ApplicationException {

		logger.debug("Request received to update message:{} for messageId: {} ", message, messageId);
		validateMessage(message);

		MessageEntity messageEntity = messagingRepo.findById(messageId).orElse(null);

		if (messageEntity != null) {
			BeanUtils.copyProperties(message, messageEntity);
			messagingRepo.save(messageEntity);
			logger.debug("Message updated successfully for messageId: {} ", message, messageId);
		} else {
			logger.error("no data available for messageId: {}", messageId);
			throw new ApplicationException("no data available for messageId: " + messageId);
		}

	}

	/**
	 * service method to delete messages of a user
	 * 
	 * @param userId
	 * @throws ApplicationException
	 */
	public void deleteMessage(String userId) throws ApplicationException {
		logger.debug("Request received to delete messages for user: {}", userId);
		List<MessageEntity> entities = messagingRepo.findByUserIdOrderByTimestampDesc(userId);

		if (entities == null || entities.size() == 0) {
			logger.error("no data available for userId: {}", userId);
			throw new ApplicationException("no data available for userId: " + userId);
		}
		logger.debug("total entities received to delete: {}", entities.size());
		messagingRepo.deleteAll(entities);
		logger.debug("Message deleted successfully for user: {}", userId);
	}

	/**
	 * service method to delete multiple messages
	 * 
	 * @param userId
	 * @param number
	 * @throws ApplicationException
	 */
	public void deleteMultiMessage(String userId, Integer number) throws ApplicationException {
		logger.debug("Request received to delete messages for user: {} and number:{}", userId, number);

		List<MessageEntity> entities = messagingRepo.findByUserIdOrderByTimestampDesc(userId,
				PageRequest.of(0, number));
		if (entities == null || entities.size() == 0) {
			logger.error("no data available for userId: {}", userId);
			throw new ApplicationException("no data available for userId: " + userId);
		}

		logger.info("total entities received to delete: {}", entities.size());
		messagingRepo.deleteAll(entities);
		logger.debug("Message deleted successfully for user: {}", userId);
	}

	/**
	 * This method validates message data
	 * 
	 * @param message
	 * @throws ApplicationException
	 */
	private void validateMessage(Message message) throws ApplicationException {

		if (StringUtils.isEmpty(message.getUserId())) {
			logger.error("user id is not found");
			throw new ApplicationException("userId is required");
		}

		if (StringUtils.isEmpty(message.getContent())) {
			logger.error("content is not found");
			throw new ApplicationException("content is required");
		}

		if (message.getTimestamp() == null) {
			logger.error("timestamp is not found");
			throw new ApplicationException("timestamp is required");
		}

		MessageDirection direction = MessageDirection.getDirection(message.getDirection());
		if (direction == null) {
			logger.error("Invalid message direction found");
			throw new ApplicationException("Invalid message direction");
		}

		if (direction == MessageDirection.INCOMING) {
			MessageType messageType = MessageType.getType(message.getType());
			if (messageType == null) {
				logger.error("Invalid message type found");
				throw new ApplicationException("Invalid message type");
			}
			if (messageType == MessageType.CONNECT || messageType == MessageType.DISCONNECT) {
				logger.debug("Connect/disconnect message found, aborting DB save process");
				throw new ApplicationException("Connect/disconnect message found, aborting DB save process");
			}
		}

	}
}
