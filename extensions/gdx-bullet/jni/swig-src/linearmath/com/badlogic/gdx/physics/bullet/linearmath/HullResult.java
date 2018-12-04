/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.linearmath;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class HullResult extends BulletBase{
    private long swigCPtr;

    protected HullResult(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new HullResult, normally you should not need this constructor it's intended for low-level usage. */
    public HullResult(long cPtr, boolean cMemoryOwn){
        this("HullResult", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(HullResult obj){
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
                LinearMathJNI.delete_HullResult(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public HullResult(){
        this(LinearMathJNI.new_HullResult(), true);
    }

    public void setMPolygons(boolean value){
        LinearMathJNI.HullResult_mPolygons_set(swigCPtr, this, value);
    }

    public boolean getMPolygons(){
        return LinearMathJNI.HullResult_mPolygons_get(swigCPtr, this);
    }

    public void setMNumOutputVertices(long value){
        LinearMathJNI.HullResult_mNumOutputVertices_set(swigCPtr, this, value);
    }

    public long getMNumOutputVertices(){
        return LinearMathJNI.HullResult_mNumOutputVertices_get(swigCPtr, this);
    }

    public void setOutputVertices(btVector3Array value){
        LinearMathJNI.HullResult_OutputVertices_set(swigCPtr, this, btVector3Array.getCPtr(value), value);
    }

    public btVector3Array getOutputVertices(){
        long cPtr = LinearMathJNI.HullResult_OutputVertices_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btVector3Array(cPtr, false);
    }

    public void setMNumFaces(long value){
        LinearMathJNI.HullResult_mNumFaces_set(swigCPtr, this, value);
    }

    public long getMNumFaces(){
        return LinearMathJNI.HullResult_mNumFaces_get(swigCPtr, this);
    }

    public void setMNumIndices(long value){
        LinearMathJNI.HullResult_mNumIndices_set(swigCPtr, this, value);
    }

    public long getMNumIndices(){
        return LinearMathJNI.HullResult_mNumIndices_get(swigCPtr, this);
    }

    public void setIndices(SWIGTYPE_p_btAlignedObjectArrayT_unsigned_int_t value){
        LinearMathJNI.HullResult_Indices_set(swigCPtr, this, SWIGTYPE_p_btAlignedObjectArrayT_unsigned_int_t.getCPtr(value));
    }

    public SWIGTYPE_p_btAlignedObjectArrayT_unsigned_int_t getIndices(){
        long cPtr = LinearMathJNI.HullResult_Indices_get(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_btAlignedObjectArrayT_unsigned_int_t(cPtr, false);
    }

}
