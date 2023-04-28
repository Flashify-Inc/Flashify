package com.example.flashify.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChatCompletionService {
    @Headers("Authorization: Bearer OPENAI_API_KEY")
    @POST("/v1/chat/completions")
    Call<ChatCompletionResponse> retrieveFlashcards(@Body ChatCompletionRequest chatCompletionRequest);
}
