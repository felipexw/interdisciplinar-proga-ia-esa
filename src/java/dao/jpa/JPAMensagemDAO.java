package dao.jpa;

import dao.core.MensagemDAO;
import model.Mensagem;

/**
 *
 * @author Felipe
 */
public class JPAMensagemDAO extends JPADAO<Mensagem> implements MensagemDAO {

    @Override
    protected Class<Mensagem> classEntity() {
        return Mensagem.class;
    }

}
