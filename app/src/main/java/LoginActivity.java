import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import java.sql.Connection;
import java.sql.DriverManager;

public class LoginActivity extends AppCompatActivity {
    private EditText textUsername;
    private EditText textPassword;
    private Button btnLogin;
    private Button btnPasswordForgot;
    private Button btnAccountCreate;
    private Button btnBack;
    private RequestQueue requestQuery;


    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_login);

        textUsername = findViewById(R.id.idTextInputUsername);
        textPassword = findViewById(R.id.idTextInputPassword);
        btnLogin = findViewById(R.id.idSingInButton);
        btnPasswordForgot = findViewById(R.id.idPasswordForgot);
        btnAccountCreate = findViewById(R.id.idCreateAccountButton);
        btnBack = findViewById(R.id.idBackButton);

        requestQuery = Volley.newRequestQueue(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();

                JSONObject loginData = new JSONObject();

                if (username.equals("admin") && password.equals("password")) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //agrego codigo para ir a otra actividad
                } else {
                    Toast.makeText(LoginActivity.this, "Username or password are wrong!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        private boolean login(String username, String password){
            try{
                Connection connection = DriverManager.getConnection("jdbc:sqlserver://<your_server>:<port>;databaseName=<your_database>", "<username>", "<password>")
            }
        }

    }
}
