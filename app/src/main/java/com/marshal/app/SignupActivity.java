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
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private TextView txtDashboard;

    String email = "";
    AppCompatButton btnSignup;
    private Toolbar toolbar;
    private EditText
            edt_Email,edt_Pass,edt_FirstName,edt_LastName,edt_Address,edt_contact,edt_ConfirmPass;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.relative_inear_layout);
//
//        email = getIntent().getStringExtra("Email");
//        Toast.makeText(this, email, Toast.LENGTH_LONG).show();
//        Log.d("Email Address", email);
//        btnSignup=findViewById(R.id.btnSignup);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.relative_inear_layout);
            edt_Email=findViewById(R.id.edtEmail);
            edt_Pass=findViewById(R.id.edtPass);
            edt_FirstName=findViewById(R.id.edtFn);
            edt_LastName=findViewById(R.id.edtLn);
            edt_ConfirmPass=findViewById(R.id.edtCpass);
            btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if
                (edt_FirstName.getText().toString().isEmpty()||edt_LastName.getText().toString().isEmpty()||edt_Email.getText().toString().isEmpty()||
                        edt_Pass.getText().toString().isEmpty()||edt_ConfirmPass.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this,"Please Fill all the field.",Toast.LENGTH_SHORT).show();
                }else if
                (!Patterns.EMAIL_ADDRESS.matcher(edt_Email.getText().toString()).matches()) {
                    Toast.makeText(SignupActivity.this, "Please enter a valid email address.",
                            Toast.LENGTH_SHORT).show();
                } else if (!edt_Pass.getText().toString().equals(edt_ConfirmPass.getText().toString()))
                {
                    Toast.makeText(SignupActivity.this, "Passwords do not match.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    launchDashboardActivity();
                }

            }
        });
    }
    private void launchDashboardActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        // prevent going back to the login screen when pressing the back button
    }
    public void onClick(View view) {

        if(view.getId()==R.id.btnSignup){

        } else if (view.getId()==R.id.txtSignup) {
            Intent intent=new Intent(SignupActivity.this,SignupActivity.class);
            dashboardActivityLauncher.launch(intent);
            //     startActivityForResult(intent,100);
            // startActivity(intent);



            //edtEmail.getText().toString().trim());
            //finish();
        }
    }
    ActivityResultLauncher<Intent> dashboardActivityLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()== Activity.RESULT_OK){
                        //there are no request code
                        Intent data=result.getData();
                        Toast.makeText(SignupActivity.this,data.getStringExtra("Result"),Toast.LENGTH_SHORT).show();
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



//                Intent intent=new Intent();
//                intent.putExtra("Result","Result Return");
//                setResult(RESULT_OK,intent);
//                finish();