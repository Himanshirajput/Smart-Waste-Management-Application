package com.example.capston.DataModels;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;

public class NotificationResponse {

    @SerializedName("data")
    NotificationItem list[];

    public NotificationResponse(NotificationItem[] data) {
        this.list = data;
    }

    public class NotificationItem {
        @SerializedName("id")
        int id;

        @SerializedName("dateTime")
        Timestamp dateTime;

        @SerializedName("fillValue")
        int fillValue;


        @SerializedName("rfID")
        String rfID;

        @SerializedName("dustbinID")
        int dustbinID;

        public NotificationItem(int fillValue, String rfID, int dustbinID, Timestamp dateTime) {

            this.id = id;
            this.fillValue = fillValue;
            this.rfID = rfID;
            this.dustbinID = dustbinID;
            this.dateTime = dateTime;
        }

        public int getId() {
            return id;
        }

        public Timestamp getDateTime() {
            return dateTime;
        }

        public int getFillValue() {
            return fillValue;
        }

        public String getRfID() {
            return rfID;
        }

        public int getDustbinID() {
            return dustbinID;
        }
    }

    public NotificationItem[] getList() {
        return list;
    }
}
