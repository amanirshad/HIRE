package com.example.hiring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiring.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
EditText et_email,et_username,et_password,et_cpassword;
Button btn_signup;
FirebaseAuth mAuth;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        et_email=findViewById(R.id.et_email_signup);
        et_username=findViewById(R.id.et_user_signup);
        et_password=findViewById(R.id.et_pass_signup);
        et_cpassword=findViewById(R.id.et_Cpass_signup);
        btn_signup=findViewById(R.id.btn_signup);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
//        if(mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//        }
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = et_email.getText().toString().trim();
                String username = et_username.getText().toString().trim();
                String Pass = et_password.getText().toString().trim();
                String C_Pass = et_cpassword.getText().toString().trim();
                if (TextUtils.isEmpty(Email)) {
                    et_email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Pass)) {
                    et_password.setError("Password is Required");
                    return;
                }
                if (Pass.length() < 6) {
                    et_password.setError("Password must be >= 6 Characters");
                    return;
                }
                if (C_Pass.equals(C_Pass)) {
                } else {
                    et_cpassword.setError("Password don't matched");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = task.getResult().getUser().getUid();
                            User user = new User(Email,username,Pass,id);
                            DatabaseReference db = database.getReference();
                            db.child(id).child("LOGIN").setValue(user);
                            Intent intent = new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}