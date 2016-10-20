package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCAntiguedadSaldosForm  extends BaseReporteForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5731104235017472022L;
	
	private String fechaCorte;
	private Date fechaCorteD;
	private String tipoReporte;

	
	public Date getFechaCorteD() {
		return fechaCorteD;
	}

	public void setFechaCorteD(Date fechaCorteD) {
		this.fechaCorteD = fechaCorteD;
	}

	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		this.fechaCorte = sdf.format(new Date(System.currentTimeMillis()));
	}
	
	/**
	 * @return the fechaCorte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}
	
	/**
	 * @param fechaCorte
	 *            The fechaCorte to set.
	 */
	
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

}
