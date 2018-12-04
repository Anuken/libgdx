/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;

public class btGjkPairDetector extends btDiscreteCollisionDetectorInterface{
    private long swigCPtr;

    protected btGjkPairDetector(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btGjkPairDetector_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btGjkPairDetector, normally you should not need this constructor it's intended for low-level usage. */
    public btGjkPairDetector(long cPtr, boolean cMemoryOwn){
        this("btGjkPairDetector", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btGjkPairDetector_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btGjkPairDetector obj){
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
                CollisionJNI.delete_btGjkPairDetector(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setLastUsedMethod(int value){
        CollisionJNI.btGjkPairDetector_lastUsedMethod_set(swigCPtr, this, value);
    }

    public int getLastUsedMethod(){
        return CollisionJNI.btGjkPairDetector_lastUsedMethod_get(swigCPtr, this);
    }

    public void setCurIter(int value){
        CollisionJNI.btGjkPairDetector_curIter_set(swigCPtr, this, value);
    }

    public int getCurIter(){
        return CollisionJNI.btGjkPairDetector_curIter_get(swigCPtr, this);
    }

    public void setDegenerateSimplex(int value){
        CollisionJNI.btGjkPairDetector_degenerateSimplex_set(swigCPtr, this, value);
    }

    public int getDegenerateSimplex(){
        return CollisionJNI.btGjkPairDetector_degenerateSimplex_get(swigCPtr, this);
    }

    public void setCatchDegeneracies(int value){
        CollisionJNI.btGjkPairDetector_catchDegeneracies_set(swigCPtr, this, value);
    }

    public int getCatchDegeneracies(){
        return CollisionJNI.btGjkPairDetector_catchDegeneracies_get(swigCPtr, this);
    }

    public void setFixContactNormalDirection(int value){
        CollisionJNI.btGjkPairDetector_fixContactNormalDirection_set(swigCPtr, this, value);
    }

    public int getFixContactNormalDirection(){
        return CollisionJNI.btGjkPairDetector_fixContactNormalDirection_get(swigCPtr, this);
    }

    public btGjkPairDetector(btConvexShape objectA, btConvexShape objectB, btVoronoiSimplexSolver simplexSolver, btConvexPenetrationDepthSolver penetrationDepthSolver){
        this(CollisionJNI.new_btGjkPairDetector__SWIG_0(btConvexShape.getCPtr(objectA), objectA, btConvexShape.getCPtr(objectB), objectB, btVoronoiSimplexSolver.getCPtr(simplexSolver), simplexSolver, btConvexPenetrationDepthSolver.getCPtr(penetrationDepthSolver), penetrationDepthSolver), true);
    }

    public btGjkPairDetector(btConvexShape objectA, btConvexShape objectB, int shapeTypeA, int shapeTypeB, float marginA, float marginB, btVoronoiSimplexSolver simplexSolver, btConvexPenetrationDepthSolver penetrationDepthSolver){
        this(CollisionJNI.new_btGjkPairDetector__SWIG_1(btConvexShape.getCPtr(objectA), objectA, btConvexShape.getCPtr(objectB), objectB, shapeTypeA, shapeTypeB, marginA, marginB, btVoronoiSimplexSolver.getCPtr(simplexSolver), simplexSolver, btConvexPenetrationDepthSolver.getCPtr(penetrationDepthSolver), penetrationDepthSolver), true);
    }

    public void getClosestPoints(btDiscreteCollisionDetectorInterface.ClosestPointInput input, btDiscreteCollisionDetectorInterface.Result output, btIDebugDraw debugDraw, boolean swapResults){
        CollisionJNI.btGjkPairDetector_getClosestPoints__SWIG_0(swigCPtr, this, btDiscreteCollisionDetectorInterface.ClosestPointInput.getCPtr(input), input, btDiscreteCollisionDetectorInterface.Result.getCPtr(output), output, btIDebugDraw.getCPtr(debugDraw), debugDraw, swapResults);
    }

    public void getClosestPoints(btDiscreteCollisionDetectorInterface.ClosestPointInput input, btDiscreteCollisionDetectorInterface.Result output, btIDebugDraw debugDraw){
        CollisionJNI.btGjkPairDetector_getClosestPoints__SWIG_1(swigCPtr, this, btDiscreteCollisionDetectorInterface.ClosestPointInput.getCPtr(input), input, btDiscreteCollisionDetectorInterface.Result.getCPtr(output), output, btIDebugDraw.getCPtr(debugDraw), debugDraw);
    }

    public void getClosestPointsNonVirtual(btDiscreteCollisionDetectorInterface.ClosestPointInput input, btDiscreteCollisionDetectorInterface.Result output, btIDebugDraw debugDraw){
        CollisionJNI.btGjkPairDetector_getClosestPointsNonVirtual(swigCPtr, this, btDiscreteCollisionDetectorInterface.ClosestPointInput.getCPtr(input), input, btDiscreteCollisionDetectorInterface.Result.getCPtr(output), output, btIDebugDraw.getCPtr(debugDraw), debugDraw);
    }

    public void setMinkowskiA(btConvexShape minkA){
        CollisionJNI.btGjkPairDetector_setMinkowskiA(swigCPtr, this, btConvexShape.getCPtr(minkA), minkA);
    }

    public void setMinkowskiB(btConvexShape minkB){
        CollisionJNI.btGjkPairDetector_setMinkowskiB(swigCPtr, this, btConvexShape.getCPtr(minkB), minkB);
    }

    public void setCachedSeperatingAxis(Vector3 seperatingAxis){
        CollisionJNI.btGjkPairDetector_setCachedSeperatingAxis(swigCPtr, this, seperatingAxis);
    }

    public Vector3 getCachedSeparatingAxis(){
        return CollisionJNI.btGjkPairDetector_getCachedSeparatingAxis(swigCPtr, this);
    }

    public float getCachedSeparatingDistance(){
        return CollisionJNI.btGjkPairDetector_getCachedSeparatingDistance(swigCPtr, this);
    }

    public void setPenetrationDepthSolver(btConvexPenetrationDepthSolver penetrationDepthSolver){
        CollisionJNI.btGjkPairDetector_setPenetrationDepthSolver(swigCPtr, this, btConvexPenetrationDepthSolver.getCPtr(penetrationDepthSolver), penetrationDepthSolver);
    }

    public void setIgnoreMargin(boolean ignoreMargin){
        CollisionJNI.btGjkPairDetector_setIgnoreMargin(swigCPtr, this, ignoreMargin);
    }

}
