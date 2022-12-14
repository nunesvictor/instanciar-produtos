package com.manoelcampos.produtos;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto
                .Builder(1, "Produto 1", "Marca 1", 100., "Categoria 1")
                .codigoEan("010203040506") // null lança NullPointerException
                .modelo("Modelo 1") // null lança NullPointerException
                .dataUltimaAtualizacao(LocalDate.now()) // data passada lança IllegalArgumentException
                .estoque(10) // valor negativo lança IllegalArgumentException
                .urlFoto("https://example.com/photo.png") // null lança NullPointerException
                .build();

        System.out.println(produto);
    }
}
