package com.outofbound.meshloaderlib.obj;

import android.content.Context;

import com.outofbound.meshloaderlib.util.Util;


public class Obj {

    private final float[] vertices;
    private final float[] textureCoords;
    private final float[] normals;
    private final int[] indices;
    private final String[] content;
    private Material material;
    private int ioutV = 0;
    private int ioutVt = 0;
    private int ioutVn = 0;

    /**
     * Instantiate Obj.
     * @param context current context.
     * @param filename the filename (.obj) in assets folder.
     */
    public Obj(Context context, String filename){
        content = Util.read(context, filename).split("\n");
        indices = new int[getNumIndices()];
        vertices = new float[indices.length * 3];
        textureCoords = new float[indices.length * 2];
        normals = new float[indices.length * 3];
        if (useMaterial()){
            material = new Material(context,getMaterialFileName());
        }
    }

    /**
     * Load the .obj file.
     */
    public void load(){
        loadFaces();
        loadIndices();
        if (material != null) {
            material.load();
        }
    }

    private float[] loadVertices(){
        float[] vertices = new float[getNumVertices()*3];
        int pos = 0;
        for (String line : content) {
            if (isVertex(line)) {
                pos = loadVertex(line, vertices, pos);
            }
        }
        return vertices;
    }

    private float[] loadTextureCoords(){
        float[] textureCoords = new float[getNumTextureCoords()*2];
        int pos = 0;
        for (String line : content) {
            if (isTextureCoord(line)){
                pos = loadTextureCoord(line, textureCoords, pos);
            }
        }
        return textureCoords;
    }

    private float[] loadNormals(){
        float[] normals = new float[getNumNormals()*3];
        int pos = 0;
        for (String line : content) {
            if (isNormal(line)){
                pos = loadNormal(line, normals, pos);
            }
        }
        return normals;
    }

    private void loadIndices(){
        int i = 0;
        int pos = 0;
        for (String line : content){
            if (isFace(line)){
                String[] values = line.split(" ");
                int numTriangles = (values.length - 1) - 2;
                int i1 = i;
                for (int j = 0; j < numTriangles; j++) {
                    int i2 = i + 1;
                    int i3 = i + 2;
                    i++;
                    pos = Util.addToArray(indices, pos, i1, i2, i3);
                }
                i++;
            }
        }
    }

    private void loadFaces(){
        float[] vertices = loadVertices();
        float[] textureCoords = loadTextureCoords();
        float[] normals = loadNormals();
        for (String line : content){
            if (isFace(line)){
                loadFace(line, vertices, textureCoords, normals);
            }
        }
    }

    private int loadVertex(String line, float[] vertices, int pos){
        String[] values = line.split(" ");
        return Util.addToArray(vertices,pos,values[1],values[2],values[3]);
    }

    private int loadTextureCoord(String line, float[] textureCoords, int pos){
        String[] values = line.split(" ");
        return Util.addToArray(textureCoords,pos,values[1],values[2]);
    }

    private int loadNormal(String line, float[] normals, int pos){
        String[] values = line.split(" ");
        return Util.addToArray(normals,pos,values[1],values[2],values[3]);
    }

    private void loadFace(String line, float[] vertices, float[] textureCoords, float[] normals){
        String[] values = line.split(" ");
        for (int i = 1; i < values.length; i++){
            String[] faceVertex = values[i].split("/");
            int iinV = Integer.parseInt(faceVertex[0]) - 1;
            int iinVt = Integer.parseInt(faceVertex[1]) - 1;
            int iinVn = Integer.parseInt(faceVertex[2]) - 1;
            ioutV = copy(vertices, iinV, this.vertices, ioutV,3);
            ioutVt = copy(textureCoords, iinVt, this.textureCoords, ioutVt,2);
            ioutVn = copy(normals, iinVn, this.normals, ioutVn,3);
        }
    }

    private int copy(float[] in, int iin, float[] out, int iout, int size){
        if (size >= 0) System.arraycopy(in, iin * size, out, iout * size, size);
        return iout + 1;
    }

    private int getNumVertices(){
        int num = 0;
        for (String line : content) {
            if (isVertex(line)) {
                num++;
            }
        }
        return num;
    }

    private int getNumTextureCoords(){
        int num = 0;
        for (String line : content) {
            if (isTextureCoord(line)) {
                num++;
            }
        }
        return num;
    }

    private int getNumNormals(){
        int num = 0;
        for (String line : content) {
            if (isNormal(line)) {
                num++;
            }
        }
        return num;
    }

    private int getNumIndices(){
        int numIndices = 0;
        for (String line : content){
            if (isFace(line)){
                numIndices += ((line.split(" ").length - 1) - 2) * 3;
            }
        }
        return numIndices;
    }

    private boolean isVertex(String line){
        return line.startsWith("v ");
    }

    private boolean isTextureCoord(String line){
        return line.startsWith("vt ");
    }

    private boolean isNormal(String line){
        return line.startsWith("vn ");
    }

    private boolean isFace(String line){
        return line.startsWith("f ");
    }

    /**
     * Return the vertices loaded.
     * @return the vertices.
     */
    public float[] getVertices() {
        return vertices;
    }

    /**
     * Return the texture coordinates loaded.
     * @return the texture coordinates.
     */
    public float[] getTextureCoords() {
        return textureCoords;
    }

    /**
     * Return the normals loaded.
     * @return the normals.
     */
    public float[] getNormals() {
        return normals;
    }

    /**
     * Return the indices loaded.
     * @return the indices.
     */
    public int[] getIndices() {
        return indices;
    }

    private boolean useMaterial(){
        for (String line : content){
            if (line.startsWith("usemtl")){
                return !line.endsWith("None");
            }
        }
        return false;
    }

    private String getMaterialFileName(){
        for (String line : content){
            if (line.startsWith("mtllib")){
                return line.split(" ")[1];
            }
        }
        return null;
    }

    /**
     * Return Material.
     * @return Material.
     */
    public Material getMaterial() {
        return material;
    }
}
