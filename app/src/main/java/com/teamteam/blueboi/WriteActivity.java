package com.teamteam.blueboi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    Button btn_write, btn_logout, btn_upload;
    EditText et_name, et_number;
    ListView lv_users;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> mUserName = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private StorageReference sr;
    private static final int galleryIntent = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        btn_write = (Button) findViewById(R.id.btn_write);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        lv_users = (ListView) findViewById(R.id.lv_users);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUserName);
        lv_users.setAdapter(arrayAdapter);

        sr = FirebaseStorage.getInstance().getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println("ADDED CHILD!");
                String value = dataSnapshot.getValue(String.class);
                mUserName.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String contact = et_number.getText().toString();
                writeToDatabase(name, contact);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, galleryIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == galleryIntent && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            StorageReference filepath = sr.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Image uploaded!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void writeToDatabase(String name, String contact) {
        System.out.println("---ACCESSING DB---");

        myRef = database.getReference("Users").child(name);

        myRef.child("Name").setValue(name);
        myRef.child("Contact").setValue(contact);

        System.out.println("---WRITTEN TO DB!---");
    }
}
