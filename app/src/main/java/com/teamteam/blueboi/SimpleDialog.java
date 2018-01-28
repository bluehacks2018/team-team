package com.teamteam.blueboi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Krizia Lynn on 28/01/2018.
 */

public class SimpleDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_cancel, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("WARNING")
                .setMessage("Are you sure you want to cancel huh?")
                .setView(v)
                .setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast.makeText(getActivity(), "Hey, I am yes", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Toast.makeText(getActivity(), "Hey, I am no", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                });
        return builder.create();
    }
}
