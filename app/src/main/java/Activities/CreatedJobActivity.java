package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

import kotlinx.coroutines.Job;

public class CreatedJobActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_job_created);

        Button buttonVolver = findViewById(R.id.buttonVolverJob);


        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatedJobActivity.this, JobActivity.class);
                startActivity(intent);
            }
        });
    }
}
