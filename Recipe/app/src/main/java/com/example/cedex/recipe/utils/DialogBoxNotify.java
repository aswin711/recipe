package com.example.cedex.recipe.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.cedex.recipe.R;

/**
 * Created by cedex on 4/18/2017.
 */

public class DialogBoxNotify {



    public static boolean show(String title, String msg, Context context){
        final int[] mode = {0};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(msg);

        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_delete_black);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event
                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                //mode[0] = 1;
                //returnValue(mode[0]);

            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                //returnValue(mode[0]);
            }
        });
        Log.d("mode",mode[0]+"");

        // Showing Alert Message
        alertDialog.show();
        /*if(mode[0]==1){
            return true;
        }else{
            return false;
        }*/

       return  returnValue(mode[0]);
    }

    public static boolean returnValue(int i){
        if(i==1){
            return true;
        }
        return false;
    }
}
