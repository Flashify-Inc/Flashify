package com.example.flashify;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    String apiResponse;
    String userInputText;
    Flashcard[] generatedFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_text_view);

        Button flashifyBtn = findViewById(R.id.FlashifyBtn);

        flashifyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                userInputText = editText.getText().toString();

                if (userInputText.length() > 3000) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MagicTextView.this);
                    builder.setMessage(getString(R.string.lengthWarning))
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    generateFlashcards();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    generateFlashcards();
                }
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

        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest(model, systemApiPrompt, userInputText);

        Call<ChatCompletionResponse> call = chatCompletionService.retrieveFlashcards(chatCompletionRequest);

        call.enqueue(new Callback<ChatCompletionResponse>() {
                         @Override
                         public void onResponse(Call<ChatCompletionResponse> call, Response<ChatCompletionResponse> response) {
                             if (response.isSuccessful()) {
                                 apiResponse = response.body().getCompletion();
                                 Log.d("APIRESPONSE:", apiResponse);
                                 generatedFlashcards = convertStringToFlashcards(apiResponse);
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

    private Flashcard[] convertStringToFlashcards(String apiOutput) {
        String[] cardStrings = apiOutput.split("\\|\\|\\|"); // split input string into card strings

        Flashcard[] flashcards = new Flashcard[cardStrings.length]; // create array of Flashcards with appropriate size

        for (int i = 1; i < cardStrings.length; i++) {
            String[] cardParts = cardStrings[i].split("///"); // split card string into front and back parts
            flashcards[i-1] = new Flashcard(cardParts[0], cardParts[1]); // create and store new Flashcard object
        }

        return flashcards; // return array of Flashcards
    }
}