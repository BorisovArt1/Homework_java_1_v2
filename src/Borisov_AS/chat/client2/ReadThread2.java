package Borisov_AS.chat.client2;

import java.io.*;

public class ReadThread2 implements Runnable {
    private InputStream inputStream;

    public ReadThread2(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Сервер: " + line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из потока: " + e.getMessage());
        }
    }
}

