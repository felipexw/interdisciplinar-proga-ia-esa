package web.bean;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import model.Jogada;
import utili.JogadaDecoder;
import utili.JogadaEncoder;

/**
 *
 * @author Felipe
 */
@ServerEndpoint(value = "/game/{room}", decoders = {JogadaDecoder.class}, encoders = {JogadaEncoder.class})
public class JogadaEndPoint {

    @OnOpen
    public void open(final Session session, @PathParam("room") final String room) {
        System.out.println("public void open(final Session session, @PathParam(\"room\") final String room)");
        session.getUserProperties().put("room", room);
    }

    @OnMessage
    public void onMessage(Session session, Jogada jogada) {
        String room = (String) session.getUserProperties().get("room");
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()
                        && room.equals(s.getUserProperties().get("room"))) {
                    System.out.println(jogada);
                    s.getBasicRemote().sendObject(jogada);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
