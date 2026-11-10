package com.example;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conexiondb.FeeReaderContract;
import com.example.conexiondb.FeeReaderDbHelper;
import com.example.testclase1.R;

public class MainActivity extends AppCompatActivity {
    private EditText txtId;
    private EditText txtNombre;
    private EditText txtApellido;
    private FeeReaderDbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtId = findViewById(R.id.etId);
        txtNombre = findViewById(R.id.etNombre);
        txtApellido = findViewById(R.id.etApellido);
        dbHelper = new FeeReaderDbHelper(this);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    public void listar(View view){
        startActivity(new Intent(this, Listado.class));
    }

    public void guardar(View view){
        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        if (nombre.isEmpty() || apellido.isEmpty()){
            mostratMensaje("Ingrese nombre o apellido");
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeeReaderContract.FeedEntry.column1, nombre);
        values.put(FeeReaderContract.FeedEntry.column2, apellido);
    }
}
