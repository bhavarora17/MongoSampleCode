/**
 * repository class used in database interaction
 */
package com.sample.messaging.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.messaging.entity.MessageEntity;

@Repository
public interface MessagingRepo extends MongoRepository<MessageEntity, String> {

	/**
	 * method to find messages of a user in descending order
	 * 
	 * @param userId
	 * @return
	 */
	public List<MessageEntity> findByUserIdOrderByTimestampDesc(String userId);

	/**
	 * method to find limited messages of a user in descending order
	 * 
	 * @param userId
	 * @param pageable
	 * @return
	 */
	public List<MessageEntity> findByUserIdOrderByTimestampDesc(String userId, Pageable pageable);
}
