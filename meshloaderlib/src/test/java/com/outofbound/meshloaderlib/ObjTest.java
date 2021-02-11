package com.outofbound.meshloaderlib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.outofbound.meshloaderlib.obj.Obj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Config(sdk = 28)
@RunWith(RobolectricTestRunner.class)
public class ObjTest {

    private final Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void load1_isCorrect(){
        Obj obj = new Obj(context,"cube.obj");
        obj.load();
        assertNotNull(obj.getVertices());
        assertNotNull(obj.getIndices());
        assertNotNull(obj.getNormals());
        assertNotNull(obj.getTextureCoords());
        assertNotNull(obj.getMaterial());
    }

    @Test
    public void load2_isCorrect(){
        Obj obj = new Obj(context,"monkey.obj");
        obj.load();
        assertNotNull(obj.getVertices());
        assertNotNull(obj.getIndices());
        assertNotNull(obj.getNormals());
        assertNotNull(obj.getTextureCoords());
        assertNotNull(obj.getMaterial());
        assertNotNull(obj.getMaterial().getMapKd());
    }

    @Test
    public void load3_isCorrect(){
        Obj obj = new Obj(context,"cube.obj");
        obj.load();
        float[] textureCoords = {
                0.375000f,0.000000f,
                0.625000f,0.000000f,
                0.625000f,0.250000f,
                0.375000f,0.250000f,
                0.375000f,0.250000f,
                0.625000f,0.250000f,
                0.625000f,0.500000f,
                0.375000f,0.500000f,
                0.375000f,0.500000f,
                0.625000f,0.500000f,
                0.625000f,0.750000f,
                0.375000f,0.750000f,
                0.375000f,0.750000f,
                0.625000f,0.750000f,
                0.625000f,1.000000f,
                0.375000f,1.000000f,
                0.125000f,0.500000f,
                0.375000f,0.500000f,
                0.375000f,0.750000f,
                0.125000f,0.750000f,
                0.625000f,0.500000f,
                0.875000f,0.500000f,
                0.875000f,0.750000f,
                0.625000f,0.750000f
        };
        assertArrayEquals(textureCoords,obj.getTextureCoords(),0);
    }
}
