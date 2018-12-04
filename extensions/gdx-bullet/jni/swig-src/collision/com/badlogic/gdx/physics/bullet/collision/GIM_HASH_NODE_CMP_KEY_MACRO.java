/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class GIM_HASH_NODE_CMP_KEY_MACRO extends BulletBase{
    private long swigCPtr;

    protected GIM_HASH_NODE_CMP_KEY_MACRO(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new GIM_HASH_NODE_CMP_KEY_MACRO, normally you should not need this constructor it's intended for low-level usage. */
    public GIM_HASH_NODE_CMP_KEY_MACRO(long cPtr, boolean cMemoryOwn){
        this("GIM_HASH_NODE_CMP_KEY_MACRO", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(GIM_HASH_NODE_CMP_KEY_MACRO obj){
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
                CollisionJNI.delete_GIM_HASH_NODE_CMP_KEY_MACRO(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public GIM_HASH_NODE_CMP_KEY_MACRO(){
        this(CollisionJNI.new_GIM_HASH_NODE_CMP_KEY_MACRO(), true);
    }

}
