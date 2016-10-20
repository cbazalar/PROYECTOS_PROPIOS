package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOValidacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MensajeValidacionSTO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ValidacionSTO;

/**
 * Implementacion del DAO que ejecutara las Validaciones
 * 
 * <p>
 * <a href="MantenimientoSTOValidacionesDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *  
 */
@Repository("spusicc.mantenimientoSTOValidacionesDAO")
public class MantenimientoSTOValidacionesDAOiBatis extends BaseDAOiBatis implements MantenimientoSTOValidacionesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#getValidacionesSTO(java.util.Map)
	 */
	public List getValidacionesSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getValidacionesSTO", criteria);
	}
	
	public String getNextCodigoValidacion(String tipoDocumento) {
		return (String)getSqlMapClientTemplate().queryForObject("sto.MantenimientoSTOSQL.getNextCodigoValidacion", tipoDocumento);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#insertValidacionesParametria(java.util.Map)
	 */
	public void insertValidacionesParametria(Map criteria) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertValidacionesParametria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#insertValidacionesMensaje(java.util.Map)
	 */
	public void insertValidacionesMensaje(Map criteria) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertValidacionesMensaje", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#insertValidacionesSecuencia(java.util.Map)
	 */
	public void insertValidacionesSecuencia(Map criteria) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertValidacionesSecuencia", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#getValidacionesParametriaMensajeSTO(java.lang.String)
	 */
	public List getValidacionesParametriaMensajeSTO(String codigoValidacion) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getValidacionesParametriaMensajeSTO", codigoValidacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#updateValidacionesParametria(java.util.Map)
	 */
	public void updateValidacionesParametria(Map criteria) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateValidacionesParametria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#updateValidacionesMensaje(java.util.Map)
	 */
	public void updateValidacionesMensaje(Map criteria) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateValidacionesMensaje", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#updateValidacionesSecuencia(java.util.Map)
	 */
	public void updateValidacionesSecuencia(Map criteria) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateValidacionesSecuencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOValidacionesDAO#getValidacionListSTO(java.util.Map)
	 */
	public List getValidacionListSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getValidacionListSTO", criteria);
	}

	public void deleteParamValidSTO(String codigoValidacion) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.deleteParamValidSTO", codigoValidacion);	
	}

	public void deleteParamSecueValidSTO(String codigoValidacion) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.deleteParamSecueValidSTO", codigoValidacion);	
	}

	public void deleteMensaValidSTO(String codigoValidacion) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.deleteMensaValidSTO", codigoValidacion);	
	}

	public ValidacionSTO getValidacionSTO(ValidacionSTO bean) {
		return (ValidacionSTO) this.getSqlMapClientTemplate().queryForObject("sto.MantenimientoSTOSQL.getValidacionSTO",bean);
	}

	public void insertValidacionSTO(ValidacionSTO bean, Usuario usuario) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertValidacionSTO", bean);		
	}

	public void updateValidacionSTO(ValidacionSTO bean, Usuario usuario) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateValidacionSTO", bean);
	}
	
	public List getMensajeValidacionListSTO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sto.MantenimientoSTOSQL.getMensajeValidacionListSTO", criteria);
	}
	
	public void deleteMensajeValidacionSTO(Map criteria) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.deleteMensajeValidacionSTO", criteria);	
	}

	public MensajeValidacionSTO getMensajeValidacionSTO(
			MensajeValidacionSTO bean) {
		return (MensajeValidacionSTO) this.getSqlMapClientTemplate().queryForObject("sto.MantenimientoSTOSQL.getMensajeValidacionSTO",bean);
	};
	
	
	public void insertMensajeValidacionSTO(MensajeValidacionSTO bean, Usuario usuario) {
		getSqlMapClientTemplate().insert("sto.MantenimientoSTOSQL.insertMensajeValidacionSTO", bean);		
	}

	public void updateMensajeValidacionSTO(MensajeValidacionSTO bean, Usuario usuario) {
		getSqlMapClientTemplate().update("sto.MantenimientoSTOSQL.updateMensajeValidacionSTO", bean);
	}
}