package Borisov_AS.chat.client2;

import java.io.*;

public class WriteThread2 implements Runnable {
    private OutputStream outputStream;

    public WriteThread2(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = keyboard.readLine()) != null) {
                outputStream.write((line + "\n").getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в поток: " + e.getMessage());
        }
    }
}

