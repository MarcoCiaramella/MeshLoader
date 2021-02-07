package com.outofbound.meshloaderlib.obj;

import android.content.Context;

import com.outofbound.meshloaderlib.util.Util;

public class Material {

    private float ns;
    private float[] ka;
    private float[] kd;
    private float[] ks;
    private float d;
    private int illum;
    private final String[] content;

    public Material(Context context, String filename){
        content = Util.read(context, filename).split("\n");
    }

    public void load(){
        for (String line : content){
            if (isNs(line)){
                loadNs(line);
            }
            else if (isKa(line)){
                loadKa(line);
            }
            else if (isKd(line)){
                loadKd(line);
            }
            else if (isKs(line)){
                loadKs(line);
            }
            else if (isD(line)){
                loadD(line);
            }
            else if (isIllum(line)){
                loadIllum(line);
            }
        }
    }

    private void loadNs(String line){
        ns = Float.parseFloat(line.split(" ")[1]);
    }

    private void loadKa(String line){
        String[] values = line.split(" ");
        Util.addToArray(ka,0,values[1],values[2],values[3]);
    }

    private void loadKd(String line){
        String[] values = line.split(" ");
        Util.addToArray(kd,0,values[1],values[2],values[3]);
    }

    private void loadKs(String line){
        String[] values = line.split(" ");
        Util.addToArray(ks,0,values[1],values[2],values[3]);
    }

    private void loadD(String line){
        d = Float.parseFloat(line.split(" ")[1]);
    }

    private void loadIllum(String line){
        illum = Integer.parseInt(line.split(" ")[1]);
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

    public float getD() {
        return d;
    }

    public float getNs() {
        return ns;
    }

    public float[] getKa() {
        return ka;
    }

    public float[] getKd() {
        return kd;
    }

    public float[] getKs() {
        return ks;
    }
}
