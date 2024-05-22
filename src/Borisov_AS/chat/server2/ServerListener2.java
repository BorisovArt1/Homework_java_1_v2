package Borisov_AS.chat.server2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListener2 {

    private ServerSocket serverSocket;

    private static ArrayList<ClientHandler2> clients;
    private static ExecutorService executorService;

    public void start() throws IOException {
        serverSocket = new ServerSocket(8082);
        ChatLog2 log = new ChatLog2();
        clients = new ArrayList<>();
        executorService = Executors.newCachedThreadPool();

        while (true) {
            Socket incomingConnection = serverSocket.accept();
            ClientHandler2 client = new ClientHandler2(incomingConnection, log);
            clients.add(client);
            executorService.execute(client);
        }
    }

    public static List<ClientHandler2> getClients() {
        return clients;
    }

    public static void removeClient(ClientHandler2 clientHandler) {
        System.out.println("Client " + clientHandler + " is deleted");
        clients.remove(clientHandler);
    }

}
