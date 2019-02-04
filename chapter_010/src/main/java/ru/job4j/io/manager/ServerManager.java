package ru.job4j.io.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.01.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ServerManager {
    private static final Logger LOG = LogManager.getLogger(ServerManager.class);
    private final Socket socket;
    private Properties property;
    private static final String LN = System.lineSeparator();

    public ServerManager(Socket socket, Properties property) {
        this.socket = socket;
        this.property = property;
    }

    public void start() throws IOException {
        DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String[] commands;
        FileManager manager = new FileManager(this.property.getProperty("root"));
        do {
            commands = in.readLine().split(" ");
            switch (commands[0]) {
                case "-help":
                    out.writeUTF(getApi());
                    break;
                case "-root":
                    out.writeUTF(manager.getRoot().toString());
                    break;
                case "-parent":
                    out.writeUTF(manager.getParent());
                    break;
                case "-cd":
                    if (commands.length > 1) {
                        out.writeUTF(manager.getAtRoot(commands[1]));
                        break;
                    }
                case "-download":
                    if (commands.length > 2) {
                        out.writeUTF(manager.downloadFile(commands[1], commands[2]));
                        break;
                    }
                case "-upload":
                    if (commands.length > 2) {
                        out.writeUTF(manager.uploadFile(commands[2], commands[1]));
                        break;
                    }
                case "-close":
                    out.writeUTF("exit");
                    break;
                default:
                    out.writeUTF("команда " + commands[0] + " не определена -help для справки по API");
                    break;
            }
        } while (!("-close".equals(commands[0])));
    }

    private String getApi() {
        return new StringBuilder()
                .append(LN).append("-help [вывести API]").append(LN)
                .append("-root [получить список текущего каталога]").append(LN)
                .append("-cd catalog [перейти в каталог]").append(LN)
                .append("-parent [перейти в родительский каталог]").append(LN)
                .append("-download filename place [загрузить файл из текущую директорию, filename -имя файла c расширением в текущей директории, place -место сохранения в файловой системе]").append(LN)
                .append("-upload place filename [загрузить файл в текущую директорию, place -адрес файла в файловой системе, filename -имя файла с расширением]").append(LN)
                .append("-close [закрыть приложение]").append(LN).toString();
    }

    public static void main(String[] args) throws IOException {
        Properties property;
        try (FileInputStream fis = new FileInputStream(ServerManager.class.getClassLoader().getResource("app.properties").getFile())) {
            property = new Properties();
            property.load(fis);

            try (final Socket socket = new ServerSocket(Integer.parseInt(property.getProperty("port"))).accept()) {
                new ServerManager(socket, property).start();
            }
        }
    }
}
