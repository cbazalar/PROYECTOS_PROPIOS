package biz.belcorp.ssicc.dao.scdf;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.scdf.model.Archivo;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ArchivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ArchivoDAO extends DAO {

    /**
     * Generalmente se le pasa el codigo para el archivo a crear o leer en la
     * clase Constants, y de acuerdo a eso se obtiene toda la información para
     * la manipulacion parametrizada de archivos.
     * 
     * @param archivo
     *            Incluye valores como el codigo del pais y el codigo del
     *            archivo
     * @return Archivo con sus campos debidamente completados, listo para el
     *         mapeo.
     */
    public Archivo getArchivo(Archivo archivo);

}


