package com.example.capston.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capston.DataModels.User;
import com.example.capston.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class UserListAdapter extends ArrayAdapter<User.UserData> {
    private Context mContext;
    private User.UserData userList[];

    public UserListAdapter(@NonNull Context context, @LayoutRes User.UserData[] list) {
        super(context, 0 , list);
        System.out.println("===========in Adapter=========");
        mContext = context;
        userList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("===========getting View=========");
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.user_list_item,parent,false);

        User.UserData currentUser = userList[position];



        String fullName = currentUser.getFirstName() + currentUser.getLastName();
        TextView nameLabel = (TextView) listItem.findViewById(R.id.user_name);
        nameLabel.setText(fullName);

        TextView mobileLabel = (TextView) listItem.findViewById(R.id.user_mobile);
        mobileLabel.setText(currentUser.getMobile());

        return listItem;
    }
}
