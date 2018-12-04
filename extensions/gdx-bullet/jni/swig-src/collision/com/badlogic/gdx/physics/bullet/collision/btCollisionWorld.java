/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.physics.bullet.linearmath.btSerializer;

public class btCollisionWorld extends BulletBase{
    private long swigCPtr;

    protected btCollisionWorld(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btCollisionWorld, normally you should not need this constructor it's intended for low-level usage. */
    public btCollisionWorld(long cPtr, boolean cMemoryOwn){
        this("btCollisionWorld", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btCollisionWorld obj){
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
                CollisionJNI.delete_btCollisionWorld(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public btCollisionWorld(btDispatcher dispatcher, btBroadphaseInterface broadphasePairCache, btCollisionConfiguration collisionConfiguration){
        this(CollisionJNI.new_btCollisionWorld(btDispatcher.getCPtr(dispatcher), dispatcher, btBroadphaseInterface.getCPtr(broadphasePairCache), broadphasePairCache, btCollisionConfiguration.getCPtr(collisionConfiguration), collisionConfiguration), true);
    }

    public void setBroadphase(btBroadphaseInterface pairCache){
        CollisionJNI.btCollisionWorld_setBroadphase(swigCPtr, this, btBroadphaseInterface.getCPtr(pairCache), pairCache);
    }

    public btBroadphaseInterface getBroadphaseConst(){
        long cPtr = CollisionJNI.btCollisionWorld_getBroadphaseConst(swigCPtr, this);
        return (cPtr == 0) ? null : new btBroadphaseInterface(cPtr, false);
    }

    public btBroadphaseInterface getBroadphase(){
        long cPtr = CollisionJNI.btCollisionWorld_getBroadphase(swigCPtr, this);
        return (cPtr == 0) ? null : new btBroadphaseInterface(cPtr, false);
    }

    public btOverlappingPairCache getPairCache(){
        long cPtr = CollisionJNI.btCollisionWorld_getPairCache(swigCPtr, this);
        return (cPtr == 0) ? null : new btOverlappingPairCache(cPtr, false);
    }

    public btDispatcher getDispatcher(){
        long cPtr = CollisionJNI.btCollisionWorld_getDispatcher(swigCPtr, this);
        return (cPtr == 0) ? null : new btDispatcher(cPtr, false);
    }

    public btDispatcher getDispatcherConst(){
        long cPtr = CollisionJNI.btCollisionWorld_getDispatcherConst(swigCPtr, this);
        return (cPtr == 0) ? null : new btDispatcher(cPtr, false);
    }

    public void updateSingleAabb(btCollisionObject colObj){
        CollisionJNI.btCollisionWorld_updateSingleAabb(swigCPtr, this, btCollisionObject.getCPtr(colObj), colObj);
    }

    public void updateAabbs(){
        CollisionJNI.btCollisionWorld_updateAabbs(swigCPtr, this);
    }

    public void computeOverlappingPairs(){
        CollisionJNI.btCollisionWorld_computeOverlappingPairs(swigCPtr, this);
    }

    public void setDebugDrawer(btIDebugDraw debugDrawer){
        CollisionJNI.btCollisionWorld_setDebugDrawer(swigCPtr, this, btIDebugDraw.getCPtr(debugDrawer), debugDrawer);
    }

    public btIDebugDraw getDebugDrawer(){
        long cPtr = CollisionJNI.btCollisionWorld_getDebugDrawer(swigCPtr, this);
        return (cPtr == 0) ? null : new btIDebugDraw(cPtr, false);
    }

    public void debugDrawWorld(){
        CollisionJNI.btCollisionWorld_debugDrawWorld(swigCPtr, this);
    }

    public void debugDrawObject(Matrix4 worldTransform, btCollisionShape shape, Vector3 color){
        CollisionJNI.btCollisionWorld_debugDrawObject(swigCPtr, this, worldTransform, btCollisionShape.getCPtr(shape), shape, color);
    }

    public int getNumCollisionObjects(){
        return CollisionJNI.btCollisionWorld_getNumCollisionObjects(swigCPtr, this);
    }

    public void rayTest(Vector3 rayFromWorld, Vector3 rayToWorld, RayResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_rayTest(swigCPtr, this, rayFromWorld, rayToWorld, RayResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public void convexSweepTest(btConvexShape castShape, Matrix4 from, Matrix4 to, ConvexResultCallback resultCallback, float allowedCcdPenetration){
        CollisionJNI.btCollisionWorld_convexSweepTest__SWIG_0(swigCPtr, this, btConvexShape.getCPtr(castShape), castShape, from, to, ConvexResultCallback.getCPtr(resultCallback), resultCallback, allowedCcdPenetration);
    }

    public void convexSweepTest(btConvexShape castShape, Matrix4 from, Matrix4 to, ConvexResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_convexSweepTest__SWIG_1(swigCPtr, this, btConvexShape.getCPtr(castShape), castShape, from, to, ConvexResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public void contactTest(btCollisionObject colObj, ContactResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_contactTest(swigCPtr, this, btCollisionObject.getCPtr(colObj), colObj, ContactResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public void contactPairTest(btCollisionObject colObjA, btCollisionObject colObjB, ContactResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_contactPairTest(swigCPtr, this, btCollisionObject.getCPtr(colObjA), colObjA, btCollisionObject.getCPtr(colObjB), colObjB, ContactResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public static void rayTestSingle(Matrix4 rayFromTrans, Matrix4 rayToTrans, btCollisionObject collisionObject, btCollisionShape collisionShape, Matrix4 colObjWorldTransform, RayResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_rayTestSingle(rayFromTrans, rayToTrans, btCollisionObject.getCPtr(collisionObject), collisionObject, btCollisionShape.getCPtr(collisionShape), collisionShape, colObjWorldTransform, RayResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public static void rayTestSingleInternal(Matrix4 rayFromTrans, Matrix4 rayToTrans, btCollisionObjectWrapper collisionObjectWrap, RayResultCallback resultCallback){
        CollisionJNI.btCollisionWorld_rayTestSingleInternal(rayFromTrans, rayToTrans, btCollisionObjectWrapper.getCPtr(collisionObjectWrap), collisionObjectWrap, RayResultCallback.getCPtr(resultCallback), resultCallback);
    }

    public static void objectQuerySingle(btConvexShape castShape, Matrix4 rayFromTrans, Matrix4 rayToTrans, btCollisionObject collisionObject, btCollisionShape collisionShape, Matrix4 colObjWorldTransform, ConvexResultCallback resultCallback, float allowedPenetration){
        CollisionJNI.btCollisionWorld_objectQuerySingle(btConvexShape.getCPtr(castShape), castShape, rayFromTrans, rayToTrans, btCollisionObject.getCPtr(collisionObject), collisionObject, btCollisionShape.getCPtr(collisionShape), collisionShape, colObjWorldTransform, ConvexResultCallback.getCPtr(resultCallback), resultCallback, allowedPenetration);
    }

    public static void objectQuerySingleInternal(btConvexShape castShape, Matrix4 convexFromTrans, Matrix4 convexToTrans, btCollisionObjectWrapper colObjWrap, ConvexResultCallback resultCallback, float allowedPenetration){
        CollisionJNI.btCollisionWorld_objectQuerySingleInternal(btConvexShape.getCPtr(castShape), castShape, convexFromTrans, convexToTrans, btCollisionObjectWrapper.getCPtr(colObjWrap), colObjWrap, ConvexResultCallback.getCPtr(resultCallback), resultCallback, allowedPenetration);
    }

    public void addCollisionObject(btCollisionObject collisionObject, int collisionFilterGroup, int collisionFilterMask){
        CollisionJNI.btCollisionWorld_addCollisionObject__SWIG_0(swigCPtr, this, btCollisionObject.getCPtr(collisionObject), collisionObject, collisionFilterGroup, collisionFilterMask);
    }

    public void addCollisionObject(btCollisionObject collisionObject, int collisionFilterGroup){
        CollisionJNI.btCollisionWorld_addCollisionObject__SWIG_1(swigCPtr, this, btCollisionObject.getCPtr(collisionObject), collisionObject, collisionFilterGroup);
    }

    public void addCollisionObject(btCollisionObject collisionObject){
        CollisionJNI.btCollisionWorld_addCollisionObject__SWIG_2(swigCPtr, this, btCollisionObject.getCPtr(collisionObject), collisionObject);
    }

    public btCollisionObjectArray getCollisionObjectArray(){
        return new btCollisionObjectArray(CollisionJNI.btCollisionWorld_getCollisionObjectArray(swigCPtr, this), false);
    }

    public btCollisionObjectArray getCollisionObjectArrayConst(){
        return new btCollisionObjectArray(CollisionJNI.btCollisionWorld_getCollisionObjectArrayConst(swigCPtr, this), false);
    }

    public void removeCollisionObject(btCollisionObject collisionObject){
        CollisionJNI.btCollisionWorld_removeCollisionObject(swigCPtr, this, btCollisionObject.getCPtr(collisionObject), collisionObject);
    }

    public void performDiscreteCollisionDetection(){
        CollisionJNI.btCollisionWorld_performDiscreteCollisionDetection(swigCPtr, this);
    }

    public btDispatcherInfo getDispatchInfo(){
        return new btDispatcherInfo(CollisionJNI.btCollisionWorld_getDispatchInfo(swigCPtr, this), false);
    }

    public btDispatcherInfo getDispatchInfoConst(){
        return new btDispatcherInfo(CollisionJNI.btCollisionWorld_getDispatchInfoConst(swigCPtr, this), false);
    }

    public boolean getForceUpdateAllAabbs(){
        return CollisionJNI.btCollisionWorld_getForceUpdateAllAabbs(swigCPtr, this);
    }

    public void setForceUpdateAllAabbs(boolean forceUpdateAllAabbs){
        CollisionJNI.btCollisionWorld_setForceUpdateAllAabbs(swigCPtr, this, forceUpdateAllAabbs);
    }

    public void serialize(btSerializer serializer){
        CollisionJNI.btCollisionWorld_serialize(swigCPtr, this, btSerializer.getCPtr(serializer), serializer);
    }

}
