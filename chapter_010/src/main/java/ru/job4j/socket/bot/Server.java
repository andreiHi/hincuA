package ru.job4j.socket.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Server {
    private final static Logger LOGGER = LogManager.getLogger(Server.class);
    private final int port;

    public Server(int port) {
        LOGGER.traceEntry();
        this.port = port;
    }

    public void connect() {
        LOGGER.traceEntry();
        try (Socket socket =  new ServerSocket(this.port).accept()) {
            start(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Socket socket) {
        LOGGER.traceEntry();
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String ask;

            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("привет".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"пока".equals(ask)) {
                    out.println("Bla, bla, bla.");
                    out.println();
                }
            } while (!"пока".equals(ask));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.connect();
    }
}
