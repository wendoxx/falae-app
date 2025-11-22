package org.magma.falae.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.magma.falae.dto.request.MessageRequestDTO;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public Message (MessageRequestDTO messageRequestDTO, User sender, Chat chat) {
        this.content = messageRequestDTO.content();
        this.sender = sender;
        this.chat = chat;
        this.timestamp = LocalDateTime.now();
    }
}
