package com.example.digilect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter electionListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
//    List<ElectionObject> electionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
     //   electionList=new ArrayList<>();
        initializeRecyclerView();
        getElectiontList();



        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                finish();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                finish();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login.")
                .setSubtitle("Login using the fingerprint sensor of your device.")
                .setNegativeButtonText("Close")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void getElectiontList() {
    }

    private void initializeRecyclerView() {

        recyclerView=findViewById(R.id.elections);
        recyclerView.setNestedScrollingEnabled (false);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
  //      electionListAdapter=new ElectionListAdapter(electionList);
        recyclerView.setAdapter(electionListAdapter);
    }
}