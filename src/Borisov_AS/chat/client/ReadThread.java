package Borisov_AS.chat.client;

import java.io.*;

public class ReadThread extends Thread{

    private InputStream inputStream;

    public ReadThread(InputStream inputStream){

        this.inputStream = inputStream;
    }
    public void run(){
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
