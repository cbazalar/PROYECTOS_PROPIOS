package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:mmacias@belcorp.biz">Carolina Macias </a>
 * 
 * @struts.form name = "reporteAPEProductosPosventaForm"
 */
public class ReporteAPEProductosPosventaForm extends BaseReporteForm
		implements Serializable {

	private String codigoPais;
	
	private String codigoCentro;
	
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	private String mapa;
	private String codsublinea;
	private String orden;
	
	private String codigoLinea;

	/**
	 * @return the codigoPais
	 */
	
	public String getCodigoPais() {
		return this.codigoPais;
	}

	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCentro
	 */
	public String getCodigoCentro() {
		return this.codigoCentro;
	}

	/**
	 * @param codigoCentro the codigoCentro to set
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
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
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		super.reset(mapping, request);  //Invocamos a su clase padre para setear valores
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		this.fechaFacturacion = sdf.format(new Date(System.currentTimeMillis()));
//	}

	public String getCodigoLinea() {
		return codigoLinea;
	}

	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	public String getCodsublinea() {
		return codsublinea;
	}

	public void setCodsublinea(String codsublinea) {
		this.codsublinea = codsublinea;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public String getOrden() {
		
		return orden;
	}

	public void setOrden(String orden) {
		this.orden=orden;
	}
}
