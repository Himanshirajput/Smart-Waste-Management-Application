package com.example.capston.DataModels;

import com.google.gson.annotations.SerializedName;

public class DustbinInfoResponse {

    @SerializedName("data")
    DustbinInfoResponse.Dustbin list[];

    public DustbinInfoResponse(Dustbin[] list) {
        this.list = list;
    }

    public Dustbin[] getList() {
        return list;
    }

    public class Dustbin {
        @SerializedName("id")
        int id;

        @SerializedName("name")
        String name;

        @SerializedName("location")
        String location;

        @SerializedName("fillValue")
        int fillValue;

        public Dustbin(int id, String name, String location, int fillValue) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.fillValue = fillValue;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public int getFillValue() {
            return fillValue;
        }
    }
}
