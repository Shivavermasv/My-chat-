package com.example.mychat;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login);
        this.getWindow().addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Button login = findViewById ( R.id.login_proceed );
        final TextView forgot_pass = findViewById ( R.id.forgot_pass_field );
        final EditText username= findViewById ( R.id.usename_field );
        final EditText password= findViewById ( R.id.pass_field );

        //login in user using come-to-chat API
        login.setOnClickListener ( new View.OnClickListener () {

            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance ();
                String email = username.getText ().toString ();
                String pass = password.getText ().toString ();
                if(!email.isEmpty () && Patterns.EMAIL_ADDRESS.matcher ( email).matches (  ) ) {
                   if(!pass.isEmpty () && pass.length ()>=6) {
                       auth.signInWithEmailAndPassword ( email, pass ).addOnSuccessListener ( new OnSuccessListener<AuthResult> () {
                           @Override
                           public void onSuccess(AuthResult authResult) {
                               Toast.makeText ( login.this, "Login Successfull..", Toast.LENGTH_SHORT ).show ();
                               if(CometChat.getLoggedInUser ()==null){
                                   //String UID = "SUPERHERO1";
                                   String authKey = "c467d5dd210f0048b95dae46b075ab87efc70f8b";
                                   CometChat.login (email, authKey,new CometChat.CallbackListener<User> () {
                                       @Override
                                       public void onSuccess(User user) {
                                           Toast.makeText ( login.this, "Login Success...", Toast.LENGTH_SHORT ).show ();
                                       }

                                       @Override
                                       public void onError(CometChatException e) {
                                           Toast.makeText ( login.this, "Login Unsuccessfull", Toast.LENGTH_SHORT ).show ();
                                       }
                                   } );
                               }
                               //startActivity ( login.this,  );
                               finish ();
                           }
                       } ).addOnFailureListener ( new OnFailureListener () {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText ( login.this, "Login Failed..", Toast.LENGTH_SHORT ).show ();
                           }
                       } );
                   }
                   else{
                       password.setError ( "Enter a valid password !!" );
                   }
                }
                else if(pass.length ()<6){
                    username.setError ( "Password must have length 6 or above " );
                }
                else{
                    username.setError ( "Enter a valid Email !!" );
                }
            }
        } );
    }

}