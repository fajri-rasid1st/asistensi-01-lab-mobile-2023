package com.example.tgsprak3quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tgsprak3quiz.databinding.ActivityResultBinding;
import com.example.tgsprak3quiz.model.User;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(QuestionActivity.EXSTRA_USER);

        binding.tvScore.setText("Score : " + user.Score);
        binding.tvBestScore.setText("Best Score : " + user.BestScore);
        binding.btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(QuestionActivity.EXSTRA_USER,user);
            startActivity(intent);
        });
    }

}