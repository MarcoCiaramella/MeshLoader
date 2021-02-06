package com.outofbound.meshloaderlib;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.outofbound.meshloaderlib.obj.Obj;

import org.junit.Test;

public class ObjTest {

    @Test
    public void load_isCorrect(){
        Context context = ApplicationProvider.getApplicationContext();
        Obj obj = new Obj(context,"cube.obj");
        obj.load();
    }
}
