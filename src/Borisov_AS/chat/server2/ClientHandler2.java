package Borisov_AS.chat.server2;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler2 implements Runnable {

    private final Socket clientSocket;
    private ChatLog2 chatLog2 = new ChatLog2();
    private BufferedReader in;
    private BufferedWriter out;

    public ClientHandler2(Socket clientSocket, ChatLog2 chatLog) {
        this.clientSocket = clientSocket;
        this.chatLog2 = chatLog2;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String nickName = in.readLine();
            chatLog2.put(nickName + " connected to chat", this);

            while (!Thread.currentThread().isInterrupted()) {
                String message = in.readLine();

                if (Objects.isNull(message)) {
                    break;
                }

//                System.out.println(nickName + ":" + message);
                chatLog2.put(nickName + ":" + message, this);
            }

            chatLog2.put(nickName + " disconnected from chat", this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerListener2.removeClient(this);
    }

    public void sendMessageToClient(String msg) throws IOException {
        if (!clientSocket.isOutputShutdown()) {
            out.write(msg);
            out.newLine();
            out.flush();
        }
    }
}
