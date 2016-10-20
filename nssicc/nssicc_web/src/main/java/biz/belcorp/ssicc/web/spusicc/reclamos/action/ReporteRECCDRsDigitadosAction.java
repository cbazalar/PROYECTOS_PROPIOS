package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ReporteRECCDRsDigitadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECCDRsDigitadosAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 4519557827024942501L;

	private String tipoConsulta;
	private String fechaIngreso;
	private String codigoPeriodo;
	private String[] regionList;
	private String[] zonaList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECCDRsDigitadosForm form = new ReporteRECCDRsDigitadosForm();

		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (tipoConsulta.compareToIgnoreCase("1") == 0)
			return "reporteRECCDRsDigitadosXLS";
		else
			return "reporteRECCDRsDigitadosHistoricoXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoIso = usuario.getIdioma().getCodigoISO();

		String codigoPeriodo = new String();
		String fechaIngreso = new String();

		if (this.fechaIngreso.compareToIgnoreCase("") == 0) {
			fechaIngreso = null;
		} else
			fechaIngreso = this.fechaIngreso;
		
		if (this.codigoPeriodo.compareToIgnoreCase("") == 0) {
			codigoPeriodo = null;
		} else
			codigoPeriodo = this.codigoPeriodo;

		String condicionRegion = obtieneCondicion(this.regionList, "C.COD_REGI_ARRI", "'");
		String condicionZona = obtieneCondicion(this.zonaList, "C.COD_ZONA_ARRI", "'");

		params.put("codigoPeriodo", codigoPeriodo);
		params.put("fechaIngreso", fechaIngreso);
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", condicionZona);
		params.put("codigoIso", codigoIso);
		params.put("formatoExportacion", "XLS");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso
	 *            the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

}
