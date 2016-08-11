/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emi.loan.mail;

/**
 *
 * @author paulraj
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

public class MailService {

    private Session session;
    private Store store;
    private Folder folder;

    // hardcoding protocol and the folder
    // it can be parameterized and enhanced as required
    private String protocol = "imaps";
    private String file = "INBOX";

    public MailService() {

    }

    public boolean isLoggedIn() {
        return store.isConnected();
    }

    /**
     * to login to the mail host server
     */
    public void login(String host, String username, String password)
            throws Exception {
        URLName url = new URLName(protocol, host, 993, file, username, password);

        if (session == null) {
            Properties props = null;
            try {
                props = System.getProperties();
                props.setProperty("mail.store.protocol", "imaps");
              
            } catch (SecurityException sex) {
                props = new Properties();
            }
            session = Session.getInstance(props, null);
        }
        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);

        folder.open(Folder.READ_WRITE);
    }

    /**
     * to logout from the mail host server
     */
    public void logout() throws MessagingException {
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }

    public int getMessageCount() {
        int messageCount = 0;
        try {
            messageCount = folder.getMessageCount();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return messageCount;
    }

    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }

    public static void main(String[] args) {

        MailService ms = new MailService();
        try {
            ms.login("imap.gmail.com", "arunsancj@gmail.com", "carljohn27");
            Message[] messages = ms.getMessages();
            for (int i = 0; i < 5; i++) {
                String subject = "";
                if (messages[i].getSubject() != null) {
                    subject = messages[i].getSubject();
                }
                System.out.println("SUBJECT: " + subject);
                Address[] fromAddress = messages[i].getFrom();
                String sender = "";
                for (Address add : fromAddress) {
                    sender = add.toString() + "; ";
                }
                System.out.println("SENDER: " + sender);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
