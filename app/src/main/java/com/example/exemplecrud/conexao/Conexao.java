package com.example.exemplecrud.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String nome = "banco.db";
    private static final int versao = 2;

    public Conexao(@Nullable Context context) {
        super(context,nome,null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists cliente (id integer primary key autoincrement, " +
                "nome varchar(100) not null, email varchar(150) not null, " +
                "senha varchar(100) not null );");

        // Criando a tabela de agenda
        db.execSQL("create table if not exists agenda (id integer primary key autoincrement, " +
                "nome_salao varchar(100) not null, horario varchar(10) not null, dia varchar(20) not null );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table if not exists cliente (id integer primary key autoincrement, " +
                "nome varchar(100) not null, email varchar(150) not null, " +
                "senha varchar(100) not null );");


        db.execSQL("create table if not exists agenda  (id integer primary key autoincrement, " +
                "nome_salao varchar(100) not null, horario varchar(10) not null, dia varchar(20) not null );");
    }
}
