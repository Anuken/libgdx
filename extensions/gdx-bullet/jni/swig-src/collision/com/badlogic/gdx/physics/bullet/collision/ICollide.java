/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class ICollide extends BulletBase{
    private long swigCPtr;

    protected ICollide(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new ICollide, normally you should not need this constructor it's intended for low-level usage. */
    public ICollide(long cPtr, boolean cMemoryOwn){
        this("ICollide", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(ICollide obj){
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
                CollisionJNI.delete_ICollide(swigCPtr);
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
        CollisionJNI.ICollide_change_ownership(this, swigCPtr, false);
    }

    public void swigTakeOwnership(){
        swigCMemOwn = true;
        CollisionJNI.ICollide_change_ownership(this, swigCPtr, true);
    }

    public void Process(btDbvtNode arg0, btDbvtNode arg1){
        if(getClass() == ICollide.class)
            CollisionJNI.ICollide_Process__SWIG_0(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0, btDbvtNode.getCPtr(arg1), arg1);
        else
            CollisionJNI.ICollide_ProcessSwigExplicitICollide__SWIG_0(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0, btDbvtNode.getCPtr(arg1), arg1);
    }

    public void Process(btDbvtNode arg0){
        if(getClass() == ICollide.class)
            CollisionJNI.ICollide_Process__SWIG_1(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
        else CollisionJNI.ICollide_ProcessSwigExplicitICollide__SWIG_1(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }

    public void Process(btDbvtNode n, float arg1){
        if(getClass() == ICollide.class)
            CollisionJNI.ICollide_Process__SWIG_2(swigCPtr, this, btDbvtNode.getCPtr(n), n, arg1);
        else CollisionJNI.ICollide_ProcessSwigExplicitICollide__SWIG_2(swigCPtr, this, btDbvtNode.getCPtr(n), n, arg1);
    }

    public boolean Descent(btDbvtNode arg0){
        return (getClass() == ICollide.class) ? CollisionJNI.ICollide_Descent(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0) : CollisionJNI.ICollide_DescentSwigExplicitICollide(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }

    public boolean AllLeaves(btDbvtNode arg0){
        return (getClass() == ICollide.class) ? CollisionJNI.ICollide_AllLeaves(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0) : CollisionJNI.ICollide_AllLeavesSwigExplicitICollide(swigCPtr, this, btDbvtNode.getCPtr(arg0), arg0);
    }

    public ICollide(){
        this(CollisionJNI.new_ICollide(), true);
        CollisionJNI.ICollide_director_connect(this, swigCPtr, swigCMemOwn, true);
    }

}
