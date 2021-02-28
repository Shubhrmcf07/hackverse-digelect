package com.example.digilect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.digilect.api.APIClient;
import com.example.digilect.api.APIInterfaceElections;
import com.example.digilect.api.APIInterfaceVoter;
import com.example.digilect.models.ResponseVoter;
import com.example.digilect.models.Voter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoterInput extends AppCompatActivity {

    EditText voterId,dob;
    String electionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_input);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        voterId=findViewById(R.id.voterid);
        electionId=getIntent().getExtras().getString("electionId");
        dob=findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            int mYear, mMonth, mDay;
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(VoterInput.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String day="",mon="";
                                if(dayOfMonth<10)
                                    day+="0"+dayOfMonth;
                                else
                                    day+=dayOfMonth;
                                if((monthOfYear+1)<10)
                                    mon+="0"+(monthOfYear+1);
                                else
                                    mon+=(monthOfYear+1);
                                dob.setText(day + "-" + mon + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate(){
        final APIInterfaceVoter apiService = APIClient.getClient().create(APIInterfaceVoter.class);
        Call<ResponseVoter> call = apiService.getVoter(voterId.getText().toString(), dob.getText().toString(),electionId);
        call.enqueue(new Callback<ResponseVoter>() {
            @Override
            public void onResponse(Call<ResponseVoter>call, Response<ResponseVoter> response) {
                if(response.body().getStatus()==200) {
                    Intent intent=new Intent(VoterInput.this,VoterProfile.class);
                    intent.putExtra("Voter",  response.body().getVoter());
                    intent.putExtra("electionId",electionId);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VoterInput.this);
                    alertDialogBuilder.setMessage(response.body().getMessage());
                            alertDialogBuilder.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            voterId.setText("");
                                            dob.setText("");
                                        }
                                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    voterId.setText("");
                    dob.setText("");
                }
            }
            @Override
            public void onFailure(Call<ResponseVoter>call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}