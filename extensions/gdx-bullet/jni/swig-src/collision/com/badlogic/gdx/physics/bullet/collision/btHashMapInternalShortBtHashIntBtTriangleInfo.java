/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.linearmath.btHashInt;

public class btHashMapInternalShortBtHashIntBtTriangleInfo extends BulletBase{
    private long swigCPtr;

    protected btHashMapInternalShortBtHashIntBtTriangleInfo(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btHashMapInternalShortBtHashIntBtTriangleInfo, normally you should not need this constructor it's intended for low-level usage. */
    public btHashMapInternalShortBtHashIntBtTriangleInfo(long cPtr, boolean cMemoryOwn){
        this("btHashMapInternalShortBtHashIntBtTriangleInfo", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btHashMapInternalShortBtHashIntBtTriangleInfo obj){
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
                CollisionJNI.delete_btHashMapInternalShortBtHashIntBtTriangleInfo(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void insert(btHashInt key, btTriangleInfo value){
        CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_insert(swigCPtr, this, btHashInt.getCPtr(key), key, btTriangleInfo.getCPtr(value), value);
    }

    public void remove(btHashInt key){
        CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_remove(swigCPtr, this, btHashInt.getCPtr(key), key);
    }

    public int size(){
        return CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_size(swigCPtr, this);
    }

    public btTriangleInfo getAtIndexConst(int index){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_getAtIndexConst(swigCPtr, this, index);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public btTriangleInfo getAtIndex(int index){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_getAtIndex(swigCPtr, this, index);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public btHashInt getKeyAtIndex(int index){
        return new btHashInt(CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_getKeyAtIndex(swigCPtr, this, index), true);
    }

    public btHashInt getKeyAtIndexConst(int index){
        return new btHashInt(CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_getKeyAtIndexConst(swigCPtr, this, index), true);
    }

    public btTriangleInfo operatorSubscript(btHashInt key){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_operatorSubscript(swigCPtr, this, btHashInt.getCPtr(key), key);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public btTriangleInfo operatorSubscriptConst(btHashInt key){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_operatorSubscriptConst(swigCPtr, this, btHashInt.getCPtr(key), key);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public btTriangleInfo findConst(btHashInt key){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_findConst(swigCPtr, this, btHashInt.getCPtr(key), key);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public btTriangleInfo find(btHashInt key){
        long cPtr = CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_find(swigCPtr, this, btHashInt.getCPtr(key), key);
        return (cPtr == 0) ? null : new btTriangleInfo(cPtr, false);
    }

    public int findIndex(btHashInt key){
        return CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_findIndex(swigCPtr, this, btHashInt.getCPtr(key), key);
    }

    public void clear(){
        CollisionJNI.btHashMapInternalShortBtHashIntBtTriangleInfo_clear(swigCPtr, this);
    }

    public btHashMapInternalShortBtHashIntBtTriangleInfo(){
        this(CollisionJNI.new_btHashMapInternalShortBtHashIntBtTriangleInfo(), true);
    }

}
