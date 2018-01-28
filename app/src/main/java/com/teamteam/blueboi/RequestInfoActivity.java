package com.teamteam.blueboi;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestInfoActivity extends AppCompatActivity {

    private TextView tvDescription;
    private TextView tvDatetime;
    private TextView tvTags;
    private TextView tvWorkerName;
    private TextView tvWorkerCompleted;
    public static String uid;
    private Button btnCancel;
    static DatabaseReference currPostRef;
    public static String status;
    static Request r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);

        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvDatetime = (TextView) findViewById(R.id.tv_datetime);
        tvTags = (TextView) findViewById(R.id.tv_tags);
        tvWorkerName = (TextView) findViewById(R.id.tv_worker_name);
        tvWorkerCompleted = (TextView) findViewById(R.id.tv_worker_completed);
        btnCancel = (Button) findViewById(R.id.button_cancel);

        uid = getIntent().getExtras().getString("uid");
        currPostRef = FirebaseDatabase.getInstance().getReference().child("requests").child(uid);
        currPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r = dataSnapshot.getValue(Request.class);
                final ActionBar actionBar = getActionBar();
                // actionBar
                //actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                // titleTextView
                //TextView titleTextView = new TextView(actionBar.getThemedContext());
                if (r.getWorker() != null) {
                    tvWorkerCompleted.setText("10000");
                    tvWorkerName.setText(r.getWorker().getUsername());
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SimpleDialog sd = new SimpleDialog();
                            sd.show(getSupportFragmentManager(), "");
                        }
                    });
                } else {
                    findViewById(R.id.tv_worker_info).setVisibility(View.GONE);
                    findViewById(R.id.worker_info).setVisibility(View.GONE);
                    findViewById(R.id.rl_skillset).setVisibility(View.GONE);
                    findViewById(R.id.button_cancel).setVisibility(View.GONE);
                }
                String tags = "";
                for (String t: r.getTags()) {
                    tags += t + ", ";
                }
                tags.substring(0,tags.length() - 2);
                tvTags.setText(tags);
                tvDatetime.setText(r.getStartDate() + " - " + r.getEndDate());
                tvDescription.setText(r.getDescription());
                //titleTextView.setText(r.getTitle());

                // Add titleTextView into ActionBar
                //actionBar.setCustomView(titleTextView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void setStatus(String status2) {
        status = status2;
        r.setStatus("Cancelled");
        currPostRef.child("requests").child(uid).setValue(r);
    }
}
