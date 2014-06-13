package utili;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import model.Jogada;

/**
 *
 * @author Felipe
 */
public class JogadaDecoder implements Decoder.Text<Jogada> {

    @Override
    public Jogada decode(String data) {
        System.out.println(data);
        Jogada jogada = null;
        try {
            JsonObject obj = Json.createReader(new StringReader(data))
                    .readObject();
            jogada = new Jogada(obj.getInt("i"), obj.getInt("j"), obj.getInt("id"), obj.getInt("buttonIndex"),obj.getString("contentValue"));
            System.out.println("deeeeeeeeeeeeeeeeeecode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jogada;
    }

    @Override
    public boolean willDecode(String string) {
        return string != null;
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("ENTROU JOGAAAAAAADADECODER");
    }

    @Override
    public void destroy() {
        System.out.println("SAIUUUUU JOGAAAAAAADADECODER");
    }

}
