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
public class Jogo implements Serializable {

    private Integer id;
    private Date dataInicio;
    private Date dataFim;

    private Usuario jogador_1;

    private Usuario jogador_2;

    private Usuario vencedor;
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

    public Usuario getJogador_1() {
        return jogador_1;
    }

    public void setJogador_1(Usuario jogador_1) {
        this.jogador_1 = jogador_1;
    }

    public Usuario getJogador_2() {
        return jogador_2;
    }

    public void setJogador_2(Usuario jogador_2) {
        this.jogador_2 = jogador_2;
    }

}
