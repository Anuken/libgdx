/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.softbody;

import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.linearmath.btVector3;

public class btSoftBodyWorldInfo extends BulletBase{
    private long swigCPtr;

    protected btSoftBodyWorldInfo(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btSoftBodyWorldInfo, normally you should not need this constructor it's intended for low-level usage. */
    public btSoftBodyWorldInfo(long cPtr, boolean cMemoryOwn){
        this("btSoftBodyWorldInfo", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btSoftBodyWorldInfo obj){
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
                SoftbodyJNI.delete_btSoftBodyWorldInfo(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setAir_density(float value){
        SoftbodyJNI.btSoftBodyWorldInfo_air_density_set(swigCPtr, this, value);
    }

    public float getAir_density(){
        return SoftbodyJNI.btSoftBodyWorldInfo_air_density_get(swigCPtr, this);
    }

    public void setWater_density(float value){
        SoftbodyJNI.btSoftBodyWorldInfo_water_density_set(swigCPtr, this, value);
    }

    public float getWater_density(){
        return SoftbodyJNI.btSoftBodyWorldInfo_water_density_get(swigCPtr, this);
    }

    public void setWater_offset(float value){
        SoftbodyJNI.btSoftBodyWorldInfo_water_offset_set(swigCPtr, this, value);
    }

    public float getWater_offset(){
        return SoftbodyJNI.btSoftBodyWorldInfo_water_offset_get(swigCPtr, this);
    }

    public void setMaxDisplacement(float value){
        SoftbodyJNI.btSoftBodyWorldInfo_maxDisplacement_set(swigCPtr, this, value);
    }

    public float getMaxDisplacement(){
        return SoftbodyJNI.btSoftBodyWorldInfo_maxDisplacement_get(swigCPtr, this);
    }

    public void setWater_normal(btVector3 value){
        SoftbodyJNI.btSoftBodyWorldInfo_water_normal_set(swigCPtr, this, btVector3.getCPtr(value), value);
    }

    public btVector3 getWater_normal(){
        long cPtr = SoftbodyJNI.btSoftBodyWorldInfo_water_normal_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btVector3(cPtr, false);
    }

    public void setBroadphase(btBroadphaseInterface value){
        SoftbodyJNI.btSoftBodyWorldInfo_broadphase_set(swigCPtr, this, btBroadphaseInterface.getCPtr(value), value);
    }

    public btBroadphaseInterface getBroadphase(){
        long cPtr = SoftbodyJNI.btSoftBodyWorldInfo_broadphase_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btBroadphaseInterface(cPtr, false);
    }

    public void setDispatcher(btDispatcher value){
        SoftbodyJNI.btSoftBodyWorldInfo_dispatcher_set(swigCPtr, this, btDispatcher.getCPtr(value), value);
    }

    public btDispatcher getDispatcher(){
        long cPtr = SoftbodyJNI.btSoftBodyWorldInfo_dispatcher_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btDispatcher(cPtr, false);
    }

    public void setGravity(btVector3 value){
        SoftbodyJNI.btSoftBodyWorldInfo_gravity_set(swigCPtr, this, btVector3.getCPtr(value), value);
    }

    public btVector3 getGravity(){
        long cPtr = SoftbodyJNI.btSoftBodyWorldInfo_gravity_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btVector3(cPtr, false);
    }

    public void setSparsesdf(btSparseSdf3 value){
        SoftbodyJNI.btSoftBodyWorldInfo_sparsesdf_set(swigCPtr, this, btSparseSdf3.getCPtr(value), value);
    }

    public btSparseSdf3 getSparsesdf(){
        long cPtr = SoftbodyJNI.btSoftBodyWorldInfo_sparsesdf_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btSparseSdf3(cPtr, false);
    }

    public btSoftBodyWorldInfo(){
        this(SoftbodyJNI.new_btSoftBodyWorldInfo(), true);
    }

}
