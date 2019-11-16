package com.example.exemplecrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exemplecrud.conexao.Conexao;
import com.example.exemplecrud.modelo.Agenda;

import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AgendaDAO(Context context) {
        conexao = new Conexao(context);
    }

    /**
     * Salvando um agemento no banco de dados
     * @param -Agenda
     * @return long
     * */
    public long inseir(Agenda agenda){
        banco = conexao.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome_salao", agenda.getNomeSalao());
        values.put("horario", agenda.getHorario());
        values.put("dia", agenda.getDia());

        return banco.insert("agenda",null, values);
    }


    /**
     * Mostrando todos os Agendamento do banco de dados
     * @return -List
     * */
    public List<Agenda> listtodos() {
        banco = conexao.getWritableDatabase();
        Cursor c = banco.rawQuery("SELECT * FROM agenda;",null);
        List<Agenda> agendamentos = new ArrayList<>();
        while (c.moveToNext()) {
            Agenda agenda = new Agenda();
            agenda.setId(c.getInt(c.getColumnIndex("id")));
            agenda.setNomeSalao(c.getString(c.getColumnIndex("nome_salao")));
            agenda.setHorario(c.getString(c.getColumnIndex("horario")));
            agenda.setDia(c.getString(c.getColumnIndex("dia")));

            agendamentos.add(agenda);
        }
        c.close();
        return agendamentos;
    }

}
