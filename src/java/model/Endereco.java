package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Felipe
 */
@Embeddable
public class Endereco implements Serializable {

    @Column(nullable = false, length = 100)
    private String pais;
    @Column(nullable = false, length = 100)
    private String uf;
    @Column(length = 16)
    private String cep;
    @Column(length = 100, nullable = false)
    private String cidade;
    @Column(length = 75)
    private String bairro;
    @Column(length = 100, nullable = false)
    private String rua;

    public Endereco() {
    }

    public Endereco(String pais, String uf, String cep, String bairro, String rua, String cidade) {
        this.pais = pais;
        this.uf = uf;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPais() {
        return pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
