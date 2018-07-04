package com.example.tabimeleque.inventariosimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CadastrarProdutosActivity extends AppCompatActivity {

    private EditText edtQuantidade;
    private EditText edtCodigo;
    private TextView txtSetor;
    private String valor;
    private Button btnInluir;

    DatabaseReference databaseProduto;

    ListView listViewProdutos;
    List<Produto> produtoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtos);

        databaseProduto = FirebaseDatabase.getInstance().getReference("Produto");

        txtSetor = (TextView)findViewById(R.id.txtSetor);
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        btnInluir = (Button)findViewById(R.id.btnIncluir);

        listViewProdutos = (ListView) findViewById(R.id.listViewProdutos);
        produtoList = new ArrayList<>();


        //Pegar valor da outra Activity
        valor = getIntent().getStringExtra("varSetor");
        txtSetor.setText("Setor:  " + valor);

        btnInluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarProduto();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseProduto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                produtoList.clear();

                for (DataSnapshot produtoSnapshot : dataSnapshot.getChildren())
                {
                    Produto produto = produtoSnapshot.getValue(Produto.class);

                    produtoList.add(produto);
                }

                ProdutoList adapter = new ProdutoList(CadastrarProdutosActivity.this, produtoList);
                listViewProdutos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void adicionarProduto()
    {
        String setor = valor;
        String codigo = edtCodigo.getText().toString().trim();
        int quantidade = Integer.parseInt(edtQuantidade.getText().toString());

        if (!TextUtils.isEmpty(codigo))
        {
            String id = databaseProduto.push().getKey();

            Produto produto = new Produto(id, codigo, quantidade, setor);

            databaseProduto.child(id).setValue(produto);

            Toast.makeText(this, "Produto inluido com sucesso.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Por favor insira o codigo.", Toast.LENGTH_LONG).show();
        }
    }

}
