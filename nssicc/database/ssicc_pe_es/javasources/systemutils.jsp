create or replace and compile java source named systemutils as
import java.io.*;

public class SystemUtils

{

    public static final String listFile = "list.txt";



	public static final String listDir = "/sicc/int/temp/";



    public SystemUtils(){

    }



	public static void list(String dirName, String pattern) throws IOException {

	    File dir = new File(dirName);

		File out = new File(listDir, listFile);

		String[] children = null;



		if(pattern != null) {

            FilenameFilter filter = new FilenameFilter(pattern);

	        children = dir.list(filter);

		}

		else {

		    children = dir.list();

	    }

        if (children == null) {

            // dir no existe o no es un directorio

        } else {

		    PrintWriter pw = new PrintWriter(new FileWriter(out));

            for (int i=0; i < children.length; i++) {

                // Obtenemos el nombre del archivo

                String filename = children[i];

			    // Escribimos el nombre en el archivo

			    pw.println(filename);

            }

			pw.close();

        }

	}

}
/

