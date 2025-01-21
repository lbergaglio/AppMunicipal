package Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Objects.cuentas;

public class HomeActivity extends AppCompatActivity {
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.frame_home);
            Intent intent = getIntent();
            /*
            String nombre = intent.getStringExtra("nombre");
            String apellido = intent.getStringExtra("apellido");
            String documento = intent.getStringExtra("documento");
            String direccion = intent.getStringExtra("direccion");
            Number codBarrio = intent.getIntExtra("codBarrio");
            String correo = intent.getStringExtra("correo");
            String contrasenia = intent.getStringExtra("contrasenia");
            Number esVecino = intent.getIntExtra("esVecino");

            */
            cuentas cuenta = (cuentas) intent.getSerializableExtra("cuenta");

            ImageButton buttonMyAccount = findViewById(R.id.accountButton);
            ImageButton buttonReport = findViewById(R.id.reportButton);
            ImageButton buttonJob = findViewById(R.id.jobButton);
            ImageButton buttonComplaint = findViewById(R.id.complaintButton);
            ImageButton buttonHome = findViewById(R.id.homeButton);
            FloatingActionButton buttonTelephone = findViewById(R.id.floatingActionButton1);


            buttonHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    intent.putExtra("cuenta",cuenta);
                    startActivity(intent);
                }
            });
            buttonJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, JobActivity.class);
                    intent.putExtra("cuenta",cuenta);
                    startActivity(intent);
                }
            });
            buttonComplaint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ComplaintActivity.class);
                    intent.putExtra("cuenta",cuenta);
                    startActivity(intent);
                }
            });
            buttonReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                    intent.putExtra("cuenta",cuenta);
                    startActivity(intent);
                }
            });

            buttonTelephone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TelephoneActivity.class);
                    intent.putExtra("cuenta",cuenta);
                    startActivity(intent);
                }
            });

        }
}
