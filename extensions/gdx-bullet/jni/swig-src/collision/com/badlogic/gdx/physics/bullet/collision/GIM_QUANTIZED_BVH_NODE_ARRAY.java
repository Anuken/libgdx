/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

public class GIM_QUANTIZED_BVH_NODE_ARRAY extends btGimQuantizedBvhNodeArray{
    private long swigCPtr;

    protected GIM_QUANTIZED_BVH_NODE_ARRAY(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.GIM_QUANTIZED_BVH_NODE_ARRAY_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new GIM_QUANTIZED_BVH_NODE_ARRAY, normally you should not need this constructor it's intended for low-level usage. */
    public GIM_QUANTIZED_BVH_NODE_ARRAY(long cPtr, boolean cMemoryOwn){
        this("GIM_QUANTIZED_BVH_NODE_ARRAY", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.GIM_QUANTIZED_BVH_NODE_ARRAY_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(GIM_QUANTIZED_BVH_NODE_ARRAY obj){
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
                CollisionJNI.delete_GIM_QUANTIZED_BVH_NODE_ARRAY(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public GIM_QUANTIZED_BVH_NODE_ARRAY(){
        this(CollisionJNI.new_GIM_QUANTIZED_BVH_NODE_ARRAY(), true);
    }

}
