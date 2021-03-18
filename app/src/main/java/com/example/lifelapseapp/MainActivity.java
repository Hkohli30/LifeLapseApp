package com.example.lifelapseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifelapseapp.adapters.MainRecyclerViewAdapter;
import com.example.lifelapseapp.model.ApiCharacter;
import com.example.lifelapseapp.model.PageInfo;
import com.example.lifelapseapp.services.ApiDataRetriever;

import java.util.ArrayList;
import java.util.List;

import static com.example.lifelapseapp.constants.Constants.SEARCH_SUGGESTION;
import static com.example.lifelapseapp.constants.Constants.SEARCH_TITLE_MESSAGE;
import static com.example.lifelapseapp.constants.Constants.baseURL;
import static com.example.lifelapseapp.constants.Constants.pageAppender;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<ApiCharacter> characterList;
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;

    private Integer currentPage = 1;
    private PageInfo pageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupButtons();
        setUpRecyclerViewElements();
    }

    /**
     * sets up the buttons
     */
    private void setupButtons() {
        findViewById(R.id.main_prev_button).setOnClickListener(this);
        findViewById(R.id.main_next_button).setOnClickListener(this);
        findViewById(R.id.main_search_button).setOnClickListener(this);
    }

    /**
     * Init the recyclerview and starts the operations
     */
    private void setUpRecyclerViewElements() {
        characterList = new ArrayList<>();
        recyclerView = findViewById(R.id.main_recyclerview);
        mainRecyclerViewAdapter = new MainRecyclerViewAdapter(characterList, getApplicationContext());
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setAdapter(mainRecyclerViewAdapter);
        recyclerView.setLayoutManager(manager);
        pageInfo = new PageInfo("", "");
        ApiDataRetriever.getInstance().fetchData(baseURL + pageAppender + currentPage, this,
                characterList, mainRecyclerViewAdapter, pageInfo);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_prev_button:
                if (pageInfo != null && !pageInfo.getPrev().equalsIgnoreCase("null")) {
                    Toast.makeText(this, pageInfo.getPrev() + " loaded", Toast.LENGTH_SHORT).show();
                    ApiDataRetriever.getInstance().fetchData(pageInfo.getPrev(), this,
                            characterList, mainRecyclerViewAdapter, pageInfo);
                }
                break;
            case R.id.main_next_button:
                if (pageInfo != null && !pageInfo.getNext().equalsIgnoreCase("null")) {
                    Toast.makeText(this, pageInfo.getNext() + " loaded", Toast.LENGTH_SHORT).show();
                    ApiDataRetriever.getInstance().fetchData(pageInfo.getNext(), this,
                            characterList, mainRecyclerViewAdapter, pageInfo);
                }
                break;
            case R.id.main_search_button:
                dialogOperation();
                break;
        }
    }

    /**
     * dialog operation to show search dialog
     */
    private void dialogOperation() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = this.getLayoutInflater().inflate(R.layout.search_layout, null);
        builder.setView(dialogView);
        builder.setTitle(SEARCH_TITLE_MESSAGE);
        final EditText editText = dialogView.findViewById(R.id.search_layout_edit_text);
        ((TextView) dialogView.findViewById(R.id.search_layout_text_view)).setText(SEARCH_SUGGESTION);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String data = editText.getText().toString().trim();
                if (data != null && !data.isEmpty()) {
                    ApiDataRetriever.getInstance().fetchData(baseURL + data, MainActivity.this,
                            characterList, mainRecyclerViewAdapter, pageInfo);
                }
            }
        });
        builder.setNegativeButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApiDataRetriever.getInstance().fetchData(baseURL + pageAppender + 1, MainActivity.this,
                        characterList, mainRecyclerViewAdapter, pageInfo);
            }
        });
        builder.create().show();
    }


    /**
     * Show the dialog to exit or to return to home page
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Choose Action")
                .setMessage("Go home page or exit")
                .setPositiveButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiDataRetriever.getInstance().fetchData(baseURL + pageAppender + 1, MainActivity.this,
                                characterList, mainRecyclerViewAdapter, pageInfo);
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}