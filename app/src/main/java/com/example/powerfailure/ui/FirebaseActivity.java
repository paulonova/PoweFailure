package com.example.powerfailure.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/** * Created by Paulo Vila Nova on 2017-06-15.
 */

class FirebaseActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if(mFirebaseUser == null){
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        }

    }

    private void logout(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth == null){
            return;
        }
        mFirebaseAuth.signOut();
        Toast.makeText(this, "Logged out of Firebase!", Toast.LENGTH_SHORT).show();
    }



    protected View.OnClickListener getLogoutListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        };
    }
}
