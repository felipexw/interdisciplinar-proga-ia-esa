package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Jogo implements Serializable {

    private Integer id;
    private Date dataInicio;
    private Date dataFim;
    private List<Usuario> jogadores;

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

    public List<Usuario> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Usuario> jogadores) {
        this.jogadores = jogadores;
    }
}
