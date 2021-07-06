package matheusrodrigues.androidapps.patosservicos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import matheusrodrigues.androidapps.patosservicos.R;
import matheusrodrigues.androidapps.patosservicos.helper.ConfiguracaoFirebase;
import matheusrodrigues.androidapps.patosservicos.model.Usuario;

public class AutenticacaoActivity extends AppCompatActivity {

    private EditText campoLoginEmail, campoLoginSenha;
    private Button botaoEntrar;
    private ProgressBar progressBar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);
        getSupportActionBar().hide();

        inicializarComponentes();

        //Fazer login do usuário
        progressBar.setVisibility(View.GONE);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoLoginEmail = campoLoginEmail.getText().toString();
                String textoLoginSenha = campoLoginSenha.getText().toString();

                if (!textoLoginEmail.isEmpty()){
                    if (!textoLoginSenha.isEmpty()){

                        usuario = new Usuario();
                        usuario.setEmail(textoLoginEmail);
                        usuario.setSenha(textoLoginSenha);
                        validarLogin(usuario);

                    }else {
                        Toast.makeText(AutenticacaoActivity.this,
                                "Preencha o campo senha!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AutenticacaoActivity.this,
                            "Preencha o campo e-mail!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaSeUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void validarLogin(Usuario usuario){
        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getApplicationContext(), AnunciosActivity.class));

                    finish();

                }else {
                    Toast.makeText(AutenticacaoActivity.this,
                            "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaCadastro(View view){
        Intent i = new Intent(AutenticacaoActivity.this, CadastroActivity.class);
        startActivity(i);
        
    }

    private void inicializarComponentes(){
        campoLoginEmail = findViewById(R.id.editLoginEmail);
        campoLoginSenha = findViewById(R.id.editLoginSenha);
        botaoEntrar = findViewById(R.id.buttonLoginEntrar);
        progressBar = findViewById(R.id.progressBarLogin);
    }
}