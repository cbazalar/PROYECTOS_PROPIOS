package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERPercepcionesOtrosCanalesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaPERConsolidadoPercepcionesAcumuladoForm;

@ManagedBean
@SessionScoped
public class ConsultaPERConsolidadoPercepcionesAcumuladoAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036800243651644658L;
	private List siccMesesList;
	private List consolidadoPercepcionesAcumuladoList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaPERConsolidadoPercepcionesAcumuladoForm form = new ConsultaPERConsolidadoPercepcionesAcumuladoForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		ConsultaPERConsolidadoPercepcionesAcumuladoForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoForm) this.formReporte;
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("anho", f.getAnho());
		criteria.put("mes", f.getMes());
		criteria.put("codigoPaisLbel", pais.getCodigo().substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);
		
		List resultado = service.getConsolidadoPercepcionesAcumulado(criteria);
		log.debug("Cantidad de resultado " + resultado.size());
		this.consolidadoPercepcionesAcumuladoList=resultado;
		
		return resultado;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
	

		Base[] mes = new Base[12];
		ArrayList resultado = new ArrayList();
		for (int i = 0; i < 12; i++) {
			mes[i] = new Base();
			String previo = StringUtils.leftPad("" + (i + 1), 2, "0");
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
			Calendar c = Calendar.getInstance();
			c.set(2004, i, 1);
			String fecha = sdf.format(c.getTime());
			mes[i].setCodigo(previo);
			mes[i].setDescripcion(StringUtils.capitalize(fecha));
			resultado.add(mes[i]);
		}
		this.siccMesesList = resultado;
	
	}

	

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/**
	 * @return the siccMesesList
	 */
	public List getSiccMesesList() {
		return siccMesesList;
	}

	/**
	 * @param siccMesesList the siccMesesList to set
	 */
	public void setSiccMesesList(List siccMesesList) {
		this.siccMesesList = siccMesesList;
	}

	/**
	 * @return the consolidadoPercepcionesAcumuladoList
	 */
	public List getConsolidadoPercepcionesAcumuladoList() {
		return consolidadoPercepcionesAcumuladoList;
	}

	/**
	 * @param consolidadoPercepcionesAcumuladoList the consolidadoPercepcionesAcumuladoList to set
	 */
	public void setConsolidadoPercepcionesAcumuladoList(
			List consolidadoPercepcionesAcumuladoList) {
		this.consolidadoPercepcionesAcumuladoList = consolidadoPercepcionesAcumuladoList;
	}
	

}
