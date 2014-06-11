package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
//@Entity
public class Jogo implements Serializable {

//    @Id
//    @GeneratedValue(generator = "gerador_sequencia", strategy = GenerationType.SEQUENCE)
    private Integer id;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "id")
    private Usuario usuario_X;
//    @JoinColumn(name = "id")
//    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario_Y;
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "id")
    private Usuario vencedor;
//    @ManyToOne
//    @Column(nullable = false)
//    @JoinColumn(name = "id")
    private Partida partida;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Usuario getVencedor() {
        return vencedor;
    }

    public void setVencedor(Usuario vencedor) {
        this.vencedor = vencedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getUsuario_X() {
        return usuario_X;
    }

    public void setUsuario_X(Usuario usuario_X) {
        this.usuario_X = usuario_X;
    }

    public Usuario getUsuario_Y() {
        return usuario_Y;
    }

    public void setUsuario_Y(Usuario usuario_Y) {
        this.usuario_Y = usuario_Y;
    }

}
