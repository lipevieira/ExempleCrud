package com.example.exemplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exemplecrud.dao.ClienteDAO;
import com.example.exemplecrud.modelo.Cliente;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private ClienteDAO dao;
    private boolean login;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEmail = findViewById(R.id.txtEmail);
        edtSenha = findViewById(R.id.txtSenha);
        cliente = new Cliente();

        login = false;

        dao = new ClienteDAO(this);
    }



    /**
     * Chamando a tela para cadastrar novo usuario
     * */
    public void chamarTelaCastroUsuario(View view){
        Intent i = new Intent(MainActivity.this, NovoUsuario.class);
        startActivity(i);
        finish();
    }

    public void clickButtuonEntrar(View view){
        login();
    }

    private boolean login(){
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();
        boolean validateCampos = validaCampos();
        cliente.setEmail(email);
        cliente.setSenha(senha);


        if (!validateCampos){
             // Chamar o metodo que verificar se exite um Cliente no Banco de dados
            login = dao.fazerLogin(cliente);

            if (login){

                Intent i = new Intent(MainActivity.this, HomeAgenda.class);
                startActivity(i);
                finish();
                return true;
            }else{
                Toast.makeText(this, "Usuário não cadastrado", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    /**
     * @descricao: Validando os campos
     * @return boolean
     * */
    private boolean validaCampos(){
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        boolean res = false;

        if(res = !isEmailValido(email)){
            edtEmail.requestFocus();
        }
        else
        if(res = isCampoVazio(senha)){
            edtSenha.requestFocus();
        }

        // Se existe algum campo vazio então vai mostrar uma MSG com aviso
        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso!");
            dlg.setMessage("Há campos inválidos ou em branco");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        return res;
    }

    /**
     * @descricao: Criando as regras de validação
     * de campos vazios
     * @return boolean
     * @param:string
     * */
    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());

        return resultado;
    }

    private boolean isEmailValido(String email){
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());

        return resultado;
    }



}
