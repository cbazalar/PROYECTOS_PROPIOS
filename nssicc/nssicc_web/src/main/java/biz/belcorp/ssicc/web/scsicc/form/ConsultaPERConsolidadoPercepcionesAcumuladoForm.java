package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author Sigcomt
 *
 */
public class ConsultaPERConsolidadoPercepcionesAcumuladoForm extends BaseReporteForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6749321432663859714L;
	private String codigoPais;
	private String anho;
	private String mes;
	
	public ConsultaPERConsolidadoPercepcionesAcumuladoForm() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		this.anho = fecha.substring(6,10);
		this.mes = fecha.substring(3,5);
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
	 * @return the anho
	 */
	public String getAnho() {
		return anho;
	}
	/**
	 * @param anho the anho to set
	 */
	public void setAnho(String anho) {
		this.anho = anho;
	}
	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
}

