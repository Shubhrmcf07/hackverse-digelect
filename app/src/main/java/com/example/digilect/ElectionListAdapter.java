package com.example.digilect;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.digilect.models.Election;

import java.util.List;

public class ElectionListAdapter extends RecyclerView.Adapter<ElectionListAdapter.ViewHolder> {
    private List<Election> electionList;

    public ElectionListAdapter(List<Election> electionList) {
        this.electionList = electionList;
    }

    @Override
    public ElectionListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.election_item, viewGroup, false);
        return new ElectionListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElectionListAdapter.ViewHolder viewHolder, int position) {
        final Election election = electionList.get(position);
        if (!TextUtils.isEmpty(election.getElectionTitle())) {
            viewHolder.mTitle.setText(election.getElectionTitle());
        }
        viewHolder.mLayout.setTag(election);
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), VoterInput.class);
                Bundle bundle=new Bundle();
                bundle.putString("electionId",election.getId());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private RelativeLayout mLayout;
        ViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.layout);
            mTitle=view.findViewById(R.id.title);
        }
    }
}
