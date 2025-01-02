package se.lexicon.assistantchatbot.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.assistantchatbot.config.ChatConfig;

@Service
public class AssistantServiceImpl implements AssistantService {

    private final OpenAiChatModel openAiChatModel;
    private final ChatConfig chatConfig;
    @Autowired
    public AssistantServiceImpl(OpenAiChatModel openAiChatModel, ChatConfig chatConfig) {
        this.openAiChatModel = openAiChatModel;
        this.chatConfig = chatConfig;
    }

    @Override
    public String chat(String input) {
        String difficulty = "Beginner";

        /*String aiPromptTemplate = """
                You are a highly intelligent and patient teaching assistant.
                Your role is to explain concepts clearly, answer questions concisely, and provide examples for better understanding.
                Always adapt your response to the user's level of knowledge.

                Query: %s
                Difficulty level: %s

                Provide a clear, structured explanation and use examples when helpful.
                """;*/

        String prompt = String.format(chatConfig.teacherAssistantPromptTemplate(), input, difficulty);
        /*OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withModel("gpt-4o")
                .withTemperature(0.5)
                .withMaxTokens(750)
                .withFrequencyPenalty(0.2)
                .build();*/

        Prompt chatbotPrompt = new Prompt(prompt, chatConfig.teacherAssistantChatOptions());
        ChatResponse response = openAiChatModel.call(chatbotPrompt);

        Generation result = response.getResult();
        /*if (result != null) {
            return result.getOutput().getContent();
        } else {
            return "No response received";
        }*/
        return result != null ? result.getOutput().getContent() : "No response received";

    }

    @Override
    public String chatMemory(String chatId, String input) {
        return "";
    }
}
