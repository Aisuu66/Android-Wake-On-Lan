package com.teamgy.wakeonlan.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CheckBox;

import com.teamgy.wakeonlan.PCInfo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Jakov on 02/11/2015.
 */
public final class Tools {
    private Tools () { // private constructor
    }
    public static void changeCheckboxState(CheckBox checkBox){

        if(checkBox.isChecked()){
            checkBox.setChecked(false);
        }
        else{
            checkBox.setChecked(true);
        }
    }
    public static int booleanToInt(boolean bool){
        if(bool){
            return 1;
        }
        return 0;
    }
    public static boolean intToBoolean(int i){
        return i == 1;
    }
    public static ArrayList<String> pcInfosToMacArrayList(ArrayList<PCInfo> pcInfos){
        ArrayList<String> macArraylist = new ArrayList<String>();
        for (PCInfo pcInfo : pcInfos) {
            macArraylist.add(pcInfo.getMacAdress());

        }
        return macArraylist;

    }

    public static ArrayList<String> getEnabledMacs(ArrayList<PCInfo> pcInfos){
        ArrayList<String> enabledMacs = new ArrayList<>();

        for(PCInfo pcInfo: pcInfos){
            if(pcInfo.isEnabled()){
                enabledMacs.add(pcInfo.getMacAdress());
            }
        }
        return enabledMacs;
    }

    public static String reformatMACInput(String input){
        //this method reformats mac to correct format
        //user can input spaces or whatever
        //this overwrites it to correct format

        //get out all numbers and characters from string
        //merge them back
        String formattedString = "";
        input = input.replaceAll("\\s+","");


        String pattern = "[abcdef0-9]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        while(m.find()){

            formattedString += m.group();

        }

        if(formattedString.length() > 12 && formattedString.length() > 0){
           try{
                formattedString = formattedString.substring(0,12);

            }catch (IndexOutOfBoundsException e){

                Log.d("exception","lenght is " +  formattedString.length());
            }


        }
        return formattedString;

    }

    public static void circularRevealShow(View myView){
        // previously invisible view

        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        }
        else{

            //TODO
            //add animation for sdk <5.0
        }


        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();

    }
    public static void circularRevealHide(final View myView) {
        // previously visible view

        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

        // get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(cx, cy);

        // create the animation (the final radius is zero)
        Animator anim =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
        } else {

            //TODO
            //add animation for sdk <5.0
        }

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();

    }


}