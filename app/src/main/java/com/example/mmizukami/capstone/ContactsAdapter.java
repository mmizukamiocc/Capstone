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

/**
 * Created by Nick on 12/7/2016.
 */

public class ContactsAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private int mResourceId;
    private ArrayList<User> mAllUsers;

    private TextView mListItemNameTextView;
    private TextView mListItemPhoneNumberTextView;

    public ContactsAdapter(Context context, int resourceId, ArrayList<User> allUsers) {
        super(context, resourceId, allUsers);
        this.mContext = context;
        this.mResourceId = resourceId;
        this.mAllUsers =  allUsers;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {
        User contact = mAllUsers.get(pos);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        mListItemNameTextView = (TextView) view.findViewById(R.id.listItemNameTextView);
        mListItemPhoneNumberTextView = (TextView) view.findViewById(R.id.listItemPhoneNumberTextView);

        mListItemNameTextView.setText(contact.getUserName());
        mListItemPhoneNumberTextView.setText(contact.getPhone());

        LinearLayout selectedLinearLayout = (LinearLayout) view.findViewById(R.id.contactItemLinearLayout);
        selectedLinearLayout.setTag(contact);

        return view;
    }
}