/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.extras;

public class SimpleTreeCreator extends MultiBodyTreeCreator{
    private long swigCPtr;

    protected SimpleTreeCreator(final String className, long cPtr, boolean cMemoryOwn){
        super(className, ExtrasJNI.SimpleTreeCreator_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new SimpleTreeCreator, normally you should not need this constructor it's intended for low-level usage. */
    public SimpleTreeCreator(long cPtr, boolean cMemoryOwn){
        this("SimpleTreeCreator", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(ExtrasJNI.SimpleTreeCreator_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(SimpleTreeCreator obj){
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
                ExtrasJNI.delete_SimpleTreeCreator(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public SimpleTreeCreator(int dim){
        this(ExtrasJNI.new_SimpleTreeCreator(dim), true);
    }

}
