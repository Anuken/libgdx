/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

public class btCompoundFromGimpactShape extends btCompoundShape{
    private long swigCPtr;

    protected btCompoundFromGimpactShape(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btCompoundFromGimpactShape_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btCompoundFromGimpactShape, normally you should not need this constructor it's intended for low-level usage. */
    public btCompoundFromGimpactShape(long cPtr, boolean cMemoryOwn){
        this("btCompoundFromGimpactShape", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btCompoundFromGimpactShape_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btCompoundFromGimpactShape obj){
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
                CollisionJNI.delete_btCompoundFromGimpactShape(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return CollisionJNI.btCompoundFromGimpactShape_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        CollisionJNI.btCompoundFromGimpactShape_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return CollisionJNI.btCompoundFromGimpactShape_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        CollisionJNI.btCompoundFromGimpactShape_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return CollisionJNI.btCompoundFromGimpactShape_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        CollisionJNI.btCompoundFromGimpactShape_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return CollisionJNI.btCompoundFromGimpactShape_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        CollisionJNI.btCompoundFromGimpactShape_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public btCompoundFromGimpactShape(){
        this(CollisionJNI.new_btCompoundFromGimpactShape(), true);
    }

}
