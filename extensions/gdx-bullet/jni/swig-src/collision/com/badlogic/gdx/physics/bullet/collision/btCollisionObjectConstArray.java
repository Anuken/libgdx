/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;

public class btCollisionObjectConstArray extends BulletBase{
    private long swigCPtr;

    protected btCollisionObjectConstArray(final String className, long cPtr, boolean cMemoryOwn){
        super(className, cPtr, cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btCollisionObjectConstArray, normally you should not need this constructor it's intended for low-level usage. */
    public btCollisionObjectConstArray(long cPtr, boolean cMemoryOwn){
        this("btCollisionObjectConstArray", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(swigCPtr = cPtr, cMemoryOwn);
    }

    public static long getCPtr(btCollisionObjectConstArray obj){
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
                CollisionJNI.delete_btCollisionObjectConstArray(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public btCollisionObjectConstArray operatorAssignment(btCollisionObjectConstArray other){
        return new btCollisionObjectConstArray(CollisionJNI.btCollisionObjectConstArray_operatorAssignment(swigCPtr, this, btCollisionObjectConstArray.getCPtr(other), other), false);
    }

    public btCollisionObjectConstArray(){
        this(CollisionJNI.new_btCollisionObjectConstArray__SWIG_0(), true);
    }

    public btCollisionObjectConstArray(btCollisionObjectConstArray otherArray){
        this(CollisionJNI.new_btCollisionObjectConstArray__SWIG_1(btCollisionObjectConstArray.getCPtr(otherArray), otherArray), true);
    }

    public int size(){
        return CollisionJNI.btCollisionObjectConstArray_size(swigCPtr, this);
    }

    public btCollisionObject atConst(int n){
        return btCollisionObject.getInstance(CollisionJNI.btCollisionObjectConstArray_atConst(swigCPtr, this, n), false);
    }

    public SWIGTYPE_p_p_btCollisionObject at(int n){
        return new SWIGTYPE_p_p_btCollisionObject(CollisionJNI.btCollisionObjectConstArray_at(swigCPtr, this, n), false);
    }

    public btCollisionObject operatorSubscriptConst(int n){
        return btCollisionObject.getInstance(CollisionJNI.btCollisionObjectConstArray_operatorSubscriptConst(swigCPtr, this, n), false);
    }

    public SWIGTYPE_p_p_btCollisionObject operatorSubscript(int n){
        return new SWIGTYPE_p_p_btCollisionObject(CollisionJNI.btCollisionObjectConstArray_operatorSubscript(swigCPtr, this, n), false);
    }

    public void clear(){
        CollisionJNI.btCollisionObjectConstArray_clear(swigCPtr, this);
    }

    public void pop_back(){
        CollisionJNI.btCollisionObjectConstArray_pop_back(swigCPtr, this);
    }

    public void resizeNoInitialize(int newsize){
        CollisionJNI.btCollisionObjectConstArray_resizeNoInitialize(swigCPtr, this, newsize);
    }

    public void resize(int newsize, btCollisionObject fillData){
        CollisionJNI.btCollisionObjectConstArray_resize__SWIG_0(swigCPtr, this, newsize, btCollisionObject.getCPtr(fillData), fillData);
    }

    public void resize(int newsize){
        CollisionJNI.btCollisionObjectConstArray_resize__SWIG_1(swigCPtr, this, newsize);
    }

    public SWIGTYPE_p_p_btCollisionObject expandNonInitializing(){
        return new SWIGTYPE_p_p_btCollisionObject(CollisionJNI.btCollisionObjectConstArray_expandNonInitializing(swigCPtr, this), false);
    }

    public SWIGTYPE_p_p_btCollisionObject expand(btCollisionObject fillValue){
        return new SWIGTYPE_p_p_btCollisionObject(CollisionJNI.btCollisionObjectConstArray_expand__SWIG_0(swigCPtr, this, btCollisionObject.getCPtr(fillValue), fillValue), false);
    }

    public SWIGTYPE_p_p_btCollisionObject expand(){
        return new SWIGTYPE_p_p_btCollisionObject(CollisionJNI.btCollisionObjectConstArray_expand__SWIG_1(swigCPtr, this), false);
    }

    public void push_back(btCollisionObject _Val){
        CollisionJNI.btCollisionObjectConstArray_push_back(swigCPtr, this, btCollisionObject.getCPtr(_Val), _Val);
    }

    public int capacity(){
        return CollisionJNI.btCollisionObjectConstArray_capacity(swigCPtr, this);
    }

    public void reserve(int _Count){
        CollisionJNI.btCollisionObjectConstArray_reserve(swigCPtr, this, _Count);
    }

    static public class less extends BulletBase{
        private long swigCPtr;

        protected less(final String className, long cPtr, boolean cMemoryOwn){
            super(className, cPtr, cMemoryOwn);
            swigCPtr = cPtr;
        }

        /** Construct a new less, normally you should not need this constructor it's intended for low-level usage. */
        public less(long cPtr, boolean cMemoryOwn){
            this("less", cPtr, cMemoryOwn);
            construct();
        }

        @Override
        protected void reset(long cPtr, boolean cMemoryOwn){
            if(!destroyed)
                destroy();
            super.reset(swigCPtr = cPtr, cMemoryOwn);
        }

        public static long getCPtr(less obj){
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
                    CollisionJNI.delete_btCollisionObjectConstArray_less(swigCPtr);
                }
                swigCPtr = 0;
            }
            super.delete();
        }

        public boolean operatorFunctionCall(btCollisionObject a, btCollisionObject b){
            return CollisionJNI.btCollisionObjectConstArray_less_operatorFunctionCall(swigCPtr, this, btCollisionObject.getCPtr(a), a, btCollisionObject.getCPtr(b), b);
        }

        public less(){
            this(CollisionJNI.new_btCollisionObjectConstArray_less(), true);
        }

    }

    public void swap(int index0, int index1){
        CollisionJNI.btCollisionObjectConstArray_swap(swigCPtr, this, index0, index1);
    }

    public int findBinarySearch(btCollisionObject key){
        return CollisionJNI.btCollisionObjectConstArray_findBinarySearch(swigCPtr, this, btCollisionObject.getCPtr(key), key);
    }

    public int findLinearSearch(btCollisionObject key){
        return CollisionJNI.btCollisionObjectConstArray_findLinearSearch(swigCPtr, this, btCollisionObject.getCPtr(key), key);
    }

    public int findLinearSearch2(btCollisionObject key){
        return CollisionJNI.btCollisionObjectConstArray_findLinearSearch2(swigCPtr, this, btCollisionObject.getCPtr(key), key);
    }

    public void removeAtIndex(int index){
        CollisionJNI.btCollisionObjectConstArray_removeAtIndex(swigCPtr, this, index);
    }

    public void remove(btCollisionObject key){
        CollisionJNI.btCollisionObjectConstArray_remove(swigCPtr, this, btCollisionObject.getCPtr(key), key);
    }

    public void initializeFromBuffer(long buffer, int size, int capacity){
        CollisionJNI.btCollisionObjectConstArray_initializeFromBuffer(swigCPtr, this, buffer, size, capacity);
    }

    public void copyFromArray(btCollisionObjectConstArray otherArray){
        CollisionJNI.btCollisionObjectConstArray_copyFromArray(swigCPtr, this, btCollisionObjectConstArray.getCPtr(otherArray), otherArray);
    }

}
