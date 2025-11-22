package org.magma.falae.repository;

import org.magma.falae.model.Chat;
import org.magma.falae.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    @Query("SELECT c FROM Chat c " +
            "WHERE c.isGroupChat = false " +
            "AND SIZE(c.participants) = 2 " +
            "AND :userA MEMBER OF c.participants " +
            "AND :userB MEMBER OF c.participants")
    Optional<Chat> findPrivateChatByParticipants(
            @Param("userA") User userA,
            @Param("userB") User userB
    );

    @Query("SELECT c FROM Chat c JOIN c.messages m " +
            "WHERE :user MEMBER OF c.participants " +
            "GROUP BY c " +
            "ORDER BY MAX(m.timestamp) DESC")
    List<Chat> findAllByParticipantsContains(@Param("user") User user);
}