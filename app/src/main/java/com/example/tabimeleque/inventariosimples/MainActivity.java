package com.example.tabimeleque.inventariosimples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText edtSetor;
    private EditText edtNome;
    private Button btnProduto;
    private Button btnExibir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSetor = (EditText)findViewById(R.id.edtSetor);
        edtNome = (EditText)findViewById(R.id.edtNome);

        btnProduto = (Button) findViewById(R.id.btnProduto);
        btnExibir = (Button) findViewById(R.id.btnExibir);

        btnProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edtSetor.getText()) && !TextUtils.isEmpty(edtNome.getText()))
                {
                    Intent intent = new Intent(MainActivity.this, CadastrarProdutosActivity.class);
                    intent.putExtra("varSetor", edtSetor.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Por favor preencha todos os campos.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnExibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                intent.putExtra("varSetor", edtSetor.getText().toString());
                startActivity(intent);
            }
        });


    }
}
