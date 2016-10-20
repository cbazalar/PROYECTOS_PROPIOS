package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoSaldosNegativosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCCCDetalladoSaldosNegativosAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -4683832016683415274L;
	private String tipoVista;
	private List siccSociedadList = new ArrayList();
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
    private Boolean estadoDiasAntiguedad;
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCCCDetalladoSaldosNegativosForm reporteCCCForm = (ReporteCCCDetalladoSaldosNegativosForm) this.formReporte;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		reporteCCCForm.setCodigoPais(pais.getCodigo());

		setEstadoDiasAntiguedad(false);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteCCCDetalladoSaldosNegativos" + tipoVista + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoSaldosNegativosForm form = new ReporteCCCDetalladoSaldosNegativosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCDetalladoSaldosNegativosForm reporteCCCForm = (ReporteCCCDetalladoSaldosNegativosForm) this.formReporte;

		this.tipoVista = reporteCCCForm.getTipoVista();

		String condicionZonas = obtieneCondicion(reporteCCCForm.getZonaList(),
				"ZZ.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteCCCForm.getRegionList(), "ZR.COD_REGI", "'");

		String condicion = condicionZonas + condicionRegion;

		params.put("condicion", condicion);

		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
	}
    public void mostrarDiasAntiguedad(ValueChangeEvent val)
    {
    	ReporteCCCDetalladoSaldosNegativosForm form = (ReporteCCCDetalladoSaldosNegativosForm) this.formReporte;
    	String x=(String)val.getNewValue();
    	if(!val.equals(null) && x.length()>0)
    	{
    		if(x.equals("DC")|| x.equals(""))
    		{
    			
    			setEstadoDiasAntiguedad(false);
    		}
    		else
    		{
    			
    			setEstadoDiasAntiguedad(true);
    		}
    		
    	}
    }
	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		ReporteCCCDetalladoSaldosNegativosForm form = (ReporteCCCDetalladoSaldosNegativosForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));
			form.setZonaList(null);
		} else {
			this.siccZonaList = null;
			form.setZonaList(null);
		}
	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
	/**
	 * @param estadoDiasAntiguedad
	 *            the estadoDiasAntiguedad to set
	 */
	public void setEstadoDiasAntiguedad(Boolean estadoDiasAntiguedad) {
		this.estadoDiasAntiguedad = estadoDiasAntiguedad;
	}
	
	/**
	 * @return the estadoDiasAntiguedad
	 */
	public Boolean getEstadoDiasAntiguedad() {
		return estadoDiasAntiguedad;
	}

	
	
}