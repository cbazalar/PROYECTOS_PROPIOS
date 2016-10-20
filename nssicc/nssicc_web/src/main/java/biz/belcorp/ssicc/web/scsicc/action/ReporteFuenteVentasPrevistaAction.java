package biz.belcorp.ssicc.web.scsicc.action;


import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.bas.MantenimientoBASParametroPaisService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFuenteVentasPrevistaForm;

@ManagedBean
@SessionScoped
public class ReporteFuenteVentasPrevistaAction extends
		BaseReporteAbstractAction {

	
	private static final long serialVersionUID = 8487882952201363273L;
	private String indTipoFvp;
	
	private String tipoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFuenteVentasPrevistaForm reporteForm = new ReporteFuenteVentasPrevistaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF=false;
		//ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteFuenteVentasPrevistaForm f = (ReporteFuenteVentasPrevistaForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
	   f.setTipoReporte("zona");
		
		
		MantenimientoBASParametroPaisService serviceBaspp = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
		BASParametroPais baspp = new BASParametroPais();
		baspp.setCodigoPais(pais.getCodigo());
		baspp.setCodigoSistema(Constants.FVP_CODIGO_SISTEMA);
		baspp.setCodigoParametro(Constants.FVP_CODIGO_PARAMETRO_IND_TIPO_CARGA_FUENTE_VENTAS);
		List list1 = serviceBaspp.getParametroPais(baspp);
		String indTipoFvp = null;
		
		if(list1 != null && list1.size() > 0){
			BASParametroPais baspp1 = (BASParametroPais) list1.get(0);
			indTipoFvp = baspp1.getValorParametro();
		}
		this.indTipoFvp = indTipoFvp;
		
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (StringUtils.equals(formatoExportacion,"XLS")) {
			if( StringUtils.equalsIgnoreCase(this.tipoReporte, "seccion") )
				return "reporteSABCargarFuenteVentaPrevistaSeccionXLS";
			else
				return "reporteSABCargarFuenteVentaPrevistaZonaXLS";
		}
		return null;
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteFuenteVentasPrevistaForm f= (ReporteFuenteVentasPrevistaForm) this.formReporte;		
		f.setFormatoExportacion(this.formatoExportacion);
		this.tipoReporte = f.getTipoReporte();

		String codInicio = StringUtils.EMPTY;
		String codFin = StringUtils.EMPTY;	
		codInicio = f.getPeriodoInicio();
		codFin =f.getPeriodoFin();	

		params.put("periodoInicio", codInicio);
		params.put("periodoFin", codFin);		
		
		if(StringUtils.equalsIgnoreCase(tipoReporte, "seccion"))
			params.put("titulo", this.getResourceMessage("procesoSABCargarFuenteVentaPrevistaForm.titulo.seccion"));
		else
			params.put("titulo", this.getResourceMessage("procesoSABCargarFuenteVentaPrevistaForm.titulo.zona"));
		
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteFuenteVentasPrevistaForm f = (ReporteFuenteVentasPrevistaForm) this.formReporte;
		int codperini = Integer.parseInt(f.getPeriodoInicio());
		int codperfin = Integer.parseInt(f.getPeriodoFin());
		if (codperfin < codperini) {
			String mensaje = "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}
		
		return null;

	}

	
	
	/* GET - SET */
	
	/**
	 * @return the indTipoFvp
	 */
	public String getIndTipoFvp() {
		return indTipoFvp;
	}

	/**
	 * @param indTipoFvp the indTipoFvp to set
	 */
	public void setIndTipoFvp(String indTipoFvp) {
		this.indTipoFvp = indTipoFvp;
	}

	
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
	

}
