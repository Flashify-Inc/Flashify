package com.example.flashify;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashify.api.ChatCompletionRequest;
import com.example.flashify.api.ChatCompletionResponse;
import com.example.flashify.api.ChatCompletionService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagicTextView extends AppCompatActivity {

    static String apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_text_view);

        Button flashifyBtn = findViewById(R.id.FlashifyBtn);

        flashifyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generateFlashcards();
            }
        });

    }



    private void generateFlashcards() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatCompletionService chatCompletionService = retrofit.create(ChatCompletionService.class);

        String model = getString(R.string.model);
        String systemApiPrompt = getString(R.string.system_api_prompt);
        EditText editText = findViewById(R.id.editText);
        String userInputText = editText.getText().toString();

        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest(model, systemApiPrompt, userInputText);

        Call<ChatCompletionResponse> call = chatCompletionService.retrieveFlashcards(chatCompletionRequest);

        call.enqueue(new Callback<ChatCompletionResponse>() {
                         @Override
                         public void onResponse(Call<ChatCompletionResponse> call, Response<ChatCompletionResponse> response) {
                             if (response.isSuccessful()) {
                                 MagicTextView.apiResponse = response.body().getCompletion();
                                 Log.d("APIRESPONSE:", MagicTextView.apiResponse);
                                 editText.setText(MagicTextView.apiResponse);
                             } else {
                                 try {
                                     Log.e("FAILED API CALL", "Error: " + response.code() + " " + response.errorBody().string());
                                 } catch (IOException e) {
                                     throw new RuntimeException(e);
                                 }
                             }

                         }

                         @Override
                         public void onFailure(Call<ChatCompletionResponse> call, Throwable t) {
                             Log.e(this.getClass().getSimpleName(), "Exception calling endpoint", t);

                         }
                     }

        );
    }
}

