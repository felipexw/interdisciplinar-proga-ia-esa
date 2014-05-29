
import dao.DAOFactory;
import java.util.Date;
import model.Sexo;
import model.TipoUsuario;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public class Main {
    
    public static void main(String[] args) {
        Usuario user = new Usuario("Felipe Appio", "felipexw@gmail.com", "060.-72",
                new Date(), "teste", TipoUsuario.NORMAL, Sexo.MASCULINO);
        user.setId(1);
//        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().insert(user);
        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().update(user);
//        System.out.println(DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().login("felipexw@gmail.com", "12"));
    }
}
