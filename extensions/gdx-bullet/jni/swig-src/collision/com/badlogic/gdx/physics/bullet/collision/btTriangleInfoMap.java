/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.linearmath.btSerializer;

public class btTriangleInfoMap extends btHashMapInternalShortBtHashIntBtTriangleInfo{
    private long swigCPtr;

    protected btTriangleInfoMap(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btTriangleInfoMap_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btTriangleInfoMap, normally you should not need this constructor it's intended for low-level usage. */
    public btTriangleInfoMap(long cPtr, boolean cMemoryOwn){
        this("btTriangleInfoMap", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btTriangleInfoMap_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btTriangleInfoMap obj){
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
                CollisionJNI.delete_btTriangleInfoMap(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setConvexEpsilon(float value){
        CollisionJNI.btTriangleInfoMap_convexEpsilon_set(swigCPtr, this, value);
    }

    public float getConvexEpsilon(){
        return CollisionJNI.btTriangleInfoMap_convexEpsilon_get(swigCPtr, this);
    }

    public void setPlanarEpsilon(float value){
        CollisionJNI.btTriangleInfoMap_planarEpsilon_set(swigCPtr, this, value);
    }

    public float getPlanarEpsilon(){
        return CollisionJNI.btTriangleInfoMap_planarEpsilon_get(swigCPtr, this);
    }

    public void setEqualVertexThreshold(float value){
        CollisionJNI.btTriangleInfoMap_equalVertexThreshold_set(swigCPtr, this, value);
    }

    public float getEqualVertexThreshold(){
        return CollisionJNI.btTriangleInfoMap_equalVertexThreshold_get(swigCPtr, this);
    }

    public void setEdgeDistanceThreshold(float value){
        CollisionJNI.btTriangleInfoMap_edgeDistanceThreshold_set(swigCPtr, this, value);
    }

    public float getEdgeDistanceThreshold(){
        return CollisionJNI.btTriangleInfoMap_edgeDistanceThreshold_get(swigCPtr, this);
    }

    public void setMaxEdgeAngleThreshold(float value){
        CollisionJNI.btTriangleInfoMap_maxEdgeAngleThreshold_set(swigCPtr, this, value);
    }

    public float getMaxEdgeAngleThreshold(){
        return CollisionJNI.btTriangleInfoMap_maxEdgeAngleThreshold_get(swigCPtr, this);
    }

    public void setZeroAreaThreshold(float value){
        CollisionJNI.btTriangleInfoMap_zeroAreaThreshold_set(swigCPtr, this, value);
    }

    public float getZeroAreaThreshold(){
        return CollisionJNI.btTriangleInfoMap_zeroAreaThreshold_get(swigCPtr, this);
    }

    public btTriangleInfoMap(){
        this(CollisionJNI.new_btTriangleInfoMap(), true);
    }

    public int calculateSerializeBufferSize(){
        return CollisionJNI.btTriangleInfoMap_calculateSerializeBufferSize(swigCPtr, this);
    }

    public String serialize(long dataBuffer, btSerializer serializer){
        return CollisionJNI.btTriangleInfoMap_serialize(swigCPtr, this, dataBuffer, btSerializer.getCPtr(serializer), serializer);
    }

    public void deSerialize(btTriangleInfoMapData data){
        CollisionJNI.btTriangleInfoMap_deSerialize(swigCPtr, this, btTriangleInfoMapData.getCPtr(data), data);
    }

}
