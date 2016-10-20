/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente;

public interface MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO extends DAO {

	List getRegionZonaBoletaRecojoInteligenteList(Map criteria);

	void insertRegionZonaBoletaRecojoInteligente(RegionZonaBoletaRecojoInteligente motivo, Usuario usuario);

	void deleteRegionZonaBoletaRecojoInteligente(Map criteria);
	
	String getExisteRegionZonaBoletaRecojoInteligenteByCriteria(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente);
	
	void updateRegionZonaBoletaRecojoInteligente(Map criteria);
	
	List getRegionByZonaList(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente);
}
