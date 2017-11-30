package com.ehighsun.bazierpractice;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
//    private RadioGroup radioGroup;
//    private Bazier2 bazier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        radioGroup = (RadioGroup) findViewById(R.id.rg_group);
//        bazier2 = (Bazier2) findViewById(R.id.bazier);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch (checkedId){
//                    case R.id.rb1:
//                        bazier2.setMode(true);
//                        break;
//                    case R.id.rb2:
//                        bazier2.setMode(false);
//                        break;
//                }
//            }
//        });
    }
}
