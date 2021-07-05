package matheusrodrigues.androidapps.patosservicos.model;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

import matheusrodrigues.androidapps.patosservicos.helper.ConfiguracaoFirebase;

public class Anuncio implements Serializable {

    private String idAnuncio;
    private String cidade;
    private String categoria;
    private String servico;
    private String valor;
    private String telefone;
    private String descricao;

    public Anuncio() {
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios");
        setIdAnuncio(anuncioRef.push().getKey());
    }

    //salva os dados no firebase
    public void salvar(){
        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios");

        anuncioRef.child(idUsuario)
                .child(getIdAnuncio())
                .setValue(this);

        salvarAnuncioPublico();
    }

    //salva os dados no firebase
    public void salvarAnuncioPublico(){

        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("anuncios");

        anuncioRef.child(getCidade())
                .child(getCategoria())
                .child(getIdAnuncio())
                .setValue(this);
    }

    public void removerAnuncio(){
        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios")
                .child(idUsuario)
                .child(getIdAnuncio());

        anuncioRef.removeValue();
        removerAnuncioPublico();
    }

    public void removerAnuncioPublico(){
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("anuncios")
                .child(getCidade())
                .child(getCategoria())
                .child(getIdAnuncio());

        anuncioRef.removeValue();
    }

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
