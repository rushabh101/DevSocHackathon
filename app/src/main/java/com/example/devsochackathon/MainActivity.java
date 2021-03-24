package com.example.devsochackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PipeLand pipes = new PipeLand();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPipes();
    }

    public void pipeClick(View view) {
//        if(pipes.first == null) {
//            pipes.first = findViewById("imageView");
//        }
//        ImageView iview = findViewById(getResources().getIdentifier("imageView11", "id", getPackageName()));
//        ImageView iview = findViewById(view.getId());
//        iview.setImageResource(R.drawable.ic_bentpipe1);

        if(pipes.swap(getResources().getResourceEntryName(view.getId()))) setPipes();

        if(pipes.checkPath()) finishDialog();
    }

    public void setPipes() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 6; j++) {
                String pipeViewId = "imageView" + (i+1) + (j+1);
                ImageView iview = findViewById(getResources().getIdentifier(pipeViewId, "id", getPackageName()));

                switch (pipes.layout[i][j]) {
                    case -2:
                        iview.setImageResource(R.drawable.ic_end);
                        break;
                    case -1:
                        iview.setImageResource(R.drawable.ic_start);
                        break;
                    case 0:
                        iview.setImageResource(R.drawable.ic_bentpipe1);
                        break;
                    case 1:
                        iview.setImageResource(R.drawable.ic_bentpipe2);
                        break;
                    case 2:
                        iview.setImageResource(R.drawable.ic_bentpipe3);
                        break;
                    case 3:
                        iview.setImageResource(R.drawable.ic_bentpipe4);
                        break;
                    case 4:
                        iview.setImageResource(R.drawable.ic_strpipe1);
                        break;
                    case 5:
                        iview.setImageResource(R.drawable.ic_strpipe2);
                        break;
                }
            }
        }
    }

    public void finishDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Congrats!");
        alertDialog.setMessage("You did it!!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
