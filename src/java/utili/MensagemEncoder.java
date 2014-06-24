//package utili;
//
//import org.atmosphere.config.managed.Encoder;
//import model.Mensagem;
//import org.codehaus.jackson.map.ObjectMapper;
//
///**
// *
// * @author Felipe
// */
//public class MensagemEncoder implements Encoder<Mensagem, String> {
//
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    @Override
//    public String encode(Mensagem m) {
//        try {
//            return mapper.writeValueAsString(m);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
