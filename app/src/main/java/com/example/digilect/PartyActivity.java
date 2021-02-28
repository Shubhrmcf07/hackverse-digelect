package com.example.digilect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;

import com.example.digilect.api.APIClient;
import com.example.digilect.api.APIInterfaceElections;
import com.example.digilect.api.APIInterfaceParties;
import com.example.digilect.models.Election;
import com.example.digilect.models.Party;
import com.example.digilect.models.ResponseModel;
import com.example.digilect.models.ResponseParty;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter partyListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Party> mPartyList;
    String electionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);
        electionId=getIntent().getExtras().getString("electionId");

        mPartyList=new ArrayList<>();
        initializeRecyclerView();
        getPartyList();
    }

    private void getPartyList() {
        final APIInterfaceParties apiService = APIClient.getClient().create(APIInterfaceParties.class);
        Call<ResponseParty> call = apiService.getPartiesList(electionId);
        call.enqueue(new Callback<ResponseParty>() {
            @Override
            public void onResponse(Call<ResponseParty>call, Response<ResponseParty>response) {
                if(response.body().getStatus()==200) {
                    List<Party> partyList = response.body().getParties();
                    for(Party party:partyList) {
                        mPartyList.add(party);
                        partyListAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseParty>call, Throwable t) {
            }
        });
    }

    private void initializeRecyclerView() {

        recyclerView=findViewById(R.id.elections);
        recyclerView.setNestedScrollingEnabled (false);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        partyListAdapter=new PartyListAdapter(mPartyList,PartyActivity.this);
        recyclerView.setAdapter(partyListAdapter);
    }

}