package me.indev0r.chattimus;

import me.indev0r.chattimus.network.NetworkServer;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MrDeathJockey on 17/01/15.
 */
public class ChattimusServer extends JFrame {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");

    public static final String TITLE = "Chattimus Server";
    private static ChattimusServer instance;

    private NetworkServer server;

    private JTextArea console;
    private JList listUsers;

    public ChattimusServer() {
        createView();

        server = new NetworkServer(1050);

        setTitle(TITLE);
        setSize(500, 400);
        setResizable(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new BorderLayout());

        console = new JTextArea();
        console.setEditable(false);
        
        // enable autoscrolling for incoming messages
        ((DefaultCaret)console.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JScrollPane consoleSP = new JScrollPane(console);
        consoleSP.setBorder(BorderFactory.createTitledBorder("Console Output"));
        panel.add(consoleSP, BorderLayout.CENTER);

        listUsers = new JList();
        JScrollPane listUsersSP = new JScrollPane(listUsers);
        listUsersSP.setBorder(BorderFactory.createTitledBorder("Connected Users:"));
        listUsersSP.setPreferredSize(new Dimension(200, 0));
        panel.add(listUsersSP, BorderLayout.EAST);
    }

    public void log(String message) {
        console.append(DATE_FORMAT.format(new Date()) + " " + message + "\n");
    }

    public static ChattimusServer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new ChattimusServer();
                instance.server.startServer();
                instance.setVisible(true);
            }
        });
    }

}
