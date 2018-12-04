/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class btFace extends BulletBase{
    private long swigCPtr;

    protected btFace(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btFace, normally you should not need this constructor it's intended for low-level usage. */
    public btFace(long cPtr, boolean cMemoryOwn){
        this("btFace", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btFace obj){
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
                CollisionJNI.delete_btFace(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setIndices(SWIGTYPE_p_btAlignedObjectArrayT_int_t value){
        CollisionJNI.btFace_indices_set(swigCPtr, this, SWIGTYPE_p_btAlignedObjectArrayT_int_t.getCPtr(value));
    }

    public SWIGTYPE_p_btAlignedObjectArrayT_int_t getIndices(){
        long cPtr = CollisionJNI.btFace_indices_get(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_btAlignedObjectArrayT_int_t(cPtr, false);
    }

    public void setPlane(float[] value){
        CollisionJNI.btFace_plane_set(swigCPtr, this, value);
    }

    public float[] getPlane(){
        return CollisionJNI.btFace_plane_get(swigCPtr, this);
    }

    public btFace(){
        this(CollisionJNI.new_btFace(), true);
    }

}
