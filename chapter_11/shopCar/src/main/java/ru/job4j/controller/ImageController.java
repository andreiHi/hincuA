package ru.job4j.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.service.ImageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2018.
 * @version $Id$.
 * @since 0.1.
 */

public class ImageController extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ImageController.class);
    private static final int BUFFER_SIZE = 10240;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullSavePath = (String) req.getServletContext().getAttribute("fullSavePath");
        String requestedFile = req.getParameter("name");
        ImageService service = new ImageService();
        File file = service.foundImage(requestedFile, fullSavePath);
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String contentType = getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        resp.reset();
        resp.setBufferSize(BUFFER_SIZE);
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setContentType(contentType);
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
        BufferedOutputStream outputStream = new BufferedOutputStream(resp.getOutputStream(), BUFFER_SIZE)
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = stream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }
}
