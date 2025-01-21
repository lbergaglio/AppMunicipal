package Activities;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmunicipal.R;

public class CreateJobActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_job_new);

        EditText editTextDireccion = findViewById(R.id.editTextDireccion);
        EditText editTextDescripcion = findViewById(R.id.editTextDescripcion);
        EditText editTextHorario = findViewById(R.id.editTextHorario);
        EditText editTextContacto = findViewById(R.id.editTextContacto);
        Button buttonAdjuntarArchivos = findViewById(R.id.buttonAdjuntarArchivosJob);
        Button buttonAgregarServicio = findViewById(R.id.buttonAgregarJob);
        Switch switchComercio = findViewById(R.id.switchComercio);
        Switch switchServicio = findViewById(R.id.switchServicio);

        buttonAgregarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = editTextDireccion.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                String horario = editTextHorario.getText().toString();
                String contacto = editTextContacto.getText().toString();
                Boolean esServicio = switchServicio.isChecked();

                Intent intent = new Intent(CreateJobActivity.this, CreatedJobActivity.class);
                startActivity(intent);
            }
        });

        switchServicio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) switchComercio.setChecked(false);
            }
        });
        switchComercio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) switchServicio.setChecked(false);
            }
        });
    }
}
