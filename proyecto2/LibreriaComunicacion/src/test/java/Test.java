
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Test {
    
    
    public static void main(String[] args) throws IOException {
        Mensaje mensaje = new Mensaje();
        mensaje.setDatos("hola".getBytes());
        mensaje.setTipoOperacion(Short.valueOf("1"));
        
        // transformando en bytes
        byte[] data = null;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); DataOutputStream dos = new DataOutputStream(baos)){
            DecoderEncoder.escribir(dos, mensaje);
            data = baos.toByteArray();
        }
        try(ByteArrayInputStream bais = new ByteArrayInputStream(data); DataInputStream dos = new DataInputStream(bais)){
            Mensaje leer = DecoderEncoder.leer(dos);
            System.out.println("" + leer);
        }
        
        
    }
    
}
