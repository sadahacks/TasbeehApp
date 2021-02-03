package com.sidhacks.tasbeeh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCES_NAME= "TOTAL_VALUE";
    public static final String SHARED_PREFERENCES_KEY="KEY";
    int FINAL_SHARED;
    TextView tv;
    Button btnCounter, btnReset;
    int counter;

    @Override
    protected void onResume() {
        GetSharedPreferenceFun();
        counter=FINAL_SHARED;
        super.onResume();
    }

    @Override
    protected void onPause() {
        SaveSharedPreferenceFun();
        super.onPause();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv=  findViewById(R.id.tvCounter);
        btnCounter=  findViewById(R.id.btnCounter);
        btnReset=  findViewById(R.id.btnReset);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tv.setText("Tasbeeh : "+counter);

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();

            }
        });



        if (savedInstanceState != null)
        {
            if (savedInstanceState.containsKey("Count"))
            {
                counter = savedInstanceState.getInt("Count");
                tv.setText("Tasbeeh : "+counter);
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Count" , counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    public void GetSharedPreferenceFun() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        FINAL_SHARED=sharedPreferences.getInt(SHARED_PREFERENCES_KEY, Integer.parseInt("0"));
        tv.setText(String.valueOf(FINAL_SHARED));
    }

    public void SaveSharedPreferenceFun() {
        SharedPreferences.Editor editor=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE).edit();
        editor.putInt(SHARED_PREFERENCES_KEY,counter);
        editor.apply();
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Reset")
                .setMessage("Do you want to Reset")
                //.setIcon(R.drawable.ic_baseline_delete_forever_24)

                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        counter = 0;
                        tv.setText(String.valueOf(counter));
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }


}
