# MeshLoader

[![](https://jitpack.io/v/MarcoCiaramella/MeshLoader.svg)](https://jitpack.io/#MarcoCiaramella/MeshLoader)

A mesh loader Android library. Supports .ply and .obj format.

## How to import in your Android project
Add JitPack in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
```

Add the dependency

```
dependencies {
	        implementation 'com.github.MarcoCiaramella:MeshLoader:1.0.0'
}
```

## How to use

### OBJ

```java
Obj obj = new Obj(context, fileName);
obj.load();
float[] vertices = obj.getVertices();
float[] normals = obj.getNormals();
int[] indices = obj.getIndices();
```

### PLY

```java
Ply ply = new Ply(context, fileName);
ply.load();
float[] vertices = ply.getVertices();
float[] normals = ply.getNormals();
float[] colors = ply.getColors();
float[] texCoords = ply.getUvs();
int[] indices = ply.getIndices();
```
