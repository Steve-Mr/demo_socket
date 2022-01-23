package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EchoMultiServer extends EchoServer{
    //多 Client

    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000*10);
        //超时关闭
        while (true){
            try{
                new EchoClientHandler(serverSocket.accept()).start();
            }catch (SocketTimeoutException e){
                System.out.println("Socket Timed Out");
                break;
            }
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread{
        private final Socket clientSocket;

        public EchoClientHandler(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );
                String input;
                while ((input = in.readLine()) != null){
                    if (input.matches(" ")){
                        out.println("closed");
                        break;
                    }
                    System.out.println("Got from client: " + input);
                    out.println(input);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
