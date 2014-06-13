package utili;

import model.Jogada;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Felipe
 */
public class JogadaEncoder implements Encoder.Text<Jogada> {

    @Override
    public String encode(Jogada jogada) throws EncodeException {
        return Json.createObjectBuilder()
                .add("i", jogada.getI())
                .add("j", jogada.getJ())
                .add("id", jogada.getId())
                .add("buttonIndex", jogada.getButtonIndex())
                .add("contentValue", jogada.getContentValue()).build()
                .toString();
    }

    @Override
    public void destroy() {
        System.out.println("ENTROU JOGAAAAAAADAeeeeeeeeencoder");
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("SAIUU JOGAAAAAAADAeeeeeeeeencoder");
    }

}
