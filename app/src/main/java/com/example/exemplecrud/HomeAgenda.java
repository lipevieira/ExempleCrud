package com.example.exemplecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.exemplecrud.dao.AgendaDAO;
import com.example.exemplecrud.modelo.Agenda;

import java.util.ArrayList;
import java.util.List;

public class HomeAgenda extends AppCompatActivity {

    private ListView list_agenda;
    private AgendaDAO dao;
    private List<Agenda> agendas;
    private List<Agenda> agendaFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agenda);
        list_agenda = findViewById(R.id.list_agenda);

        dao = new AgendaDAO(this);

        agendas = dao.listtodos();
        agendaFiltradas.addAll(agendas);

        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this,android.R.layout.simple_list_item_1, agendas);
        list_agenda.setAdapter(adapter);
    }

    public void chamarTelaCad(View view){
        Intent i = new Intent(HomeAgenda.this, CadAgenda.class);
        startActivity(i);
    }
}
