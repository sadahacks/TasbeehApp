package com.sidhacks.tasbeeh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td=new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                }catch(Exception ex)
                {
                    ex.printStackTrace();
                }finally
                {
                    Intent it=new Intent(Intro.this,MainActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };td.start();

    }
}
