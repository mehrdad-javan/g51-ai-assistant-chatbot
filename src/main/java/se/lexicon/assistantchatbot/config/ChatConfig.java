package se.lexicon.assistantchatbot.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {


    @Bean
    public ChatMemory chatMemory(){
        // 1. InMemoryChatMemory

        // 2. RedisChatMemory
        // 3. Database-Backend
        return new InMemoryChatMemory();
    }

    @Bean
    public OpenAiChatOptions teacherAssistantChatOptions() {
        return OpenAiChatOptions.builder()
                .withModel("gpt-4o")
                .withTemperature(0.5)
                .withMaxTokens(750)
                .withFrequencyPenalty(0.2)
                .build();
    }

    @Bean
    public String teacherAssistantPromptTemplate() {
        return """
                You are a highly intelligent and patient teaching assistant.
                Your role is to explain concepts clearly, answer questions concisely, and provide examples for better understanding.
                Always adapt your response to the user's level of knowledge.
                
                Query: %s
                Difficulty level: %s
                
                Provide a clear, structured explanation and use examples when helpful.
                """;
    }

    @Bean
    public String mathExpertPromptTemplate() {
        return """
                """;
    }

}
