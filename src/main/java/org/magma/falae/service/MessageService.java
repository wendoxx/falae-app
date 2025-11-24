package org.magma.falae.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.magma.falae.dto.request.MessageRequestDTO;
import org.magma.falae.dto.response.MessageResponseDTO;
import org.magma.falae.model.Chat;
import org.magma.falae.model.Message;
import org.magma.falae.model.User;
import org.magma.falae.repository.ChatRepository;
import org.magma.falae.repository.MessageRepository;
import org.magma.falae.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger();

    public MessageResponseDTO createMessage(MessageRequestDTO messageRequestDTO) {

        LOGGER.info("Posting a new message...");
        Message message = new Message();


        Chat chat = chatRepository.findById(messageRequestDTO.chatId())
                .orElseThrow(() -> {
                    LOGGER.error("Chat not found.");
                    return new RuntimeException("Chat not found.");
                });

        User user = userRepository.findById(messageRequestDTO.userId())
                .orElseThrow(() -> { LOGGER.error("User not found.");
                    return new RuntimeException("User not found.");
                });

        message.setChat(chat);
        message.setSender(user);
        message.setContent(messageRequestDTO.content());
        Message savedMessage = messageRepository.save(message);
        LOGGER.info("Message posted successfully.");
        return convertToDTO(savedMessage);
    }

    public MessageResponseDTO updateMessage(MessageRequestDTO messageRequestDTO) {
        Message message = messageRepository.findById(messageRequestDTO.id()).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(messageRequestDTO.content());
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    public MessageResponseDTO getMessageById(UUID id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found."));
        return convertToDTO(message);
    }

    public void deleteMessageById(UUID id) {
        messageRepository.deleteById(id);
    }

    public void getMessagesByChatId() {
        // Implementation goes here
    }

    public MessageResponseDTO convertToDTO(Message message) {
        return new MessageResponseDTO(
                message.getId(),
                message.getChat().getId(),
                message.getSender().getId(),
                message.getContent(),
                message.getTimestamp()
                );
    }
}
