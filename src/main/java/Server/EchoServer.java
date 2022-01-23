package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer extends SimpleServer{
    //多次传输单 Client 链接

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        //向 client 输出
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String input;
        while ((input = in.readLine()) != null){
            if (input.matches(" ")){
                System.out.println("Connection Released");
                stop();
                break;
            }
            out.println(input + " got");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
