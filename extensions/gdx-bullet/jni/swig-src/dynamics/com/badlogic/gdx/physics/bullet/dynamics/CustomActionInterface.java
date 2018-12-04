/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

public class CustomActionInterface extends btActionInterface{
    private long swigCPtr;

    protected CustomActionInterface(final String className, long cPtr, boolean cMemoryOwn){
        super(className, DynamicsJNI.CustomActionInterface_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new CustomActionInterface, normally you should not need this constructor it's intended for low-level usage. */
    public CustomActionInterface(long cPtr, boolean cMemoryOwn){
        this("CustomActionInterface", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(DynamicsJNI.CustomActionInterface_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(CustomActionInterface obj){
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
                DynamicsJNI.delete_CustomActionInterface(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    protected void swigDirectorDisconnect(){
        swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership(){
        swigCMemOwn = false;
        DynamicsJNI.CustomActionInterface_change_ownership(this, swigCPtr, false);
    }

    public void swigTakeOwnership(){
        swigCMemOwn = true;
        DynamicsJNI.CustomActionInterface_change_ownership(this, swigCPtr, true);
    }

    public void updateAction(float timeStep){
        DynamicsJNI.CustomActionInterface_updateAction(swigCPtr, this, timeStep);
    }

    public void debugDraw(){
        DynamicsJNI.CustomActionInterface_debugDraw(swigCPtr, this);
    }

    public CustomActionInterface(){
        this(DynamicsJNI.new_CustomActionInterface(), true);
        DynamicsJNI.CustomActionInterface_director_connect(this, swigCPtr, swigCMemOwn, true);
    }

}
