package Borisov_AS.chat.server2;

import java.io.IOException;

public class ChatServer2 {
    public static void main(String[] args) throws IOException {

        ServerListener2 serverListener = new ServerListener2();
        serverListener.start();
    }
}
