/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class btLemkeAlgorithm extends BulletBase{
    private long swigCPtr;

    protected btLemkeAlgorithm(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btLemkeAlgorithm, normally you should not need this constructor it's intended for low-level usage. */
    public btLemkeAlgorithm(long cPtr, boolean cMemoryOwn){
        this("btLemkeAlgorithm", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btLemkeAlgorithm obj){
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
                DynamicsJNI.delete_btLemkeAlgorithm(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public btLemkeAlgorithm(SWIGTYPE_p_btMatrixXT_float_t M_, SWIGTYPE_p_btVectorXT_float_t q_, int DEBUGLEVEL_){
        this(DynamicsJNI.new_btLemkeAlgorithm__SWIG_0(SWIGTYPE_p_btMatrixXT_float_t.getCPtr(M_), SWIGTYPE_p_btVectorXT_float_t.getCPtr(q_), DEBUGLEVEL_), true);
    }

    public btLemkeAlgorithm(SWIGTYPE_p_btMatrixXT_float_t M_, SWIGTYPE_p_btVectorXT_float_t q_){
        this(DynamicsJNI.new_btLemkeAlgorithm__SWIG_1(SWIGTYPE_p_btMatrixXT_float_t.getCPtr(M_), SWIGTYPE_p_btVectorXT_float_t.getCPtr(q_)), true);
    }

    public int getInfo(){
        return DynamicsJNI.btLemkeAlgorithm_getInfo(swigCPtr, this);
    }

    public int getSteps(){
        return DynamicsJNI.btLemkeAlgorithm_getSteps(swigCPtr, this);
    }

    public void setSystem(SWIGTYPE_p_btMatrixXT_float_t M_, SWIGTYPE_p_btVectorXT_float_t q_){
        DynamicsJNI.btLemkeAlgorithm_setSystem(swigCPtr, this, SWIGTYPE_p_btMatrixXT_float_t.getCPtr(M_), SWIGTYPE_p_btVectorXT_float_t.getCPtr(q_));
    }

    public SWIGTYPE_p_btVectorXT_float_t solve(long maxloops){
        return new SWIGTYPE_p_btVectorXT_float_t(DynamicsJNI.btLemkeAlgorithm_solve__SWIG_0(swigCPtr, this, maxloops), true);
    }

    public SWIGTYPE_p_btVectorXT_float_t solve(){
        return new SWIGTYPE_p_btVectorXT_float_t(DynamicsJNI.btLemkeAlgorithm_solve__SWIG_1(swigCPtr, this), true);
    }

}
