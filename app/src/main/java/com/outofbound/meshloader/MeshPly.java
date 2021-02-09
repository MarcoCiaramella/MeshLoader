package com.outofbound.meshloader;

import android.content.Context;
import android.graphics.Bitmap;

public class MeshPly extends Mesh {

    public MeshPly(Context context, String mesh, Bitmap textureBitmap) {
        super(context, mesh, textureBitmap);
        rotation.x = -90;
    }

    @Override
    public void doTransformation(float[] mMatrix) {
        rotation.y += 0.5f;
        rotateY(mMatrix);
        rotateX(mMatrix);
    }
}
