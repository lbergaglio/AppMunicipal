package Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.appmunicipal.R;

public class CreateClaimActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_claim_new);
        Button buttonVolver = findViewById(R.id.buttonVolverReport);
        Button buttonAdjuntarArchivos = findViewById(R.id.buttonAdjuntarArchivosReport);
        Button buttonEnviarReporte = findViewById(R.id.buttonEnviarReport);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateClaimActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });

        buttonEnviarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateClaimActivity.this,CreatedClaimActivity.class);
                startActivity(intent);
            }
        });

    }
}
