package com.teamteam.blueboi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gab on 1/28/2018.
 */

public class SkillsetDialog extends DialogFragment {
    CheckBox cb_electrical;
    CheckBox cb_mechanic;
    CheckBox cb_plumbing;
    CheckBox cb_carpentry;
    CheckBox cb_housekeeping;
    CheckBox cb_caregiving;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_layout, null);

        cb_electrical = v.findViewById(R.id.cb_electrical);
        cb_mechanic = v.findViewById(R.id.cb_mechanic);
        cb_plumbing = v.findViewById(R.id.cb_plumbing);
        cb_carpentry = v.findViewById(R.id.cb_carpentry);
        cb_housekeeping = v.findViewById(R.id.cb_housekeeping);
        cb_caregiving = v.findViewById(R.id.cb_caregiving);

        ArrayList<String> skillset = new ArrayList<>();

        AlertDialog.Builder builder
                = new AlertDialog.Builder(getActivity())
                .setTitle("Select Skillsets")
                .setView(v)
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinearLayout l = getActivity().findViewById(R.id.ll_skillsets);
                        View v2 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_create_request, null);

                        if(cb_electrical.isChecked())
                            l.addView(createSkillset(cb_electrical.getText().toString()));

                        if(cb_caregiving.isChecked())
                            l.addView(createSkillset(cb_caregiving.getText().toString()));

                        if(cb_housekeeping.isChecked())
                            l.addView(createSkillset(cb_housekeeping.getText().toString()));

                        if(cb_carpentry.isChecked())
                            l.addView(createSkillset(cb_carpentry.getText().toString()));

                        if(cb_mechanic.isChecked())
                            l.addView(createSkillset(cb_mechanic.getText().toString()));

                        if(cb_plumbing.isChecked())
                            l.addView(createSkillset(cb_plumbing.getText().toString()));

                    }
                });


        return builder.create();
    }

    public TextView createSkillset(String text) {
        TextView v = new TextView(getContext());
        v.setText(text);

        return v;
    }
}
