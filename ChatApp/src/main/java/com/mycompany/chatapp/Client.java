package com.mycompany.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(String serverAddress) {
        // Setup GUI
        setTitle("Client");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.addActionListener(e -> {
            String message = inputField.getText();
            writer.println(message);  // Send message to server
            textArea.append("Client: " + message + "\n");
            inputField.setText("");
        });
        add(inputField, BorderLayout.SOUTH);

        setVisible(true);

        try {
            socket = new Socket(serverAddress, 12345);  // Port 12345
            textArea.append("Terhubung ke server!\n");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Terima pesan dari server secara asynchronous
            new Thread(this::receiveMessages).start();
        } catch (IOException ex) {
            textArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    private void receiveMessages() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                textArea.append("Server: " + message + "\n");
            }
        } catch (IOException ex) {
            textArea.append("Koneksi terputus.\n");
        }
    }

    public static void main(String[] args) {
        String serverAddress = JOptionPane.showInputDialog(
                "Masukkan IP Server:", "localhost");
        SwingUtilities.invokeLater(() -> new Client(serverAddress));
    }
}
