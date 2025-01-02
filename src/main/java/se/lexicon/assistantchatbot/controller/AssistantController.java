package se.lexicon.assistantchatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.assistantchatbot.service.AssistantService;

@RestController
public class AssistantController {

    AssistantService service;

    @Autowired
    public AssistantController(AssistantService service) {
        this.service = service;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String question) {
        // todo: add validation
        return ResponseEntity.ok(service.chat(question));
    }

    @GetMapping("/memory/chat")
    public ResponseEntity<String> chatMemory(@RequestParam String chatId, @RequestParam String question) {
        // todo: add validation
        return ResponseEntity.ok(service.chatMemory(chatId, question));
    }

}
