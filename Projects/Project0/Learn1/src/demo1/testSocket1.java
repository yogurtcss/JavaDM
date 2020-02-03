package demo1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;

public class testSocket1 {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try{
            socket = new Socket( "127.0.0.1", 9999 );
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            out.print("start") ;
        }catch( Exception e ){
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
}
