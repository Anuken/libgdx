/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.dynamics;

public class btSequentialImpulseConstraintSolver extends btConstraintSolver{
    private long swigCPtr;

    protected btSequentialImpulseConstraintSolver(final String className, long cPtr, boolean cMemoryOwn){
        super(className, DynamicsJNI.btSequentialImpulseConstraintSolver_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btSequentialImpulseConstraintSolver, normally you should not need this constructor it's intended for low-level usage. */
    public btSequentialImpulseConstraintSolver(long cPtr, boolean cMemoryOwn){
        this("btSequentialImpulseConstraintSolver", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(DynamicsJNI.btSequentialImpulseConstraintSolver_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btSequentialImpulseConstraintSolver obj){
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
                DynamicsJNI.delete_btSequentialImpulseConstraintSolver(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        DynamicsJNI.btSequentialImpulseConstraintSolver_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        DynamicsJNI.btSequentialImpulseConstraintSolver_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        DynamicsJNI.btSequentialImpulseConstraintSolver_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        DynamicsJNI.btSequentialImpulseConstraintSolver_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public btSequentialImpulseConstraintSolver(){
        this(DynamicsJNI.new_btSequentialImpulseConstraintSolver(), true);
    }

    public long btRand2(){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_btRand2(swigCPtr, this);
    }

    public int btRandInt2(int n){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_btRandInt2(swigCPtr, this, n);
    }

    public void setRandSeed(long seed){
        DynamicsJNI.btSequentialImpulseConstraintSolver_setRandSeed(swigCPtr, this, seed);
    }

    public long getRandSeed(){
        return DynamicsJNI.btSequentialImpulseConstraintSolver_getRandSeed(swigCPtr, this);
    }

    public SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar getActiveConstraintRowSolverGeneric(){
        long cPtr = DynamicsJNI.btSequentialImpulseConstraintSolver_getActiveConstraintRowSolverGeneric(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar(cPtr, false);
    }

    public void setConstraintRowSolverGeneric(SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar rowSolver){
        DynamicsJNI.btSequentialImpulseConstraintSolver_setConstraintRowSolverGeneric(swigCPtr, this, SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar.getCPtr(rowSolver));
    }

    public SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar getActiveConstraintRowSolverLowerLimit(){
        long cPtr = DynamicsJNI.btSequentialImpulseConstraintSolver_getActiveConstraintRowSolverLowerLimit(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar(cPtr, false);
    }

    public void setConstraintRowSolverLowerLimit(SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar rowSolver){
        DynamicsJNI.btSequentialImpulseConstraintSolver_setConstraintRowSolverLowerLimit(swigCPtr, this, SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar.getCPtr(rowSolver));
    }

    public SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar getScalarConstraintRowSolverGeneric(){
        long cPtr = DynamicsJNI.btSequentialImpulseConstraintSolver_getScalarConstraintRowSolverGeneric(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar(cPtr, false);
    }

    public SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar getScalarConstraintRowSolverLowerLimit(){
        long cPtr = DynamicsJNI.btSequentialImpulseConstraintSolver_getScalarConstraintRowSolverLowerLimit(swigCPtr, this);
        return (cPtr == 0) ? null : new SWIGTYPE_p_f_r_btSolverBody_r_btSolverBody_r_q_const__btSolverConstraint__btSimdScalar(cPtr, false);
    }

}
