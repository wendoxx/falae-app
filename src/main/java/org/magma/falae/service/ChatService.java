package org.magma.falae.service;

import org.magma.falae.dto.request.ChatRequestDTO;
import org.magma.falae.dto.response.ChatResponseDTO;
import org.magma.falae.model.Chat;
import org.magma.falae.model.User;
import org.magma.falae.repository.ChatRepository;
import org.magma.falae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Set<User> participants = chatRequestDTO.participants().stream().map(id -> userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."))).collect(Collectors.toSet());

        participants.add(currentUser);

        /*
        // if its a chat group or not
        if() {

        } else {

        }
        */
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
