package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

public class LoginCreateActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_login_create);

        Button buttonSignIn = findViewById(R.id.idSignInButton1);
        Button buttonCreateAccount = findViewById(R.id.idCreateAccountButton1);
        Button buttonGuestSignIn = findViewById(R.id.idGuestSignIn1);



        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginCreateActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginCreateActivity.this, CreateAccountActivityVerify.class);
                startActivity(intent);
            }
        });

        buttonGuestSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginCreateActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
