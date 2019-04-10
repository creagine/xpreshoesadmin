package com.creaginetech.xpreshoesadmin;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creaginetech.xpreshoesadmin.Model.Shipper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddShipperActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddShipperActivity";
    EditText inputName,inputEmail,inputPassword;
    Button btnRegisterShipper;
    ProgressBar progressBarShipper;

    FirebaseAuth mAuth;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipper);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        widgets();

        btnRegisterShipper.setOnClickListener(this);

    }

    private void widgets() {
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnRegisterShipper = findViewById(R.id.btnRegisterShipper);
        progressBarShipper = findViewById(R.id.progressBarShipper);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnRegisterShipper){
            registerShipper();
        }
    }

    private void registerShipper() {

        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Input Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Input Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show();
            return;
        }


        progressBarShipper.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG,"createUser:onComplete: " + task.isSuccessful());
                progressBarShipper.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    onAuthsuccess(task.getResult().getUser());
                    Toast.makeText(AddShipperActivity.this, "Success to sign up", Toast.LENGTH_SHORT).show();

                } else {
                    String errorMEssage = task.getException().getMessage();
                    Toast.makeText(AddShipperActivity.this, "Failed to sign up" +errorMEssage, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void onAuthsuccess(FirebaseUser user) {

        String shipperName = inputName.getText().toString();
        String emailShipper = inputEmail.getText().toString();

        writeNewShipper(user.getUid(),shipperName,emailShipper);

    }

    private void writeNewShipper(String uid, String shipperName, String email) {

        String role = "shipper";

        Shipper shipper = new Shipper(shipperName,email,role);
        mDatabaseReference.child("shippers").child(uid).setValue(shipper);

    }


}
