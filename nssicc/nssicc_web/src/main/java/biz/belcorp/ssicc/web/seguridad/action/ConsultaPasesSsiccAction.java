/*
 * biz.belcorp.ssicc.web.action.RolSearchAction
 */
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.PasesSsiccService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.seguridad.form.ConsultaPasesSsiccForm;

/**
 * <p>
 * <a href="ConsultaPasesSsiccAction.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@ManagedBean
@SessionScoped
public class ConsultaPasesSsiccAction extends BaseConsultaAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5346507504553380550L;
	protected List listaPaisMarca;
	protected List listaResultadoEjecucion;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaPasesSsiccForm searchForm = new ConsultaPasesSsiccForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		String fechaInicio = null;
		String fechaFinal = null;
		
		ConsultaPasesSsiccForm searchForm = (ConsultaPasesSsiccForm) this.formBusqueda;
		Map criteria = BeanUtils.describe(searchForm);
		
		String tipoSeleccion = searchForm.getTipoSeleccion();
		log.debug("-------->" + tipoSeleccion);
		
		
		if (StringUtils.equals(searchForm.getTipoSeleccion(), Constants.TIPO_PASE_CODIGO)){
			criteria.put("codigoPase",searchForm.getCodigoPase());
			criteria.put("fechaInicio", fechaInicio);
	        criteria.put("fechaFinal", fechaFinal);
        }else if (StringUtils.equals(searchForm.getTipoSeleccion(), Constants.TIPO_PASE_MES)){
        	String sMes= new String();
        	String sUltimoDia = new String();
        	String sAnho = searchForm.getAnho(); 
        	
        	sMes = searchForm.getMes();
        	sUltimoDia = DevuelveUltimoDiaMes(searchForm.getMes(), sAnho);
        	
        	//Se forma las fechas de Inicio y de Fin del mes y ao ingresado
            fechaInicio = "01/" + sMes + "/" + sAnho;
            fechaFinal = sUltimoDia+ "/" + sMes + "/" + sAnho;
            
            criteria.put("fechaInicio", fechaInicio);
            criteria.put("fechaFinal", fechaFinal);
        }
		
		log.debug(searchForm.getPaisMarca());
		if (searchForm.getPaisMarca().equals("T")){
			criteria.put("paisMarca", null);
		}
		
        if (log.isDebugEnabled()) {
            log.debug(criteria.toString());
        }
        
        PasesSsiccService service = (PasesSsiccService) this.getBeanService("pasesSsiccService");
        
        List listaLog = service.getResultadoLogErrores(criteria);
        
        listaResultadoEjecucion = new ArrayList();
        if (StringUtils.equals(searchForm.getTipoSeleccion(), Constants.TIPO_PASE_CODIGO)){
        	this.listaResultadoEjecucion = service.getResultadoEjecucion(criteria);
        }
        
		return listaLog;
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
	
		ConsultaPasesSsiccForm searchForm = (ConsultaPasesSsiccForm) this.formBusqueda;
        searchForm.setTipoSeleccion(Constants.TIPO_PASE_CODIGO);
        searchForm.setAnho(null);
        searchForm.setMes(null);
        searchForm.setCodigoPase(null);
		
		// Carga de los combos
        PasesSsiccService service = (PasesSsiccService) getBeanService("pasesSsiccService");
        this.listaPaisMarca = service.getListaPaisMarca();
        //
           
	}

    /**
     * Devuelve el ultimo dia del mes dado como parametro
     * @param mes
     * @param anho
     * @return
     */
    public String DevuelveUltimoDiaMes(String mes, String anho){
    	String vDia = new String();
    	Integer nAnho = Integer.parseInt(anho);
    
    	switch (Integer.parseInt(mes)){
    		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
    				vDia = "31";
    				break;
    		
    		case 2: if ( nAnho % 4 == 0 ) 
            			vDia = "29";
            		else
            			vDia = "28";
					break;
					
    		case 4: case 6: case 9: case 11:
    				vDia = "30";
					break;
					
    	}
    	
    	return vDia;
    }

	/**
	 * @return the listaPaisMarca
	 */
	public List getListaPaisMarca() {
		return listaPaisMarca;
	}

	/**
	 * @param listaPaisMarca the listaPaisMarca to set
	 */
	public void setListaPaisMarca(List listaPaisMarca) {
		this.listaPaisMarca = listaPaisMarca;
	}

	/**
	 * @return the listaResultadoEjecucion
	 */
	public List getListaResultadoEjecucion() {
		return listaResultadoEjecucion;
	}

	/**
	 * @param listaResultadoEjecucion the listaResultadoEjecucion to set
	 */
	public void setListaResultadoEjecucion(List listaResultadoEjecucion) {
		this.listaResultadoEjecucion = listaResultadoEjecucion;
	}
	
}