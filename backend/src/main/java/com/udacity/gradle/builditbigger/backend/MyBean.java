package com.udacity.gradle.builditbigger.backend;

import ladsoft.com.jokeslib.entity.Joke;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }


//    public Joke myData;
//
//    public void setData(Joke myData) {
//        this.myData = myData;
//    }
//
//    public Joke getData() {
//        return myData;
//    }

}