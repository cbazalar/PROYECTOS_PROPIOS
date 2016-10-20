package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCargaPedidoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CargaMica;

@Repository("spusicc.pedidos.mantenimientoOCRCargaPedidoDAO")
public class MantenimientoOCRCargaPedidoDAOiBatis extends
		BaseDAOiBatis implements MantenimientoOCRCargaPedidoDAO {
	
	public void insertcabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {
        getSqlMapClientTemplate().insert(
                    "spusicc.pedidos.PedidosSQL.insertCabeceraPedido", objDatosConsultora);		
	}
	public void insertDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario) {	
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertDetallePedido", objPedidoConsultora);
		
	}
	public List getSearchPedidosByCriteria(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getSearchPedidosByCriteria",
				criteria);
	}
	
	public List getDetallePedidosConsultoraByCriteria(Map criteria) {
        List l = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetallePedidosConsultoraByCriteria",
				criteria);        
		return l;
	}
	
	public void updateDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario) {
		log.debug("-----> numLote =  "+objPedidoConsultora.getNumLote());
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateDetallePedido", objPedidoConsultora);
		
	}

	public void deleteDetallePedido(Map detallePedidos) {
        getSqlMapClientTemplate().delete(
                "spusicc.pedidos.PedidosSQL.deleteDetallePedido", detallePedidos);
	}

	public void updateRemoveCabeceraPedido(LabelDatosConsultoraValue consultoraValue, Usuario usuario) {
		getSqlMapClientTemplate().update(
				"spusicc.pedidos.PedidosSQL.removeCabeceraPedido",
				consultoraValue);
	}

	public List getDetallePedidoByFilter(Map filter){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDetallePedidoByFilter",
				filter);
	}
	
	public void executeEnviarDetallesDigitados(Map params){
		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeConsolidadoPedidosDigitados",params);
		
	}
	
	public String getCodConsultora(Map params){
		String codConsul = "";
		try {			
			codConsul = (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCodConsultora", params);
			log.debug("__codConsul = "+codConsul);
			if(codConsul==null) codConsul="";			
		} catch (Exception e) {
			codConsul = "";
		}		
		return codConsul;
	}
	
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateCabeceraPedido", objDatosConsultora);		
	}
	
	public List getSearchDetallesByCriteria(Map criteria){
		List l = getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getSearchDetallesByCriteria",
				criteria);        
		return l;
	}
	
	public String getCuponEquivalente(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCuponEquivalente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#execute(java.util.List)
	 */
	public void execute(List lista){
					
		for (int i=0;i<lista.size();i++){
			
			Map params = new HashMap();	
			params.put("codigoPais", ((CargaMica)lista.get(i)).getCodigoPais());
			params.put("periodo", ((CargaMica)lista.get(i)).getCodigoPeriodo());
			params.put("codigoCliente",((CargaMica)lista.get(i)).getCodigoCliente());
			params.put("tipoOC",((CargaMica)lista.get(i)).getTipoOrden());
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
	        try {
	        	params.put("fecha", sdf.parse(((CargaMica)lista.get(i)).getFechaSolicitud()));
	        } catch (ParseException e) {
	            log.error("Error al formatear la fecha: " + ((CargaMica)lista.get(i)).getFechaSolicitud());
	        }	        	
	        log.debug(i+" mica "+((CargaMica)lista.get(i)).toString() );
	        getSqlMapClientTemplate().insert(
					"spusicc.pedidos.PedidosSQL.insertMantenimientoEVICargaMica", (CargaMica)lista.get(i));
			getSqlMapClientTemplate().insert(
					"sisicc.InterfazSQL.insertInterfazEVIRecepcionarOC", params);
						
		}
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getDatosTipoConsultora(java.util.Map)
	 */
	public List getDatosTipoConsultora(Map criteria){	
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getDatosTipoConsultora",
				criteria);				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getCodigoVentaList(java.util.Map)
	 */
	public List getCodigoVentaList(Map criteria){	
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getCodigoVentaList",
				criteria);				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#validaCodigoVenta(java.util.Map)
	 */
	public LabelPedidosValue validaCodigoVenta(Map criteria){
		return (LabelPedidosValue)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validaCodigoVenta",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getTiposRecepcionMICAOCSWebDD()
	 */
	public List getTiposRecepcionMICAOCSWebDD() {		
		
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getTiposRecepcionMICAOCSWebDD",null);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getTiposRecepcionMICAOCSDemandaWebDD()
	 */
	public List getTiposRecepcionMICAOCSDemandaWebDD() {
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getTiposRecepcionMICAOCSDemandaWebDD",null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getTodosDetallesPedidoByFilter(java.util.Map)
	 */
	public List getTodosDetallesPedidoByFilter(Map filter){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTodosDetallesPedidoByFilter",
				filter);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#saveSolicitudesTransferidas(java.util.Map)
	 */
	public void saveSolicitudesTransferidas(Map map) {
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.deleteSolicitudesTransferidas", map);
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.saveSolicitudesTransferidas", map);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#executeConsolidaPedidoOnline(java.util.Map)
	 */
	public void executeConsolidaPedidoOnline(Map map){
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeConsolidaPedidoOnline",map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#getOidTerritorioAdministrativo(java.lang.String)
	 */
	public String getOidTerritorioAdministrativo(String codigoCliente) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidTerritorioAdministrativo", codigoCliente);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#validarCUVFaltanteAnunciado(java.util.Map)
	 */
	public Integer validarCUVFaltanteAnunciado(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validarCUVFaltanteAnunciado", map);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#validarCUVCompuesta(java.util.Map)
	 */
	public String validarCUVCompuesta(Map map) {
		BigDecimal valor = (BigDecimal) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validarCUVCompuesta", map);
		return valor.setScale(2, RoundingMode.HALF_EVEN).toString();
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#validarCUVLimiteVenta(java.util.Map)
	 */
	public Integer validarCUVLimiteVenta(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.validarCUVLimiteVenta", map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCargaPedidoDAO#executeGetMontoMinimoConsultora(java.util.Map)
	 */
	public void executeGetMontoMinimoConsultora(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeGetMontoMinimoConsultora", criteria);
	}
}