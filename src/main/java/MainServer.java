import Server.EchoMultiServer;
import Server.SimpleServer;

import java.io.IOException;

public class MainServer {

    private static void Test_Simple(SimpleServer server){
        try {
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimpleServer server = new EchoMultiServer();
        Test_Simple(server);
    }
}
