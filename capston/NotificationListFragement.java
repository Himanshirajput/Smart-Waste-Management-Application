package com.example.capston;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.capston.Adapter.NotificationListAdapter;
import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.Network.LoginManager;

import java.util.ArrayList;

public class NotificationListFragement extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_list_feagement, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("===========Creating Fragment=========");
        NotificationResponse.NotificationItem notificationList[] = LoginManager.shared.getNotifications().getList();

        NotificationListAdapter adapter = new NotificationListAdapter(this.getContext(),notificationList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}