package com.example.norto.prova2bim.models;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MatrizLeitera extends SugarRecord implements Serializable {
    @Unique
    private int identificador;
    @NotNull
    private String descricao;
    private int idade;
    private Date dtUltParto;

    public MatrizLeitera() {
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Date getDtUltParto() {
        return dtUltParto;
    }

    public void setDtUltParto(Date dtUltParto) {
        this.dtUltParto = dtUltParto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatrizLeitera)) return false;
        MatrizLeitera that = (MatrizLeitera) o;
        return identificador == that.identificador;
    }

    @Override
    public int hashCode() {

        return Objects.hash(identificador);
    }

    @Override
    public String toString() {
        return identificador + " - " + descricao;
    }
}
