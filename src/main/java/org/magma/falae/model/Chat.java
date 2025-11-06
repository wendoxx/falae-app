package org.magma.falae.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.magma.falae.dto.response.ChatResponseDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    private UUID id;

    @OneToMany(mappedBy = "chat")

    private Set<Message> messages;

    private boolean isGroupChat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_participants", // Nome da tabela que liga Chats e Users
            joinColumns = @JoinColumn(name = "chat_id"), // Chave estrangeira para ESTA classe (Chat)
            inverseJoinColumns = @JoinColumn(name = "user_id") // Chave estrangeira para a OUTRA classe (User)
    )
    private Set<User> participants = new HashSet<>();

    public Chat(ChatResponseDTO chatResponseDTO){
        this.id = chatResponseDTO.id();
        this.messages = chatResponseDTO.messages();
        this.isGroupChat = chatResponseDTO.isGroupChat();
    }
}
