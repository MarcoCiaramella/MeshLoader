package com.outofbound.meshloader;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.outofbound.meshloaderlib.util.Util;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MeshView extends GLSurfaceView implements GLSurfaceView.Renderer {

    private SceneShader sceneShader;
    private ArrayList<Mesh> meshes;
    private final float[] mMatrix = new float[16];
    private final float[] mvpMatrix = new float[16];
    private CameraPerspective cameraPerspective;
    private static final Vector3f CAMERA_EYE = new Vector3f(3,2,3);
    private static final Vector3f CAMERA_CENTER = new Vector3f(0,0,0);
    private static final Vector3f CAMERA_UP = new Vector3f(0,1,0);

    public MeshView(Context context) {
        super(context);
        setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE | SYSTEM_UI_FLAG_FULLSCREEN);
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LESS);
        GLES20.glClearDepthf(1.0f);
        GLES20.glFrontFace(GLES20.GL_CCW);

        cameraPerspective = new CameraPerspective(CAMERA_EYE, CAMERA_CENTER, CAMERA_UP, 1, 1000);

        meshes = new ArrayList<>();
        //MeshObj meshObj = new MeshObj(getContext(),"monkey2.obj");
        MeshObj meshObj = new MeshObj(getContext(),"cube.obj");
        meshes.add(meshObj);
        MeshPly meshPly = new MeshPly(getContext(),"monkey.ply", Util.getBitmapFromAsset(getContext(),"monkey_ply_texture.png"));
        //meshes.add(meshPly);
        sceneShader = new SceneShader(getContext());
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        cameraPerspective.setWidth(width).setHeight(height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0, 0, 0, 0);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        cameraPerspective.loadVpMatrix();
        sceneShader.setViewPos(cameraPerspective.getEye());
        for (Mesh mesh : meshes) {
            Matrix.setIdentityM(mMatrix, 0);
            mesh.doTransformation(mMatrix);
            Matrix.multiplyMM(mvpMatrix, 0, cameraPerspective.getVpMatrix(), 0, mMatrix, 0);
            sceneShader.setMesh(mesh);
            sceneShader.setMMatrix(mMatrix);
            sceneShader.setMvpMatrix(mvpMatrix);
            sceneShader.bindData();
            GLES20.glDrawElements(GLES20.GL_TRIANGLES, mesh.getIndicesBuffer().capacity(), GLES20.GL_UNSIGNED_INT, mesh.getIndicesBuffer());
            sceneShader.unbindData();
        }
    }
}
