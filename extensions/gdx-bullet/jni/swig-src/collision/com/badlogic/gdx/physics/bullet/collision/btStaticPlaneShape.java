/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.math.Vector3;

public class btStaticPlaneShape extends btConcaveShape{
    private long swigCPtr;

    protected btStaticPlaneShape(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btStaticPlaneShape_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btStaticPlaneShape, normally you should not need this constructor it's intended for low-level usage. */
    public btStaticPlaneShape(long cPtr, boolean cMemoryOwn){
        this("btStaticPlaneShape", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btStaticPlaneShape_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btStaticPlaneShape obj){
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
                CollisionJNI.delete_btStaticPlaneShape(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return CollisionJNI.btStaticPlaneShape_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        CollisionJNI.btStaticPlaneShape_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return CollisionJNI.btStaticPlaneShape_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        CollisionJNI.btStaticPlaneShape_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return CollisionJNI.btStaticPlaneShape_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        CollisionJNI.btStaticPlaneShape_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return CollisionJNI.btStaticPlaneShape_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        CollisionJNI.btStaticPlaneShape_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public btStaticPlaneShape(Vector3 planeNormal, float planeConstant){
        this(CollisionJNI.new_btStaticPlaneShape(planeNormal, planeConstant), true);
    }

    public Vector3 getPlaneNormal(){
        return CollisionJNI.btStaticPlaneShape_getPlaneNormal(swigCPtr, this);
    }

    public float getPlaneConstant(){
        return CollisionJNI.btStaticPlaneShape_getPlaneConstant(swigCPtr, this);
    }

}
