import Server.EchoServer;
import Server.SimpleServer;

import java.io.IOException;

public class MainServer {
    private static void Test_Simple(){
        SimpleServer server = new SimpleServer();
        try {
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Test_Echo(){
        EchoServer server = new EchoServer();
        try {
            server.start(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test_Echo();
    }
}
