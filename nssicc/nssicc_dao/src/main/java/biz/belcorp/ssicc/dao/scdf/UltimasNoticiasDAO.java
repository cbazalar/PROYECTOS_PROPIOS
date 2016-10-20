/*
 * Created on 26/12/2005 10:50:34 AM biz.belcorp.ssicc.dao.UltimasNoticiasDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface UltimasNoticiasDAO extends DAO {

    /**
     * Obtiene la información de las consultoras y de sus pedidos, la cual será
     * usada para crear el reporte de últimas noticias
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais.
     * @return Una lista de objetos map con la información de las consultoras.
     */
    public List getConsultoras(Map criteria);
    
    /**
     * Obtiene el indicador de marca LBel de un pais.
     * @param criteria
     * @return
     */
    public List getIndicadorLEbel(Map criteria);
    
    /**
     * Obtiene la información de las fichas de inscripción registradas por la
     * consultora y que formarán parte del detalle del reporte de últimas
     * noticias.
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais y
     *            el codigo de la consultora.
     * @return Una lista de objetos map con la información de las fichas de
     *         inscripcion.
     */
    public List getFichasInscripcion(Map criteria);
    
    /**
     * Obtienen la relacion de clientes inscritos en las ultimas 6 campañas de
     * una determinada consultora.
     * 
     * @param criteria
     * @return
     */
    public List getMovimientoClientes(Map criteria);
    
    /**
     * Obtiene la relacion de clientes rechazados al momento de inscribirse de
     * una determinada consultora.
     * 
     * @param criteria
     * @return
     */
    public List getClientesRechazados(Map criteria);
    
    public List getMensajesPrivilege(Map mensajesCriteria);
    /**
     * Obtiene la información de las tarjetas de puntos registradas por la
     * consultora y que formarán parte del detalle del reporte de últimas
     * noticias.
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais y
     *            el codigo de la consultora.
     * @return Una lista de objetos map con la información de las tarjetas de
     *         puntos.
     */
    public List getTarjetasPuntos(Map criteria);

    /**
     * Obtiene la información de los premios solicitados por los clientes de una
     * consultora y que formarán parte del detalle del reporte de últimas
     * noticias.
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais y
     *            el codigo de la consultora.
     * @return Una lista de objetos map con la información de los premios
     *         solicitados.
     */
    public List getPremiosSolicitados(Map criteria);
    
    public List getPremiosAcumulados(Map premiosAcumCriteria);
    
    /**
     * Obtiene la información de los clientes que cumplena años para una
     * determinada consultora y que formatran parte del detalle del reporte de
     * ultimas noticias
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais y
     *            el codigo de la consultora.
     * @return Una lista de objetos map con la información de los clientes
     *         que cumplen años.
     */
    public List getClientesCumpleaños(Map criteria);
    
    public List getCarneBeneficios(Map carneBeneficiosCriteria);

    /**
     * Inserta el archivo XML conteniendo el reporte de Ultimas Noticias
     * Privilege para una consultora en la base de datos
     * 
     * @param codigoConsultora
     *            Codigo de la Consultora
     * @param archivoXML
     *            Archivo XML conteniendo los tags del reporte de ultimas
     *            noticias Privilege de la consultora
     */
    public void insertUltimasNoticiasConsultora(String codigoConsultora,
            String archivoXML);

    /**
     * Se encarga de eliminar toda la información de las ultimas noticias
     * ingresadas en procesos anteriores dejando la tabla lista para el nuevo
     * procesamiento
     */
    public void executeEliminarUltimasNoticias();

}
