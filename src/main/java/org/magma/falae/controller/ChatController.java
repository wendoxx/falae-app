package org.magma.falae.controller;

import org.magma.falae.dto.request.ChatRequestDTO;
import org.magma.falae.dto.response.ChatResponseDTO;
import org.magma.falae.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/get-or-create-chat")
    public ResponseEntity<ChatResponseDTO> getOrCreateChat(@RequestBody ChatRequestDTO chatRequestDTO) {
        return ResponseEntity.status(201).body(chatService.getOrCreateChat(chatRequestDTO));
    }

    @GetMapping("/user-chats")
    public ResponseEntity<List<ChatResponseDTO>> getChatsByUser() {
        return ResponseEntity.ok(chatService.listUserChats());
    }
    

    @DeleteMapping("/delete-chat/{id}")
    public ResponseEntity<ChatResponseDTO> deleteChat(@PathVariable UUID id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}
