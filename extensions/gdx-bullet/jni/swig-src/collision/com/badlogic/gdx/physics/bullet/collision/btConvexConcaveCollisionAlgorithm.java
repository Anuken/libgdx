/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

public class btConvexConcaveCollisionAlgorithm extends btActivatingCollisionAlgorithm{
    private long swigCPtr;

    protected btConvexConcaveCollisionAlgorithm(final String className, long cPtr, boolean cMemoryOwn){
        super(className, CollisionJNI.btConvexConcaveCollisionAlgorithm_SWIGUpcast(cPtr), cMemoryOwn);
        swigCPtr = cPtr;
    }

    /** Construct a new btConvexConcaveCollisionAlgorithm, normally you should not need this constructor it's intended for low-level usage. */
    public btConvexConcaveCollisionAlgorithm(long cPtr, boolean cMemoryOwn){
        this("btConvexConcaveCollisionAlgorithm", cPtr, cMemoryOwn);
        construct();
    }

    @Override
    protected void reset(long cPtr, boolean cMemoryOwn){
        if(!destroyed)
            destroy();
        super.reset(CollisionJNI.btConvexConcaveCollisionAlgorithm_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
    }

    public static long getCPtr(btConvexConcaveCollisionAlgorithm obj){
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
                CollisionJNI.delete_btConvexConcaveCollisionAlgorithm(swigCPtr);
            }
            swigCPtr = 0;
        }
        super.delete();
    }

    public long operatorNew(long sizeInBytes){
        return CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorNew__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDelete(long ptr){
        CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorDelete__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNew(long arg0, long ptr){
        return CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorNew__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDelete(long arg0, long arg1){
        CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorDelete__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public long operatorNewArray(long sizeInBytes){
        return CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorNewArray__SWIG_0(swigCPtr, this, sizeInBytes);
    }

    public void operatorDeleteArray(long ptr){
        CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorDeleteArray__SWIG_0(swigCPtr, this, ptr);
    }

    public long operatorNewArray(long arg0, long ptr){
        return CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorNewArray__SWIG_1(swigCPtr, this, arg0, ptr);
    }

    public void operatorDeleteArray(long arg0, long arg1){
        CollisionJNI.btConvexConcaveCollisionAlgorithm_operatorDeleteArray__SWIG_1(swigCPtr, this, arg0, arg1);
    }

    public btConvexConcaveCollisionAlgorithm(btCollisionAlgorithmConstructionInfo ci, btCollisionObjectWrapper body0Wrap, btCollisionObjectWrapper body1Wrap, boolean isSwapped){
        this(CollisionJNI.new_btConvexConcaveCollisionAlgorithm(btCollisionAlgorithmConstructionInfo.getCPtr(ci), ci, btCollisionObjectWrapper.getCPtr(body0Wrap), body0Wrap, btCollisionObjectWrapper.getCPtr(body1Wrap), body1Wrap, isSwapped), true);
    }

    public void clearCache(){
        CollisionJNI.btConvexConcaveCollisionAlgorithm_clearCache(swigCPtr, this);
    }

    static public class CreateFunc extends btCollisionAlgorithmCreateFunc{
        private long swigCPtr;

        protected CreateFunc(final String className, long cPtr, boolean cMemoryOwn){
            super(className, CollisionJNI.btConvexConcaveCollisionAlgorithm_CreateFunc_SWIGUpcast(cPtr), cMemoryOwn);
            swigCPtr = cPtr;
        }

        /** Construct a new CreateFunc, normally you should not need this constructor it's intended for low-level usage. */
        public CreateFunc(long cPtr, boolean cMemoryOwn){
            this("CreateFunc", cPtr, cMemoryOwn);
            construct();
        }

        @Override
        protected void reset(long cPtr, boolean cMemoryOwn){
            if(!destroyed)
                destroy();
            super.reset(CollisionJNI.btConvexConcaveCollisionAlgorithm_CreateFunc_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
        }

        public static long getCPtr(CreateFunc obj){
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
                    CollisionJNI.delete_btConvexConcaveCollisionAlgorithm_CreateFunc(swigCPtr);
                }
                swigCPtr = 0;
            }
            super.delete();
        }

        public CreateFunc(){
            this(CollisionJNI.new_btConvexConcaveCollisionAlgorithm_CreateFunc(), true);
        }

    }

    static public class SwappedCreateFunc extends btCollisionAlgorithmCreateFunc{
        private long swigCPtr;

        protected SwappedCreateFunc(final String className, long cPtr, boolean cMemoryOwn){
            super(className, CollisionJNI.btConvexConcaveCollisionAlgorithm_SwappedCreateFunc_SWIGUpcast(cPtr), cMemoryOwn);
            swigCPtr = cPtr;
        }

        /** Construct a new SwappedCreateFunc, normally you should not need this constructor it's intended for low-level usage. */
        public SwappedCreateFunc(long cPtr, boolean cMemoryOwn){
            this("SwappedCreateFunc", cPtr, cMemoryOwn);
            construct();
        }

        @Override
        protected void reset(long cPtr, boolean cMemoryOwn){
            if(!destroyed)
                destroy();
            super.reset(CollisionJNI.btConvexConcaveCollisionAlgorithm_SwappedCreateFunc_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
        }

        public static long getCPtr(SwappedCreateFunc obj){
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
                    CollisionJNI.delete_btConvexConcaveCollisionAlgorithm_SwappedCreateFunc(swigCPtr);
                }
                swigCPtr = 0;
            }
            super.delete();
        }

        public SwappedCreateFunc(){
            this(CollisionJNI.new_btConvexConcaveCollisionAlgorithm_SwappedCreateFunc(), true);
        }

    }

}
