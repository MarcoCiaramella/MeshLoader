package com.outofbound.meshloaderlib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.outofbound.meshloaderlib.obj.Obj;

import org.junit.Test;

public class ObjTest {

    private final Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void load_isCorrect(){
        Obj obj = new Obj(context,"cube.obj");
        obj.load();
    }
}
