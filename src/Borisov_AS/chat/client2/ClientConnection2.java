package Borisov_AS.chat.client2;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientConnection2 {
    private final OutputStream outputStream;
    private final InputStream inputStream;

    public ClientConnection2(String serverAddress, int port) throws IOException {
        Socket socket = new Socket(serverAddress, port);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new WriteThread2(outputStream));
        executorService.submit(new ReadThread2(inputStream));
        executorService.shutdown();
    }

    public static void main(String[] args) {
        try {
            ClientConnection2 client = new ClientConnection2("localhost", 8082);
            client.start();
        } catch (IOException e) {
            System.out.println("Не удалось установить соединение с сервером: " + e.getMessage());
        }
    }
}

