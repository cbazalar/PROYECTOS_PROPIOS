/*
 * Created on 26/12/2005 10:50:34 AM biz.belcorp.ssicc.dao.UltimasNoticiasDAO
 */
package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface GeneracionDetallePedidoDAO extends DAO {

    /**
     * Obtiene la informacin de las consultoras y de sus pedidos, la cual ser
     * usada para crear el reporte de ltimas noticias
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais.
     * @return Una lista de objetos map con la informacin de las consultoras.
     */
    public List getConsultoras();

    /**
     * Obtiene la informacin de las fichas de inscripcin registradas por la
     * consultora y que formarn parte del detalle del reporte de ltimas
     * noticias.
     * 
     * @param criteria
     *            Objeto map contiendo los criterios usados para la consulta,
     *            entre los cuales tenemos principalmente el codigo del pais y
     *            el codigo de la consultora.
     * @return Una lista de objetos map con la informacin de las fichas de
     *         inscripcion.
     */
    public List getProductosDetallePedido(Map producto);

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
    public void insertDetallePedido(String idCabeceraSolicitud, String numeroSolicitud, String archivoXML);
    
    /**
     * Inserta los subpartes del XML del detalle de factura para evitar el problema
     * de insercion de cadenas grandes en campos de tipo CLOB.
     * @param codConsultora
     * @param numeroSolicitud
     * @param archivoXML
     * @param orden
     */
    public void insertXMLDetallePedido(String codConsultora, String numeroSolicitud, String archivoXML, int orden);
    
    public void executeCargarCabeceraDetallePedidos(Map parametros);

    /**
     * Se encarga de eliminar toda la informacin de las ultimas noticias
     * ingresadas en procesos anteriores dejando la tabla lista para el nuevo
     * procesamiento
     */
    public void executeEliminarDetallesPedidos();
    public void executeEliminarXMLDetallePedido();
    
    public void executeCargarDetallesPedidos();
    
    /**
     * @return
     * Devuelve la lista de tipos de solicitud para cargar un combo
     */
    public List getTipoSolicitud();
}
