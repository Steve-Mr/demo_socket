import Client.NIOClient;
import Server.EchoNIOServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EchoTest {
    Process server;
    NIOClient client;

    @Before
    public void setUp() throws Exception {
        server = EchoNIOServer.start();
        client = NIOClient.start();

    }

    @After
    public void tearDown() throws Exception {
        server.destroy();
        NIOClient.stop();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrent() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);
    }
}
