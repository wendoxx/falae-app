package org.magma.falae.service;

import org.magma.falae.dto.request.ChatRequestDTO;
import org.magma.falae.dto.response.ChatResponseDTO;
import org.magma.falae.model.Chat;
import org.magma.falae.model.User;
import org.magma.falae.repository.ChatRepository;
import org.magma.falae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;


    /*public ChatResponseDTO createPrivateChat(ChatRequestDTO chatRequestDTO) {
        Chat chat;

        User authenticatedUser =
       // add variable for user 1 and user 2 to method parameters

        if(chatRepository.findPrivateChatByParticipants()){
            // Logic for creating one-on-one chat
        } else {
            // Logic for creating group chat
        }
        return null; // Replace with actual return
    }
     */


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
