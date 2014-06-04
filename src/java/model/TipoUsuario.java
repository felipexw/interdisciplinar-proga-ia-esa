package model;

import java.io.Serializable;

/**
 *
 * @author Felipe
 */
public enum TipoUsuario implements Serializable {

    NORMAL("Normal"),
    ADMINISTRADOR("Administrador");

    private final String value;

    private TipoUsuario(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
