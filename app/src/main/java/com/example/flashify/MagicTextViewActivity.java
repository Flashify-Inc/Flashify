package com.example.flashify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.flashify.api.ChatCompletionRequest;
import com.example.flashify.api.ChatCompletionResponse;
import com.example.flashify.api.ChatCompletionService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagicTextViewActivity extends AppCompatActivity {

    String apiResponse;
    String userInputText;
    Button flashifyBtn;

    boolean isLoading;
    TextView text;
    EditText editText;
    LottieAnimationView loadingAnimation, writting, fronto;
    static ArrayList<Flashcard> generatedFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_text_view);

        flashifyBtn = findViewById(R.id.FlashifyBtn);
        loadingAnimation = findViewById(R.id.animationView);
        writting = findViewById(R.id.fronti);
        fronto = findViewById(R.id.fronto);


        flashifyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                text = findViewById(R.id.textTitle);
                editText = findViewById(R.id.editText);

                userInputText = editText.getText().toString();


                if (userInputText.length() > 3000) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MagicTextViewActivity.this);
                    EditText finalEditText = editText;
                    builder.setMessage(getString(R.string.lengthWarning))
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    loadScreen( text, finalEditText, flashifyBtn);
                                    loadingAnimation.setVisibility(View.VISIBLE);
                                    fronto.setVisibility(View.VISIBLE);
                                    writting.setVisibility(View.INVISIBLE);
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
                    loadScreen( text, editText, flashifyBtn);
                    loadingAnimation.setVisibility(View.VISIBLE);
                    fronto.setVisibility(View.VISIBLE);
                    writting.setVisibility(View.INVISIBLE);

                    Log.d("API", "onClicked before generatecards");
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
        isLoading = true;
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest(model, systemApiPrompt, userInputText);
        Log.d("APIRESPONSErequest:", "apiresponserequest...");


        Call<ChatCompletionResponse> call = chatCompletionService.retrieveFlashcards(chatCompletionRequest);

        call.enqueue(new Callback<ChatCompletionResponse>() {
                         @Override
                         public void onResponse(Call<ChatCompletionResponse> call, Response<ChatCompletionResponse> response) {
                             if (response.isSuccessful()) {
                                 apiResponse = response.body().getCompletion();
                                 Log.d("APIRESPONSE:", apiResponse);
                                 generatedFlashcards = convertStringToFlashcards(apiResponse);
                                 Intent intent = new Intent(MagicTextViewActivity.this, SaveCardsActivity.class);
                                 startActivity(intent);
                             } else {
                                 UnloadScreen(text, editText, flashifyBtn);
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

    public void loadScreen(TextView T, EditText ET, Button B){
        B.setVisibility(View.INVISIBLE);

        ET.setVisibility(View.INVISIBLE);
        T.setVisibility(View.INVISIBLE);
    }

    public void UnloadScreen( TextView T, EditText ET, Button B){
        B.setVisibility(View.VISIBLE);

        ET.setVisibility(View.VISIBLE);
        T.setVisibility(View.VISIBLE);
    }

    private ArrayList<Flashcard> convertStringToFlashcards(String apiOutput) {
        String[] cardStrings = apiOutput.split("\\|\\|\\|"); // split input string into card strings

        ArrayList<Flashcard> flashcards = new ArrayList<Flashcard>(); // create array of Flashcards with appropriate size

        for (int i = 1; i < cardStrings.length; i++) {
            String[] cardParts = cardStrings[i].split("///"); // split card string into front and back parts
            flashcards.add(new Flashcard(cardParts[0], cardParts[1])); // create and store new Flashcard object
        }

        return flashcards; // return array of Flashcards
    }

    @Override
    public void onBackPressed() {
        if (!isLoading) {
            super.onBackPressed();
            Intent intentF = new Intent(MagicTextViewActivity.this, MainActivity.class);
            startActivity(intentF);
        }
    }
}