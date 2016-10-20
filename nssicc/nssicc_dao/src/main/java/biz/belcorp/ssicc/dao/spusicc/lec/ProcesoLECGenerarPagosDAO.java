package biz.belcorp.ssicc.dao.spusicc.lec;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoLECGenerarPagosDAO extends DAO{
	
	public List getTipoPago(Map map);
	
	public List getTipoGanancia(Map map);
	
	public void executeGenerarPagoRegular(Map params);
	
	public void executeGenerarPagoAdicional(Map params);

	public void insertGloblalTemporaryForGrupoUA(Map params);
	
	public int getContadorGloblalTemporaryForGrupoUA();
	
	/**
	 * Devuelve Lista de tipos de Pago
	 * @param params
	 * @return
	 */
	public List getTipoPago02(Map params);
	
	
	/**
	 * Devuelve Lista de Motivos de Bloqueo
	 * @param params
	 * @return
	 */
	public List getTipoMotivoBloqueo(Map params);
}
