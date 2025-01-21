package Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appmunicipal.R;

public class TelephoneActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_telephone_number);
        Button buttonVolver = findViewById(R.id.buttonVolverHome);
        Button buttonCallPolice = findViewById(R.id.buttonPolice);


        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelephoneActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        buttonCallPolice.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("911"));

                startActivity(intent);
            }

        });
    }
}
