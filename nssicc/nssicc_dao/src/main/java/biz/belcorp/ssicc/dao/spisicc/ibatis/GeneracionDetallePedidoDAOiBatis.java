/*
 * Created on 26/12/2005 11:34:11 AM
 *
 * biz.belcorp.ssicc.dao.ibatis.UltimasNoticiasDAOiBatis
 */
package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.GeneracionDetallePedidoDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("spisicc.generacionDetallePedidoDAO")
public class GeneracionDetallePedidoDAOiBatis extends BaseDAOiBatis implements
	GeneracionDetallePedidoDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UltimasNoticiasDAO#getConsultoras(java.util.Map)
     */
    public List getConsultoras() {
        List consultoras = getSqlMapClientTemplate().queryForList("GeneracionDetallePedidoSQL.getConsultoras",null);
        return consultoras;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.UltimasNoticiasDAO#getFichasInscripcion(java.util.Map)
     */
    public List getProductosDetallePedido(Map productos) {
        List listadoProductos = getSqlMapClientTemplate().queryForList(
                "GeneracionDetallePedidoSQL.getProductosDetallePedido", productos);
        return listadoProductos;
    }
    
    public void executeCargarCabeceraDetallePedidos(Map parametros) {
        getSqlMapClientTemplate().update(
                "GeneracionDetallePedidoSQL.executeCargarCabeceraDetallePedidos", parametros);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#insertUltimasNoticiasConsultora(java.lang.String,
     *      java.lang.String)
     */
    public void insertDetallePedido(String idCabeceraSolicitud, String numeroSolicitud, String archivoXML) {
        Map map = new HashMap();
        map.put("idCabeceraSolicitud", idCabeceraSolicitud);
        map.put("numeroSolicitud", numeroSolicitud);
        map.put("archivoXML", archivoXML);

        getSqlMapClientTemplate().insert(
                "GeneracionDetallePedidoSQL.insertDetallePedido", map);
    }
    
    public void insertXMLDetallePedido(String codConsultora, String numeroSolicitud, String archivoXML, int orden) {
        Map map = new HashMap();
        map.put("codConsultora", codConsultora);
        map.put("numeroSolicitud", numeroSolicitud);
        map.put("archivoXML", archivoXML);
        map.put("orden", String.valueOf(orden));

        getSqlMapClientTemplate().insert(
                "GeneracionDetallePedidoSQL.insertXMLDetallePedido", map);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.UltimasNoticiasDAO#executeEliminarUltimasNoticias()
     */
    public void executeEliminarDetallesPedidos() {
        getSqlMapClientTemplate().update(
                "GeneracionDetallePedidoSQL.executeEliminarDetallesPedidos", null);
    }
    
    public void executeEliminarXMLDetallePedido(){
    	getSqlMapClientTemplate().update(
                "GeneracionDetallePedidoSQL.executeEliminarXMLDetallePedido", null);
    }

    public void executeCargarDetallesPedidos(){
        getSqlMapClientTemplate().update(
                "GeneracionDetallePedidoSQL.executeCargarDetallesPedidos", null);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spisicc.dao.GeneracionDetallePedidoDAO#getTipoSolicitud()
     */
    public List getTipoSolicitud() {         
        return getSqlMapClientTemplate().queryForList(
                "GeneracionDetallePedidoSQL.getTipoSolicitud", null);
    }
}
