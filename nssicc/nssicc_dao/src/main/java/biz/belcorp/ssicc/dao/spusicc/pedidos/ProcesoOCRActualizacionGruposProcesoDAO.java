package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoOCRActualizacionGruposProcesoDAO extends DAO{

	/**
	 * Devuelve todos los tipos de solicitud
	 * @return List
	 */
	public List getTiposSolicitud();

	/**
	 * Devuelve la cantidad de registros GP2 seleccionados que cumplen con las condiciones ingresadas
	 * @param periodo
	 * @param tipoSolicitud
	 * @param fecha
	 * @param fechaFin 
	 * @return Integer
	 */
	public Integer getSolicitudesGP2ByPeriodoTipoSoliFecha(String periodo,ArrayList tipoSolicitud, String fecha, String fechaFin);

	/**
	 * Actualiza todos los registros de GP2 a GP3 que cumplan con las condiciones ingresadas
	 * @param parametros
	 */
	public void updateGrupoProceso(Map parametros);

}