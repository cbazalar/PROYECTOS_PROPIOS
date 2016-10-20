package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Premio;

public interface PremioDAO {

    /**
     * Obtiene un listado de todos los premios
     * 
     * @param premio
     *            objeto Premio cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Premio poblados
     */
    public List getPremios(Premio premio);

    /**
     * Obtiene un listado de premios según País
     * 
     * @param codigoPais
     *            Codigo del Pais que es obtenido a partir del Usuario logueado
     * @return Lista de objetos Premio poblados
     */
    public List getPremiosMapByPais(String codigoPais);

    /**
     * Obtiene un listado de premios según el Map criteria
     * 
     * @param criteria
     *            Map que contiene información que servirá de filtro
     * @return Lista de objetos Premio poblados
     */
    public List getPremiosByCriteria(Map criteria);

    /**
     * Obtiene un listado de premios
     * 
     * @param codigoPais
     *            Código del País del Usuario que está en el sistema
     * @return Lista de objetos Premio poblados
     */
    public List getPremiosWithTarjetas(String codigoPais);

    /**
     * Registra la información de un nuevo Premio
     * 
     * @param premio
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertPremio(Premio premio, Usuario usuario);

    /**
     * Actualiza la informacion de un premio
     * 
     * @param premio
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updatePremio(Premio premio, Usuario usuario);

    /**
     * Elimina un premio de la base de datos en base a su codigo
     * 
     * @param premio
     *            el premio que se usa como filtro
     */
    public void removePremio(Premio premio);

    /**
     * Elimina un premio de la base de datos en base a su codigo
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     */
    public void removePremioByPais(String codigoPais);

    /**
     * Ejecuta el Procesamiento de Premios que actualizara diversos FLAG
     * 
     * @param pais
     *            Pais del Usuario Logueado.
     * @param usuario
     *            Usuario logueado.
     * @return el numero de premios procesados
     */
    public int executeProcesaPremios(Pais pais, Usuario usuario);

    /**
     * Consigue los status de los premios referentes a un determinado Producto
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario Logueado.
     * @return Lista de Premios.
     */
    public List getEstadosPremiosByPais(String codigoPais);

    /**
     * Conseguimos la cabecera para los archivos OCR
     * 
     * @param criteria
     *            Map conteniendo los parametros para la extraccion de la
     *            informacion de las cabeceras.
     * @return Lista de Maps de informacion
     */
    public List getPremiosCabecera(Map criteria);

    /**
     * Conseguimos el detalle para los archivos OCR
     * 
     * @param criteria
     *            Map conteniendo los parametros para la extraccion de la
     *            información de los detales de las solicitudes OCR.
     * @return Lista de Maps de informacion
     */
    public List getPremiosDetalle(Map criteria);

   /**
     * Proceso que obtiene la lista de cabeceras y detalles y realiza la insercion en PED_SOLIC_CABEC y PED_SOLIC_POSIC
     * @param criteria
     */
    public void executeGenerarSolicitudesPremiosPrivilege(Map criteria);
    
 
    /**
     * Metodo que obtiene los estados de los premios
     * @param params
     * @return
     */
    public List getCantidadPremiosxEstado(Map params);

    /**
     * Metodo que obtiene el numero de solicitudes generadas
     * @return
     */
    public Integer getNumeroSolicitudesGeneradas();
}