package biz.belcorp.ssicc.dao.scdf.ibatis;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.ArchivoDAO;
import biz.belcorp.ssicc.dao.scdf.model.Archivo;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ArchivoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("scdf.archivoDAO")
public class ArchivoDAOiBatis extends BaseDAOiBatis implements ArchivoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ArchivoDAO#getArchivo(biz.belcorp.ssicc.scdf.model.Archivo)
     */
    public Archivo getArchivo(Archivo archivo) {
        Archivo resultado = (Archivo) getSqlMapClientTemplate().queryForObject(
                "scdf.ArchivoSQL.getArchivo", archivo);
        if (archivo == null) {
            throw new ObjectRetrievalFailureException(Archivo.class, archivo);
        }
        return resultado;
    }

}
