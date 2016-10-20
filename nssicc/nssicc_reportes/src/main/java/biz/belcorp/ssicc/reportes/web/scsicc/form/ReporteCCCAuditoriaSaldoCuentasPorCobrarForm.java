
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteCCCAuditoriaSaldoCuentasPorCobrarForm extends BaseReporteForm	implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8054239093109947205L;

	private String codigoPais;

	private String fechaProcDesde;
	
	public ReporteCCCAuditoriaSaldoCuentasPorCobrarForm() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaProcDesde = sdf.format(new Date(System.currentTimeMillis()));

	}

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
	 * @return the fechaProcDesde
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde the fechaProcDesde to set
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}
	
	
}

