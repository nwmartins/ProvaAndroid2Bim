package com.example.norto.prova2bim.models;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Ordenha extends SugarRecord implements Serializable {

    @Unique
    private int identifcador;
    @NotNull
    private MatrizLeitera matriz;
    private int qntLitros;
    private Date dtHora;

    public Ordenha() {
    }

    public int getIdentifcador() {
        return identifcador;
    }

    public void setIdentifcador(int identifcador) {
        this.identifcador = identifcador;
    }

    public MatrizLeitera getMatriz() {
        return matriz;
    }

    public void setMatriz(MatrizLeitera matriz) {
        this.matriz = matriz;
    }

    public int getQntLitros() {
        return qntLitros;
    }

    public void setQntLitros(int qntLitros) {
        this.qntLitros = qntLitros;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordenha)) return false;
        Ordenha ordenha = (Ordenha) o;
        return identifcador == ordenha.identifcador;
    }

    @Override
    public int hashCode() {

        return Objects.hash(identifcador);
    }

    @Override
    public String toString() {
        return identifcador + " Vaca: " + matriz.toString();
    }
}
