package biz.belcorp.ssicc.service.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

public interface PremioService extends Service {

    /**
     * Mediante este procedimiento obtenos informacin de los Premios por mediom
     * de las tarjetas.
     * 
     * @param codigoPais
     *            Codigo de Pais del Usuario invocador.
     * @return Lista de objetos premios.
     */
    public List executeObtienePremios(String codigoPais);

    /**
     * Realiza el procesamiento de Premios, lo cual asigna diversos valores de
     * Flag, para determinados productos.
     * 
     * @param pais
     *            Codigo de Pais del Usuario invocador.
     * @param usuario
     *            Informacion del usuario Invocador
     * @return Numero de premios procesados (que fueron actualizados con un
     *         flag)
     */
    public int executeProcesamientoPremios(Pais pais, Usuario usuario);

    /**
     * Procedimiento que obtiene informacion acerca de los Estados de los
     * Premios
     * 
     * @param pais
     *            Codigo del Pais del Usuario invocador.
     * @return Numero de Premios, agrupados por COnsultora y Producto.
     */
    public List selectEstadosPremios(Pais pais);

    /**
     * Proceso que obtiene la lista de cabeceras y detalles y realiza la insercion en PED_SOLIC_CABEC y PED_SOLIC_POSIC
     * @param params
     */
    public void executeGenerarSolicitudesPremiosPrivilege(Map params);

    /**
     * Metodo que obtiene los estados de los premios
     * @param params
     * @return
     */
    public List getCantidadPremiosxEstado(Map params);
    
    /**
     * Ejecuta el proceso de carga en Sicc de Privilege
     * @param codigoPais
     * @param codigoPeriodo
     * @param usuario
     * @return
     */
    public int executeCargaSiCC(String codigoPais, String codigoPeriodo, String usuario);
    
    /**
     * Proceso que genera la Solicitud de Premios Privilege
     * @param pais
     * @param codigoPeriodo
     * @param usuario
     * @param paramsBatch
     */
    public void generarSolicitudesAtencionPrivilege(Pais pais, String codigoPeriodo, Usuario usuario, Map paramsBatch);
    
    /**
     * Metodo que obtiene el numero de solicitudes generadas
     * @return
     */
    public Integer getNumeroSolicitudesGeneradas();
}
