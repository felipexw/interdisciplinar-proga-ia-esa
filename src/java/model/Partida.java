package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Felipe
 */
//@NamedQueries({
//    @NamedQuery(name = "partida.getCountVitorias", query = "SELECT COUNT(j) from Partida p WHERE p.jogos.")})
//@Entity
public class Partida implements Serializable {

//    @Id
//    @GeneratedValue(generator = "gerador_sequencia", strategy = GenerationType.SEQUENCE)
    private Integer id;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
//    @JoinColumn(name = "id")
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida")
    private List<Jogo> jogos;

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

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

}
