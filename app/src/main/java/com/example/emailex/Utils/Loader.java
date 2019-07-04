package com.example.emailex.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.example.emailex.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class Loader {

    private DialogPlus dialogPlus;

    public Loader(Context context) {

        dialogPlus = DialogPlus.newDialog(context)
                .setContentBackgroundResource(Color.TRANSPARENT)
                .setContentHolder(new ViewHolder(R.layout.activity_lottie))
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();

    }
    public  void show(){
        dialogPlus.show();
    }

    public  void dismis(){
        dialogPlus.dismiss();
    }


}
