package ru.job4j.socket.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Client {
    private final static Logger LOGGER = LogManager.getLogger(Client.class);
    private final String ip;
    private final int port;

    public Client(String ip, int port) {
        LOGGER.traceEntry();
        this.ip = ip;
        this.port = port;
    }
    public void connect() {
        LOGGER.traceEntry();
        try (Socket socket = new Socket(InetAddress.getByName(this.ip), this.port)) {
            start(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Socket socket) {
        LOGGER.traceEntry();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            boolean chatting = true;
            do {
                String request = console.nextLine();
                out.println(request);
                if ("пока".equals(request)) {
                    chatting = false;
                } else {
                    String response;
                    do {
                        response = in.readLine();
                        if (!response.isEmpty()) {
                            System.out.println(response);
                        }
                    } while (!response.isEmpty());
                }
            } while (chatting);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
        client.connect();
    }

}
