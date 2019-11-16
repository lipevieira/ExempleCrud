package com.example.exemplecrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exemplecrud.conexao.Conexao;
import com.example.exemplecrud.modelo.Cliente;

public class ClienteDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context) {
        conexao = new Conexao(context);
       // banco = conexao.getWritableDatabase();
    }

    public boolean fazerLogin(Cliente cliente){

        banco = conexao.getReadableDatabase();
        String sqlSelect = "SELECT * FROM cliente WHERE email= '"+cliente.getEmail()+"' AND senha= '"+cliente.getSenha()+"' ";
        Cursor c = banco.rawQuery(sqlSelect,null);

        if(c.getCount()>0){
            return true;
        }

        c.close();
        banco.close();

        return false;
    }

    /**
     * Salvando um client dentro do banco de dados
     * @param -Cliente
     * @return long
     * */
    public long inseir(Cliente cliente){
        banco = conexao.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("email", cliente.getEmail());
        values.put("senha", cliente.getSenha());

       return banco.insert("cliente",null, values);
    }

}
