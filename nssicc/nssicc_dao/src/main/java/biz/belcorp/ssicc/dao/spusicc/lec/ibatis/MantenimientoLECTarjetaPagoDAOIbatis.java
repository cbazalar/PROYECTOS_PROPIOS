package biz.belcorp.ssicc.dao.spusicc.lec.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.MantenimientoLECTarjetaPagoDAO;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaLider;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;


/**
 * <p>
 * <a href="MantenimientoLECTarjetaPagoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="dtorres@sigcomt.com">Diego Torres</a>
 *         
 */
@Repository("spusicc.mantenimientoLECTarjetaPagoDAO")
public class MantenimientoLECTarjetaPagoDAOIbatis extends BaseDAOiBatis implements MantenimientoLECTarjetaPagoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getTarjetaPagoByCriteria(java.util.Map)
	 */
	public List getTarjetaPagoByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getTarjetaPagoByCriteria", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getTarjetaPago(java.lang.String)
	 */
	public TarjetaPago getTarjetaPago(String id) {
		return (TarjetaPago)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getTarjetaPago", id);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#insertTarjetaPago(biz.belcorp.ssicc.spusicc.mae.model.TarjetaPago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.insertTarjetaPago", tarjetaPago);		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#updateTarjetaPago(biz.belcorp.ssicc.spusicc.mae.model.TarjetaPago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateTarjetaPago", tarjetaPago);	
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#deleteTarjetaPago(biz.belcorp.ssicc.spusicc.mae.model.TarjetaPago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteTarjetaPago(TarjetaPago tarjetaPago) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.deleteTarjetaPago", tarjetaPago);			
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getEstadoTarjetaPago(java.util.Map)
	 */
	public List getEstadoTarjetaPago() {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getEstadoTarjetaPago");
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(java.util.Map)
	 */
	public List getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getLecAsociacionTarjetaPagoPorLiderByCriteria(java.util.Map)
	 */
	public List getLecAsociacionTarjetaPagoPorLiderByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getLecAsociacionTarjetaPagoPorLiderByCriteria", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getNombreLider(java.util.Map)
	 */
	public String getNombreLider(String codigoLider) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getNombreLider",codigoLider);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getNombreLider(java.lang.String)
	 */
	public boolean validaCodigoLider(String codigoLider) {
		boolean valida=false;
		String retorno = (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.validaCodigoLider",codigoLider);
		if(retorno!=null && retorno.length()>0){
			valida=true;
		}
		return valida;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#validaLiderTarjetaAsociada(java.lang.String)
	 */
	public String validaLiderTarjetaAsociada(String codigoLider) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.validaLiderTarjetaAsociada",codigoLider);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#insertTarjetaLider(biz.belcorp.ssicc.spusicc.mae.model.TarjetaLider, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTarjetaLider(TarjetaLider tarjetaLider, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.insertTarjetaLider", tarjetaLider);				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#updateEstadoTarjetaPago(biz.belcorp.ssicc.spusicc.mae.model.TarjetaPago, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstadoTarjetaPago(TarjetaPago tarjetaPago, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateEstadoTarjetaPago", tarjetaPago);			
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getTarjetaLider(java.util.Map)
	 */
	public TarjetaLider getTarjetaLider(Map params) {
		return (TarjetaLider)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getTarjetaLider", params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#updateTarjetaLider(java.util.Map)
	 */
	public void updateTarjetaLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateTarjetaLider", params);	
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#getTarjetaPagoByNumeroTarjeta(java.lang.String)
	 */
	public TarjetaPago getTarjetaPagoByNumeroTarjeta(String numeroTarjeta) {
		return (TarjetaPago)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.getTarjetaPagoByNumeroTarjeta", numeroTarjeta);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.dao.MantenimientoLECTarjetaPagoDAO#validaTarjetaPagoAsociada(java.lang.String)
	 */
	public String validaTarjetaPagoAsociada(String numeroTarjeta) {
		return(String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.validaTarjetaPagoAsociada",numeroTarjeta);
	}
	public boolean validaNumeroTarjeta(String numeroTarjeta) {
		boolean valida=false;
		String retorno = (String)getSqlMapClientTemplate().queryForObject("spusicc.lec.MantenimientoLECSQL.validaNumeroTarjeta",numeroTarjeta);
		if(retorno!=null && retorno.length()>0){
			valida=true;
		}
		return valida;
	}
	public void updateClienteLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.updateClienteLider", params);			
	}
	public List getLecConsultaTarjetasPago(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.lec.MantenimientoLECSQL.getLecConsultaTarjetasPago", params);
	}
	public void deleteTarjetaLider(Map params) {
		getSqlMapClientTemplate().update("spusicc.lec.MantenimientoLECSQL.deleteTarjetaLider", params);			
		
	}

}
