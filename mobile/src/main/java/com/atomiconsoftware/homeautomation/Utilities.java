package com.atomiconsoftware.homeautomation;

import java.util.ArrayList;

/**
 * Created by newyork167 on 8/7/16.
 */
class Utilities {

    // Builds string from array of strings with given delimiter
    public static String StringFromArrayList(ArrayList<String> a, String delimter){
        String s = "";

        for (String ss : a)
            s += ss + delimter;

        return s;
    }

    // Builds string from array of strings with default space delimiter
    public static String StringFromArrayList(ArrayList<String> a){
        String s = "";

        for (String ss : a)
            s += ss + " ";

        return s;
    }
}
