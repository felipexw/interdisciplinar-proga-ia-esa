package web.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Usuario;
import java.util.Collections;

/**
 *
 * @author Felipe
 */
@ManagedBean
@ApplicationScoped
public class ChatBean {

    private List<Usuario> usuariosOnline;

    public ChatBean() {
        usuariosOnline = Collections.synchronizedList(new ArrayList<>());
    }

    public void addUsuario(Usuario u) {
        usuariosOnline.add(u);
    }

    public void removerUsuario(Usuario u) {
        usuariosOnline.remove(u);
    }
}
