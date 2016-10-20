package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Evaluar
 * Ingresos Efectivos de la Seccion al Cierre de Region
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO")
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO{

	
	/**
	 * @param criteria
	 * @return
	 */
	public List getTiposEvaluacion(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.ProcesosLIDSQL.getTiposEvaluacion", criteria);
	}
	
    /**
     * @param params
     * @return
     */
    public boolean verificaRegionProcesada(Map params) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.lideres.ProcesosLIDSQL.getRegionProcesada2daRecomendacion", params);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarIngresosEfectivosSeccionCierreRegionDAO#executeEvaluarIngresosEfectivosSeccionCierreRegion(java.util.Map)
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierreRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarIngresosEfectivosSeccionCierreRegion",params);
		
	}

}


