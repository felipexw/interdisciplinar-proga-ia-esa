
import dao.DAOFactory;
import java.util.Date;
import model.Endereco;
import model.Usuario;
import static model.Usuario_.sexo;

/**
 *
 * @author Felipe
 */
public class Main {

    public static void main(String[] args) {
        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO()
                .insert(new Usuario("Felipe Appio", "felipexw@gmail.com", 'a', "060.116.339-78", new Date(), "teste", 'M', new Endereco()));
    }
}
