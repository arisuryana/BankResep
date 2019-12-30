package com.example.resep.bantuan;

public class ConstantURL {
    public static final String BASE_URL = "http://192.168.43.247:8000/";

    public static class URL{
        public static String api(){
            return BASE_URL+"api/";
        }

        public static String imgResep(String file){
            return BASE_URL+file;
        }
    }
}
