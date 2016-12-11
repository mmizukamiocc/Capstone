package com.example.mmizukami.capstone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmizukami on 11/29/2016.
 */

public class FindPetListAdapter extends ArrayAdapter<Pet> {

    private Context mContext;
    private List<Pet> mPetsList = new ArrayList<>();
    private int mResourceId;
    private User petOwner;
    private List<Relation> mAllRelations;
    private LinearLayout petListLinearLayout;
    private TextView petListNameTextView;
    private TextView petListTypeTextView;
    private TextView petListUserNameTextView;
    private DBHelper db;


    public FindPetListAdapter(Context context, int resource, List<Pet> pets) {
        super(context, resource);
        mContext = context;
        mResourceId = resource;
        mPetsList = pets;
        mAllRelations = db.getAllRelations();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {

        final Pet selectedPet = mPetsList.get(pos);

        for(Relation singleRelation : mAllRelations)
        {
            if(singleRelation.getPet() == selectedPet)
               petOwner =  singleRelation.getUser();
        }

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId,null);

        LinearLayout petListLinearLayout = (LinearLayout) view.findViewById(R.id.petListLinearLayout);
        petListLinearLayout.setTag(selectedPet);

        //set all TextView inside the listItem layout

        petListNameTextView = (TextView) view.findViewById(R.id.petListNameTextView);
        petListTypeTextView = (TextView) view.findViewById(R.id.petListTypeTextView);
        petListUserNameTextView = (TextView) view.findViewById(R.id.petListUserNameTextView);

        petListNameTextView.setText(selectedPet.getPetName());
        petListTypeTextView.setText(selectedPet.getType());
        petListUserNameTextView.setText(petOwner.getUserName());


        return view;
    }

}
