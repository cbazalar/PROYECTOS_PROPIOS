package biz.belcorp.ssicc.service.spusicc.lideres.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDFactorPuntajeDAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.FactorPuntaje;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDFactorPuntajeService;


@Service("spusicc.mantenimientoLIDFactorPuntajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLIDFactorPuntajeServiceImpl extends BaseService implements MantenimientoLIDFactorPuntajeService {
	
	@Resource(name="spusicc.mantenimientoLIDFactorPuntajeDAO")
	MantenimientoLIDFactorPuntajeDAO mantenimientoLIDFactorPuntajeDAO; 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDFactorPuntajeService#getFactorPuntajeList(java.util.Map)
	 */
	public List getFactorPuntajeList(Map criteria) {
		boolean locate = false;
		List _incrementoVariableVentaList=new ArrayList();
		
		criteria.put("valObservacion", Constants.VAL_OBSE_PROGRAMA_LIDERES);
		criteria.put("indicadorActividad",Constants.IND_ACTI_UNO);
		String periodoDesde = mantenimientoLIDFactorPuntajeDAO.getPeridoDesde(criteria);
		String periodoHasta = mantenimientoLIDFactorPuntajeDAO.getPeridoHasta(criteria);
		log.debug("- periodoDesde  : " +periodoDesde);
		log.debug("- periodoHasta  : " +periodoHasta);
		criteria.put("periodoHasta",periodoHasta);
		criteria.put("periodoDesde",periodoDesde);
		
		List factorPuntajeList = mantenimientoLIDFactorPuntajeDAO.getFactorPuntajeList(criteria);
	    String periodoProceso = (String) criteria.get("periodoProceso");
		String periodoMaximo=(String) criteria.get("periodoHasta");
		String codProcesoAux;
		int anioProceso =Integer.parseInt(periodoDesde.substring(0,4));
		int perProceso= Integer.parseInt(periodoDesde.substring(4));
		int anioMaximo=Integer.parseInt(periodoMaximo.substring(0,4));
		int perMaximo=Integer.parseInt(periodoMaximo.substring(4));
		log.debug("valores de la grilla : "+ anioProceso+" - "+perProceso +" - "+anioMaximo+" - "+perMaximo );
		log.debug("tamao de la lista   : " + factorPuntajeList.size());
		int auxPerMax = (anioProceso<anioMaximo?Constants.NUMERO_CAMPANYAS:perMaximo);
		while (anioProceso<=anioMaximo) {
		
		  while(perProceso<=auxPerMax){
			locate = false;
			if(perProceso<10){
				codProcesoAux= anioProceso+"0"+perProceso;
			}else{
				codProcesoAux= ""+anioProceso+perProceso;
			}
			log.debug("- anioProceso : " + codProcesoAux);
			for (Iterator iter = factorPuntajeList.iterator(); iter.hasNext();) {
				FactorPuntaje factorPuntaje = (FactorPuntaje) iter.next();
				if(factorPuntaje.getCodPeriodo().equals(codProcesoAux)){
					locate=true;
					if(factorPuntaje.getValorFactorMultiplicador()==null){
						log.debug(" - Seteando...");
						factorPuntaje.setValorFactorMultiplicador(Constants.NUMERO_CERO);
					}
					if(Integer.parseInt(periodoProceso)>Integer.parseInt(codProcesoAux)){//solo sera no modificado
						//cuando campanha es menor al periodo
						factorPuntaje.setFlag(Constants.FLAG_DEFAULT);
					}	
					_incrementoVariableVentaList.add(factorPuntaje);
				}
				
			}
			if(!locate){
				log.debug("nuevos registros : ");
				FactorPuntaje factorPuntajeNew = new FactorPuntaje();
				factorPuntajeNew.setCodPeriodo(codProcesoAux);
				factorPuntajeNew.setValorFactorMultiplicador(Constants.NUMERO_CERO);
				log.debug(" - Seteando...");
				if(Integer.parseInt(periodoProceso)>Integer.parseInt(codProcesoAux)){
					factorPuntajeNew.setFlag(Constants.FLAG_DEFAULT);
				}	
				_incrementoVariableVentaList.add(factorPuntajeNew);
				
			}
//			if(perProceso>=Constants.NUMERO_CAMPANYAS){
//				anioProceso++;
//				perProceso = 0;
//			}
			perProceso++;
		  }		
		  anioProceso++;
		  auxPerMax = (anioProceso<anioMaximo?Constants.NUMERO_CAMPANYAS:perMaximo);
		  perProceso =1;
		}
		
		
		log.debug("tamao de la lista a retornar : " + _incrementoVariableVentaList.size());	
		return _incrementoVariableVentaList;
	}

	/**
	 * @return Returns the mantenimientoLIDFactorPuntajeDAO.
	 */
	public MantenimientoLIDFactorPuntajeDAO getMantenimientoLIDFactorPuntajeDAO() {
		return mantenimientoLIDFactorPuntajeDAO;
	}




	/**
	 * @param mantenimientoLIDFactorPuntajeDAO The mantenimientoLIDFactorPuntajeDAO to set.
	 */
	public void setMantenimientoLIDFactorPuntajeDAO(
			MantenimientoLIDFactorPuntajeDAO mantenimientoLIDFactorPuntajeDAO) {
		this.mantenimientoLIDFactorPuntajeDAO = mantenimientoLIDFactorPuntajeDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDFactorPuntajeService#getTipoAsignacionPuntajeList()
	 */
	public List getTipoAsignacionPuntajeList() {
		return mantenimientoLIDFactorPuntajeDAO.getTipoAsignacionList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDFactorPuntajeService#updateFactorPuntaje(biz.belcorp.ssicc.spusicc.lideres.model.FactorPuntaje)
	 */
	public void updateFactorPuntaje(FactorPuntaje factorPuntaje) {
		mantenimientoLIDFactorPuntajeDAO.updateFactorPuntaje(factorPuntaje);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDFactorPuntajeService#saveFactorPuntaje(biz.belcorp.ssicc.spusicc.lideres.model.FactorPuntaje)
	 */
	public void saveFactorPuntaje(FactorPuntaje factorPuntaje) {
		mantenimientoLIDFactorPuntajeDAO.saveFactorPuntaje(factorPuntaje);
		
	}

	
}
