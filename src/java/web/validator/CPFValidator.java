package web.validator;

import dao.DAOFactory;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Felipe
 */
@FacesValidator
public class CPFValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String cpf = (String) o;

        if (cpf.equals("111.111.111-11") || cpf.equals("222.222.222-22")
                || cpf.equals("000.000.000-00")
                || cpf.equals("333.333.333-33")
                || cpf.equals("444.444.444-44")
                || cpf.equals("555.555.555-55")
                || cpf.equals("666.666.666-66")
                || cpf.equals("777.777.777-77")
                || cpf.equals("888.888.888-88")
                || cpf.equals("999.999.999-99")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido. Por favor, informe outro valor.", ""));
        }

        String result = getSegundoDigitoVerificador(getPrimeiroDigito(cpf));

        cpf = cpf.replace("-", "");
        cpf = cpf.replace(".", "");

        if (!cpf.equals(result)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido. Por favor, informe outro valor.", ""));
        } else {
            if (DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().listarCPF(cpf) != null) {
                throw new ValidatorException(new FacesMessage("Este CPF já está cadastrado. Por favor, informe outro valor."));
            }
        }
    }

    private static String getPrimeiroDigito(String cpf) {
        cpf = cpf.substring(0, cpf.length() - 3);
        cpf = cpf.replace(".", "");
        byte aux = 10;
        int resultado = 0;
        for (byte i = 0; i < cpf.length(); i++) {
            resultado += Integer.parseInt(String.valueOf(cpf.charAt(i))) * aux;
            aux--;
        }
        resultado = 11 - (resultado % 11);
        if (resultado == 10 || resultado == 11) {
            resultado = 0;
        }
        return cpf + String.valueOf(resultado);
    }

    private static String getSegundoDigitoVerificador(String cpf) {
        int resultado = 0;

        int aux = 11;
        for (byte i = 0; i < cpf.length(); i++) {
            resultado += Integer.parseInt(String.valueOf(cpf.charAt(i))) * aux;
            aux--;
        }
        resultado = 11 - (resultado % 11);
        if (resultado == 10 || resultado == 11) {
            resultado = 0;
        }
        return cpf + String.valueOf(resultado);
    }

}
