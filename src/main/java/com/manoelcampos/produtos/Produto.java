package com.manoelcampos.produtos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Produto da loja.
 * @author Manoel Campos da Silva Filho
 */
public class Produto {
    static class Builder {
        private final long id;
        private String codigoEan;
        private final String descricao;
        private final String marca;
        private String modelo;
        private final double preco;
        private final LocalDate dataCadastro = LocalDate.now();
        private LocalDate dataUltimaAtualizacao;
        private int estoque = 0;
        private final String categoria;
        private String urlFoto;

        public Builder(long id, String descricao, String marca, double preco, String categoria) {
            this.id = id;
            this.descricao = Objects.requireNonNull(descricao);
            this.marca = Objects.requireNonNull(marca);
            this.preco = this.requireGreaterThanZero(preco);
            this.categoria = Objects.requireNonNull(categoria);
        }

        public Builder codigoEan(String codigoEan) {
            this.codigoEan = Objects.requireNonNull(codigoEan);
            return this;
        }

        public Builder modelo(String modelo) {
            this.modelo = Objects.requireNonNull(modelo);
            return this;
        }

        public Builder dataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
            this.dataUltimaAtualizacao = requireNotBefore(dataUltimaAtualizacao, dataCadastro);
            return this;
        }

        public Builder estoque(int estoque) {
            this.estoque = this.requireGreaterThenOrEqualsZero(estoque);
            return this;
        }

        public Builder urlFoto(String urlFoto) {
            this.urlFoto = urlFoto;
            return this;
        }

        public Produto build() {
            return new Produto(this);
        }

        private double requireGreaterThanZero(double value) {
            if (value <= 0) {
                throw new IllegalArgumentException("value precisa ser maior que zero");
            }

            return value;
        }

        private int requireGreaterThenOrEqualsZero(int value) {
            if (value < 0) {
                throw new IllegalArgumentException(String.format("valor precisa ser maior ou igual a zero: %d", value));
            }

            return value;
        }

        private LocalDate requireNotBefore(LocalDate date, LocalDate referenceDate) {
            if (date.isBefore(referenceDate)) {
                throw new IllegalArgumentException(String.format("data precisa ser mais atual do que a referência: %s < %s", date, referenceDate));
            }

            return date;
        }
    }

    private final long id;
    private final String codigoEan;
    private final String descricao;
    private final String marca;
    private final String modelo;
    private final double preco;
    private final LocalDate dataCadastro;
    private final LocalDate dataUltimaAtualizacao;
    private final int estoque;
    private final String categoria;
    private final String urlFoto;

    public Produto(Builder builder) {
        this.id = builder.id;
        this.codigoEan = Objects.toString(builder.codigoEan, "");
        this.descricao = builder.descricao;
        this.marca = builder.marca;
        this.modelo = Objects.toString(builder.modelo, "");
        this.preco = builder.preco;
        this.dataCadastro = builder.dataCadastro;
        this.dataUltimaAtualizacao = builder.dataUltimaAtualizacao;
        this.estoque = builder.estoque;
        this.categoria = builder.categoria;
        this.urlFoto = Objects.toString(builder.urlFoto, "");
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", codigoEan='" + codigoEan + '\'' +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", dataCadastro=" + dataCadastro +
                ", dataUltimaAtualizacao=" + dataUltimaAtualizacao +
                ", estoque=" + estoque +
                ", categoria='" + categoria + '\'' +
                ", urlFoto='" + urlFoto + '\'' +
                '}';
    }
}
