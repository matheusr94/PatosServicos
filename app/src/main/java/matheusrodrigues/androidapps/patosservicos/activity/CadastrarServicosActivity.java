package matheusrodrigues.androidapps.patosservicos.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.santalu.maskara.widget.MaskEditText;

import java.util.Locale;

import dmax.dialog.SpotsDialog;
import matheusrodrigues.androidapps.patosservicos.R;
import matheusrodrigues.androidapps.patosservicos.model.Anuncio;

public class CadastrarServicosActivity extends AppCompatActivity {

    private EditText campoServico, campoDescricao;
    private CurrencyEditText campoValor;
    private MaskEditText campoTelefone;
    private Spinner campoCidade, campoCategoria;

    private Anuncio anuncio;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servicos);

        inicializarComponentes();
        carregarDadosDoSpinner();
    }

    private void carregarDadosDoSpinner() {
        //Configuração do spinner para cidades
        String[] cidades = getResources().getStringArray(R.array.cidades);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                cidades
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoCidade.setAdapter(adapter);

        //Configuração do spinner para categorias
        String[] categoria = getResources().getStringArray(R.array.categoria);
        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                categoria
        );
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoCategoria.setAdapter(adapterCategoria);
    }

    private Anuncio configurarAnuncio(){
        String cidade = campoCidade.getSelectedItem().toString();
        String categoria = campoCategoria.getSelectedItem().toString();
        String servico = campoServico.getText().toString();
        String valor = campoValor.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String descricao = campoDescricao.getText().toString();

        Anuncio anuncio = new Anuncio();
        anuncio.setCidade(cidade);
        anuncio.setCategoria(categoria);
        anuncio.setServico(servico);
        anuncio.setValor(valor);
        anuncio.setTelefone(telefone);
        anuncio.setDescricao(descricao);

        return anuncio;

    }

    public void validarDadosAnuncio(View view){
        anuncio = configurarAnuncio();
        String valor = String.valueOf(campoValor.getRawValue());

        if (!anuncio.getCidade().isEmpty()){
            if (!anuncio.getCategoria().isEmpty()){
                if (!anuncio.getServico().isEmpty()){
                    if (!valor.isEmpty() && !valor.equals("0")){
                        if (!anuncio.getTelefone().isEmpty()){
                            if (!anuncio.getDescricao().isEmpty()){
                                salvarAnuncio();
                            }else {
                                exibirMensagemDeErro("Informe um breve descrição do seu serviço");
                            }

                        }else {
                            exibirMensagemDeErro("Informe um telefone para contato");
                        }

                    }else {
                        exibirMensagemDeErro("Preencha o campo valor");
                    }

                }else {
                    exibirMensagemDeErro("Informe um título para o serviço");
                }

            }else {
                exibirMensagemDeErro("Selecione uma categoria para o serviço");
            }

        }else {
            exibirMensagemDeErro("Selecione uma cidade");
        }
    }

    private void exibirMensagemDeErro(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }


    public void salvarAnuncio(){

        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Salvando o serviço!")
                .setCancelable(false)
                .build();
        alertDialog.show();

        anuncio.salvar();

        //Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_LONG).show();

        //alertDialog.dismiss();

        finish();
    }

    private void inicializarComponentes(){
        campoServico = findViewById(R.id.editServico);
        campoDescricao = findViewById(R.id.editDescricao);
        campoValor = findViewById(R.id.editValor);
        campoTelefone = findViewById(R.id.editTelefone);
        campoCidade = findViewById(R.id.spinnerCidade);
        campoCategoria = findViewById(R.id.spinnerCategoria);

        //Configurar a localidade para brasil
        Locale locale = new Locale("pt", "BR");
        campoValor.setLocale(locale);
    }
}