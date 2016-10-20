/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * <p>
 * <a href="ProcesoPERGeneracionCtaCteDocumentoLegalDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes</a>
 */
public interface ProcesoPERGeneracionCtaCteDocumentoLegalDAO extends DAO {

    /**
     * Ejecuta un Stored Procedure el cual realiza la
     * generacion de Documento Legales
     * 
     * @param pais
     *            Codigo de pais del usuario invocador
     * @param usuario
     *            Usuario del Sistem
     * @return Numero de Lote.
     */
    public Map executeGeneracionCtaCteDocumentoLegal(Pais pais, Usuario usuario, String codigoInterfaz, String tipOrigenDatos);

}
