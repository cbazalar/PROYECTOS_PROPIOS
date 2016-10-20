/*
 * Created on 31/05/2005 02:43:55 PM biz.belcorp.ssicc.util.StreamConsumer
 */
package biz.belcorp.ssicc.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * TODO Include class description here.
 * <p>
 * <a href="StreamConsumer.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class StreamConsumer extends Thread {
    private InputStream in;

    private OutputStream out;

    private String type;

    /** Creates a new instance of StreamConsumer */
    public StreamConsumer(InputStream in) {
        this.in = in;
    }

    public StreamConsumer(InputStream in, java.lang.String type, OutputStream out) {
        this.in = in;
        this.out = out;
        this.type = type;
    }

    public void run() {
        try {
            PrintWriter pw = null;
            if (out != null)
                pw = new PrintWriter(out);

            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(type + ">" + line);
            }
            if (pw != null)
                pw.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
