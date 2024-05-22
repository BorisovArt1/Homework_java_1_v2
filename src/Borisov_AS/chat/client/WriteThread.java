package Borisov_AS.chat.client;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class WriteThread extends Thread{
    private OutputStream outputStream;
    public WriteThread(OutputStream outputStream){
        this.outputStream = outputStream;
    }
    public void run(){
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))){
            String line;
            while ((line = keyboard.readLine()) != null){
                outputStream.write((line + "\n").getBytes());
                outputStream.flush();
            }
        } catch (IOException e){
            System.out.println("Ошибка при записи в поток: " + e.getMessage());
        }
    }
}
