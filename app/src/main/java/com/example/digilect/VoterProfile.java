package com.example.digilect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digilect.models.Voter;
import com.squareup.picasso.Picasso;

public class VoterProfile extends AppCompatActivity {

    Voter voter;
    ImageView imageView;
    String electionId;
    TextView name,voterId,gender,constituency,state,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_profile);
        electionId=getIntent().getExtras().getString("electionId");
        voter=(Voter) getIntent().getSerializableExtra("Voter");
        imageView=findViewById(R.id.picture);
        Picasso
                .get()
                .load(voter.getPhotoURL())
                .placeholder(R.drawable.loading_small)
                .into(imageView);
        name=findViewById(R.id.name);
        voterId=findViewById(R.id.voterid);
        gender=findViewById(R.id.gender);
        constituency=findViewById(R.id.constituency);
        state=findViewById(R.id.state);
        phone=findViewById(R.id.phone);
        name.setText(voter.getName());
        voterId.setText(voter.getVoterID());
        String gen="";
        switch(voter.getGender()){
            case 0:
                gen="Female";
                break;
            case 1:
                gen="Male";
                break;
            case 2:
                gen="Other";
                break;
        }
        gender.setText(gen);
        constituency.setText(voter.getConstituency());
        state.setText(voter.getState());
        phone.setText(voter.getPhone());
        findViewById(R.id.proceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VoterProfile.this,PhotoVerification.class);
                intent.putExtra("electionId",electionId);
                startActivity(intent);
            }
        });
    }
}