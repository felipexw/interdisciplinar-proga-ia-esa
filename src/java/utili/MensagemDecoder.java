//package utili;
//
//import org.atmosphere.config.managed.Decoder;
//import model.Mensagem;
//import org.codehaus.jackson.map.ObjectMapper;
//
///**
// *
// * @author Felipe
// */
//public class MensagemDecoder implements Decoder<String, Mensagem> {
//
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public Mensagem decode(String s) {
//        try {
//            return mapper.readValue(s, Mensagem.class);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
