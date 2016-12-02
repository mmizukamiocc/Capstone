package com.example.mmizukami.capstone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmizukami on 11/29/2016. !!
 */

public class FindPetListAdapter extends ArrayAdapter<Pet> {

    private Context mContext;
    private List<Pet> mPetsList = new ArrayList<>();
    private int mResourceId;

    public FindPetListAdapter(Context context, int resource, List<Pet> pets) {
        super(context, resource);
        mContext = context;
        mResourceId = resource;
        mPetsList = pets;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
   int i = 0; // for push test
        final Pet selectedPet = mPetsList.get(pos);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId,null);

        //LinearLayout petListLinearLayout = (LinearLayout) view.findViewById(R.id.petListLinearLayout)
        petListLinearLayout.setTag(selectedPet);

        //set all TextView inside the listItem layout
        return view;
    }

}
