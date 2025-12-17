package org.magma.falae.controller;

import org.magma.falae.dto.request.MessageRequestDTO;
import org.magma.falae.dto.response.MessageResponseDTO;
import org.magma.falae.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send-message")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        return ResponseEntity.status(201).body(messageService.createMessage(messageRequestDTO));
    }

    @PutMapping("/update-message")
    public ResponseEntity<MessageResponseDTO> updateMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        return ResponseEntity.status(201).body(messageService.updateMessage(messageRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> getMessageById(@PathVariable UUID id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Page<MessageResponseDTO>> listMessageByChatId(@PathVariable UUID chatId, Pageable pageable) throws AccessDeniedException {
        return ResponseEntity.ok(messageService.listMessagesByChatId(chatId, pageable));
    }

    @DeleteMapping("/delete-message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.noContent().build();
    }
}
