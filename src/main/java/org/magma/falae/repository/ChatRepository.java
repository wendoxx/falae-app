package org.magma.falae.repository;

import org.magma.falae.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    /*
   TODO: implement this method when User entity is created
   List<Chat> findByUserName(String username);
   */
}