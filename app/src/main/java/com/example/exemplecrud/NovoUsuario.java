package com.example.exemplecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exemplecrud.dao.ClienteDAO;
import com.example.exemplecrud.modelo.Cliente;

import java.util.regex.Pattern;

public class NovoUsuario extends AppCompatActivity {

    private Button btnCadastrar;
    private EditText edtNome, edtEmail, edtSenha;
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        edtNome =  findViewById(R.id.txtNome);
        edtEmail =  findViewById(R.id.txtEmail);
        edtSenha =  findViewById(R.id.txtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        dao = new ClienteDAO(this);
    }

    public void salvar(View view){
        Cliente c = new Cliente();

        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        boolean validateCampos = validaCampos();

        if(!validateCampos){
            c.setNome(nome);
            c.setEmail(email);
            c.setSenha(senha);

            try {
                // Salvando um cliente no Banco de dados
                dao.inseir(c);
                Toast.makeText(this, "Cliente Salvo com sucesso", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(NovoUsuario.this, MainActivity.class);
                startActivity(i);
                finish();
            }catch (Exception e){
                Toast.makeText(this, "Erron ao Salvar Cliente " + e, Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * @descricao: Validando os campos
     * @return boolean
     * */
    private boolean validaCampos(){
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();
        boolean res = false;

        if(res = isCampoVazio(nome)){
            edtNome.requestFocus();
        }
        else
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
