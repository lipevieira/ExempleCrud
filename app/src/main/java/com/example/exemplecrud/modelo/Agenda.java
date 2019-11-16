package com.example.exemplecrud.modelo;


import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable {

    private Integer id;
    private String nomeSalao;
    private String horario;
    private String dia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeSalao() {
        return nomeSalao;
    }

    public void setNomeSalao(String nomeSalao) {
        this.nomeSalao = nomeSalao;
    }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return " Salão " + nomeSalao + " Horário " + horario + " Data " + dia;
    }
}
