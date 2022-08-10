package com.kaywalker.sns_proto2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaywalker.sns_proto2.models.UserAccount;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText et_email,et_pw;
    private Button btn_regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("SNS_User");

        et_email = findViewById(R.id.et_email);
        et_pw = findViewById(R.id.et_pw);
        btn_regi = findViewById(R.id.btn_regi);

        btn_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strEmail = et_email.getText().toString();
                String strPw = et_pw.getText().toString();

                //파이어베이스 인증 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail,strPw).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {

                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount userAccount = new UserAccount();
                            userAccount.setIdToken(firebaseUser.getUid());
                            userAccount.setEmailId(firebaseUser.getEmail());
                            userAccount.setPassword(strPw);

                            //데이터베이스에 데이터 삽입
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(userAccount);

                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하였습니다!", Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하였습니다!", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });
    }
}