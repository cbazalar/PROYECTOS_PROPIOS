create or replace and compile java source named dirlist as
import java.io.*;
import java.sql.*;

public class DirList
{
public static void getList(String directory)
                   throws SQLException
{
    File path = new File( directory );
    String[] list = path.list();
    String element;

    for(int i = 0; i < list.length; i++)
    {
        element = list[i];
        #sql { INSERT INTO IMP_TMP_ARCHI_DIREC (NOM_ARCH)
               VALUES (:element) };
    }
}

}
/

