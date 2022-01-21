import Client.SimpleClient;

import java.io.IOException;

public class MainClient {

    private static void Test_Simple(){
        SimpleClient client = new SimpleClient();
        try {
            client.startConnection("127.0.0.1", 6666);
            String response = client.sendMessage("hello");
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SimpleClient echoClient;

    public static void setup(){
        echoClient = new SimpleClient();
        try {
            echoClient.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(){
        try {
            echoClient.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Test_EchoClient(){
        try {
            echoClient = new SimpleClient();
            echoClient.startConnection("127.0.0.1", 6666);
            for (int i = 0; i < 5; i++){
                System.out.println(echoClient.sendMessage("Message " + i));
            }
            echoClient.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Test_EchoClient();
    }
}
