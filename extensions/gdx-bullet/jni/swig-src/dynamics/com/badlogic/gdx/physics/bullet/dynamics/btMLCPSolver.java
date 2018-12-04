/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

public class btMLCPSolver extends btSequentialImpulseConstraintSolver{
    private long swigCPtr;

    protected btMLCPSolver(final String className, long cPtr, boolean cMemoryOwn){
        super(className, DynamicsJNI.btMLCPSolver_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btMLCPSolver, normally you should not need this constructor it's intended for low-level usage. */
    public btMLCPSolver(long cPtr, boolean cMemoryOwn){
        this("btMLCPSolver", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(DynamicsJNI.btMLCPSolver_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btMLCPSolver obj){
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
                DynamicsJNI.delete_btMLCPSolver(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public btMLCPSolver(btMLCPSolverInterface solver){
        this(DynamicsJNI.new_btMLCPSolver(btMLCPSolverInterface.getCPtr(solver), solver), true);
    }

    public void setMLCPSolver(btMLCPSolverInterface solver){
        DynamicsJNI.btMLCPSolver_setMLCPSolver(swigCPtr, this, btMLCPSolverInterface.getCPtr(solver), solver);
    }

    public int getNumFallbacks(){
        return DynamicsJNI.btMLCPSolver_getNumFallbacks(swigCPtr, this);
    }

    public void setNumFallbacks(int num){
        DynamicsJNI.btMLCPSolver_setNumFallbacks(swigCPtr, this, num);
    }

}
