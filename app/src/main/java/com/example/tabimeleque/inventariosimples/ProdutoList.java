package com.example.tabimeleque.inventariosimples;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdutoList extends ArrayAdapter<Produto> {
    private Activity context;
    private List<Produto> produtoList;

    public ProdutoList (Activity context, List<Produto> produtoList)
    {
        super(context, R.layout.list_layout, produtoList);
        this.context = context;
        this.produtoList = produtoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View lisViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewProduto = (TextView) lisViewItem.findViewById(R.id.textViewProduto);
        TextView textViewSetor = (TextView) lisViewItem.findViewById(R.id.textViewSetor);

        Produto produto = produtoList.get(position);

        textViewProduto.setText("Codigo: " + produto.getProdutoCodigo() + ", Quantidade: " + produto.getProdutoQuantidade());
        textViewSetor.setText("Setor: " + produto.getProdutoSetor());

        return lisViewItem;
    }
}
