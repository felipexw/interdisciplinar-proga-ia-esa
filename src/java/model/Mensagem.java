package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
@Entity
public class Mensagem implements java.io.Serializable {

    @ManyToOne
    private Usuario usuarioOrigem;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    private String conteudo;
    @ManyToOne
    private Usuario usuarioDestino;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geradorSequenciaMensagem")
    @SequenceGenerator(name = "geradorsequenciamensagem", sequenceName = "sequencia_mensagem", initialValue = 1)
    private Integer id;

    public Mensagem() {
    }

    public Mensagem(Usuario usuarioOrigem, Date data, String conteudo, Usuario usuarioDestino) {
        this.usuarioOrigem = usuarioOrigem;
        this.data = data;
        this.conteudo = conteudo;
        this.usuarioDestino = usuarioDestino;
    }

    public Usuario getUsuarioOrigem() {
        return usuarioOrigem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuarioOrigem(Usuario usuarioOrigem) {
        this.usuarioOrigem = usuarioOrigem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

}
