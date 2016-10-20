package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACSaldoConsultorasForm;

/**
 * The Class ReporteSACSaldoConsultorasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 08/09/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACSaldoConsultorasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] sACestadosList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACSaldoConsultorasForm form = new ReporteSACSaldoConsultorasForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACSaldoConsultorasForm form = (ReporteSACSaldoConsultorasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		
		if ("XLS".equals(form.getFormatoExportacion())) {			
			valor =  "reporteSACSaldoConsultorasXLS";
		}else{
			valor = "reporteMaestroHorizontal";
		}
		
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACSaldoConsultorasForm form = (ReporteSACSaldoConsultorasForm)this.formReporte;
		log.debug(form.getFormatoExportacion());		
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        ReporteSACSaldoConsultorasForm form = (ReporteSACSaldoConsultorasForm)this.formReporte;
        
        Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()]; 		
			int i = 0;
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}
		
		List listaSACestadosList = reporteService.getListaGenerico("getEstadoSaldoConsultora",criteriaOperacion);
		
		if(listaSACestadosList.size()>0){
			this.sACestadosList = new LabelValue[listaSACestadosList.size()];
			int i = 0;
			for(Object object : listaSACestadosList){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getsACestadosList()[i] = labelValue;
				i++;
			}
		}		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACSaldoConsultorasForm form = (ReporteSACSaldoConsultorasForm)this.formReporte;	
		
		String condicionRegion = this.obtieneCondicion(form.getRegionList(), "g.COD_REGI", "'");
		String condicionZonas = this.obtieneCondicion(form.getZonaList(), "f.COD_ZONA", "'");
		String condicionEstado = this.obtieneCondicion(form.getEstadoList(), "i.ESTA_OID_ESTA_CLIE", "'");
		String condicion = condicionRegion + condicionZonas + condicionEstado;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = params;
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("oidPais", oidPais);

		params.put("condicion", condicion);
		
		String condicionPedidos = obtieneCondicionPedido(form);
		params.put("condicionPedidos", condicionPedidos);
		
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage("reporteSACSaldoConsultorasForm.titulo"));
		return criteria;
	}
	
	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ReporteSACSaldoConsultorasForm form = (ReporteSACSaldoConsultorasForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, 
				valor, 
				Constants.FORMATEAR_TODOS));
	}
	
	/**
	 * Funcion que devuelve la condicion para el numero de pedidos
	 * @param reporteForm
	 * @return
	 */
	protected String obtieneCondicionPedido ( ReporteSACSaldoConsultorasForm reporteForm ){
		String condicionPedidos = new String();
		String condicionResultado = new String ();
		
		if (reporteForm.getNumeroPedidos() == null || reporteForm.getNumeroPedidos().length <= 0){
			condicionResultado = " >= 0 ";
		}else{
			condicionPedidos = " IN (";
			for (int i = 0; i < reporteForm.getNumeroPedidos().length; i++) {
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "1"))
					condicionPedidos = condicionPedidos + " 1" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "2"))
					condicionPedidos = condicionPedidos + " 2" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "3"))
					condicionPedidos = condicionPedidos + " 3" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "4"))
					condicionPedidos = condicionPedidos + " 4" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "5"))
					condicionPedidos = condicionPedidos + " 5" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "6"))
					condicionPedidos = condicionPedidos + " 6" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "7"))
					condicionPedidos = condicionPedidos + " 7" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "8"))
					condicionPedidos = condicionPedidos + " 8" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "9"))
					condicionPedidos = condicionPedidos + " 9" + ",";
				if (StringUtils.equals(reporteForm.getNumeroPedidos()[i], "10"))
					condicionPedidos = condicionPedidos + " 10" + ",";				
			}
			condicionResultado = condicionPedidos.substring(1, condicionPedidos.length() - 1) ;
			condicionResultado = condicionResultado + " )";
		}
		
		return condicionResultado;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getsACestadosList() {
		return sACestadosList;
	}

	public void setsACestadosList(LabelValue[] sACestadosList) {
		this.sACestadosList = sACestadosList;
	}	
}
