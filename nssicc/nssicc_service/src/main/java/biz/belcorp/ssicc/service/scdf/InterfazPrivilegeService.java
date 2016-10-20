/*
 * Created on 19/05/2005 02:10:03 PM
 * biz.belcorp.privilege.service.impl.InterfazPrivilegeServiceImpl
 */
package biz.belcorp.ssicc.service.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPrivilegeService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface InterfazPrivilegeService extends Service {

    /**
     * Elimina informacion de las tablas PRI_CLIEN, PRI_CUENT_CLIEN, PRI_TARJE,
     * PRI_PREMI de acuerdo al Cdigo del Pas del Usuario Logueado.
     * 
     * @param pais
     *            Pas al que pertenece el Usuario.
     */
    public void executeEliminarInformacionPrivilege(String codigoPais);

    /**
     * Realiza la carga (lectura) de Archivos a partir de los archivos GFICINS,
     * GTARPTOS, GPREDESP, que actualizaran las tablas anteriormente
     * mencionadas, asi como carga el valor del archivo GFACTOR y lo actualiza
     * en la tabla BAS_CONTR. Para ello se vale de las Tablas PRI_MAEST_ARCHI y
     * PRI_MAEST_COLUM, las cuales contienen la METADATA de la Estructura de
     * Archivos.
     * 
     * @param usuario
     *            Usuario que se encuentra logueado en el sistema.
     * @param pais
     *            Pas al que pertenece el Usuario.
     * @return Lista conteniendo las Bitacoras por cada archivo que ha sido
     *         cargado.
     */
    public List executeCargarArchivosPrivilege(Usuario usuario, Pais pais);

    /**
     * Realiza la descarga (escritura) de Archivos a partir de Tablas. Para ello
     * se vale de las Tablas PRI_MAEST_ARCHI y PRI_MAEST_COLUM, las cuales
     * contienen la METADATA de la Estructura de Archivos.
     * 
     * @param usuario
     *            Usuario que se encuentra logueado en el sistema.
     * @param pais
     *            Pas al que pertenece el Usuario.
     * @return Lista conteniendo las Bitacoras por cada archivo que ha sido
     *         descargado.
     */
    public List executeDescargarArchivosPrivilege(Usuario usuario, Pais pais);

    /**
     * Realiza la descarga (escritura) de 2 archivos que obtienen su informacion
     * a partir de la Tabla PRI_PREMI
     * 
     * @param usuario
     *            Usuario que se encuentra logueado en el sistema.
     * @param pais
     *            Pas al que pertenece el Usuario.
     * @return Lista conteniendo las Bitacoras por cada archivoOCR que ha sido
     *         descargado.
     */
    public List executeGeneracionArchivoOCR(Usuario usuario, Pais pais);

    /**
     * @param codigoPais
     */
    public void executeEliminarBuzonMsg(String codigoPais);
   
}