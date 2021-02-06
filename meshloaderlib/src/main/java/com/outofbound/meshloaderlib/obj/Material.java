package com.outofbound.meshloaderlib.obj;

import android.content.Context;

import com.outofbound.meshloaderlib.util.TextFileReader;

public class Material {

    private float ns;
    private float ka;
    private float kd;
    private float ks;
    private float d;
    private float illum;
    private String[] content;

    public Material(Context context, String filename){
        content = TextFileReader.getString(context, filename).split("\n");
    }

    public void load(){

    }

    private boolean isNs(String line){
        return line.startsWith("ns");
    }

    private boolean isKa(String line){
        return line.startsWith("ka");
    }

    private boolean isKd(String line){
        return line.startsWith("kd");
    }

    private boolean isKs(String line){
        return line.startsWith("ks");
    }

    private boolean isD(String line){
        return line.startsWith("d");
    }

    private boolean isIllum(String line){
        return line.startsWith("illum");
    }
}
