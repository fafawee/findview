package com.fagawee.flib;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Mr.Tian on 2019/12/20.
 */

public class Invoke implements InvocationHandler {

    private Object obj;
    public Object bind(Object obj)
    {
        this.obj=obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("T","invoke1");
        Object objj=method.invoke(obj,args);
        Log.i("T","invoke2");
        return objj;
    }
}
