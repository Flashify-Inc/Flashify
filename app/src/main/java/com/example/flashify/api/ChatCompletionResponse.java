package com.example.flashify.api;

public class ChatCompletionResponse {

    private String id;
    private String object;
    private String created;
    private Choice[] choices;
    private Usage usage;

    private class Choice {
        int index;
        String finish_reason;
        Message message;

        public String getMessage() {
            return message.getContent();
        }
    }

    private class Usage {
        int prompt_tokens;
        int completion_tokens;
        int total_tokens;
    }

    public String getCompletion() {
        return choices[0].getMessage();
    }
}
