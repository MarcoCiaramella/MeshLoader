package com.outofbound.meshloaderlib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.outofbound.meshloaderlib.obj.Obj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@Config(sdk = 28)
@RunWith(RobolectricTestRunner.class)
public class ObjTest {

    private final Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void load_isCorrect(){
        Obj obj = new Obj(context,"cube.obj");
        obj.load();
    }
}
