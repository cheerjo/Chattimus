package me.indev0r.chattimus.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by MrDeathJockey on 18/01/15.
 */
public class NetworkClient {

    private Socket socket;
    private String ipAddress;
    private int serverPort;

    public NetworkClient(String ipAddress, int serverPort) {
        this.ipAddress = ipAddress;
        this.serverPort = serverPort;
    }

    public void connectToServer() {
        try {
            socket = new Socket(ipAddress, serverPort);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("Hello Server!");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {

                }
            }
        }).start();
    }
}
