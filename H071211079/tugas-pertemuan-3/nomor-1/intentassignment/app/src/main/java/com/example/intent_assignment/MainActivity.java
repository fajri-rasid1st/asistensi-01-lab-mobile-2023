package com.example.intent_assignment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.intent_assignment.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    ActivityResultLauncher<Intent> imageSelectLauncher;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imageSelectLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK || result.getData() != null) {
                Intent intent = result.getData();
                Uri uri = intent != null ? intent.getData() : null;

                try {
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    binding.imageProfile.setImageBitmap(image);
                }catch (IOException ioException) {
                    throw new RuntimeException(ioException);
                }
            }
        });

        binding.imageProfile.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            imageSelectLauncher.launch(intent);
            binding.imageProfile.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#403E3E")));
        });


        binding.buttonSubmit.setOnClickListener(view -> {
            Intent submit = new Intent(this, MainActivity2.class);
            User.name = Objects.requireNonNull(binding.editText1.getText()).toString();
            User.username = Objects.requireNonNull(binding.editText2.getText()).toString();
            User.image = image;

            if (User.name.isEmpty() || User.username.isEmpty() || User.image == null) {
                if (User.name.isEmpty()) {
                    binding.editText1.setError("Silahkan di isi!");
                }
                if (User.username.isEmpty()) {
                    binding.editText2.setError("Silahkan di isi!");
                }
                if (User.image == null) {
                    binding.imageProfile.setStrokeColor(ColorStateList.valueOf(Color.parseColor("#D51A1A")));
                }
                return;
            }
            startActivity(submit);

        });
    }
}