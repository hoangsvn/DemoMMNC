import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;

public class FTPS {

    public static void main(String[] args) {
        int port = 6789;
        
        // Tạo server factory
        FtpServerFactory serverFactory = new FtpServerFactory();
        
        // Thiết lập SSL/TLS
        SslConfigurationFactory ssl = new SslConfigurationFactory();
        ssl.setKeystoreFile(new File("keystore.jks"));
        ssl.setKeystorePassword("password");
        
        // Tạo listener factory và thiết lập cổng
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(port);
        factory.setSslConfiguration(ssl.createSslConfiguration());
        factory.setImplicitSsl(true);
        
        // Thêm listener cho server
        serverFactory.addListener("default", factory.createListener());
        
        // Cấu hình user
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("users.properties"));
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        
        // Tạo và khởi chạy server
        FtpServer server = serverFactory.createServer();
        try {
            server.start();
            System.out.println("SFTP server started on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
