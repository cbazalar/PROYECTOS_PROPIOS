package biz.belcorp.ssicc.service.spusicc.let;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface MantenimientoLETBonosCampaniaService extends Service{

	/**
	 * Obtiene la lista de Bonos
	 * @param criteria
	 * @return
	 */
	public List getBonoCampaniaList(Map criteria);

	/**
	 * Valida que un Concurso y Campaa no se repitan el Mantenimiento Bonos por Campaa
	 * @param criteria
	 * @return
	 */
	public int getValidaBonoCampaniaExiste(Map criteria);

	/**
	 * Mtodo que elimina Bono por Campaa de acuerdo al codigo concurso y codigo de periodo de despacho
	 * @param criteria
	 */
	public void deleteBonoCampania(Map criteria);

	/**
	 *  Mtodo que inserta Bono por Campaa
	 * @param criteria
	 */
	public void insertBonoCampania(Map criteria);
}