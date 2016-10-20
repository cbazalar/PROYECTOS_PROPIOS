/*
 * Created on 18/03/2005 01:25:24 PM
 * biz.belcorp.ssicc.dao.ibatis.LookupDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.LookupDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * TODO Include class description here.
 * <p>
 * <a href="LookupDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("lookupDAO")
public class LookupDAOiBatis extends BaseDAOiBatis implements LookupDAO {

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.LookupDAO#getAllTiposAcciones()
     */
	public List getAllTiposAcciones() {
        return this.getSqlMapClientTemplate().queryForList("LookupSQL.getTiposAcciones", null);
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.LookupDAO#getAllTiposBloqueoUsuario()
     */
	public List getAllTiposBloqueoUsuario() {
		return this.getSqlMapClientTemplate().queryForList("LookupSQL.getTiposBloqueoUsuario", null);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.LookupDAO#getExtensionesArchivo()
     */
    public List getExtensionesArchivo() {
        return this.getSqlMapClientTemplate().queryForList("LookupSQL.getExtensionesArchivo",
                null);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.LookupDAO#getExtensionesLog()
     */
    public List getExtensionesLog() {
        return this.getSqlMapClientTemplate().queryForList("LookupSQL.getExtensionesLog",
                null);
    }

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.LookupDAO#getIdiomas()
     */
    public List getIdiomas() {
        return this.getSqlMapClientTemplate().queryForList("LookupSQL.getIdiomas",
                null);
    }

	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.LookupDAO#getPaises()
     */
    public List getPaises() {
        return this.getSqlMapClientTemplate().queryForList("LookupSQL.getPaises",
                null);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.LookupDAO#updateProcesosNoTerminados()
	 */
	public void updateProcesosNoTerminados() {
		
		//Comentar para desarrollo, habilitar en ambientes de QAS y PRD
		this.getSqlMapClientTemplate().update("LookupSQL.updateProcesosBatch", null);
		this.getSqlMapClientTemplate().update("LookupSQL.updateProcesosSTO", null);
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalConsultaSTO", null);
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteDatosTempCUV", null);		
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalPostVentaCabecera", null);
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalPostVentaDetalle", null);		
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalPedidosCabecera", null);
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalPedidosDetalle", null);
		this.getSqlMapClientTemplate().delete("LookupSQL.deleteTemporalIVRRecepcionPedidos", null);
		this.getSqlMapClientTemplate().update("LookupSQL.updateProcesosPROL", null);
		//boorado de la tabla temporal de control asistencia
		this.getSqlMapClientTemplate().update("LookupSQL.deleteControlAsistenciaPER", null);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.LookupDAO#getPoliticasSeguridad()
	 */
	public List getAllPoliticasSeguridadContrasenia() {
		return this.getSqlMapClientTemplate().queryForList("LookupSQL.getAllPoliticasSeguridadContrasenia", null);
	}
}
