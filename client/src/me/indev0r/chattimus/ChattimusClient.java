package me.indev0r.chattimus;

import me.indev0r.chattimus.network.NetworkClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MrDeathJockey on 17/01/15.
 */
public class ChattimusClient extends JFrame {

    public static final String TITLE = "Chattimus Client";
    private static ChattimusClient instance;

    //List of users connected
    private JList listUsers;

    //Chat text
    private JTextPane textChat;

    //User input
    private JTextField fieldInput;
    private JButton buttonSend;

    //Network client object (networking module)
    private NetworkClient client;

    public ChattimusClient() {
        createView();
        client = new NetworkClient("127.0.0.1", 1050);
        client.connectToServer();

        setTitle(TITLE);
        setSize(600, 500);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout());

        listUsers = new JList();
        JScrollPane listUsersSP = new JScrollPane(listUsers);
        listUsersSP.setPreferredSize(new Dimension(200, 0));
        panel.add(listUsersSP, BorderLayout.EAST);

        JPanel panelChat = new JPanel(new BorderLayout());
        panel.add(panelChat, BorderLayout.CENTER);

        textChat = new JTextPane();
        textChat.setEditable(false);
        JScrollPane textChatSP = new JScrollPane(textChat);
        panelChat.add(textChatSP, BorderLayout.CENTER);

        JPanel panelInput = new JPanel(new BorderLayout());
        panel.add(panelInput, BorderLayout.SOUTH);
        fieldInput = new JTextField();
        panelInput.add(fieldInput, BorderLayout.CENTER);
        buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO send chat message to server
            }
        });
        panelInput.add(buttonSend, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                instance = new ChattimusClient();
                instance.setVisible(true);
            }
        });
    }
}
