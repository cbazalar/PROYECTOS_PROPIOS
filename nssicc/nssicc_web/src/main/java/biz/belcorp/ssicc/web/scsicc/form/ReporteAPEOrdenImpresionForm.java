package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPEOrdenImpresionForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String fechaFacturacion;
	private Date fechaFacturacionDt;
	


	public Date getFechaFacturacionDt() {
		return fechaFacturacionDt;
	}

	public void setFechaFacturacionDt(Date fechaFacturacionDt) {
		this.fechaFacturacionDt = fechaFacturacionDt;
	}

	private String[] tipoSolicitudList;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the tipoSolicitudList
	 */
	public String[] getTipoSolicitudList() {
		return tipoSolicitudList;
	}

	/**
	 * @param tipoSolicitudList the tipoSolicitudList to set
	 */
	public void setTipoSolicitudList(String[] tipoSolicitudList) {
		this.tipoSolicitudList = tipoSolicitudList;
	}
}