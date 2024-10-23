package com.mycompany.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Server extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server() {
        // Setup GUI
        setTitle("Server");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String message = inputField.getText();
            writer.println(message);  // Send message to client
            textArea.append("Server: " + message + "\n");
            inputField.setText("");
        });
        add(inputField, BorderLayout.SOUTH);

        setVisible(true);

        try {
            serverSocket = new ServerSocket(12345);  // Port 12345
            textArea.append("Menunggu koneksi dari client...\n");
            clientSocket = serverSocket.accept();  // Accept client connection
            textArea.append("Client terhubung!\n");

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Terima pesan dari client secara asynchronous
            new Thread(this::receiveMessages).start();
        } catch (IOException ex) {
            textArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void receiveMessages() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                textArea.append("Client: " + message + "\n");
            }
        } catch (IOException ex) {
            textArea.append("Koneksi terputus.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }
}
