package com.foxy.bookmanager.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.foxy.bookmanager.project.sqlitedao.UserDAO;
import com.foxy.bookmanager.project.R;
import com.foxy.bookmanager.project.model.User;
import com.foxy.bookmanager.project.sqlite.DatabaseHelper;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mID;
    private EditText mPassword;
    private EditText mRePassword;

    private RadioButton mCheck;
    Toolbar toolbarNguoiDung;
    private DatabaseHelper databaseHelper;
    private  Button btnCreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbarNguoiDung = findViewById(R.id.toolbarNguoiDung);
        setSupportActionBar(toolbarNguoiDung);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        //
        databaseHelper = new DatabaseHelper(this);
        final UserDAO userDAO = new UserDAO(databaseHelper);
        btnCreat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mID.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String repassword = mRePassword.getText().toString().trim();
                if (password.length() < 6 || userName.isEmpty() || password.isEmpty()||password.equals(repassword)==false) {

                    if (userName.isEmpty()) {
                        mID.setError(getString(R.string.notify_empty_user));
                        return;
                    }

                    if (password.length() < 6) {
                        mPassword.setError(getString(R.string.notify_length_pass));
                        return;
                    }

                    if (password.isEmpty()){
                        mPassword.setError(getString(R.string.notify_empty_pass));
                        return;
                    }
                    if (password.equals(repassword)==false){
                        mRePassword.setError("Mật khẩu nhập lại không đúng");
                        return;
                    }

                }
                else {
                    User user=new User();
                    user.username=userName;
                    user.password=password;
                    userDAO.insertUser(user);
                    Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }


            }
        });

    }
    public void initView(){
        btnCreat=findViewById(R.id.btn_Creat);
        mPassword=findViewById(R.id.edt_password);
        mID=findViewById(R.id.edt_username);
        mRePassword=findViewById(R.id.edt_repassword);
    }
}

