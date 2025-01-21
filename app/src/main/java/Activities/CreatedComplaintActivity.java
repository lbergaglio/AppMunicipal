package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

public class CreatedComplaintActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_complaint_created);
        Button buttonVolver = findViewById(R.id.buttonVolverComplaintList);

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatedComplaintActivity.this,ComplaintActivity.class);
                startActivity(intent);
            }
        });
    }
}
