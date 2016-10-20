package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLETProgramaCorporativoRangoNivelForm extends BaseEditForm{

	
	private static final long serialVersionUID = -1159401480892569977L;
	
	private String codigoNivel;
	//private String nivel;
	private String pedidosIniciales;
	private String pedidosFinales;
	private String tolerancia;
	private String percentil;
	private String gananciaCumplimiento;
	private String gananciaSobrecumplimiento;
	
	private String correlativo;
	private String estado;
	
	
	public String getCodigoNivel() {
		return codigoNivel;
	}
	
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	
	public String getPedidosIniciales() {
		return pedidosIniciales;
	}
	
	public void setPedidosIniciales(String pedidosIniciales) {
		this.pedidosIniciales = pedidosIniciales;
	}
	
	public String getPedidosFinales() {
		return pedidosFinales;
	}
	
	public void setPedidosFinales(String pedidosFinales) {
		this.pedidosFinales = pedidosFinales;
	}
	
	public String getTolerancia() {
		return tolerancia;
	}
	
	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}
	
	public String getPercentil() {
		return percentil;
	}
	
	public void setPercentil(String percentil) {
		this.percentil = percentil;
	}
	
	public String getGananciaCumplimiento() {
		return gananciaCumplimiento;
	}
	
	public void setGananciaCumplimiento(String gananciaCumplimiento) {
		this.gananciaCumplimiento = gananciaCumplimiento;
	}
	
	public String getGananciaSobrecumplimiento() {
		return gananciaSobrecumplimiento;
	}
	
	public void setGananciaSobrecumplimiento(String gananciaSobrecumplimiento) {
		this.gananciaSobrecumplimiento = gananciaSobrecumplimiento;
	}
	
	public String getCorrelativo() {
		return correlativo;
	}
	
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
