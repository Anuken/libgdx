/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.linearmath;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.BulletBase;

public class btMatrix3x3 extends BulletBase{
    private long swigCPtr;

    protected btMatrix3x3(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btMatrix3x3, normally you should not need this constructor it's intended for low-level usage. */
    public btMatrix3x3(long cPtr, boolean cMemoryOwn){
        this("btMatrix3x3", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btMatrix3x3 obj){
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
                LinearMathJNI.delete_btMatrix3x3(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public btMatrix3x3(){
        this(LinearMathJNI.new_btMatrix3x3__SWIG_0(), true);
    }

    public btMatrix3x3(Quaternion q){
        this(LinearMathJNI.new_btMatrix3x3__SWIG_1(q), true);
    }

    public btMatrix3x3(float xx, float xy, float xz, float yx, float yy, float yz, float zx, float zy, float zz){
        this(LinearMathJNI.new_btMatrix3x3__SWIG_2(xx, xy, xz, yx, yy, yz, zx, zy, zz), true);
    }

    public btMatrix3x3(Matrix3 other){
        this(LinearMathJNI.new_btMatrix3x3__SWIG_3(other), true);
    }

    public Matrix3 operatorAssignment(Matrix3 other){
        return LinearMathJNI.btMatrix3x3_operatorAssignment(swigCPtr, this, other);
    }

    public Vector3 getColumn(int i){
        return LinearMathJNI.btMatrix3x3_getColumn(swigCPtr, this, i);
    }

    public Vector3 getRow(int i){
        return LinearMathJNI.btMatrix3x3_getRow(swigCPtr, this, i);
    }

    public Vector3 operatorSubscript(int i){
        return LinearMathJNI.btMatrix3x3_operatorSubscript(swigCPtr, this, i);
    }

    public Vector3 operatorSubscriptConst(int i){
        return LinearMathJNI.btMatrix3x3_operatorSubscriptConst(swigCPtr, this, i);
    }

    public Matrix3 operatorMultiplicationAssignment(Matrix3 m){
        return LinearMathJNI.btMatrix3x3_operatorMultiplicationAssignment(swigCPtr, this, m);
    }

    public Matrix3 operatorAdditionAssignment(Matrix3 m){
        return LinearMathJNI.btMatrix3x3_operatorAdditionAssignment(swigCPtr, this, m);
    }

    public Matrix3 operatorSubtractionAssignment(Matrix3 m){
        return LinearMathJNI.btMatrix3x3_operatorSubtractionAssignment(swigCPtr, this, m);
    }

    public void setFromOpenGLSubMatrix(java.nio.FloatBuffer m){
        assert m.isDirect() : "Buffer must be allocated direct.";
        {
            LinearMathJNI.btMatrix3x3_setFromOpenGLSubMatrix(swigCPtr, this, m);
        }
    }

    public void setValue(float xx, float xy, float xz, float yx, float yy, float yz, float zx, float zy, float zz){
        LinearMathJNI.btMatrix3x3_setValue(swigCPtr, this, xx, xy, xz, yx, yy, yz, zx, zy, zz);
    }

    public void setRotation(Quaternion q){
        LinearMathJNI.btMatrix3x3_setRotation(swigCPtr, this, q);
    }

    public void setEulerYPR(float yaw, float pitch, float roll){
        LinearMathJNI.btMatrix3x3_setEulerYPR(swigCPtr, this, yaw, pitch, roll);
    }

    public void setEulerZYX(float eulerX, float eulerY, float eulerZ){
        LinearMathJNI.btMatrix3x3_setEulerZYX(swigCPtr, this, eulerX, eulerY, eulerZ);
    }

    public void setIdentity(){
        LinearMathJNI.btMatrix3x3_setIdentity(swigCPtr, this);
    }

    public static Matrix3 getIdentity(){
        return LinearMathJNI.btMatrix3x3_getIdentity();
    }

    public void getOpenGLSubMatrix(java.nio.FloatBuffer m){
        assert m.isDirect() : "Buffer must be allocated direct.";
        {
            LinearMathJNI.btMatrix3x3_getOpenGLSubMatrix(swigCPtr, this, m);
        }
    }

    public void getRotation(Quaternion q){
        LinearMathJNI.btMatrix3x3_getRotation(swigCPtr, this, q);
    }

    public void getEulerYPR(SWIGTYPE_p_float yaw, SWIGTYPE_p_float pitch, SWIGTYPE_p_float roll){
        LinearMathJNI.btMatrix3x3_getEulerYPR(swigCPtr, this, SWIGTYPE_p_float.getCPtr(yaw), SWIGTYPE_p_float.getCPtr(pitch), SWIGTYPE_p_float.getCPtr(roll));
    }

    public void getEulerZYX(SWIGTYPE_p_float yaw, SWIGTYPE_p_float pitch, SWIGTYPE_p_float roll, long solution_number){
        LinearMathJNI.btMatrix3x3_getEulerZYX__SWIG_0(swigCPtr, this, SWIGTYPE_p_float.getCPtr(yaw), SWIGTYPE_p_float.getCPtr(pitch), SWIGTYPE_p_float.getCPtr(roll), solution_number);
    }

    public void getEulerZYX(SWIGTYPE_p_float yaw, SWIGTYPE_p_float pitch, SWIGTYPE_p_float roll){
        LinearMathJNI.btMatrix3x3_getEulerZYX__SWIG_1(swigCPtr, this, SWIGTYPE_p_float.getCPtr(yaw), SWIGTYPE_p_float.getCPtr(pitch), SWIGTYPE_p_float.getCPtr(roll));
    }

    public Matrix3 scaled(Vector3 s){
        return LinearMathJNI.btMatrix3x3_scaled(swigCPtr, this, s);
    }

    public float determinant(){
        return LinearMathJNI.btMatrix3x3_determinant(swigCPtr, this);
    }

    public Matrix3 adjoint(){
        return LinearMathJNI.btMatrix3x3_adjoint(swigCPtr, this);
    }

    public Matrix3 absolute(){
        return LinearMathJNI.btMatrix3x3_absolute(swigCPtr, this);
    }

    public Matrix3 transpose(){
        return LinearMathJNI.btMatrix3x3_transpose(swigCPtr, this);
    }

    public Matrix3 inverse(){
        return LinearMathJNI.btMatrix3x3_inverse(swigCPtr, this);
    }

    public Vector3 solve33(Vector3 b){
        return LinearMathJNI.btMatrix3x3_solve33(swigCPtr, this, b);
    }

    public Matrix3 transposeTimes(Matrix3 m){
        return LinearMathJNI.btMatrix3x3_transposeTimes(swigCPtr, this, m);
    }

    public Matrix3 timesTranspose(Matrix3 m){
        return LinearMathJNI.btMatrix3x3_timesTranspose(swigCPtr, this, m);
    }

    public float tdotx(Vector3 v){
        return LinearMathJNI.btMatrix3x3_tdotx(swigCPtr, this, v);
    }

    public float tdoty(Vector3 v){
        return LinearMathJNI.btMatrix3x3_tdoty(swigCPtr, this, v);
    }

    public float tdotz(Vector3 v){
        return LinearMathJNI.btMatrix3x3_tdotz(swigCPtr, this, v);
    }

    public void extractRotation(Quaternion q, float tolerance, int maxIter){
        LinearMathJNI.btMatrix3x3_extractRotation__SWIG_0(swigCPtr, this, q, tolerance, maxIter);
    }

    public void extractRotation(Quaternion q, float tolerance){
        LinearMathJNI.btMatrix3x3_extractRotation__SWIG_1(swigCPtr, this, q, tolerance);
    }

    public void extractRotation(Quaternion q){
        LinearMathJNI.btMatrix3x3_extractRotation__SWIG_2(swigCPtr, this, q);
    }

    public void diagonalize(Matrix3 rot, float tolerance, int maxIter){
        LinearMathJNI.btMatrix3x3_diagonalize__SWIG_0(swigCPtr, this, rot, tolerance, maxIter);
    }

    public void diagonalize(Matrix3 rot, float tolerance){
        LinearMathJNI.btMatrix3x3_diagonalize__SWIG_1(swigCPtr, this, rot, tolerance);
    }

    public void diagonalize(Matrix3 rot){
        LinearMathJNI.btMatrix3x3_diagonalize__SWIG_2(swigCPtr, this, rot);
    }

    public float cofac(int r1, int c1, int r2, int c2){
        return LinearMathJNI.btMatrix3x3_cofac(swigCPtr, this, r1, c1, r2, c2);
    }

    public void serialize(btMatrix3x3FloatData dataOut){
        LinearMathJNI.btMatrix3x3_serialize(swigCPtr, this, btMatrix3x3FloatData.getCPtr(dataOut), dataOut);
    }

    public void serializeFloat(btMatrix3x3FloatData dataOut){
        LinearMathJNI.btMatrix3x3_serializeFloat(swigCPtr, this, btMatrix3x3FloatData.getCPtr(dataOut), dataOut);
    }

    public void deSerialize(btMatrix3x3FloatData dataIn){
        LinearMathJNI.btMatrix3x3_deSerialize(swigCPtr, this, btMatrix3x3FloatData.getCPtr(dataIn), dataIn);
    }

    public void deSerializeFloat(btMatrix3x3FloatData dataIn){
        LinearMathJNI.btMatrix3x3_deSerializeFloat(swigCPtr, this, btMatrix3x3FloatData.getCPtr(dataIn), dataIn);
    }

    public void deSerializeDouble(btMatrix3x3DoubleData dataIn){
        LinearMathJNI.btMatrix3x3_deSerializeDouble(swigCPtr, this, btMatrix3x3DoubleData.getCPtr(dataIn), dataIn);
    }

}
