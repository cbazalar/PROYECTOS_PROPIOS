/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionBoletasRecojoDiscrepanteDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;


/**
 * @author peextjcairampoma
 *
 */
@Repository("spusicc.mantenimientoRECGestionBoletasRecojoDiscrepanteDAO")
public class MantenimientoRECGestionBoletasRecojoDiscrepanteDAOIbatis extends		BaseDAOiBatis implements MantenimientoRECGestionBoletasRecojoDiscrepanteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoCOMCalificacionComisionDAO#getCalificacionesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoCOMCalificacionComisionSearchForm)
	 */
	public List getBoletasRecojoDiscrepantesList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBoletasRecojoDiscrepantesList",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionBoletasRecojoDiscrepanteDAO#updateBoletaRecojoDiscrepanteCabeceraDetalle(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera, java.util.List, java.util.Map)
	 */
	public void updateBoletaRecojoDiscrepanteCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,	Map detalDiscList) {
		
		boletaRecojoCabecera = (BoletaRecojoCabecera) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getBoletasRecojoCabeceraById", boletaRecojoCabecera);
		
		
		float diferencia = 0;
		
		//Inserta los detalles y actualiza unidades recogidas en la Cabeceras
		for (int i = 0; i < detalList.size(); i++) {
			
			BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)detalList.get(i);
			
			//Si la linea tiene discrepancia Actualiza e Inserta Discrepantes
			if (boletaRecojoDetalle.getIndicadorDiscrepante().equals("1")){
				List detalDiscrepanteList = (List)detalDiscList.get(boletaRecojoDetalle.getCodigoDetalle());
				diferencia = diferencia + Float.parseFloat(boletaRecojoDetalle.getPrecio())*Float.parseFloat(boletaRecojoDetalle.getUnidadesReclamadas());				
				for (int j = 0; j < detalDiscrepanteList.size(); j++) {
					 
					BoletaRecojoDetalle boletaRecojoDetalleDiscrepante = (BoletaRecojoDetalle)detalDiscrepanteList.get(j);
					diferencia = diferencia - boletaRecojoDetalleDiscrepante.getPrecioDiscrepante().floatValue()*Float.parseFloat(boletaRecojoDetalleDiscrepante.getUnidadesRecogidas());;
					getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateBoletaRecojoDiscrepanteDetalle", boletaRecojoDetalleDiscrepante);
					
				}
			}
		}
		//Actualiza el numero de unidades Recogidas de la cabecera
		boletaRecojoCabecera.setDiferencia(new Float(diferencia));
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateBoletaRecojoDiscrepanteCabecera", boletaRecojoCabecera); 
		
	}





	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionBoletasRecojoDiscrepanteDAO#executeProcesoBoletaRecojoDiscrepante(java.util.Map)
	 */
	public void executeProcesoBoletaRecojoDiscrepante(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeProcesoBoletaRecojoDiscrepante",params);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECGestionBoletasRecojoDiscrepanteDAO#getCodigoVentaListByCriteria(java.util.Map)
	 */
	public List getCodigoVentaListByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoVentaListByCriteria",params);
	}



}
