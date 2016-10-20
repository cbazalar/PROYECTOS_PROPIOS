package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRCapturaPedidoDAO;

/**
 * @author peextdoliva
 */
@Repository("spusicc.pedidos.mantenimientoOCRCapturaPedidoDAO")
public class MantenimientoOCRCapturaPedidoDAOiBatis extends
		BaseDAOiBatis implements MantenimientoOCRCapturaPedidoDAO {
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#insertcabeceraPedido(biz.belcorp.ssicc.model.LabelDatosConsultoraValue, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertcabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {
        getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertCabeceraPedido", objDatosConsultora);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#insertDetallePedido(biz.belcorp.ssicc.model.LabelPedidosValue, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario) {	
		getSqlMapClientTemplate().insert("spusicc.pedidos.PedidosSQL.insertDetallePedidoCaptura", objPedidoConsultora);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#updateDetallePedido(biz.belcorp.ssicc.model.LabelPedidosValue, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDetallePedido(LabelPedidosValue objPedidoConsultora, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateDetallePedidoCaptura", objPedidoConsultora);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getCodConsultora(java.util.Map)
	 */
	public String getCodConsultora(Map params){
		String codConsul = "";
		try {			
			codConsul = (String)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getCodConsultora", params);	
			if(codConsul==null) codConsul="";			
		} catch (Exception e) {
			codConsul = "";
		}		
		return codConsul;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#deleteDetallePedido(java.util.Map)
	 */
	public void deleteDetallePedido(Map detallePedidos) {
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteDetallePedido", detallePedidos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#updateCabeceraPedido(biz.belcorp.ssicc.model.LabelDatosConsultoraValue, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCabeceraPedido(LabelDatosConsultoraValue objDatosConsultora, Usuario usuario) {		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.updateCabeceraPedido", objDatosConsultora);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getListaCodigosVentaMatriz(java.util.Map)
	 */
	public List getListaCodigosVentaMatriz(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaCodigosVentaMatriz", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#validarBloqueoDigitacionPedidos(java.util.Map)
	 */
	public String validarBloqueoDigitacionPedidos(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getValidaBloqueoDigitacionPedidos", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getListaDetallePedido(java.util.Map)
	 */
	public List getListaDetallePedido(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getListaDetallePedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getSumaTotalPedidoListaDetallePedido(java.util.Map)
	 */
	public String getSumaTotalPedidoListaDetallePedido(Map criteria){
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getSumaTotalPedidoListaDetallePedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#deleteDetallePedidoOnLine(java.util.Map)
	 */
	public void deleteDetallePedidoOnLine(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteDetallePedidoOnLine", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#verificarDetallePedido(java.util.Map)
	 */
	public String verificarDetallePedido(Map criteria) {
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.verificarDetallePedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#deleteCabeceraPedidoOnLine(java.util.Map)
	 */
	public void deleteCabeceraPedidoOnLine(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.deleteCabeceraPedidoOnLine", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getBloqueoOnline(java.util.Map)
	 */
	public Integer getBloqueoOnline(Map criteria){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getBloqueoOnline", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#actualizaIndicadorOCS(java.util.Map)
	 */
	public void actualizaIndicadorOCS(Map criteria) {
        getSqlMapClientTemplate().delete("spusicc.pedidos.PedidosSQL.actualizaIndicadorOCS", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getNumeroLoteByPk(java.util.Map)
	 */
	public String getNumeroLoteByPk(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNumeroLoteByPk", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getFechaFacturacion(java.util.Map)
	 */
	public String getFechaFacturacion(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getFechaFacturacion", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getNumeroSecuenciaDocumento(java.util.Map)
	 */
	public String getNumeroSecuenciaDocumento(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNumeroSecuenciaDocumento", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getOidSolicitud(java.util.Map)
	 */
	public String getOidSolicitud(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidSolicitud", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#actualizaIndicadorProcDetal(java.util.Map)
	 */
	public void actualizaIndicadorProcDetal(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.actualizaIndicadorProcDetal", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#existePedido(java.util.Map)
	 */
	public int existePedido(Map criteria){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.existePedido", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.MantenimientoOCRCapturaPedidoDAO#getOidSolicitudPROL(java.util.Map)
	 */
	public String getOidSolicitudPROL(Map criteria){
		return (String)this.getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getOidSolicitudPROL", criteria);
	}
}