/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucion;

public interface MantenimientoRECMovitoDevolucionDAO extends DAO {

	List getMovitoDevolucionList(Map criteria);

	MotivoDevolucion getMovitoDevolucion(String id);

	void insertMotivoDevolucion(MotivoDevolucion motivo);

	void updateMotivoDevolucion(MotivoDevolucion motivo);

	void deleteMotivoDevolucion(String id);

	
	Integer getNextOidMovitoDevolucion();
	
	void insertMovitoDescripcion(MotivoDevolucion motivo);
	
	void updateMotivoDevolucionIndicador(MotivoDevolucion motivo);
	
}
