package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucion;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author 
 *
 */
public interface MantenimientoRECMotivoDevolucionService extends Service {

	/**
	 * @param criteria
	 * @return
	 * Devuelve las operaciones de Reclamos
	 */
	public List getMovitoDevolucionList(Map criteria);

	public MotivoDevolucion getMovitoDevolucion(String id);

	public void insertMotivoDevolucion(MotivoDevolucion motivo);

	public void updateMotivoDevolucion(MotivoDevolucion motivo);

	public void deleteMotivoDevolucion(String id);
	
	public void updateMotivoDevolucionIndicador(MotivoDevolucion motivo);

}