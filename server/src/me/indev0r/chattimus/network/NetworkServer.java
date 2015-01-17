package me.indev0r.chattimus.network;

import me.indev0r.chattimus.ChattimusServer;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MrDeathJockey on 17/01/15.
 */
public class NetworkServer {

    private ServerSocket socket;
    private boolean running = false;
    private int port;

    public NetworkServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            socket = new ServerSocket(port);
            ChattimusServer.getInstance().log("Server socket initialized on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                ChattimusServer.getInstance().log("Listening for clients...");

                while(running) {
                    try {
                        final Socket client = socket.accept();
                        ChattimusServer.getInstance().log("Client has connected! " + client.getRemoteSocketAddress());

                        //Read individual client data
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean error = false;
                                while(!error && client.isConnected()) {
                                    try {
                                        DataInputStream input = new DataInputStream(client.getInputStream());
                                        String request = input.readUTF();
                                        //TODO interpret request
                                        ChattimusServer.getInstance().log(client.getRemoteSocketAddress()
                                                + " requests:\n\t" + request);
                                    } catch(EOFException e) {
                                        error = true;
                                        ChattimusServer.getInstance().log("Client " + client.getRemoteSocketAddress()
                                                + " has disconnected!");
                                    } catch (IOException e) {
                                        error = true;
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        running = true;
    }

    public void stopServer() {
        running = false;
    }
}
