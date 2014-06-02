package model;

/**
 *
 * @author Felipe
 */
public enum Sexo {

    MASCULINO("M"),
    FEMININO("F");
    private final String sexo;

    private Sexo(final String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
