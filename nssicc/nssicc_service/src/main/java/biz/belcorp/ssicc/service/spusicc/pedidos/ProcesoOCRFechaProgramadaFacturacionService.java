package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author avillavicencio
 */

public interface ProcesoOCRFechaProgramadaFacturacionService extends Service{

	/**
	 * Devuelve todos los tipos de solicitud
	 * @return
	 */
	public List getTiposSolicitudOcr();

	/**
	 * Actualiza todos los registros de GP2 a GP3 que cumplan con las condiciones ingresadas
	 * @param parametros
	 */
	public void updateFechaProgramada(Map parametros);

	/**
	 * Actualiza la fecha en base a la accion configurada del tipo solictud pais
	 * @return
	 */

	public void getExeProcFech(Map parametros);
}