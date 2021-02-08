package com.outofbound.meshloaderlib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.outofbound.meshloaderlib.obj.Obj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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
        assertNull(obj.getMaterial());
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
}
