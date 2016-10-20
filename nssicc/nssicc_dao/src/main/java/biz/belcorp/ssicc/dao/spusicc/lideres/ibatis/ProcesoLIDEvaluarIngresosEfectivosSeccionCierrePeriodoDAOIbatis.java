package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO;

/**
 * Implementacion del DAO que ejecutara los metodos del proceso de Evaluar
 * Ingresos Efectivos de la Seccion al Cierre de Periodo
 * <p>
 * <a href="ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.procesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO")
public class ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO {
    
    /**
     * @param params
     * @return
     */
    public boolean verificaCampanaProcesada(Map params) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.lideres.ProcesosLIDSQL.getCampanaProcesada2daRecomendacion", params);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.dao.ProcesoLIDEvaluarIngresosEfectivosSeccionCierrePeriodoDAO#executeEvaluarIngresosEfectivosSeccionCierrePeriodo(java.util.Map)
	 */
	public void executeEvaluarIngresosEfectivosSeccionCierrePeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeEvaluarIngresosEfectivosSeccionCierrePeriodo",params);
		
	}

}


