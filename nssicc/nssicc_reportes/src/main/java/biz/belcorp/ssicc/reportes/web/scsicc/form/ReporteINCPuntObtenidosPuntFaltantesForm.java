package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCPuntObtenidosPuntFaltantesForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1843309034245176659L;

	private String codigoPais;
	
	private String descPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codigoConcurso;	

	private String[] codigoRegion;

	private String descripcionRegion;

	private String[] codigoZona;

	private String descripcionZona;
	
	private String[] codigoEstado;
	
	private String descripcionEstado;

	
	public ReporteINCPuntObtenidosPuntFaltantesForm() {
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;		
		this.codigoRegion  = null;
		this.codigoZona    = null;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDescPais() {
		return descPais;
	}

	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String[] getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		String temp = StringUtils.replace(descripcionRegion, "&&","\n" );
		this.descripcionRegion = temp;
	}

	public String getDescripcionZona() {
		return descripcionZona;
	}

	public void setDescripcionZona(String descripcionZona) {
		String temp = StringUtils.replace(descripcionZona, "&&","\n" );
		this.descripcionZona = temp;
	}	

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String[] getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String[] codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	
}
