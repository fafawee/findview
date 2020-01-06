package com.fagawee.flib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.fagawee.flib.ioc.FInject;
import com.fagawee.flib.ioc.view.ViewById;
import com.fagawee.flib.ioc.view.ViewClick;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.text)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FInject.inject(this);
        mTextView.setText("HHHH");

    }


    @ViewClick(R.id.text)
    private void onClick(TextView textView)
    {
        Toast.makeText(this,textView.getText().toString(),Toast.LENGTH_SHORT).show();

        Persion persion=new Persion();


        Invoke invoke=new Invoke();
        IPersion persion1=(IPersion)invoke.bind(persion);

        persion1.say();

    }





}
