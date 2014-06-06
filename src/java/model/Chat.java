package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Chat {

    private List<Mensagem> mensagens;

    public Chat() {
        mensagens = Collections.synchronizedList(new ArrayList<Mensagem>());
    }

    public void addMensagem(Mensagem m) {
        mensagens.add(m);
    }

}
