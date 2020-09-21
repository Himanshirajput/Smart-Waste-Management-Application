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

import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends ArrayAdapter<NotificationResponse.NotificationItem> {
    private Context mContext;
    private NotificationResponse.NotificationItem notificationList[];

    public NotificationListAdapter(@NonNull Context context, @LayoutRes NotificationResponse.NotificationItem[] list) {
        super(context, 0 , list);
        System.out.println("===========in Adapter=========");
        mContext = context;
        notificationList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("===========getting View=========");
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.notification_list_item,parent,false);

        NotificationResponse.NotificationItem currentNoti = notificationList[position];

        String messageStr;
        if ( currentNoti.getRfID() != null ) {
            messageStr = "Dustbin opened by your rfID. Reward Point 5 is added in your rewards, Keep using dustbin to earn more rewards. In Case you didn't Open it contact to customer support. Thank You!";

        } else {

            int fillvalue = currentNoti.getFillValue();
            if (fillvalue == 0) {
                messageStr = "Dustbin is Empty, Now you can use it.";
            } else if (fillvalue == 100) {
                messageStr = "Dustbin is full wait for our employee to empty it. We will notify you once it will be empty.";
            } else {
                messageStr = "Dustbin is " + fillvalue + "% full, You can still use it.";
            }


        }

        TextView message = (TextView) listItem.findViewById(R.id.noti_message);
        message.setText(messageStr);

        Timestamp timestamp = currentNoti.getDateTime();
        TextView dateView = (TextView) listItem.findViewById(R.id.noti_Date);
        dateView.setText(timestamp.toString());

        return listItem;
    }
}
