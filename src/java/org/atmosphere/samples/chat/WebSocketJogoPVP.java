package org.atmosphere.samples.chat;

/**
 *
 * @author Felipe
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import org.atmosphere.config.service.WebSocketHandlerService;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.util.SimpleBroadcaster;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketEventListenerAdapter;
import org.atmosphere.websocket.WebSocketHandler;
import org.atmosphere.websocket.WebSocketStreamingHandlerAdapter;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.bean.JogoPVPBean;

/**
 * Simple {@link WebSocketHandler} that implement the logic to build a Chat
 * application.
 *
 * @author Jeanfrancois Arcand
 */
@WebSocketHandlerService(path = "/jogo", broadcaster = SimpleBroadcaster.class,
        atmosphereConfig = {"org.atmosphere.websocket.WebSocketProtocol=org.atmosphere.websocket.protocol.StreamingHttpProtocol"})
public class WebSocketJogoPVP extends WebSocketStreamingHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebSocketJogoPVP.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onOpen(WebSocket webSocket) throws IOException {
        System.out.println("public void onOpen(WebSocket webSocket) throws IOException {");
        webSocket.resource().addEventListener(new WebSocketEventListenerAdapter() {
            @Override
            public void onDisconnect(AtmosphereResourceEvent event) {
                if (event.isCancelled()) {
                    logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
                } else if (event.isClosedByClient()) {
                    logger.info("Browser {} closed the connection", event.getResource().uuid());
                }
            }
        });
    }

    @Override
    public void onTextStream(WebSocket webSocket, Reader reader) throws IOException {
        AtmosphereResource resource = webSocket.resource();
        AtmosphereRequest request = resource.getRequest();
//        JogoPVPBean pvpBean = (JogoPVPBean) request.getSession().getAttribute("jogoPVPBean");
        //todo encoder e decoder

        webSocket.broadcast(mapper.writeValueAsString(mapper.readValue(new BufferedReader(reader).readLine(), Jogada.class)));
    }

    @Override
    public void onTextMessage(WebSocket webSocket, String data) throws IOException {
        super.onTextMessage(webSocket, data);
    }

}
