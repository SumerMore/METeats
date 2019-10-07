package com.example.meteats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewUserActivity extends AppCompatActivity {
    EditText emailId,password;
    Button register;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId = (EditText)findViewById(R.id.NewUserEmail);
        password =(EditText)findViewById(R.id.NewUserPassword);
        register=(Button)findViewById(R.id.RegisterBtn);


        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email =emailId.getText().toString();
                String pwrd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("EmailId Not Entered.");
                    emailId.requestFocus();
                }
                else if(pwrd.isEmpty()){
                    password.setError("Password Not Entered");
                    emailId.requestFocus();
                }
                else if(email.isEmpty() && pwrd.isEmpty()){
                    Toast.makeText(NewUserActivity.this,"Fields Empty ",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwrd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwrd).addOnCompleteListener(NewUserActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(NewUserActivity.this,"SignUp Failed...Try Again Later",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(NewUserActivity.this,yolo.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(NewUserActivity.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
