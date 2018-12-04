/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class btGeneric6DofSpringConstraint extends btGeneric6DofConstraint{
    private long swigCPtr;

    protected btGeneric6DofSpringConstraint(final String className, long cPtr, boolean cMemoryOwn){
        super(className, DynamicsJNI.btGeneric6DofSpringConstraint_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btGeneric6DofSpringConstraint, normally you should not need this constructor it's intended for low-level usage. */
    public btGeneric6DofSpringConstraint(long cPtr, boolean cMemoryOwn){
        this("btGeneric6DofSpringConstraint", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(DynamicsJNI.btGeneric6DofSpringConstraint_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btGeneric6DofSpringConstraint obj){
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
                DynamicsJNI.delete_btGeneric6DofSpringConstraint(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return DynamicsJNI.btGeneric6DofSpringConstraint_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        DynamicsJNI.btGeneric6DofSpringConstraint_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return DynamicsJNI.btGeneric6DofSpringConstraint_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        DynamicsJNI.btGeneric6DofSpringConstraint_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return DynamicsJNI.btGeneric6DofSpringConstraint_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        DynamicsJNI.btGeneric6DofSpringConstraint_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return DynamicsJNI.btGeneric6DofSpringConstraint_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        DynamicsJNI.btGeneric6DofSpringConstraint_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public btGeneric6DofSpringConstraint(btRigidBody rbA, btRigidBody rbB, Matrix4 frameInA, Matrix4 frameInB, boolean useLinearReferenceFrameA){
        this(DynamicsJNI.new_btGeneric6DofSpringConstraint__SWIG_0(btRigidBody.getCPtr(rbA), rbA, btRigidBody.getCPtr(rbB), rbB, frameInA, frameInB, useLinearReferenceFrameA), true);
    }

    public btGeneric6DofSpringConstraint(btRigidBody rbB, Matrix4 frameInB, boolean useLinearReferenceFrameB){
        this(DynamicsJNI.new_btGeneric6DofSpringConstraint__SWIG_1(btRigidBody.getCPtr(rbB), rbB, frameInB, useLinearReferenceFrameB), true);
    }

    public void enableSpring(int index, boolean onOff){
        DynamicsJNI.btGeneric6DofSpringConstraint_enableSpring(swigCPtr, this, index, onOff);
    }

    public void setStiffness(int index, float stiffness){
        DynamicsJNI.btGeneric6DofSpringConstraint_setStiffness(swigCPtr, this, index, stiffness);
    }

    public void setDamping(int index, float damping){
        DynamicsJNI.btGeneric6DofSpringConstraint_setDamping(swigCPtr, this, index, damping);
    }

    public void setEquilibriumPoint(){
        DynamicsJNI.btGeneric6DofSpringConstraint_setEquilibriumPoint__SWIG_0(swigCPtr, this);
    }

    public void setEquilibriumPoint(int index){
        DynamicsJNI.btGeneric6DofSpringConstraint_setEquilibriumPoint__SWIG_1(swigCPtr, this, index);
    }

    public void setEquilibriumPoint(int index, float val){
        DynamicsJNI.btGeneric6DofSpringConstraint_setEquilibriumPoint__SWIG_2(swigCPtr, this, index, val);
    }

    public boolean isSpringEnabled(int index){
        return DynamicsJNI.btGeneric6DofSpringConstraint_isSpringEnabled(swigCPtr, this, index);
    }

    public float getStiffness(int index){
        return DynamicsJNI.btGeneric6DofSpringConstraint_getStiffness(swigCPtr, this, index);
    }

    public float getDamping(int index){
        return DynamicsJNI.btGeneric6DofSpringConstraint_getDamping(swigCPtr, this, index);
    }

    public float getEquilibriumPoint(int index){
        return DynamicsJNI.btGeneric6DofSpringConstraint_getEquilibriumPoint(swigCPtr, this, index);
    }

    public void setAxis(Vector3 axis1, Vector3 axis2){
        DynamicsJNI.btGeneric6DofSpringConstraint_setAxis(swigCPtr, this, axis1, axis2);
    }

}
