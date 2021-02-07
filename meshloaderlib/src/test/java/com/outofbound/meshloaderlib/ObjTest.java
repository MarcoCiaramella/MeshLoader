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
    public void load_isCorrect(){
        Obj obj1 = new Obj(context,"cube.obj");
        obj1.load();
        assertNotNull(obj1.getVertices());
        assertNotNull(obj1.getIndices());
        assertNotNull(obj1.getNormals());
        assertNotNull(obj1.getTextureCoords());
        assertNull(obj1.getMaterial());

        Obj obj2 = new Obj(context,"monkey.obj");
        obj2.load();
        assertNotNull(obj2.getVertices());
        assertNotNull(obj2.getIndices());
        assertNotNull(obj2.getNormals());
        assertNotNull(obj2.getTextureCoords());
        assertNotNull(obj2.getMaterial());
    }
}
