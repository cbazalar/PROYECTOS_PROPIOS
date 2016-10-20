package biz.belcorp.ssicc.dao.spusicc.emprendedoras.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.emprendedoras.MantenimientoEMPEmprendedoraDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
/**
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Repository("spusicc.mantenimientoEMPEmprendedoraDAO")
public class MantenimientoEMPEmprendedoraDAOiBatis extends	BaseDAOiBatis implements MantenimientoEMPEmprendedoraDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#getRegimenes()
	 */
	public List getRegimenes(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getRegimenes",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#getTipoEmprededoras()
	 */
	public List getTipoEmprededoras(){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getTipoEmprededoras");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#getDatosConsultora(java.util.Map)
	 */
	public List getDatosConsultora(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getDatosConsultora", criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#executeValidarEmprendedoraRegular(java.util.Map)
	 */
	public void executeValidarEmprendedoraRegular(Map criteria){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeValidarEmprendedoraRegular", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#executeValidarEmprendedoraFastTrack(java.util.Map)
	 */
	public void executeValidarEmprendedoraFastTrack(Map criteria){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.executeValidarEmprendedoraFastTrack", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#getRequisitosNoCumple(java.util.Map)
	 */
	public List getRequisitosNoCumple(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getRequisitosNoCumple", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#updateDatosCliente(java.util.Map)
	 */
	public void updateDatosCliente(Map criteria){
		getSqlMapClientTemplate().update("spusicc.emprendedoras.procesosEMPSQL.updateDatosCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#insertDatosEmprendedora(java.util.Map)
	 */
	public void insertDatosEmprendedora(Map criteria){
		getSqlMapClientTemplate().insert("spusicc.emprendedoras.procesosEMPSQL.insertDatosEmprendedora", criteria);
	}
	
	public List getRecomendadas(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.emprendedoras.procesosEMPSQL.getRecomendadas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.emprendedoras.dao.MantenimientoEMPEmprendedoraDAO#getTextoComunicacion(java.util.Map)
	 */
	public String getTextoComunicacion(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.emprendedoras.procesosEMPSQL.getTextoComunicacion",criteria);
	}
	
	public void insertClienteComunicacion(ClienteComunicacion clienteComunicacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteComunicacion", clienteComunicacion);
	}
}
