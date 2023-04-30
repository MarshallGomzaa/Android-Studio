package com.marshal.app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtEmail, edtPassword;
    private AppCompatButton btnLogin;
    private TextView txtForget,txtSignup,txtWelcome;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //initToolbar();
        findView();

    }
    private void initToolbar(){
        toolbar=findViewById(R.id.lytToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //For title
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Login Page");
    }
    private void findView(){
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        txtForget=findViewById(R.id.txtForget);
        txtSignup=findViewById(R.id.txtSignup);
        txtWelcome=findViewById(R.id.txtWelcome);
        btnLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmail.getText().toString().isEmpty()&&edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"username and password is empty",Toast.LENGTH_LONG).show();
                }
                else if(edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this,"password is empty",Toast.LENGTH_LONG).show();
                }
                else if(edtEmail.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Username is empty",Toast.LENGTH_LONG).show();
                }
                else{
                    launchDashboardActivity();
                }
            }
        });
    }
    private void launchDashboardActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
//this is for multiple actionlistener
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btnLogin){

        } else if (view.getId()==R.id.txtSignup) {
            Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
            intent.putExtra("Email","Marshal Tamang");
            signupActivityLauncher.launch(intent);
       //     startActivityForResult(intent,100);
           // startActivity(intent);
                    //edtEmail.getText().toString().trim());
            //finish();
        }
    }
    //Signup Activity without request code
    ActivityResultLauncher<Intent> signupActivityLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        //there are no request code
                        Intent data=result.getData();
                        Toast.makeText(LoginActivity.this,data.getStringExtra("Result"),Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode==100&& resultCode==RESULT_OK){
            Toast.makeText(this, data.getStringExtra("Result"),Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

   }

//

//Activity using request code
//   @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//       if (requestCode == 100 && resultCode == RESULT_OK) {
//           Toast.makeText(this, data.getStringExtra("Result"), Toast.LENGTH_SHORT).show();
//       }
//       super.onActivityResult(requestCode, resultCode, data);

/* this is for single defination or call of onclick listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().isEmpty()&& !edtPassword.getText().toString().isEmpty()){

                }
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){}
        }
        });
*/

