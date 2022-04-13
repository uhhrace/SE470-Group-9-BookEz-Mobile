package com.example.myapplication;

import androidx.fragment.app.Fragment;

public class BartScreen extends Fragment {

    public MainActivity main;

    protected void changeScreenToCalendar(){
        main.changeScreenToCalendar();
    }

    protected void changeScreenToFoodBank(){
        main.changeScreenToFoodBank();
    }

    protected void changeScreenToHome(){
        main.changeScreenToHome();
    }

    protected void changeScreenToPets(){
        main.changeScreenToPets();
    }

    public void attach(MainActivity foreignMain){
        main = foreignMain;
    }
}