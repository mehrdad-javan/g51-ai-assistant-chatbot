package se.lexicon.assistantchatbot.service;

public interface AssistantService {
    String chat(String input);

    String chatMemory(String chatId, String input);
}
