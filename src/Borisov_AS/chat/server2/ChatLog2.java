package Borisov_AS.chat.server2;

import java.io.IOException;
import java.util.ArrayList;

public class ChatLog2 {
    private final ArrayList<String> chatHistory;
    private int pointer = 0;

    public void put(String message, ClientHandler2 clientSender) throws IOException {
        chatHistory.add(message);
        System.out.println(message);
        update(clientSender);
        pointer++;
    }

    public void update(ClientHandler2 clientSender) throws IOException {
        for (ClientHandler2 client : ServerListener2.getClients()) {
            if (client != clientSender)
                client.sendMessageToClient(chatHistory.get(pointer));
        }
    }

    public ChatLog2() {
        chatHistory = new ArrayList<>();
    }
}
