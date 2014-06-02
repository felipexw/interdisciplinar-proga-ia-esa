package web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import model.Sexo;

/**
 *
 * @author Felipe
 */
@FacesConverter(forClass = Enum.class, value = "sexoConverter")
public class SexoConverter extends EnumConverter {

    public SexoConverter() {
        super(Sexo.class);
    }

}