package com.ralphvsclark.sctest.socket;

import com.ralphvsclark.sctest.service.ExecutorEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Class definition
 *
 * @author Ralph Hu
 * @version 2019/1/22
 */
public class SocketProcessRunnable implements Runnable {

    private final static Logger logger = Logger.getLogger(SocketProcessRunnable.class.getName());

    private final static String END_OF_COMM_STR = "BYE";

    private Socket socket = null;
    private ExecutorEngine executorEngine = null;

    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    public SocketProcessRunnable(Socket socket, ExecutorEngine executorEngine) {
        this.socket = socket;
        this.executorEngine = executorEngine;
    }

    @Override
    public void run() {

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line;

            while (!(line = reader.readLine()).toUpperCase().equals(END_OF_COMM_STR)) {
                logger.info("Got message: " + line);

                String msg = executorEngine.exec(line);
                output(msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.severe("Error occurred when communicating");
        } finally {

            try {
                if (reader != null) {
                    reader.close();
                }

                if (writer != null) {
                    writer.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                logger.severe("Failed to close channel");
            }
        }
    }

    private void output(String msg) throws IOException {
        writer.write(msg);
        writer.newLine();
        writer.flush();
    }
}
