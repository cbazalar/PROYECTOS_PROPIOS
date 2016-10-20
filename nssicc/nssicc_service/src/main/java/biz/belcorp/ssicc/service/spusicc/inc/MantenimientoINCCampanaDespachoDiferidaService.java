package biz.belcorp.ssicc.service.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCCampanaDespachoDiferidaService  extends Service{

	/**
	 * Retorna la lista de Concurso con Despacho Deferido
	 * 
	 * @return
	 */
	List getListConcursoDespachoDiferido();

	/**
	 * Actualiza un registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	void updateNivelDespachoDiferido(Map criteria);

	/**
	 * Elimina un registro en la entidad Nivel Despacho Diferido
	 * 
	 * @param criteria
	 */
	void deleteNivelDespachoDiferido(Map criteria);
	
}
