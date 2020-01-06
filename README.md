# FindView
 
 这是一个通过注解方式实现findViewById的库
 
## 如何使用
```
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
```
## 依赖方式
```
implementation 'com.github.fafawee:findview:1.0.0'
 
```
根gradle中添加
```
maven { url 'https://jitpack.io' }
```

