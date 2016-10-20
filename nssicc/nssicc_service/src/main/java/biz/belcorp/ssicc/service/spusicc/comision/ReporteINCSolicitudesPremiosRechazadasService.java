/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ComisionPeriodoGerenteZona;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
public interface ReporteINCSolicitudesPremiosRechazadasService extends Service {
	
	/**
     * Obtiene la información correspondiente a una Comision de Gerente de Zona 
     * @param bean
     * @return
     */
    public ComisionPeriodoGerenteZona getComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean);
	
	/**
	 * Obtiene una lista de Comisiones de Gerente de Zona en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getComisionPeriodoGerenteZonaByCriteria(Map criteria);
	
	/**
	 * Obtiene una lista de Comisiones de Lideres en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getComisionPeriodoLideresByCriteria(Map criteria);
	
	/**
	 * Devuelve ultimo OID de acuerdo al codigo de comision Ingresado
	 * @param criteria
	 * @return
	 */
	public Integer getDevuelveIDComision(Map criteria);
	
	
	/**
	 * Devuelve indicadores de Comision
	 * @param criteria
	 * @return
	 */
	public Map getIndicadoresComision(Map criteria);
	
	
	/**
	 * Devuelve Parametros del tramo de acuerdo a la comision y nro de tramo ingresadas
	 * @param criteria
	 * @return
	 */
	public ParametroTramoComision getParametrosTramoComision(Map criteria);
	
	
	/**
	 * Actualiza registro correspondiente a Comision de Gerente de Zona 
	 * @param bean
	 * @param usuario
	 */
	public void updateComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean, Usuario usuario);
	
	/**
	 * Obtiene una lista de Comisiones de Gerente de Zona en base a los filtros del map 
	 * @param criteria
	 * @return
	 */
	public List getComisionPeriodoGerenteZonaEscalonadaByCriteria(Map criteria);

	public List getComisionPeriodoGerenteRegion(Map criteria);

	public List getComisionVal(Map criteria);

	public List getComisionRegionList(Map criteria);

	public List getComGerenteZonaList(Map criteria);

	public List getComArchivoNominaList(Map criteria);
	
	public List getComisionGerenteRegionObjetivo(Map criteria);

}
