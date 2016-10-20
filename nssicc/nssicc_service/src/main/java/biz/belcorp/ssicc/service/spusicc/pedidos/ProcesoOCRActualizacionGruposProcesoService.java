package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoOCRActualizacionGruposProcesoService extends Service{

	/**
	 * Devuelve todos los tipos de solicitud
	 * @return
	 */
	public List getTiposSolicitud();

	/**
	 * Actualiza todos los registros de GP2 a GP3 que cumplan con las condiciones ingresadas
	 * @param parametros
	 */
	public void updateGrupoProceso(Map parametros);

}