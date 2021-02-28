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

import com.example.digilect.api.APIClient;
import com.example.digilect.api.APIInterfaceElections;
import com.example.digilect.models.Election;
import com.example.digilect.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter electionListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    List<Election> mElectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        mElectionList=new ArrayList<>();
        initializeRecyclerView();
        getElectionList();



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
                        "Welcome to Digilect!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login.")
                .setSubtitle("Login using the fingerprint sensor of your device.")
                .setNegativeButtonText("Close")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void getElectionList() {
        final APIInterfaceElections apiService = APIClient.getClient().create(APIInterfaceElections.class);
        Call<ResponseModel> call = apiService.getElectionsList();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus()==200) {
                    List<Election> electionList = response.body().getElections();
                    for(Election election:electionList) {
                        mElectionList.add(election);
                        electionListAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
            }
        });
    }

    private void initializeRecyclerView() {

        recyclerView=findViewById(R.id.elections);
        recyclerView.setNestedScrollingEnabled (false);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        electionListAdapter=new ElectionListAdapter(mElectionList);
        recyclerView.setAdapter(electionListAdapter);
    }
}