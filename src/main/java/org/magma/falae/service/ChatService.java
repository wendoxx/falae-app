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
import java.util.UUID;
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

        if(!chatRequestDTO.isGroupChat()) {
            return handleCreatePrivateChat(participants);
        } else {
            return handleCreateGroupChat(participants);
        }
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
        return convertToChatResponseDTO(chat);
    }

    public ChatResponseDTO handleCreateGroupChat(Set<User> participants) {

        Chat groupChat = new Chat();
        groupChat.setGroupChat(true);
        groupChat.setParticipants(participants);

        Chat savedChat = chatRepository.save(groupChat);

        return convertToChatResponseDTO(savedChat);
    }

    public ChatResponseDTO convertToChatResponseDTO(Chat chat) {
        return new ChatResponseDTO(
                chat.getId(),
                chat.getMessages(),
                chat.isGroupChat(),
                chat.getParticipants()
        );
    }
    public ChatResponseDTO getChatById(UUID id) {
        return chatRepository.findById(id)
                .map(this::convertToChatResponseDTO)
                .orElseThrow(() -> new RuntimeException("Chat not found."));

    }

    public void deleteChat(UUID id) {
        if(!chatRepository.existsById(id)) {
            throw new RuntimeException("Chat not found.");
        }
        chatRepository.deleteById(id);
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
