package view;

import org.directwebremoting.ScriptSessions;


/**
 *
 * @author Felipe
 */
public class Message {

    public String getMessage() {
        ScriptSessions.addFunctionCall("alert", "Felipe he uma bixa. Att. Felipe");
        System.out.println("MessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessageMessage");
        return "Hello DWR from Server";
    }

}
