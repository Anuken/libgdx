/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.linearmath.btVector3FloatData;

public class btDynamicsWorldFloatData extends BulletBase{
    private long swigCPtr;

    protected btDynamicsWorldFloatData(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btDynamicsWorldFloatData, normally you should not need this constructor it's intended for low-level usage. */
    public btDynamicsWorldFloatData(long cPtr, boolean cMemoryOwn){
        this("btDynamicsWorldFloatData", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btDynamicsWorldFloatData obj){
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
                DynamicsJNI.delete_btDynamicsWorldFloatData(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setSolverInfo(btContactSolverInfoFloatData value){
        DynamicsJNI.btDynamicsWorldFloatData_solverInfo_set(swigCPtr, this, btContactSolverInfoFloatData.getCPtr(value), value);
    }

    public btContactSolverInfoFloatData getSolverInfo(){
        long cPtr = DynamicsJNI.btDynamicsWorldFloatData_solverInfo_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btContactSolverInfoFloatData(cPtr, false);
    }

    public void setGravity(btVector3FloatData value){
        DynamicsJNI.btDynamicsWorldFloatData_gravity_set(swigCPtr, this, btVector3FloatData.getCPtr(value), value);
    }

    public btVector3FloatData getGravity(){
        long cPtr = DynamicsJNI.btDynamicsWorldFloatData_gravity_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btVector3FloatData(cPtr, false);
    }

    public btDynamicsWorldFloatData(){
        this(DynamicsJNI.new_btDynamicsWorldFloatData(), true);
    }

}
