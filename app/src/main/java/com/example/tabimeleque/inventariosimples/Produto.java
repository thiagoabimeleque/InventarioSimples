package com.example.tabimeleque.inventariosimples;

public class Produto {

    String produtoId;
    String produtoCodigo;
    int produtoQuantidade;
    String produtoSetor;

    public Produto()
    {

    }

    public Produto(String produtoId, String produtoCodigo, int produtoQuantidade, String produtoSetor) {
        this.produtoId = produtoId;
        this.produtoCodigo = produtoCodigo;
        this.produtoQuantidade = produtoQuantidade;
        this.produtoSetor = produtoSetor;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getProdutoCodigo() {
        return produtoCodigo;
    }

    public int getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public String getProdutoSetor() {
        return produtoSetor;
    }
}
