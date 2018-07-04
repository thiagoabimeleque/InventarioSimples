package com.example.tabimeleque.inventariosimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    DatabaseReference databaseProduto;

    ListView listViewProdutos;
    List<Produto> produtoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        databaseProduto = FirebaseDatabase.getInstance().getReference("Produto");


        listViewProdutos = (ListView) findViewById(R.id.listViewProdutos);
        produtoList = new ArrayList<>();
    }
public void rbclick(View v){

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

                ProdutoList adapter = new ProdutoList(ListarProdutosActivity.this, produtoList);
                listViewProdutos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
