package com.example.flashify;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ChatCompletionService {
    @Headers("Authorization: Bearer OPENAI_API_KEY")
    @POST("/v1/chat/completions")
    Call<ChatCompletionResponse> retrieveFlashcards(@Query("model") String model,
                                                    @Query("messages") String[] messages
                                                    );
}
