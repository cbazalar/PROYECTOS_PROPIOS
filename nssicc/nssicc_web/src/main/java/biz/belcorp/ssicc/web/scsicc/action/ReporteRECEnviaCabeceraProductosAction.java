package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECEnviaProductosForm;



@ManagedBean
@SessionScoped
public class ReporteRECEnviaCabeceraProductosAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private Map mapProperties;
 
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteRECEnviaProductosForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	
	   return "reporteRECCabeceraEnvioReclamosXLS";
	}
	
	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECEnviaProductosService";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

	   return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteRECEnviaProductosForm f = (ReporteRECEnviaProductosForm) this.formReporte;
		f.setFormatoExportacion("XLS");
		
		params = getMapProperties();

        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
                
        GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_MUESTRA_STOCK_FISICO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List parametros = genericoService.getParametrosPais(parametroPais);
        
		String parametroMuestraStockFisico = "";
		
		if(parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais)parametros.get(0);
			parametroMuestraStockFisico = p.getValorParametro();
		}
        
		params.put("parametroMuestraStockFisico", parametroMuestraStockFisico);
		
        // Agrego los parametros necesarios
        params.put("usuario", usuario);
        params.put("codigoPais", pais.getCodigo());

        // Remuevo los parametros que no son necesarios
      /*  params.remove("resultValueMap");
        params.remove("validatorResults");
        params.remove("servletWrapper");
        params.remove("multipartRequestHandler");
        params.remove("class");
        params.remove("page");*/
        //params.put("selectedItems", f.getSelectedItems());
        MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");

       //Se obtiene el numero de secuencia de la sesion para asignarlo al usuario
        String numSecUsuario = operacionService.getNumeroSecuenciaUsuario();
      
        //Guardamos el numero de secuencia en el map
        params.put("numeroSecuencia", numSecUsuario);
        
	    // Insertamos en la tabla temporal las lineas de reclamos tal como lo haciamos al traer la lista
        operacionService.insertRECProductosList(params);

	    params.put("numSecUsu", numSecUsuario);
	    
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
	
	}
	
	

	/**
	 * @return the mapProperties
	 */
	public Map getMapProperties() {
		return mapProperties;
	}

	/**
	 * @param mapProperties the mapProperties to set
	 */
	public void setMapProperties(Map mapProperties) {
		this.mapProperties = mapProperties;
	}

	
	
	

}