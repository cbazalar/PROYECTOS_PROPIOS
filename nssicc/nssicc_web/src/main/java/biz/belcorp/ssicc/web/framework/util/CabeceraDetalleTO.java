package biz.belcorp.ssicc.web.framework.util;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;

import biz.belcorp.ssicc.web.util.NumberUtil;

public class CabeceraDetalleTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String numLote;
	private String codigoCliente;
	private String codigoPeriodo;
	private String codigoVenta;
	private String desProducto;
	private String valorPrecCatalogo;
	private String unidDemanda;
	private String preTotal;
	private String preTotalFormat;
	private String totalUnid;
	private String txtnumPedidos;
	private String montoMinimo;

	public CabeceraDetalleTO() {
		super();
	}
	
	public CabeceraDetalleTO(String codigoPais, String numLote,
			String codigoCliente, String codigoPeriodo, String codigoVenta,
			String desProducto, String valorPrecCatalogo, String unidDemanda,
			String preTotal, String totalUnid, String txtnumPedidos,
			String montoMinimo) {
		super();
		this.codigoPais = codigoPais;
		this.numLote = numLote;
		this.codigoCliente = codigoCliente;
		this.codigoPeriodo = codigoPeriodo;
		this.codigoVenta = codigoVenta;
		this.desProducto = desProducto;
		this.valorPrecCatalogo = valorPrecCatalogo;
		this.unidDemanda = unidDemanda;
		this.preTotal = preTotal;
		this.totalUnid = totalUnid;
		this.txtnumPedidos = txtnumPedidos;
		this.montoMinimo = montoMinimo;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNumLote() {
		return numLote;
	}

	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public String getValorPrecCatalogo() {
		return valorPrecCatalogo;
	}

	public void setValorPrecCatalogo(String valorPrecCatalogo) {
		this.valorPrecCatalogo = valorPrecCatalogo;
	}

	public String getUnidDemanda() {
		return unidDemanda;
	}

	public void setUnidDemanda(String unidDemanda) {
		this.unidDemanda = unidDemanda;
	}

	public String getPreTotal() {
		return preTotal;
	}

	public void setPreTotal(String preTotal) {
		this.preTotal = preTotal;
	}

	public String getTotalUnid() {
		return totalUnid;
	}

	public void setTotalUnid(String totalUnid) {
		this.totalUnid = totalUnid;
	}

	public String getTxtnumPedidos() {
		return txtnumPedidos;
	}

	public void setTxtnumPedidos(String txtnumPedidos) {
		this.txtnumPedidos = txtnumPedidos;
	}

	public String getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	public String getPreTotalFormat() {		
		//preTotalFormat = NumberUtil.NumberToString(NumberUtils.toDouble(this.getPreTotalFormat(),0.00d));
		return preTotalFormat;
	}
	

	public void setPreTotalFormat(String preTotalFormat) {
		this.preTotalFormat = preTotalFormat;
	}

}
