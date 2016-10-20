/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOBHistoricoOperacionCrediticiaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author fochoa
 *
 */

@ManagedBean
@SessionScoped
public class ReporteCOBHistoricoOperacionCrediticiaAction extends BaseReporteAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4422951928722611950L;
	
	private List siccMesesList = new ArrayList();

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBHistoricoOperacionCrediticiaForm report = new ReporteCOBHistoricoOperacionCrediticiaForm();
		return report;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug("ReporteCOBHistoricoOperacionCrediticiaAction - prepareParameterMap");
		
//		HttpSession session = request.getSession(true);
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOBHistoricoOperacionCrediticiaForm f =(ReporteCOBHistoricoOperacionCrediticiaForm)formReporte;
		
		String mesAnnio = "";
		String fechaInicioMesAnnio = "";
		String fechaFinMesAnnio = "";
		
		
		String anhio = f.getAnhio();
		String mes = f.getMes();
		
		mesAnnio = mes + anhio;
		fechaInicioMesAnnio = "01/"+mes+"/"+anhio;
		
		Date primerDiaMes = DateUtil.convertStringToDate("01/"+mes+"/"+anhio);
		Date fechaPosterior31Dias = DateUtil.addToDate(primerDiaMes, Calendar.DATE, 31);
		
		String ifechaPosterior31Dias = DateUtil.convertDateToString("yyyyMMdd", fechaPosterior31Dias);
		
		String ianhio = ifechaPosterior31Dias.substring(0, 4);
		String imes = ifechaPosterior31Dias.substring(4, 6);
		
		Date fechaInicioSiguienteMes = DateUtil.convertStringToDate("01/"+imes+"/"+ianhio);
		Date fechaFinMesActual = DateUtil.addToDate(fechaInicioSiguienteMes, Calendar.DATE, -1);

		fechaFinMesAnnio = DateUtil.convertDateToString(fechaFinMesActual);
			
		
		
		params.put("codigoPais", pais.getCodigo());
		params.put("mesAnnio", mesAnnio);
		params.put("fechaInicioMesAnnio", fechaInicioMesAnnio);
		params.put("fechaFinMesAnnio", fechaFinMesAnnio);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		

		log.debug("ReporteCOBHistoricoOperacionCrediticiaAction - setViewAttributes");
//		HttpSession session = request.getSession(true);
		this.mostrarReportePDF = false;
		this.mostrarReporteOTXT = true;
		
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteCOBHistoricoOperacionCrediticiaForm f =(ReporteCOBHistoricoOperacionCrediticiaForm)this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		
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
//		request.getSession().setAttribute(Constants.SICC_MESES_LIST, resultado);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		f.setAnhio(fecha.substring(6,10));
		f.setMes(fecha.substring(3,5));
	
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		
		return "reportes.reporteCOBHistoricoOperacionCrediticiaService";
		
		
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

	
}
