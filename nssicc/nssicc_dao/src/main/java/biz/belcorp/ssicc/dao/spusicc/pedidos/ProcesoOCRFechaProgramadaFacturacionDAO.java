package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author avillavicencio
 */

public interface ProcesoOCRFechaProgramadaFacturacionDAO extends DAO{

	/**
	 * Devuelve todos los tipos de solicitud
	 * @return List
	 */
	public List getTiposSolicitudOcr();

	/**
	 * @param periodo
	 * @param tipoSolicitud
	 * @param fecha
	 * @param grupoProceso
	 * @return
	 */
	public Integer getRegistroByPeriodoTipoSoliFecha(String periodo,String tipoSolicitud, String fecha, String grupoProceso);

	/**
	 * Actualiza todos los registros que cumplan con las condiciones ingresadas
	 * @param parametros
	 */
	public void updateFechaProgramada(Map parametros);
	
	/**
	 * @param tipoSolicitud
	 * @return
	 */
	public String getNomMetodoProcFecha(Map parametros);

}