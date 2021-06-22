package com.outofbound.meshloaderlib.obj;

import android.content.Context;
import android.graphics.Bitmap;

import com.outofbound.meshloaderlib.util.Util;

public class Material {

    private final Context context;
    private final String[] content;
    private float ns;
    private final float[] ka;
    private final float[] kd;
    private final float[] ks;
    private float d;
    private int illum;
    private float ni;
    private Bitmap mapKa;
    private Bitmap mapKd;
    private Bitmap mapKs;

    public Material(Context context, String filename){
        this.context = context;
        content = Util.read(context, filename).split("\n");
        ka = new float[3];
        kd = new float[3];
        ks = new float[3];
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
            else if (isNi(line)){
                loadNi(line);
            }
            else if (isMapKa(line)){
                loadMapKa(line);
            }
            else if (isMapKd(line)){
                loadMapKd(line);
            }
            else if (isMapKs(line)){
                loadMapKs(line);
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

    private void loadNi(String line){
        ni = Float.parseFloat(line.split(" ")[1]);
    }

    private void loadMapKa(String line){
        mapKa = Util.getBitmapFromAsset(context,line.split(" ")[1]);
    }

    private void loadMapKd(String line){
        mapKd = Util.getBitmapFromAsset(context,line.split(" ")[1]);
    }

    private void loadMapKs(String line){
        mapKs = Util.getBitmapFromAsset(context,line.split(" ")[1]);
    }

    private boolean containsSpace(String line){
        return line.contains(" ");
    }

    private boolean startsWith(String line, String charSeq){
        return line.contains(" ") && line.split(" ")[0].equalsIgnoreCase(charSeq);
    }

    private boolean isNs(String line){
        return startsWith(line, "ns");
    }

    private boolean isKa(String line){
        return startsWith(line, "ka");
    }

    private boolean isKd(String line){
        return startsWith(line, "kd");
    }

    private boolean isKs(String line){
        return startsWith(line, "ks");
    }

    private boolean isD(String line){
        return startsWith(line, "d");
    }

    private boolean isIllum(String line){
        return startsWith(line, "illum");
    }

    private boolean isNi(String line){
        return startsWith(line, "Ni");
    }

    private boolean isMapKa(String line){
        return startsWith(line, "map_Ka");
    }

    private boolean isMapKd(String line){
        return startsWith(line, "map_Kd");
    }

    private boolean isMapKs(String line){
        return startsWith(line, "map_Ks");
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

    public int getIllum() {
        return illum;
    }

    public float getNi() {
        return ni;
    }

    public Bitmap getMapKa() {
        return mapKa;
    }

    public Bitmap getMapKd() {
        return mapKd;
    }

    public Bitmap getMapKs() {
        return mapKs;
    }
}
