package org.java.lecciones.leccion1;

import java.io.IOException;
import java.net.Socket;

public class CrearSockets {


    private String getConfigurationHost(){
//        File...
        return "localhost";
    }

    private Integer getConfigurationPort(){
//        File...
        return 8080;
    }

    public Socket getSocket() throws IOException {
        String host = getConfigurationHost();
        Integer port = getConfigurationPort();
        return new Socket(host, port);
    }

}
