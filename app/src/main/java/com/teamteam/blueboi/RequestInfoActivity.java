package com.teamteam.blueboi;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RequestInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);

        final ActionBar actionBar = getActionBar();

        // actionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // titleTextView
        TextView titleTextView = new TextView(actionBar.getThemedContext());

        titleTextView.setText("Title");

        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);
    }
}
