package com.fagawee.flib;

import android.util.Log;

/**
 * Created by Mr.Tian on 2019/12/20.
 */

public class Persion implements IPersion{


    @Override
    public void say() {
        Log.i("T","say");
    }

    @Override
    public void wark() {
        Log.i("T","wark");
    }


}
interface  IPersion
{
    void say();
    void wark();
}