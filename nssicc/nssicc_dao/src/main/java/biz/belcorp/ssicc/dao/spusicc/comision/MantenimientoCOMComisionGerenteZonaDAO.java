package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ComisionPeriodoGerenteZona;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
public interface MantenimientoCOMComisionGerenteZonaDAO extends DAO {
	
public ComisionPeriodoGerenteZona getComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean);
	
	public List getComisionPeriodoGerenteZonaByCriteria(Map criteria); 
	
	public List getComisionPeriodoLideresByCriteria(Map criteria);
	
	public Integer getDevuelveIDComision(Map criteria);
	
	/**
	 * Devuelve indicadores de Comision
	 * @param criteria
	 * @return
	 */
	public Map getIndicadoresComision(Map criteria);
	
	/**
	 * Devuelve Listado en el Mantenimiento de Comisión Gerente de Zona 
	 * @param criteria
	 * @return
	 */
	public List getMantenimientoComisionPeriodoGerenteZonaByCriteria(Map criteria);

	public ParametroTramoComision getParametrosTramoComision(Map criteria);
	
	public void updateComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean, Usuario usuario);
	
	public List getComisionPeriodoGerenteZonaEscalonadaByCriteria(Map criteria);

	public List getComisionPeriodoGerenteRegion(Map criteria);

	public List getComisionVal(Map criteria);

	public List getComisionRegionList(Map criteria);

	public List getComGerenteZonaList(Map criteria);

	public List getComArchivoNominaList(Map criteria);
	
	/**
	 *  Obtiene lista de Comisiones de Gerente de Region Objetivo Venta en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getComisionGerenteRegionObjetivo(Map criteria);

	
	/**
	 * Obtiene lista de Comisiones Retail en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getComisionRetail(Map criteria);
	
	
	/**
	 * Obtiene comisiones en base al Arreglo de codigos de Base
	 * @param criteria
	 * @return
	 */
	public List getComisionBase(Map criteria);
}
