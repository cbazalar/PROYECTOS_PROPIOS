package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECalificacionEstatusDAO;

/**
 * Implementacion del DAO que ejecutara los procesos de Calificacion de Estatus
 * <p>
 * <a href="ProcesoMAECalificacionEstatusDAOiBatis"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoMAECalificacionEstatusDAO")
public class ProcesoMAECalificacionEstatusDAOiBatis extends BaseDAOiBatis implements ProcesoMAECalificacionEstatusDAO {

	public boolean verificarCargaInicialEstatus(String codigoPais) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosMAESQL.verificarCargaInicialEstatus", codigoPais);

		if(Integer.parseInt(result)>0)
			return true;
		else
			return false;
	}
	
	public void executeCalificacionEstatusCargaInicial(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeCalificacionEstatusCargaInicial", criteria);
	}

	public void executeCalificacionEstatusFacturacionDiaria(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeCalificacionEstatusFacturacionDiaria", criteria);
	}

	public void executeCalificacionEstatusCierreRegion(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeCalificacionEstatusCierreRegion", criteria);
	}
	
	public void executeCalificacionEstatusCierreCampana(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeCalificacionEstatusCierreCampana", criteria);
	}

	public String getUltimaFechaCierreRegion(Map criteria) {
        return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getUltimaFechaCierreRegion", criteria);
    }	
	
	public List getRegionesCerradas(Map criteria) {
        return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getRegionesCerradas", criteria);
    }	
	
	public List getTipoProcesoList() {
        return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getTipoProcesoList");
	}
	
	public List getClasificacionEstatusList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getClasificacionEstatusList", params);
	}
	
	public void executeProcesoAtendidos(Map params) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoAtendidos", params);
	}
	
	public void deleteClasificacionEstatusList(Map params) {
        getSqlMapClientTemplate().delete("spusicc.ProcesosMAESQL.deleteClasificacionEstatusList", params);
	}
	
}
