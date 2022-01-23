package Server;

import java.net.*;
import java.io.*;

public class SimpleServer {
    //单次输入单 Client 链接
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        //侦听并接受此 Socket 链接
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //接收 Client 输入
        String inMessage = in.readLine();
        System.out.println(inMessage);
    }

    public void stop() throws IOException {
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
