
import dao.DAOFactory;
import java.util.Date;
import model.Endereco;
import model.TipoUsuario;
import model.Usuario;

/**
 *
 * @author Felipe
 */
public class Main {

    public static void main(String[] args) {

        Usuario u = new Usuario("Felipe Appio", "felipexw@gmail.com", "060.116.339-78", new Date(), "root", TipoUsuario.ADMINISTRADOR, 'M', "felipexw",
                new Endereco("Brasil", "SC", "88590-000", "Centro", "Padre Remígio Della Vechia", "Anita Garibaldi"), 0);
//        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().insert(u);

        u = new Usuario("João da Loke", "felipexw@loke.com", "092.471.639-86", new Date(), "root", TipoUsuario.NORMAL, 'M', "heusser",
                new Endereco("Brasil", "SC", "88590-000", "Centro", "Padre Remígio Della Vechia", "Anita Garibaldi"), 1);
        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().insert(u);
        
        u = new Usuario("João da Loke", "felipexw@loke.br", "667.904.874-50", new Date(), "root", TipoUsuario.NORMAL, 'M', "loke",
                new Endereco("Brasil", "SC", "88590-000", "Centro", "Padre Remígio Della Vechia", "Anita Garibaldi"), 100);
        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().insert(u);
//        Usuario usuarioOrigem = DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().find(4651);
//        Usuario destino = DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().find(8451);
//        Mensagem m = new Mensagem(usuarioOrigem, new Date(), "TESTE", destino);
//        DAOFactory.getDAOFactory(DAOFactory.JPA).getMensagemDAO().insert(m);

//        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().insert(user);
//        DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().update(user);
//        System.out.println(DAOFactory.getDAOFactory(DAOFactory.JPA).getUsuarioDAO().login("felipexw@gmail.com", "12"));
    }
}
