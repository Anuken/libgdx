/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class btTypedConstraintDoubleData extends BulletBase{
    private long swigCPtr;

    protected btTypedConstraintDoubleData(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btTypedConstraintDoubleData, normally you should not need this constructor it's intended for low-level usage. */
    public btTypedConstraintDoubleData(long cPtr, boolean cMemoryOwn){
        this("btTypedConstraintDoubleData", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btTypedConstraintDoubleData obj){
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
                DynamicsJNI.delete_btTypedConstraintDoubleData(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public void setRbA(btRigidBodyDoubleData value){
        DynamicsJNI.btTypedConstraintDoubleData_rbA_set(swigCPtr, this, btRigidBodyDoubleData.getCPtr(value), value);
    }

    public btRigidBodyDoubleData getRbA(){
        long cPtr = DynamicsJNI.btTypedConstraintDoubleData_rbA_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btRigidBodyDoubleData(cPtr, false);
    }

    public void setRbB(btRigidBodyDoubleData value){
        DynamicsJNI.btTypedConstraintDoubleData_rbB_set(swigCPtr, this, btRigidBodyDoubleData.getCPtr(value), value);
    }

    public btRigidBodyDoubleData getRbB(){
        long cPtr = DynamicsJNI.btTypedConstraintDoubleData_rbB_get(swigCPtr, this);
        return (cPtr == 0) ? null : new btRigidBodyDoubleData(cPtr, false);
    }

    public void setName(String value){
        DynamicsJNI.btTypedConstraintDoubleData_name_set(swigCPtr, this, value);
    }

    public String getName(){
        return DynamicsJNI.btTypedConstraintDoubleData_name_get(swigCPtr, this);
    }

    public void setObjectType(int value){
        DynamicsJNI.btTypedConstraintDoubleData_objectType_set(swigCPtr, this, value);
    }

    public int getObjectType(){
        return DynamicsJNI.btTypedConstraintDoubleData_objectType_get(swigCPtr, this);
    }

    public void setUserConstraintType(int value){
        DynamicsJNI.btTypedConstraintDoubleData_userConstraintType_set(swigCPtr, this, value);
    }

    public int getUserConstraintType(){
        return DynamicsJNI.btTypedConstraintDoubleData_userConstraintType_get(swigCPtr, this);
    }

    public void setUserConstraintId(int value){
        DynamicsJNI.btTypedConstraintDoubleData_userConstraintId_set(swigCPtr, this, value);
    }

    public int getUserConstraintId(){
        return DynamicsJNI.btTypedConstraintDoubleData_userConstraintId_get(swigCPtr, this);
    }

    public void setNeedsFeedback(int value){
        DynamicsJNI.btTypedConstraintDoubleData_needsFeedback_set(swigCPtr, this, value);
    }

    public int getNeedsFeedback(){
        return DynamicsJNI.btTypedConstraintDoubleData_needsFeedback_get(swigCPtr, this);
    }

    public void setAppliedImpulse(double value){
        DynamicsJNI.btTypedConstraintDoubleData_appliedImpulse_set(swigCPtr, this, value);
    }

    public double getAppliedImpulse(){
        return DynamicsJNI.btTypedConstraintDoubleData_appliedImpulse_get(swigCPtr, this);
    }

    public void setDbgDrawSize(double value){
        DynamicsJNI.btTypedConstraintDoubleData_dbgDrawSize_set(swigCPtr, this, value);
    }

    public double getDbgDrawSize(){
        return DynamicsJNI.btTypedConstraintDoubleData_dbgDrawSize_get(swigCPtr, this);
    }

    public void setDisableCollisionsBetweenLinkedBodies(int value){
        DynamicsJNI.btTypedConstraintDoubleData_disableCollisionsBetweenLinkedBodies_set(swigCPtr, this, value);
    }

    public int getDisableCollisionsBetweenLinkedBodies(){
        return DynamicsJNI.btTypedConstraintDoubleData_disableCollisionsBetweenLinkedBodies_get(swigCPtr, this);
    }

    public void setOverrideNumSolverIterations(int value){
        DynamicsJNI.btTypedConstraintDoubleData_overrideNumSolverIterations_set(swigCPtr, this, value);
    }

    public int getOverrideNumSolverIterations(){
        return DynamicsJNI.btTypedConstraintDoubleData_overrideNumSolverIterations_get(swigCPtr, this);
    }

    public void setBreakingImpulseThreshold(double value){
        DynamicsJNI.btTypedConstraintDoubleData_breakingImpulseThreshold_set(swigCPtr, this, value);
    }

    public double getBreakingImpulseThreshold(){
        return DynamicsJNI.btTypedConstraintDoubleData_breakingImpulseThreshold_get(swigCPtr, this);
    }

    public void setIsEnabled(int value){
        DynamicsJNI.btTypedConstraintDoubleData_isEnabled_set(swigCPtr, this, value);
    }

    public int getIsEnabled(){
        return DynamicsJNI.btTypedConstraintDoubleData_isEnabled_get(swigCPtr, this);
    }

    public void setPadding(String value){
        DynamicsJNI.btTypedConstraintDoubleData_padding_set(swigCPtr, this, value);
    }

    public String getPadding(){
        return DynamicsJNI.btTypedConstraintDoubleData_padding_get(swigCPtr, this);
    }

    public btTypedConstraintDoubleData(){
        this(DynamicsJNI.new_btTypedConstraintDoubleData(), true);
    }

}
