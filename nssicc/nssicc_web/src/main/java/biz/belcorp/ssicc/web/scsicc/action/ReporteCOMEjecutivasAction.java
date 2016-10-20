package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMEjecutivasForm;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMEjecutivasAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1717131371136554160L;
	private List comTramoList;
	private String formatoExportacion;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMEjecutivasForm reporteForm = new ReporteCOMEjecutivasForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoExportacion = ((ReporteCOMEjecutivasForm)this.formReporte).getFormatoExportacion();
		if (StringUtils.equals(formatoExportacion, "XLS"))
			return "reporteCOMEjecutivasXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteCOMEjecutivasXLS";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMEjecutivasAction.prepareParameterMap' method");
		}
		
		ProcesoCOMCalculoCalificacionTramoService service = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOMEjecutivasForm f = (ReporteCOMEjecutivasForm) this.formReporte;
		
		this.formatoExportacion = f.getFormatoExportacion();	
		String periodoInicial="";
		String periodoFinal="";
		String anho="";
		String tramo="";
		
		Map criteria = new HashMap();
		
		criteria.put("codigoTramo",f.getNumeroTramo());	
		criteria.put("codigoPais",pais.getCodigo());
		
		Base base = (Base) service.getCampanasRango(criteria).get(0);
		String campIni = base.getCodigo();
		String campFin = base.getDescripcion();
				
		if(StringUtils.isNotBlank(campIni) &&StringUtils.isNotBlank(campFin)){
			
			periodoInicial = f.getAnhoInicial()+campIni;
	
			if (Integer.parseInt(campIni) <= Integer.parseInt(campFin)) 		
				periodoFinal = f.getAnhoInicial()+campFin;		
			else					
				periodoFinal = (Integer.parseInt(f.getAnhoInicial())+1)+ campFin;						
			
			if (f.getNumeroTramo().equals(Constants.PRIMER_TRAMO))
				tramo=Constants.ULTIMO_TRAMO;
			else
				tramo = "0"+(Integer.parseInt(f.getNumeroTramo())-1);		
			
			if(f.getNumeroTramo().equals(Constants.PRIMER_TRAMO))
				anho=(Integer.parseInt(f.getAnhoInicial())-1)+"";
			else
				anho=f.getAnhoInicial();
		}	
		
		params.put("periodoInicial",periodoInicial);
		params.put("periodoFinal",periodoFinal);
		params.put("anho",anho);
		params.put("tramo",tramo);
		
		return params;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteCOMEjecutivasForm f = (ReporteCOMEjecutivasForm) this.formReporte;
		
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getDescripcion());
		setComTramoList(tramoService.getTramos(pais.getCodigo()));
		
	}

	
	
	public List getComTramoList() {
		return comTramoList;
	}

	public void setComTramoList(List comTramoList) {
		this.comTramoList = comTramoList;
	}

	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}
	
	
}
