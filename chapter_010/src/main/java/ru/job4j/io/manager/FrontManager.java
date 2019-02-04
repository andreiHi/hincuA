package ru.job4j.io.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class FrontManager implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(FrontManager.class);
    private final Socket socket;

    public FrontManager(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        DataInputStream in = new DataInputStream(this.socket.getInputStream());
        Scanner console = new Scanner(System.in);
        String input;
        out.println("-help");
        while (true) {
            input = in.readUTF();
            if (input.isEmpty()) {
                break;
            }
            if ("exit".equals(input)) {
                break;
            }
            System.out.println(input);
            out.println(console.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        Properties property;
        try (FileInputStream fis = new FileInputStream(ServerManager.class.getClassLoader().getResource("app.properties").getFile())) {
            property = new Properties();
            property.load(fis);
            try (final Socket socket = new Socket(InetAddress.getByName(property.getProperty("host")), Integer.parseInt(property.getProperty("port")))) {
                new FrontManager(socket).start();
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
