create or replace and compile java source named filenamefilter as
import java.io.*;

public class FilenameFilter implements java.io.FilenameFilter

{

    protected String pattern;

	

    public FilenameFilter(String pattern) {

	    this.pattern = pattern;

    }



    public boolean accept(File dir, String name) {

        return name.startsWith(pattern);

    }

	

}
/

