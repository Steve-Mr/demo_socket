package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static NIOClient instance;

    public static NIOClient start() {
        if (instance == null) {
            instance = new NIOClient();
        }
        return instance;
    }

    public static void stop () throws IOException {
        client.close();
        buffer = null;
    }

    private NIOClient() {
        try {
            client = SocketChannel.open(new java.net.InetSocketAddress("127.0.0.1", 6666));
            buffer = ByteBuffer.allocate(1024);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String message) {
        buffer = ByteBuffer.wrap(message.getBytes());
        String response = null;
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            System.out.println("response = " + response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
