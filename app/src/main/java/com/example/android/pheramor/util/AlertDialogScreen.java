package com.example.android.pheramor.util;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.android.pheramor.R;

public class AlertDialogScreen {

    public void showAlert(final Context context){
        new AlertDialog
                .Builder(context, R.style.AlertDialogTheme)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.ExitDialogTitle)
                .setMessage(R.string.ExitDialogMessage)
                .setPositiveButton(R.string.Positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((Activity)context).finish();
                    }
                })
                .setNegativeButton(R.string.Negative,null)
                .show();
    }
}
