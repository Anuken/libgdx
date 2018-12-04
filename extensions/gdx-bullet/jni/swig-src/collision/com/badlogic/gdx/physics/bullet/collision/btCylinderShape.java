/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.math.Vector3;

public class btCylinderShape extends btConvexInternalShape{
    private long swigCPtr;

    protected btCylinderShape(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btCylinderShape_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btCylinderShape, normally you should not need this constructor it's intended for low-level usage. */
    public btCylinderShape(long cPtr, boolean cMemoryOwn){
        this("btCylinderShape", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btCylinderShape_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btCylinderShape obj){
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    @Override
    protected void finalize() throws Throwable{
        if(!destroyed)
            destroy();
        super.finalize();
    }

    @Override
    protected synchronized void delete(){
        if(swigCPtr != 0){
            if(swigCMemOwn){
                swigCMemOwn = false;
                CollisionJNI.delete_btCylinderShape(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return CollisionJNI.btCylinderShape_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        CollisionJNI.btCylinderShape_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return CollisionJNI.btCylinderShape_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        CollisionJNI.btCylinderShape_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return CollisionJNI.btCylinderShape_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        CollisionJNI.btCylinderShape_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return CollisionJNI.btCylinderShape_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        CollisionJNI.btCylinderShape_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public Vector3 getHalfExtentsWithMargin(){
        return CollisionJNI.btCylinderShape_getHalfExtentsWithMargin(swigCPtr, this);
    }

    public Vector3 getHalfExtentsWithoutMargin(){
        return CollisionJNI.btCylinderShape_getHalfExtentsWithoutMargin(swigCPtr, this);
    }

    public btCylinderShape(Vector3 halfExtents){
        this(CollisionJNI.new_btCylinderShape(halfExtents), true);
    }

    public int getUpAxis(){
        return CollisionJNI.btCylinderShape_getUpAxis(swigCPtr, this);
    }

    public float getRadius(){
        return CollisionJNI.btCylinderShape_getRadius(swigCPtr, this);
    }

}
