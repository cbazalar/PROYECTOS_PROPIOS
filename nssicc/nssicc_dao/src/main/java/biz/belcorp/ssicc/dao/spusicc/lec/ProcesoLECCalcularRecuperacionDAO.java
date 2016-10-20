package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * <p>
 * <a href="ProcesoLECCalcularRecuperacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Juan Altamirano
 *         
 */
public interface ProcesoLECCalcularRecuperacionDAO extends DAO{
	
	/**
	 * Mtodo que permite calcular masivamente el Nivel de xito de las Lderes.
	 * @param params
	 */
	public void executeProcesoLECCalcularRecuperacion(Map params);	
	
	/**
	 * Devuelve Codigo de Periodo en base a la cantidad de campaas
	 * @param params
	 * @return
	 */
	public String getDevuelvePeriodoByCodigoPeriodo(Map params) ;
	
	/**
	 * Devuelve Indicador Campaa Recaudo para Programa LEC
	 * @param params
	 * @return
	 */
	public String getIndicadorCampannaRecaudo(Map params) ;
	
	/**
	 * Mtodo que permite calcular masivamente el Nivel de xito de las Lderes.
	 * @param params
	 */
	public void executeProcesoLECCalcularRecuperacionNuevo(Map params);	

}
