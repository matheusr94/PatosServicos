package matheusrodrigues.androidapps.patosservicos.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import matheusrodrigues.androidapps.patosservicos.R;
import matheusrodrigues.androidapps.patosservicos.model.Anuncio;

public class DetalhesServicosActivity extends AppCompatActivity {

    private TextView tituloServico;
    private TextView descricaoDetalhe;
    private TextView cidadeDetalhe;
    private TextView valorDetalhe;
    private Anuncio anuncioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servicos);

        //Configurar Toolbar
        getSupportActionBar().setTitle("Detalhe do Serviço");

        inicializarComponentes();

        //recuperar dados do anuncio
        anuncioSelecionado = (Anuncio) getIntent().getSerializableExtra("anuncioSelecionado");

        if(anuncioSelecionado != null){
            tituloServico.setText(anuncioSelecionado.getServico());
            descricaoDetalhe.setText(anuncioSelecionado.getDescricao());
            cidadeDetalhe.setText(anuncioSelecionado.getCidade());
            valorDetalhe.setText(anuncioSelecionado.getValor());
        }
    }

    public void inicializarTelefone(View view){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", anuncioSelecionado.getTelefone(), null));
        startActivity(i);

    }

    public void inicializarComponentes(){
        tituloServico = findViewById(R.id.textTituloDetalhe);
        descricaoDetalhe = findViewById(R.id.textDescricaoDetalhe);
        cidadeDetalhe = findViewById(R.id.textCidadeDetalhe);
        valorDetalhe = findViewById(R.id.textValorDetalhe);
    }
}