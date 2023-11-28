package org.java.lecciones.cliente.mensajes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.java.lecciones.libreriacomunicacion.Mensaje;
import org.java.lecciones.libreriacomunicacion.TipoOperacion;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class GestorMS {

    // definir una estructura de datos que alamcene los mensajes, cuidar concurrencia!!!
    private final Map<TipoOperacion, List<Mensaje>> mensajesMap = new HashMap<>();
    private final Map<String, Integer> acusesMap = new HashMap<>();

    private GestorMS() {
    }

    public static GestorMS getInstance() {
        return ConfiguracionHolder.INSTANCE;
    }

    private static class ConfiguracionHolder {

        private static final GestorMS INSTANCE = new GestorMS();
    }

    public Mensaje getNextMessage() {
        for (Map.Entry<TipoOperacion, List<Mensaje>> entry : mensajesMap.entrySet()) {
            TipoOperacion key = entry.getKey();
            List<Mensaje> value = entry.getValue();
            Mensaje mensaje = value.get(value.size()-1);
            String evento = ""; //mensaje.getEvento();
            // si el numero de acuses recibidos aun no se cumple hay que enviar nuevamente
            acusesMap.put(evento, 0);
            // se reenvia el mensaje;
            
            // en caso contrario 

        }
        
        // 1. se busca en el mapa de mensajes el sig mensaje
        // 2. verificar si el mensaje ya fue acusado
        // si no ha sido acusado sería bueno volver a enviarlo
        // si ya fue acusado se saca de la lista de los mensajes y se envía el siguiente
        return null;
    }

    public void putMessage(Mensaje mensaje) {
        List<Mensaje> list = mensajesMap.getOrDefault(mensaje.getTipoOperacion(), new ArrayList<>());
        list.add(mensaje);
        mensajesMap.put(mensaje.getTipoOperacion(), list);
    }

    public void registrarAcuse(String ack) {
        Integer cuantos = acusesMap.getOrDefault(ack, 0);
        acusesMap.put(ack, ++cuantos);
    }

}
