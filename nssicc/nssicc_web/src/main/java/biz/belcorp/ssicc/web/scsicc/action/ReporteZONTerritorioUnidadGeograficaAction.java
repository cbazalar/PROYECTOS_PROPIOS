package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;



import biz.belcorp.ssicc.dao.spusicc.zon.model.EstructuraGeopolitica;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONUnidadGeograficaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteZONTerritorioUnidadGeograficaForm;

@ManagedBean
@SessionScoped
public class ReporteZONTerritorioUnidadGeograficaAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197020064797547385L;
	private String formatoExportacion;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteZONTerritorioUnidadGeograficaForm form = new ReporteZONTerritorioUnidadGeograficaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteZONTerritorioUnidadGeograficaCSV";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteZONTerritorioUnidadGeograficaForm reporteForm = (ReporteZONTerritorioUnidadGeograficaForm) formReporte;
		this.formatoExportacion = reporteForm.getFormatoExportacion();
		//MessageResources messageResources = this.getResources(request);
		String titulo = this.getResourceMessage("reporteZONTerritorioUnidadGeograficaForm.titulo");
		params.put("NroReporte", " ");
		MantenimientoZONUnidadGeograficaService mantenimientoZONUnidadGeograficaService = (MantenimientoZONUnidadGeograficaService) getBean("spusicc.mantenimientoZONUnidadGeograficaService");
		//Obtenemos la estructura geopolitica
		List estructuraGeo =  mantenimientoZONUnidadGeograficaService.getEstructuraGeopoliticaList();
		
		StringBuffer tituloReporteOracle = new StringBuffer();
		if(estructuraGeo != null && estructuraGeo.size() > 0)
		{
			for(int i=0; i<estructuraGeo.size(); i++)
			{
				EstructuraGeopolitica eg = (EstructuraGeopolitica)estructuraGeo.get(i);
				tituloReporteOracle.append(eg.getDescripcion()).append(",");
			}			
			
			params.put("titulo", titulo + StringUtils.substring(tituloReporteOracle.toString(), 0, tituloReporteOracle.toString().length() - 1));
		}
		
	//super.prepareParameterMap(params, form, request);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReportePDF=false;
		this.mostrarReporteOCSV=true;
		
	}
	protected String devuelveBeanReporteService(){
		return "reportes.reporteZONTerritorioUnidadGeograficaService";
				
	}
	
	
	
}