package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import utili.GeraMD5;
import utili.Col;

/**
 *
 * @author Felipe
 */
@NamedQueries({
    @NamedQuery(name = "usuario.listarcpf", query = "select u.cpf from Usuario u WHERE u.cpf = :cpf"),
    @NamedQuery(name = "usuario.login", query = "select u from Usuario u WHERE u.email = :email AND u.senha = :senha"),
    @NamedQuery(name = "usuario.findByEmail", query = "select u from Usuario u where u.email = :email"),
    @NamedQuery(name = "usuario.findByNick", query = "select u from Usuario u WHERE u.nick = :nick")
})

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_sequencia")
    @SequenceGenerator(name = "gerador_sequencia", sequenceName = "sequencia_inter", initialValue = 1)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, unique = true, length = 80)
    private String email;
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Column(nullable = false)
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoUsuario tipo;
    @Column(length = 1, nullable = false)
    private char sexo;
    @Embedded
    private Endereco endereco;
    @Column(nullable = false, length = 55, unique = true)
    private String nick;
    private Integer qtdVitorias;

    public Usuario(String nome, String email, String cpf, Date dataNascimento, String senha, TipoUsuario tipo, char sexo, String nick, Endereco enderec, Integer qtdVitorias) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        setSenha(senha);
        this.tipo = tipo;
        this.sexo = sexo;
        endereco = enderec;
        this.nick = nick;
        this.qtdVitorias = qtdVitorias;
    }

    public Integer getQtdVitorias() {
        return qtdVitorias;
    }

    public void setQtdVitorias(Integer qtdVitorias) {
        this.qtdVitorias = qtdVitorias;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Usuario() {
    }

    @Col
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = GeraMD5.criptografar(senha);
    }

}
