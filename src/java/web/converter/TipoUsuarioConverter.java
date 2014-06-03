package web.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import model.TipoUsuario;

/**
 *
 * @author Felipe
 */
@FacesConverter(forClass = TipoUsuario.class, value = "sexoConverter")
public class TipoUsuarioConverter extends EnumConverter {

    public TipoUsuarioConverter() {
        super(TipoUsuario.class);
    }
}
