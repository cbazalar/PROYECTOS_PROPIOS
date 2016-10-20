package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEUnidadesAdministrativasLineaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */

public interface MantenimientoAPEUnidadesAdministrativasLineaService extends Service {

	/**
	 * Recupera la Lista de Centros de Distribucin por Pas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCodigoCentroDistribucionList(Map criteria);
		
	/**
	 * @param criteria
	 * @return el Cdigo CD por defecto.
	 */
	public String getCodigoCDDefecto(Map criteria);
		
	/**
	 * @param criteria
	 * @return Lista de Linea de Armado
	 */
	public List getLineaArmadoListar(Map criteria);
		
	/**
	 * @param criteria
	 * @return Codigo Linea Armado por Defecto
	 */
	public String getCodLineaArmadaDefecto(Map criteria);
	
	/**
	 * @param criteria
	 * @return OidCentroDistribucionPais
	 */
	public String getOidCentroDistribucionPais(Map criteria);
	
	/**
	 * @param criteria
	 * Realiza la insercion de la Unidad Administrativa por Lnea
	 */
	public void insertUnidadAdministrativaLinea(Map criteria, String[] arrCodRegion, String[] arrCodZona, String[] arrCodSeccion);
	
	/**
	 * @param criteria
	 * @return el OidLineaArmado
	 */
	public String getOidLineaArmado(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valida si existe registro previo en Tabla de Unidad Adm por Linea
	 */
	public String getValidaExisteUadmLineaAPE(Map criteria, String[] arrCodRegion, String[] arrCodZona, String[] arrCodSeccion);
	
	
	/**
	 * @param criteria
	 * @return Listado de Unidades Administrativas por Linea
	 */
	public List getUnidadAdministrativaLineaList(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina el registro en la tabla Unidad Administrativa por Linea
	 */
	public void deleteUnidadAdministrativa(Map criteria,String[] items);
	
	/**
	 * @param criteria
	 * @return el oIdNivelAgrupacionOlas
	 */
	public String getOidNivelAgrupacionOlas(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Nivel Olas por OidCentroDistribucin
	 */
	public List getObtenerNivelOlas(Map criteria);
	
}
