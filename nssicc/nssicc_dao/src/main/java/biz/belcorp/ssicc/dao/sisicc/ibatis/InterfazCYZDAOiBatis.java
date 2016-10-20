/*
 * Created on 12-dic-08 16:12:10
 * biz.belcorp.ssicc.sisicc.dao.ibatis.InterfazCYZDAOiBatis
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazCYZDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCYZDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("sisicc.interfazCYZDAO")
public class InterfazCYZDAOiBatis extends BaseDAOiBatis implements
        InterfazCYZDAO {

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeInterfazCYZCargarDespachoProductos(java.util.Map)
     */
    public void executeInterfazCYZCargarDespachoProductos(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeInterfazCYZCargarDespachoProductos", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeInterfazCYZCargarDespachoPremios(java.util.Map)
     */
    public void executeInterfazCYZCargarDespachoPremios(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeInterfazCYZCargarDespachoPremios", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeInterfazCYZEnviarDespachoProductos(java.util.Map)
     */
    public void executeInterfazCYZEnviarDespachoProductos(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeInterfazCYZEnviarDespachoProductos", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarClasificacion(java.util.Map)
     */
    public void executeProcesoCYZActualizarClasificacion(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarClasificacion", params);
    }

    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZAActualizarProductosPrograma(java.util.Map)
	 */
	public void executeProcesoCYZAActualizarProductosPrograma(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZAActualizarProductosPrograma", params);
	}

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZEnviarMensajes(java.util.Map)
     */
    public void executeProcesoCYZEnviarMensajes(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZEnviarMensajes", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#deleteProductosSolicitados(java.util.Map)
     */
    public void deleteProductosSolicitados(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.deleteProductosSolicitados", params);        
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#insertProductoSolicitado(java.util.Map)
     */
    public void insertProductoSolicitado(Map map) {
        getSqlMapClientTemplate().insert(
                "sisicc.InterfazCYZSQL.insertProductoSolicitado", map);        
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarPrimerasDuplas(java.util.Map)
     */
    public void executeProcesoCYZActualizarPrimerasDuplas(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarPrimerasDuplas", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeInterfazCYZCargarDespachoWelcomePack(java.util.Map)
     */
    public void executeInterfazCYZCargarDespachoWelcomePack(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeInterfazCYZCargarDespachoWelcomePack", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarClasificacionWelcomePack(java.util.Map)
     */
    public void executeProcesoCYZActualizarClasificacionWelcomePack(Map params) {
    	// CHR 18/03/2010 - Se modifica la llamada al nuevo paquete para la 
    	// implementacion del nuevo welcome pack vigente a partir de C201005
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarClasificacionNuevoWelcomePack", params);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZEnviarMensajesWelcomePack(java.util.Map)
     */
    public void executeProcesoCYZEnviarMensajesWelcomePack(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZEnviarMensajesWelcomePack", params);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarCumpleanyosDuplas(java.util.Map)
	 */
	public void executeProcesoCYZActualizarCumpleanyosDuplas(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarCumpleanyosDuplas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarClasificacionCumpleanyos(java.util.Map)
	 */
	public void executeProcesoCYZActualizarClasificacionCumpleanyos(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarClasificacionCumpleanyos", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZEnviarMensajesError(java.util.Map)
	 */
	public void executeProcesoCYZEnviarMensajesError(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZEnviarMensajesError", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZCargarBolsaProductosWelcomePack(java.util.Map)
	 */
	public void executeProcesoCYZCargarBolsaProductosWelcomePack(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZCargarBolsaProductosWelcomePack", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZCargarBolsaProductosCumpleanyosDuplas(java.util.Map)
	 */
	public void executeProcesoCYZCargarBolsaProductosCumpleanyosDuplas(
			Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZCargarBolsaProductosCumpleanyosDuplas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZCargarBolsaProductosCumpleanyosConsultoras(java.util.Map)
	 */
	public void executeProcesoCYZCargarBolsaProductosCumpleanyosConsultoras(
			Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZCargarBolsaProductosCumpleanyosConsultoras", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarUnidadesAtendidasBolsa(java.util.Map)
	 */
	public void executeProcesoCYZActualizarUnidadesAtendidasBolsa(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarUnidadesAtendidasBolsa", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarCumpleanyosConsultoras(java.util.Map)
	 */
	public void executeProcesoCYZActualizarCumpleanyosConsultoras(Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarCumpleanyosConsultoras", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#executeProcesoCYZActualizarClasificacionCumpleanyosConsultoras(java.util.Map)
	 */
	public void executeProcesoCYZActualizarClasificacionCumpleanyosConsultoras(
			Map params) {
        getSqlMapClientTemplate().update(
                "sisicc.InterfazCYZSQL.executeProcesoCYZActualizarClasificacionCumpleanyosConsultoras", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#getProgramasListByCriteria(java.util.Map)
	 */
	public List getProgramasListByCriteria(Map criteria) {
		List programas = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazCYZSQL.getProgramasListByCriteria", criteria);
		return programas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazCYZDAO#getProductosProgramaListByCriteria(java.util.Map)
	 */
	public List getProductosProgramaListByCriteria(Map criteria) {
		List productos = getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazCYZSQL.getProductosProgramaListByCriteria", criteria);
		return productos;
	}

}
