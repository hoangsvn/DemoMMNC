package com.example;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;

public class FTP {

    public static void main(String[] args) {
        int port = 6788;
        // Tạo server factory
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();
        // Thiết lập cổng cho com.example.FTP server
        factory.setPort(port);
        // Thêm listener cho server
        serverFactory.addListener("default", factory.createListener());
        // Cấu hình user
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("ftp.properties"));
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());

        // Tạo và khởi chạy server
        FtpServer server = serverFactory.createServer();
        try {
            server.start();
            System.out.println("FTP server started on port "+port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

