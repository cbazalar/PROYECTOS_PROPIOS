package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.spusicc.comision.form.ReporteCOMComisionComercializacionForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>                                                
 */
@ManagedBean
@SessionScoped
public class ReporteCOMComisionComercializacionAction extends BaseReporteAbstractAction {
		
	private static final long serialVersionUID = -958011093372879581L;
	
	private List listaTipoComision;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteCOMComisionComercializacionForm reporteForm = new ReporteCOMComisionComercializacionForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {	
		return "reporteMaestroVertical";		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		return "reporteCOMComisionComercializacion";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteCOMComisionComercializacionAction.setViewAtributes' method");            
        }       
		//Seteo de valores por default de nuevos registros
		ReporteCOMComisionComercializacionForm reporteForm = (ReporteCOMComisionComercializacionForm)this.formReporte;
		reporteForm.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		//Seteo de tipo de comisiones
		ArrayList<Base> resultadoTipo = new ArrayList<Base>();
		Base[] mesTipo = new Base[2];		
		String[] presentacionesTipo = { 
				"Promotora",
				"Gerente de Zona"
				};
		for (int i = 0; i < 2; i++) {
			mesTipo[i] = new Base();
			mesTipo[i].setCodigo("" + (i + 1));
			mesTipo[i].setDescripcion(presentacionesTipo[i]);
			resultadoTipo.add(mesTipo[i]);
		}
		this.listaTipoComision = resultadoTipo;		
		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionComercializacionAction.prepareParameterMap' method");
		}
		try{
			ReporteCOMComisionComercializacionForm reporteForm = (ReporteCOMComisionComercializacionForm)this.formReporte;
		
			reporteForm.setTitulo(this.getReportResourceMessage("reporteCOMComisionComercializacionForm.titulo"));
			reporteForm.setNroReporte("");
			
			String tipoComision = reporteForm.getTipoComision();
			
			if (tipoComision.equals("1")) 
				tipoComision = "P";
			else 
				tipoComision = "G";
			
			reporteForm.setTipoComision(tipoComision);
			
			params.put("NroReporte", " ");
			params.put("titulo", this.getReportResourceMessage("reporteCOMComisionComercializacionForm.titulo"));
			params.put("tipoComision", tipoComision);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		
		ReporteCOMComisionComercializacionForm form = (ReporteCOMComisionComercializacionForm)this.formReporte;
		
		if(form.getFechaDesde()!=null && form.getFechaHasta()!=null){
			if(form.getFechaDesde().compareTo(form.getFechaHasta())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		return null;
	}
	
	/**
	 * @return the listaTipoComision
	 */
	public List getListaTipoComision() {
		return listaTipoComision;
	}

	/**
	 * @param listaTipoComision the listaTipoComision to set
	 */
	public void setListaTipoComision(List listaTipoComision) {
		this.listaTipoComision = listaTipoComision;
	}	
}
