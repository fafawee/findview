package com.fagawee.flib.ioc.view;


import android.app.Activity;
import android.view.View;

/**
 * Created by Mr.Tian on 2019/12/19.
 */

public class ViewFinder {


    private Activity activity;
    private View view;

    public ViewFinder(Activity activity) {
        this.activity = activity;
    }

    public ViewFinder(View view) {
        this.view = view;
    }
    public View findViewById(int resid)
    {

        return activity!=null?activity.findViewById(resid):(view!=null?view.findViewById(resid):null);
    }
}
