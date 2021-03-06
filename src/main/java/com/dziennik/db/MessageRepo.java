package com.dziennik.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dziennik.model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
	
	@Query(value = "select new java.lang.Boolean(count(*) > 0 ) from Message where fromid=:fromid and toid=:toid and isread=1")
	Boolean newMessage(@Param("fromid") Long fromid,
						@Param("toid") Long toid);
	
	@Query(value = "From Message m where ((m.fromid=:person1 and m.toid=:person2) or (m.fromid=:person2 and m.toid=:person1)) ORDER BY m.timestampmsg DESC")
	List<Message> findChat(@Param("person1") Long person1,
							@Param("person2") Long person2);

	@Query(value = "select new java.lang.Boolean(count(*) > 0 ) from Message where toid=:toid and isread=1")
	Boolean amIHaveMassage(@Param("toid") Long toid);
}
