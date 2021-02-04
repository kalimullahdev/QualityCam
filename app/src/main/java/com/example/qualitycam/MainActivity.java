package com.example.qualitycam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainCardsAdaptor adapter;
    TextView card_detail_text, signOut_text;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        card_detail_text = findViewById(R.id.card_detail_text);
        signOut_text = findViewById(R.id.signOut_text);

        mAuth = FirebaseAuth.getInstance();


        List<SingleCard> data = new ArrayList<>();

        SingleCard singleCard1 = new SingleCard("Canon EOS Rebel SL3 / 250D", R.drawable.camera1,240);
        SingleCard singleCard2 = new SingleCard("Olympus OM-D E-M10 Mark IV", R.drawable.camera2,590);
        SingleCard singleCard3 = new SingleCard("Fujifilm X-S10", R.drawable.camera3,374);
        SingleCard singleCard4 = new SingleCard("Nikon Z5", R.drawable.camera1,190);


        data.add(singleCard1);
        data.add(singleCard2);
        data.add(singleCard3);
        data.add(singleCard4);

        card_detail_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardDetailsActivity.class);
                startActivity(intent);
            }
        });

        adapter = new MainCardsAdaptor(MainActivity.this,data);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        signOut_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 signOutUser();


            }
        });


    }

    private void signOutUser() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logging out the User");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        builder.setNegativeButton("No", null);
        builder.show();




    }


}