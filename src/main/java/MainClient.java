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

    public static void Test_EchoClient(){
        try {
            SimpleClient echoClient = new SimpleClient();
            echoClient.startConnection("127.0.0.1", 6666);
            for (int i = 0; i < 5; i++){
                System.out.println(echoClient.sendMessage("Message " + i));
            }
            echoClient.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Test_Multi(String diff){
        try {
            SimpleClient echoClient = new SimpleClient();
            echoClient.startConnection("127.0.0.1", 6666);
            for (int i = 0; i < 10; i++){
                System.out.println(echoClient.sendMessage("Client " + diff + "/ Message" + i));
                Thread.sleep(1000);
            }
            System.out.println(echoClient.sendMessage(" "));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 2; i++){
            int finalI = i;
            new Thread(() -> Test_Multi(String.valueOf(finalI))).start();
        }
    }
}
