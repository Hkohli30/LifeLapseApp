package com.example.lifelapseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifelapseapp.model.ApiCharacter;
import com.example.lifelapseapp.services.AsyncImageManager;

import static com.example.lifelapseapp.constants.Constants.CHARACTER_TAG;

public class CharacterDetailActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        textView = findViewById(R.id.character_details);
        imageView = findViewById(R.id.detail_image);
        ApiCharacter character = (ApiCharacter) getIntent().getSerializableExtra(CHARACTER_TAG);
        if (character != null) {
            textView.setText(character.toString());
            if (!character.getImage().isEmpty()) {
                AsyncImageManager manager = new AsyncImageManager(character.getImage(), imageView);
                manager.execute();
            }
        }
    }
}