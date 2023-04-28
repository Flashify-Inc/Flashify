package com.example.flashify.api;

public class ChatCompletionResponse {

    private String id;
    private String object;
    private String created;
    private String model;
    private Choices[] choices;
    private Usage usage;

    private class Choices {
        String text;
        int index;
        Object logprobs;
        String finish_reason;

        public String getText() {
            return text;
        }
    }

    private class Usage {
        int prompt_tokens;
        int completion_tokens;
        int total_tokens;
    }

    public String getCompletion() {
        return choices[0].getText();
    }
}
