package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPPPedidosBolsaForm extends BaseReporteForm	implements Serializable {

	
	private static final long serialVersionUID = -3543727400171217669L;
	
	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String codigoPeriodo;
	
	
	public ReporteAPPPedidosBolsaForm(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		if (StringUtils.isEmpty(this.codigoPeriodo))
			this.codigoPeriodo = periodo;
	}

	
	public String getCodigoCanal() {
		return codigoCanal;
	}

	
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
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

	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	
}
