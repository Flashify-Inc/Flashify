package com.example.flashify.api;

public class ChatCompletionRequest {
    private String model;
    private Message[] messages;

    public ChatCompletionRequest(String model, String systemApiPrompt, String userInputText) {
        this.model = model;
        messages = new Message[] {
                new Message("system", systemApiPrompt),
                new Message("user", userInputText)
        };
    }
}
