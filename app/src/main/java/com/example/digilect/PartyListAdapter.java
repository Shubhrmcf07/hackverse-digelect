package com.example.digilect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.digilect.models.Election;
import com.example.digilect.models.Party;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PartyListAdapter extends RecyclerView.Adapter<PartyListAdapter.ViewHolder> {
    private List<Party> partyList;
    Context context;
    public PartyListAdapter(List<Party> partyList, Context context) {
        this.partyList = partyList;
        this.context=context;
    }

    @Override
    public PartyListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.party_item, viewGroup, false);
        return new PartyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PartyListAdapter.ViewHolder viewHolder, int position) {
        final Party party = partyList.get(position);
        if (!TextUtils.isEmpty(party.getName())) {
            viewHolder.mTitle.setText(party.getName());
        }
        if (party.getLogo()!=null) {
            Picasso.get().load(party.getLogo()).into(viewHolder.imageView);
        }
        viewHolder.mLayout.setTag(party);
        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( context);
                alertDialogBuilder.setTitle("Confirm Vote");
                alertDialogBuilder.setMessage("Are you sure you want to vote for "+party.getName()+"?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                context.startActivity(new Intent(context,EndSplashScreen.class));
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return partyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView imageView;
        private RelativeLayout mLayout;
        ViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.layout);
            mTitle=view.findViewById(R.id.title);
            imageView=view.findViewById(R.id.imageView);
        }
    }
}
