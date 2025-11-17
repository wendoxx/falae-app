package org.magma.falae.service;

import org.magma.falae.dto.request.ChatRequestDTO;
import org.magma.falae.dto.response.ChatResponseDTO;
import org.magma.falae.model.Chat;
import org.magma.falae.model.User;
import org.magma.falae.repository.ChatRepository;
import org.magma.falae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ChatResponseDTO getOrCreateChat(ChatRequestDTO chatRequestDTO) {

        User currentUser = userService.getAuthenticatedUser();

        Set<User> participants = chatRequestDTO.participants()
                .stream()
                .map(id -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found."))).collect(Collectors.toSet());

        participants.add(currentUser);

        /*
        // if its a chat group or not
        if() {

        } else {

        }
        */
        return null;
    }

    public ChatResponseDTO handleCreatePrivateChat(Set<User> participants) {

        if(participants.size() != 2) {
            throw new RuntimeException("Private chat must have exactly two participants.");
        }

        Iterator<User> iterator = participants.iterator();
        User userA = iterator.next();
        User userB = iterator.next();

        Chat chat = chatRepository.findPrivateChatByParticipants(userA, userB).orElseGet(() -> {
            Chat newChat = new Chat();
            newChat.setGroupChat(false);

            newChat.addParticipant(userA);
            newChat.addParticipant(userB);

            return chatRepository.save(newChat);
        });

        return null; // Convert Chat to ChatResponseDTO
    }

    public ChatResponseDTO handleCreateGroupChat(Set<User> participants) {

        Chat groupChat = new Chat();
        groupChat.setGroupChat(true);
        groupChat.setParticipants(participants);

        Chat savedChat = chatRepository.save(groupChat);
        
        return null;
    }
    public void getChat() {
        // Implementation goes here
    }

    public void deleteChat() {
        // Implementation goes here
    }

    public void updateChat() {
        // Implementation goes here
    }

    public void listChats() {
        // Implementation goes here
    }

    public void createGroupChat() {
        // Implementation goes here
    }

    public void addUserToGroupChat() {
        // Implementation goes here
    }

    public void removeUserFromGroupChat() {
        // Implementation goes here
    }
}
