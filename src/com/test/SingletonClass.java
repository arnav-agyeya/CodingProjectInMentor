package com.test;

public class SingletonClass {

    private static SingletonClass singletonClass;

    private SingletonClass(){

    }

    public static synchronized SingletonClass getInstance(){
        if(singletonClass!=null){
            singletonClass = new SingletonClass();
        }

        return singletonClass;
    }
}
