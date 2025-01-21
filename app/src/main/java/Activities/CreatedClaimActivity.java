package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

public class CreatedClaimActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_claim_created);

        Button buttonVolver = findViewById(R.id.volverReportList);



        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatedClaimActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
