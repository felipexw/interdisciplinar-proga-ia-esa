package minimax;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 4081926149210106476L;

    public JanelaPrincipal() {
        super("Jogo da Velha");
        setBounds(100, 100, 640, 480);
        GradeJogo painel = new GradeJogo();
        getContentPane().add(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void mostraJanela() {
        setVisible(true);
    }
}
