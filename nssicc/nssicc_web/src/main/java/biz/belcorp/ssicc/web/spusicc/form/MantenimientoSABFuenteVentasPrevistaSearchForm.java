/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.FuenteVentas;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoSABFuenteVentasPrevistaSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656523501481781337L;
	
	private String codigoPais;

	private String nombrePais;

	private String codigoSociedad;

	private String codigoAlmacen;

	private String codigoAnio;

	private String codigoRegion;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoRangoPeriodo;

	private String codigoZona;

	private String nombreSociedad;

	private String nombreAlmacen;

	private String nombreAnio;

	private String nombreRegion;

	private String nombreMarca;

	private String nombreCanal;

	private String nombreRangoPeriodo;

	private String nombreZona;
	
	public MantenimientoSABFuenteVentasPrevistaSearchForm(){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		this.codigoSociedad = Constants.CODIGO_SOCIEDAD_DEFAULT;
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoAnio = sdf.format(new Date(System.currentTimeMillis()))
				.substring(6, 10);
		log.debug("En el form hallando el codigoAnio:" + this.codigoAnio);
		//Ini efernandezo
		this.codigoAlmacen = Constants.CODIGO_ALMACEN_DEFAULT;
		//Fin efernandezo
	
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
	 * @return the nombrePais
	 */
	public String getNombrePais() {
		return nombrePais;
	}

	/**
	 * @param nombrePais the nombrePais to set
	 */
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return the codigoAlmacen
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}

	/**
	 * @param codigoAlmacen the codigoAlmacen to set
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}

	/**
	 * @return the codigoAnio
	 */
	public String getCodigoAnio() {
		return codigoAnio;
	}

	/**
	 * @param codigoAnio the codigoAnio to set
	 */
	public void setCodigoAnio(String codigoAnio) {
		this.codigoAnio = codigoAnio;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoRangoPeriodo
	 */
	public String getCodigoRangoPeriodo() {
		return codigoRangoPeriodo;
	}

	/**
	 * @param codigoRangoPeriodo the codigoRangoPeriodo to set
	 */
	public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
		this.codigoRangoPeriodo = codigoRangoPeriodo;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the nombreSociedad
	 */
	public String getNombreSociedad() {
		return nombreSociedad;
	}

	/**
	 * @param nombreSociedad the nombreSociedad to set
	 */
	public void setNombreSociedad(String nombreSociedad) {
		this.nombreSociedad = nombreSociedad;
	}

	/**
	 * @return the nombreAlmacen
	 */
	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	/**
	 * @param nombreAlmacen the nombreAlmacen to set
	 */
	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	/**
	 * @return the nombreAnio
	 */
	public String getNombreAnio() {
		return nombreAnio;
	}

	/**
	 * @param nombreAnio the nombreAnio to set
	 */
	public void setNombreAnio(String nombreAnio) {
		this.nombreAnio = nombreAnio;
	}

	/**
	 * @return the nombreRegion
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}

	/**
	 * @param nombreRegion the nombreRegion to set
	 */
	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	/**
	 * @return the nombreMarca
	 */
	public String getNombreMarca() {
		return nombreMarca;
	}

	/**
	 * @param nombreMarca the nombreMarca to set
	 */
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	/**
	 * @return the nombreCanal
	 */
	public String getNombreCanal() {
		return nombreCanal;
	}

	/**
	 * @param nombreCanal the nombreCanal to set
	 */
	public void setNombreCanal(String nombreCanal) {
		this.nombreCanal = nombreCanal;
	}

	/**
	 * @return the nombreRangoPeriodo
	 */
	public String getNombreRangoPeriodo() {
		return nombreRangoPeriodo;
	}

	/**
	 * @param nombreRangoPeriodo the nombreRangoPeriodo to set
	 */
	public void setNombreRangoPeriodo(String nombreRangoPeriodo) {
		this.nombreRangoPeriodo = nombreRangoPeriodo;
	}

	/**
	 * @return the nombreZona
	 */
	public String getNombreZona() {
		return nombreZona;
	}

	/**
	 * @param nombreZona the nombreZona to set
	 */
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	
	
	private String c1f1 = "0";

	private String c1f2 = "0";

	private String c1f3 = "0";

	private String c1f4 = "0";

	private String c1f5 = "0";

	private String c1f6 = "0";

	private String c1f7 = "0";

	private String c1f8 = "0";

// Ini efernandezo 
	private String c1f9 = "0";

	private String c1f10 = "0";

	private String c1f11 = "0";

	private String c1f12 = "0";

	private String c1f13 = "0";

	private String c1f14 = "0";

	private String c1f15 = "0";

	private String c1f16 = "0";

	private String c1f17 = "0";

	private String c1f18 = "0";

	private String c1f19 = "0";


// Fin efernandezo
	
	private String c2f1 = "0";

	private String c2f2 = "0";

	private String c2f3 = "0";

	private String c2f4 = "0";

	private String c2f5 = "0";

	private String c2f6 = "0";

	private String c2f7 = "0";

	private String c2f8 = "0";

	// Ini efernandezo
	private String c2f9 = "0";

	private String c2f10 = "0";

	private String c2f11 = "0";

	private String c2f12 = "0";

	private String c2f13 = "0";

	private String c2f14 = "0";

	private String c2f15 = "0";

	private String c2f16 = "0";

	private String c2f17 = "0";

	private String c2f18 = "0";

	private String c2f19 = "0";
	
	// Fin efernandezo
	
	private String c3f1 = "0";

	private String c3f2 = "0";

	private String c3f3 = "0";

	private String c3f4 = "0";

	private String c3f5 = "0";

	private String c3f6 = "0";

	private String c3f7 = "0";

	private String c3f8 = "0";

	// Ini efernandezo
	private String c3f9 = "0";

	private String c3f10 = "0";

	private String c3f11 = "0";

	private String c3f12 = "0";

	private String c3f13 = "0";

	private String c3f14 = "0";

	private String c3f15 = "0";

	private String c3f16 = "0";

	private String c3f17 = "0";

	private String c3f18 = "0";

	private String c3f19 = "0";	
	
	// Fin efernandezo	

	private String c4f1 = "0";

	private String c4f2 = "0";

	private String c4f3 = "0";

	private String c4f4 = "0";

	private String c4f5 = "0";

	private String c4f6 = "0";

	private String c4f7 = "0";

	private String c4f8 = "0";

	// Ini efernandezo
	private String c4f9 = "0";

	private String c4f10 = "0";

	private String c4f11 = "0";

	private String c4f12 = "0";

	private String c4f13 = "0";

	private String c4f14 = "0";

	private String c4f15 = "0";

	private String c4f16 = "0";

	private String c4f17 = "0";

	private String c4f18 = "0";

	private String c4f19 = "0";		

	// Fin efernandezo	

	private String c5f1 = "0";

	private String c5f2 = "0";

	private String c5f3 = "0";

	private String c5f4 = "0";

	private String c5f5 = "0";

	private String c5f6 = "0";

	private String c5f7 = "0";

	private String c5f8 = "0";

	// Ini efernandezo
	private String c5f9 = "0";

	private String c5f10 = "0";

	private String c5f11 = "0";

	private String c5f12 = "0";

	private String c5f13 = "0";

	private String c5f14 = "0";

	private String c5f15 = "0";

	private String c5f16 = "0";

	private String c5f17 = "0";

	private String c5f18 = "0";

	private String c5f19 = "0";		
	
	// Fin efernandezo

	private String c6f1 = "0";

	private String c6f2 = "0";

	private String c6f3 = "0";

	private String c6f4 = "0";

	private String c6f5 = "0";

	private String c6f6 = "0";

	private String c6f7 = "0";

	private String c6f8 = "0";

	// Ini efernandezo
	private String c6f9 = "0";

	private String c6f10 = "0";

	private String c6f11 = "0";

	private String c6f12 = "0";

	private String c6f13 = "0";

	private String c6f14 = "0";

	private String c6f15 = "0";

	private String c6f16 = "0";

	private String c6f17 = "0";

	private String c6f18 = "0";

	private String c6f19 = "0";		
	// Fin efernandezo

	private String c1f1v = "0";

	private String c1f2v = "0";

	private String c1f3v = "0";

	private String c1f4v = "0";

	private String c1f5v = "0";

	private String c1f6v = "0";

	private String c1f7v = "0";

	private String c1f8v = "0";

	// Ini efernandezo
	private String c1f9v = "0";

	private String c1f10v = "0";

	private String c1f11v = "0";

	private String c1f12v = "0";

	private String c1f13v = "0";

	private String c1f14v = "0";

	private String c1f15v = "0";

	private String c1f16v = "0";

	private String c1f17v = "0";

	private String c1f18v = "0";

	private String c1f19v = "0";
	
	// Fin efernandezo

	private String c2f1v = "0";

	private String c2f2v = "0";

	private String c2f3v = "0";

	private String c2f4v = "0";

	private String c2f5v = "0";

	private String c2f6v = "0";

	private String c2f7v = "0";

	private String c2f8v = "0";

	// Ini efernandezo
	private String c2f9v = "0";

	private String c2f10v = "0";

	private String c2f11v = "0";

	private String c2f12v = "0";

	private String c2f13v = "0";

	private String c2f14v = "0";

	private String c2f15v = "0";

	private String c2f16v = "0";

	private String c2f17v = "0";

	private String c2f18v = "0";	

	private String c2f19v = "0";
	
	// Fin efernandezo

	private String c3f1v = "0";

	private String c3f2v = "0";

	private String c3f3v = "0";

	private String c3f4v = "0";

	private String c3f5v = "0";

	private String c3f6v = "0";

	private String c3f7v = "0";

	private String c3f8v = "0";

	// Ini efernandezo
	private String c3f9v = "0";

	private String c3f10v = "0";

	private String c3f11v = "0";

	private String c3f12v = "0";

	private String c3f13v = "0";

	private String c3f14v = "0";

	private String c3f15v = "0";

	private String c3f16v = "0";

	private String c3f17v = "0";

	private String c3f18v = "0";

	private String c3f19v = "0";
	
	// Fin efernandezo	

	private String c4f1v = "0";

	private String c4f2v = "0";

	private String c4f3v = "0";

	private String c4f4v = "0";

	private String c4f5v = "0";

	private String c4f6v = "0";

	private String c4f7v = "0";

	private String c4f8v = "0";

	// Ini efernandezo

	private String c4f9v = "0";

	private String c4f10v = "0";

	private String c4f11v = "0";

	private String c4f12v = "0";

	private String c4f13v = "0";

	private String c4f14v = "0";

	private String c4f15v = "0";

	private String c4f16v = "0";

	private String c4f17v = "0";

	private String c4f18v = "0";

	private String c4f19v = "0";	
	
	// Fin efernandezo	

	private String c5f1v = "0";

	private String c5f2v = "0";

	private String c5f3v = "0";

	private String c5f4v = "0";

	private String c5f5v = "0";

	private String c5f6v = "0";

	private String c5f7v = "0";

	private String c5f8v = "0";

	// Ini efernandezo

	private String c5f9v = "0";

	private String c5f10v = "0";

	private String c5f11v = "0";

	private String c5f12v = "0";

	private String c5f13v = "0";

	private String c5f14v = "0";

	private String c5f15v = "0";

	private String c5f16v = "0";

	private String c5f17v = "0";

	private String c5f18v = "0";

	private String c5f19v = "0";

	// Fin efernandezo

	private String c6f1v = "0";

	private String c6f2v = "0";

	private String c6f3v = "0";

	private String c6f4v = "0";

	private String c6f5v = "0";

	private String c6f6v = "0";

	private String c6f7v = "0";

	private String c6f8v = "0";

	// Ini efernandezo
	private String c6f9v = "0";

	private String c6f10v = "0";

	private String c6f11v = "0";

	private String c6f12v = "0";

	private String c6f13v = "0";

	private String c6f14v = "0";

	private String c6f15v = "0";

	private String c6f16v = "0";

	private String c6f17v = "0";

	private String c6f18v = "0";

	private String c6f19v = "0";
	
	
	// Fin efernandezo	
	
	private String pk1 = "0";

	private String pk2 = "0";

	private String pk3 = "0";

	private String pk4 = "0";

	private String pk5 = "0";

	private String pk6 = "0";

	private String c1 = "";

	private String c2 = "";

	private String c3 = "";

	private String c4 = "";

	private String c5 = "";

	private String c6 = "";

	private String f1 = "Activas Iniciales";

	private String f2 = "Ingresos";

	private String f3 = "Reingresos";

	private String f4 = "Egresos Puros";

	private String f5 = "%Actividad";

	private String f6 = "PPU";

	private String f7 = "PUP";

	private String f8 = "Número Clientes";

	// Ini efernandezo
	private String f9 = "Activas Lider";

	private String f10 = "Numero Consultora Clie Privilege";

	private String f11 = "Numero Clientes Insc Privilege";
	
	private String f12 = "Numero Clientes Tran Privelege";	

	private String f13 = "Retencion Sdo Periodo";

	private String f14 = "Retencion Ter Periodo";

	private String f15 = "Retencion Cto Periodo";

	private String f16 = "Retencion Activas";

	private String f17 = "Porcentaje Rotacion";

	private String f18 = "Posibles Egresos";

	private String f19 = "Retencion Posibles Egresos";
	
	
	public void camposACero() {
		if (c1f1.trim().equals(""))
			c1f1 = "0";
		if (c1f2.trim().equals(""))
			c1f2 = "0";
		if (c1f3.trim().equals(""))
			c1f3 = "0";
		if (c1f4.trim().equals(""))
			c1f4 = "0";
		if (c1f5.trim().equals(""))
			c1f5 = "0";
		if (c1f6.trim().equals(""))
			c1f6 = "0";
		if (c1f7.trim().equals(""))
			c1f7 = "0";
		if (c1f8.trim().equals(""))
			c1f8 = "0";
		// Ini efernandezo		
		if (c1f9.trim().equals(""))
			c1f9 = "0";
		if (c1f10.trim().equals(""))
			c1f10 = "0";
		if (c1f11.trim().equals(""))
			c1f11 = "0";
		if (c1f12.trim().equals(""))
			c1f12 = "0";
		if (c1f13.trim().equals(""))
			c1f13 = "0";
		if (c1f14.trim().equals(""))
			c1f14 = "0";
		if (c1f15.trim().equals(""))
			c1f15 = "0";
		if (c1f16.trim().equals(""))
			c1f16 = "0";
		if (c1f17.trim().equals(""))
			c1f17 = "0";
		if (c1f18.trim().equals(""))
			c1f18 = "0";
		if (c1f19.trim().equals(""))
			c1f19 = "0";		
		// Fin efernandezo

		if (c2f1.trim().equals(""))
			c2f1 = "0";
		if (c2f2.trim().equals(""))
			c2f2 = "0";
		if (c2f3.trim().equals(""))
			c2f3 = "0";
		if (c2f4.trim().equals(""))
			c2f4 = "0";
		if (c2f5.trim().equals(""))
			c2f5 = "0";
		if (c2f6.trim().equals(""))
			c2f6 = "0";
		if (c2f7.trim().equals(""))
			c2f7 = "0";
		if (c2f8.trim().equals(""))
			c2f8 = "0";

		// Ini efernandezo
		if (c2f9.trim().equals(""))
			c2f9 = "0";
		if (c2f10.trim().equals(""))
			c2f10 = "0";
		if (c2f11.trim().equals(""))
			c2f11 = "0";
		if (c2f12.trim().equals(""))
			c2f12 = "0";
		if (c2f13.trim().equals(""))
			c2f13 = "0";
		if (c2f14.trim().equals(""))
			c2f14 = "0";
		if (c2f15.trim().equals(""))
			c2f15 = "0";
		if (c2f16.trim().equals(""))
			c2f16 = "0";
		if (c2f17.trim().equals(""))
			c2f17 = "0";
		if (c2f18.trim().equals(""))
			c2f18 = "0";
		if (c2f19.trim().equals(""))
			c2f19 = "0";
		// Fin efernandezo		
		
		if (c3f1.trim().equals(""))
			c3f1 = "0";
		if (c3f2.trim().equals(""))
			c3f2 = "0";
		if (c3f3.trim().equals(""))
			c3f3 = "0";
		if (c3f4.trim().equals(""))
			c3f4 = "0";
		if (c3f5.trim().equals(""))
			c3f5 = "0";
		if (c3f6.trim().equals(""))
			c3f6 = "0";
		if (c3f7.trim().equals(""))
			c3f7 = "0";
		if (c3f8.trim().equals(""))
			c3f8 = "0";

		// Ini efernandezo
		if (c3f9.trim().equals(""))
			c3f9 = "0";
		if (c3f10.trim().equals(""))
			c3f10 = "0";
		if (c3f11.trim().equals(""))
			c3f11 = "0";
		if (c3f12.trim().equals(""))
			c3f12 = "0";
		if (c3f13.trim().equals(""))
			c3f13 = "0";
		if (c3f14.trim().equals(""))
			c3f14 = "0";
		if (c3f15.trim().equals(""))
			c3f15 = "0";
		if (c3f16.trim().equals(""))
			c3f16 = "0";
		if (c3f17.trim().equals(""))
			c3f17 = "0";
		if (c3f18.trim().equals(""))
			c3f18 = "0";
		if (c3f19.trim().equals(""))
			c3f19 = "0";
		// Fin efernandezo		
		
		if (c4f1.trim().equals(""))
			c4f1 = "0";
		if (c4f2.trim().equals(""))
			c4f2 = "0";
		if (c4f3.trim().equals(""))
			c4f3 = "0";
		if (c4f4.trim().equals(""))
			c4f4 = "0";
		if (c4f5.trim().equals(""))
			c4f5 = "0";
		if (c4f6.trim().equals(""))
			c4f6 = "0";
		if (c4f7.trim().equals(""))
			c4f7 = "0";
		if (c4f8.trim().equals(""))
			c4f8 = "0";

		// Ini efernandezo
		if (c4f9.trim().equals(""))
			c4f9 = "0";
		if (c4f10.trim().equals(""))
			c4f10 = "0";
		if (c4f11.trim().equals(""))
			c4f11 = "0";
		if (c4f12.trim().equals(""))
			c4f12 = "0";
		if (c4f13.trim().equals(""))
			c4f13 = "0";
		if (c4f14.trim().equals(""))
			c4f14 = "0";
		if (c4f15.trim().equals(""))
			c4f15 = "0";
		if (c4f16.trim().equals(""))
			c4f16 = "0";
		if (c4f17.trim().equals(""))
			c4f17 = "0";
		if (c4f18.trim().equals(""))
			c4f18 = "0";
		if (c4f19.trim().equals(""))
			c4f19 = "0";
		// Fin efernandezo
		
		if (c5f1.trim().equals(""))
			c5f1 = "0";
		if (c5f2.trim().equals(""))
			c5f2 = "0";
		if (c5f3.trim().equals(""))
			c5f3 = "0";
		if (c5f4.trim().equals(""))
			c5f4 = "0";
		if (c5f5.trim().equals(""))
			c5f5 = "0";
		if (c5f6.trim().equals(""))
			c5f6 = "0";
		if (c5f7.trim().equals(""))
			c5f7 = "0";
		if (c5f8.trim().equals(""))
			c5f8 = "0";

		// Ini efernandezo
		if (c5f9.trim().equals(""))
			c5f9 = "0";
		if (c5f10.trim().equals(""))
			c5f10 = "0";
		if (c5f11.trim().equals(""))
			c5f11 = "0";
		if (c5f12.trim().equals(""))
			c5f12 = "0";
		if (c5f13.trim().equals(""))
			c5f13 = "0";
		if (c5f14.trim().equals(""))
			c5f14 = "0";
		if (c5f15.trim().equals(""))
			c5f15 = "0";
		if (c5f16.trim().equals(""))
			c5f16 = "0";
		if (c5f17.trim().equals(""))
			c5f17 = "0";
		if (c5f18.trim().equals(""))
			c5f18 = "0";
		if (c5f19.trim().equals(""))
			c5f19 = "0";
		// Fin efernandezo
		
		if (c6f1.trim().equals(""))
			c6f1 = "0";
		if (c6f2.trim().equals(""))
			c6f2 = "0";
		if (c6f3.trim().equals(""))
			c6f3 = "0";
		if (c6f4.trim().equals(""))
			c6f4 = "0";
		if (c6f5.trim().equals(""))
			c6f5 = "0";
		if (c6f6.trim().equals(""))
			c6f6 = "0";
		if (c6f7.trim().equals(""))
			c6f7 = "0";
		if (c6f8.trim().equals(""))
			c6f8 = "0";

		// Ini efernandezo
		if (c6f9.trim().equals(""))
			c6f9 = "0";
		if (c6f10.trim().equals(""))
			c6f10 = "0";
		if (c6f11.trim().equals(""))
			c6f11 = "0";
		if (c6f12.trim().equals(""))
			c6f12 = "0";
		if (c6f13.trim().equals(""))
			c6f13 = "0";
		if (c6f14.trim().equals(""))
			c6f14 = "0";
		if (c6f15.trim().equals(""))
			c6f15 = "0";
		if (c6f16.trim().equals(""))
			c6f16 = "0";
		if (c6f17.trim().equals(""))
			c6f17 = "0";
		if (c6f18.trim().equals(""))
			c6f18 = "0";
		if (c6f19.trim().equals(""))
			c6f19 = "0";
		// Fin efernandezo

	}
	
	
	// public listaACampos

		public ArrayList camposALista() {
			ArrayList lista = new ArrayList();

			FuenteVentas fuenteVentas;
			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk1));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c1f1));
			fuenteVentas.setIngresos(Long.parseLong(this.c1f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c1f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c1f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c1f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c1f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c1f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c1f8.trim()));
			
			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c1f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c1f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c1f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c1f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c1f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c1f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c1f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c1f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c1f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c1f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c1f19.trim()));
			// Fin efernandezo
			
			lista.add(0, fuenteVentas);

			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk2));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c2f1.trim()));
			fuenteVentas.setIngresos(Long.parseLong(this.c2f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c2f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c2f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c2f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c2f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c2f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c2f8.trim()));
			
			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c2f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c2f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c2f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c2f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c2f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c2f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c2f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c2f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c2f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c2f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c2f19.trim()));
			// Fin efernandezo
			
			lista.add(1, fuenteVentas);

			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk3));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c3f1.trim()));
			fuenteVentas.setIngresos(Long.parseLong(this.c3f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c3f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c3f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c3f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c3f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c3f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c3f8.trim()));

			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c3f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c3f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c3f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c3f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c3f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c3f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c3f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c3f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c3f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c3f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c3f19.trim()));
			// Fin efernandezo
			
			lista.add(2, fuenteVentas);

			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk4));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c4f1.trim()));
			fuenteVentas.setIngresos(Long.parseLong(this.c4f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c4f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c4f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c4f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c4f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c4f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c4f8.trim()));

			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c4f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c4f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c4f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c4f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c4f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c4f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c4f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c4f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c4f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c4f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c4f19.trim()));
			// Fin efernandezo		
			
			lista.add(3, fuenteVentas);

			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk5));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c5f1.trim()));
			fuenteVentas.setIngresos(Long.parseLong(this.c5f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c5f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c5f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c5f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c5f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c5f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c5f8.trim()));
			
			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c5f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c5f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c5f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c5f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c5f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c5f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c5f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c5f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c5f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c5f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c5f19.trim()));
			// Fin efernandezo	
			
			lista.add(4, fuenteVentas);

			fuenteVentas = new FuenteVentas();
			fuenteVentas.setCodigoFuenteVentas(Long.parseLong(this.pk6));
			fuenteVentas.setActividadesIniciales(Long.parseLong(this.c6f1.trim()));
			fuenteVentas.setIngresos(Long.parseLong(this.c6f2.trim()));
			fuenteVentas.setReingresos(Long.parseLong(this.c6f3.trim()));
			fuenteVentas.setEgresos(Long.parseLong(this.c6f4.trim()));
			fuenteVentas.setPorcentajeActividad(Double
					.parseDouble(this.c6f5.trim()));
			fuenteVentas.setPpu(Double.parseDouble(this.c6f6.trim()));
			fuenteVentas.setPup(Double.parseDouble(this.c6f7.trim()));
			fuenteVentas.setNumeroClientes(Long.parseLong(this.c6f8.trim()));

			// Ini efernandezo
			fuenteVentas.setActividadLider(Double.parseDouble(this.c6f9.trim()));
			fuenteVentas.setNumeroConsultoraCliPrivilege(Long.parseLong(this.c6f10.trim()));
			fuenteVentas.setNumeroClientesInsPrivilege(Long.parseLong(this.c6f11.trim()));
			fuenteVentas.setNumeroClientesTrsPrivelege(Long.parseLong(this.c6f12.trim()));
			fuenteVentas.setRetencionSdoPeriodo(Long.parseLong(this.c6f13.trim()));
			fuenteVentas.setRetencionTerPeriodo(Long.parseLong(this.c6f14.trim()));
			fuenteVentas.setRetencionCuaPeriodo(Long.parseLong(this.c6f15.trim()));
			fuenteVentas.setRetencionActivas(Long.parseLong(this.c6f16.trim()));
			fuenteVentas.setPorcentajeRotacion(Double.parseDouble(this.c6f17.trim()));
			fuenteVentas.setPosiblesEgresos(Long.parseLong(this.c6f18.trim()));
			fuenteVentas.setRetencionPosEgresos(Long.parseLong(this.c6f19.trim()));
			// Fin efernandezo			
			
			lista.add(5, fuenteVentas);

			return lista;

		}
		
		
		public void listaACampos(ArrayList lista) {
			FuenteVentas fuenteVentas;

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(0);
			this.pk1 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c1f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c1f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c1f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c1f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c1f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c1f6 = String.valueOf(fuenteVentas.getPpu());
			log.debug("PRUEBA c1f7 : " + String.valueOf(fuenteVentas.getPup()));		
			this.c1f7 = String.valueOf(fuenteVentas.getPup());
			this.c1f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			log.debug("PRUEBA c1f9 : " + String.valueOf(fuenteVentas.getActividadLider()));
			this.c1f9 = String.valueOf(fuenteVentas.getActividadLider());
			log.debug("PRUEBA c1f10 : " + String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege()));
			this.c1f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c1f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c1f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c1f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c1f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c1f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c1f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c1f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c1f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c1f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo
			this.c1f1v = fuenteVentas.getEstadoAbierto();
			this.c1f2v = fuenteVentas.getEstadoAbierto();
			this.c1f3v = fuenteVentas.getEstadoAbierto();
			this.c1f4v = fuenteVentas.getEstadoAbierto();
			this.c1f5v = fuenteVentas.getEstadoAbierto();
			this.c1f6v = fuenteVentas.getEstadoAbierto();
			this.c1f7v = fuenteVentas.getEstadoAbierto();
			this.c1f8v = fuenteVentas.getEstadoAbierto();
			// Ini efernandezo
			this.c1f9v = fuenteVentas.getEstadoAbierto();
			this.c1f10v = fuenteVentas.getEstadoAbierto();
			this.c1f11v = fuenteVentas.getEstadoAbierto();
			this.c1f12v = fuenteVentas.getEstadoAbierto();
			this.c1f13v = fuenteVentas.getEstadoAbierto();
			this.c1f14v = fuenteVentas.getEstadoAbierto();
			this.c1f15v = fuenteVentas.getEstadoAbierto();
			this.c1f16v = fuenteVentas.getEstadoAbierto();
			this.c1f17v = fuenteVentas.getEstadoAbierto();
			this.c1f18v = fuenteVentas.getEstadoAbierto();
			this.c1f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo
			this.c1 = fuenteVentas.getCodigoPeriodoAnio();

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(1);
			this.pk2 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c2f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c2f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c2f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c2f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c2f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c2f6 = String.valueOf(fuenteVentas.getPpu());
			this.c2f7 = String.valueOf(fuenteVentas.getPup());
			this.c2f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			this.c2f9 = String.valueOf(fuenteVentas.getActividadLider());
			this.c2f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c2f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c2f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c2f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c2f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c2f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c2f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c2f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c2f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c2f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo
			this.c2f1v = fuenteVentas.getEstadoAbierto();
			this.c2f2v = fuenteVentas.getEstadoAbierto();
			this.c2f3v = fuenteVentas.getEstadoAbierto();
			this.c2f4v = fuenteVentas.getEstadoAbierto();
			this.c2f5v = fuenteVentas.getEstadoAbierto();
			this.c2f6v = fuenteVentas.getEstadoAbierto();
			this.c2f7v = fuenteVentas.getEstadoAbierto();
			this.c2f8v = fuenteVentas.getEstadoAbierto();
			this.c2 = fuenteVentas.getCodigoPeriodoAnio();
			// Ini efernandezo
			this.c2f9v = fuenteVentas.getEstadoAbierto();
			this.c2f10v = fuenteVentas.getEstadoAbierto();
			this.c2f11v = fuenteVentas.getEstadoAbierto();
			this.c2f12v = fuenteVentas.getEstadoAbierto();
			this.c2f13v = fuenteVentas.getEstadoAbierto();
			this.c2f14v = fuenteVentas.getEstadoAbierto();
			this.c2f15v = fuenteVentas.getEstadoAbierto();
			this.c2f16v = fuenteVentas.getEstadoAbierto();
			this.c2f17v = fuenteVentas.getEstadoAbierto();
			this.c2f18v = fuenteVentas.getEstadoAbierto();
			this.c2f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(2);
			this.pk3 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c3f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c3f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c3f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c3f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c3f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c3f6 = String.valueOf(fuenteVentas.getPpu());
			this.c3f7 = String.valueOf(fuenteVentas.getPup());
			this.c3f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			this.c3f9 = String.valueOf(fuenteVentas.getActividadLider());
			this.c3f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c3f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c3f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c3f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c3f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c3f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c3f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c3f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c3f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c3f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo		
			this.c3f1v = fuenteVentas.getEstadoAbierto();
			this.c3f2v = fuenteVentas.getEstadoAbierto();
			this.c3f3v = fuenteVentas.getEstadoAbierto();
			this.c3f4v = fuenteVentas.getEstadoAbierto();
			this.c3f5v = fuenteVentas.getEstadoAbierto();
			this.c3f6v = fuenteVentas.getEstadoAbierto();
			this.c3f7v = fuenteVentas.getEstadoAbierto();
			this.c3f8v = fuenteVentas.getEstadoAbierto();
			// Ini efernandezo
			this.c3f9v = fuenteVentas.getEstadoAbierto();
			this.c3f10v = fuenteVentas.getEstadoAbierto();
			this.c3f11v = fuenteVentas.getEstadoAbierto();
			this.c3f12v = fuenteVentas.getEstadoAbierto();
			this.c3f13v = fuenteVentas.getEstadoAbierto();
			this.c3f14v = fuenteVentas.getEstadoAbierto();
			this.c3f15v = fuenteVentas.getEstadoAbierto();
			this.c3f16v = fuenteVentas.getEstadoAbierto();
			this.c3f17v = fuenteVentas.getEstadoAbierto();
			this.c3f18v = fuenteVentas.getEstadoAbierto();
			this.c3f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo		
			this.c3 = fuenteVentas.getCodigoPeriodoAnio();

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(3);
			this.pk4 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c4f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c4f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c4f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c4f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c4f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c4f6 = String.valueOf(fuenteVentas.getPpu());
			this.c4f7 = String.valueOf(fuenteVentas.getPup());
			this.c4f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			this.c4f9 = String.valueOf(fuenteVentas.getActividadLider());
			this.c4f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c4f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c4f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c4f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c4f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c4f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c4f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c4f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c4f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c4f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo			
			this.c4f1v = fuenteVentas.getEstadoAbierto();
			this.c4f2v = fuenteVentas.getEstadoAbierto();
			this.c4f3v = fuenteVentas.getEstadoAbierto();
			this.c4f4v = fuenteVentas.getEstadoAbierto();
			this.c4f5v = fuenteVentas.getEstadoAbierto();
			this.c4f6v = fuenteVentas.getEstadoAbierto();
			this.c4f7v = fuenteVentas.getEstadoAbierto();
			this.c4f8v = fuenteVentas.getEstadoAbierto();
			// Ini efernandezo
			this.c4f9v = fuenteVentas.getEstadoAbierto();
			this.c4f10v = fuenteVentas.getEstadoAbierto();
			this.c4f11v = fuenteVentas.getEstadoAbierto();
			this.c4f12v = fuenteVentas.getEstadoAbierto();
			this.c4f13v = fuenteVentas.getEstadoAbierto();
			this.c4f14v = fuenteVentas.getEstadoAbierto();
			this.c4f15v = fuenteVentas.getEstadoAbierto();
			this.c4f16v = fuenteVentas.getEstadoAbierto();
			this.c4f17v = fuenteVentas.getEstadoAbierto();
			this.c4f18v = fuenteVentas.getEstadoAbierto();
			this.c4f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo			
			this.c4 = fuenteVentas.getCodigoPeriodoAnio();

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(4);
			this.pk5 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c5f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c5f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c5f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c5f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c5f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c5f6 = String.valueOf(fuenteVentas.getPpu());
			this.c5f7 = String.valueOf(fuenteVentas.getPup());
			this.c5f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			this.c5f9 = String.valueOf(fuenteVentas.getActividadLider());
			this.c5f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c5f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c5f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c5f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c5f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c5f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c5f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c5f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c5f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c5f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo			
			this.c5f1v = fuenteVentas.getEstadoAbierto();
			this.c5f2v = fuenteVentas.getEstadoAbierto();
			this.c5f3v = fuenteVentas.getEstadoAbierto();
			this.c5f4v = fuenteVentas.getEstadoAbierto();
			this.c5f5v = fuenteVentas.getEstadoAbierto();
			this.c5f6v = fuenteVentas.getEstadoAbierto();
			this.c5f7v = fuenteVentas.getEstadoAbierto();
			this.c5f8v = fuenteVentas.getEstadoAbierto();
			// Ini efernandezo
			this.c5f9v = fuenteVentas.getEstadoAbierto();
			this.c5f10v = fuenteVentas.getEstadoAbierto();
			this.c5f11v = fuenteVentas.getEstadoAbierto();
			this.c5f12v = fuenteVentas.getEstadoAbierto();
			this.c5f13v = fuenteVentas.getEstadoAbierto();
			this.c5f14v = fuenteVentas.getEstadoAbierto();
			this.c5f15v = fuenteVentas.getEstadoAbierto();
			this.c5f16v = fuenteVentas.getEstadoAbierto();
			this.c5f17v = fuenteVentas.getEstadoAbierto();
			this.c5f18v = fuenteVentas.getEstadoAbierto();
			this.c5f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo			
			this.c5 = fuenteVentas.getCodigoPeriodoAnio();

			fuenteVentas = new FuenteVentas();
			fuenteVentas = (FuenteVentas) lista.get(5);
			this.pk6 = String.valueOf(fuenteVentas.getCodigoFuenteVentas());
			this.c6f1 = String.valueOf(fuenteVentas.getActividadesIniciales());
			this.c6f2 = String.valueOf(fuenteVentas.getIngresos());
			this.c6f3 = String.valueOf(fuenteVentas.getReingresos());
			this.c6f4 = String.valueOf(fuenteVentas.getEgresos());
			this.c6f5 = String.valueOf(fuenteVentas.getPorcentajeActividad());
			this.c6f6 = String.valueOf(fuenteVentas.getPpu());
			this.c6f7 = String.valueOf(fuenteVentas.getPup());
			this.c6f8 = String.valueOf(fuenteVentas.getNumeroClientes());
			// Ini efernandezo
			this.c6f9 = String.valueOf(fuenteVentas.getActividadLider());
			this.c6f10 = String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
			this.c6f11 = String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
			this.c6f12 = String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
			this.c6f13 = String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
			this.c6f14 = String.valueOf(fuenteVentas.getRetencionTerPeriodo());
			this.c6f15 = String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
			this.c6f16 = String.valueOf(fuenteVentas.getRetencionActivas());
			this.c6f17 = String.valueOf(fuenteVentas.getPorcentajeRotacion());
			this.c6f18 = String.valueOf(fuenteVentas.getPosiblesEgresos());
			this.c6f19 = String.valueOf(fuenteVentas.getRetencionPosEgresos());
			// Fin efernandezo		
			this.c6f1v = fuenteVentas.getEstadoAbierto();
			this.c6f2v = fuenteVentas.getEstadoAbierto();
			this.c6f3v = fuenteVentas.getEstadoAbierto();
			this.c6f4v = fuenteVentas.getEstadoAbierto();
			this.c6f5v = fuenteVentas.getEstadoAbierto();
			this.c6f6v = fuenteVentas.getEstadoAbierto();
			this.c6f7v = fuenteVentas.getEstadoAbierto();
			this.c6f8v = fuenteVentas.getEstadoAbierto();
			// Ini efernandezo
			this.c6f9v = fuenteVentas.getEstadoAbierto();
			this.c6f10v = fuenteVentas.getEstadoAbierto();
			this.c6f11v = fuenteVentas.getEstadoAbierto();
			this.c6f12v = fuenteVentas.getEstadoAbierto();
			this.c6f13v = fuenteVentas.getEstadoAbierto();
			this.c6f14v = fuenteVentas.getEstadoAbierto();
			this.c6f15v = fuenteVentas.getEstadoAbierto();
			this.c6f16v = fuenteVentas.getEstadoAbierto();
			this.c6f17v = fuenteVentas.getEstadoAbierto();
			this.c6f18v = fuenteVentas.getEstadoAbierto();
			this.c6f19v = fuenteVentas.getEstadoAbierto();
			// Fin efernandezo		
			this.c6 = fuenteVentas.getCodigoPeriodoAnio();

		}

		/**
		 * @return the c1f1
		 */
		public String getC1f1() {
			return c1f1;
		}

		/**
		 * @param c1f1 the c1f1 to set
		 */
		public void setC1f1(String c1f1) {
			this.c1f1 = c1f1;
		}

		/**
		 * @return the c1f2
		 */
		public String getC1f2() {
			return c1f2;
		}

		/**
		 * @param c1f2 the c1f2 to set
		 */
		public void setC1f2(String c1f2) {
			this.c1f2 = c1f2;
		}

		/**
		 * @return the c1f3
		 */
		public String getC1f3() {
			return c1f3;
		}

		/**
		 * @param c1f3 the c1f3 to set
		 */
		public void setC1f3(String c1f3) {
			this.c1f3 = c1f3;
		}

		/**
		 * @return the c1f4
		 */
		public String getC1f4() {
			return c1f4;
		}

		/**
		 * @param c1f4 the c1f4 to set
		 */
		public void setC1f4(String c1f4) {
			this.c1f4 = c1f4;
		}

		/**
		 * @return the c1f5
		 */
		public String getC1f5() {
			return c1f5;
		}

		/**
		 * @param c1f5 the c1f5 to set
		 */
		public void setC1f5(String c1f5) {
			this.c1f5 = c1f5;
		}

		/**
		 * @return the c1f6
		 */
		public String getC1f6() {
			return c1f6;
		}

		/**
		 * @param c1f6 the c1f6 to set
		 */
		public void setC1f6(String c1f6) {
			this.c1f6 = c1f6;
		}

		/**
		 * @return the c1f7
		 */
		public String getC1f7() {
			return c1f7;
		}

		/**
		 * @param c1f7 the c1f7 to set
		 */
		public void setC1f7(String c1f7) {
			this.c1f7 = c1f7;
		}

		/**
		 * @return the c1f8
		 */
		public String getC1f8() {
			return c1f8;
		}

		/**
		 * @param c1f8 the c1f8 to set
		 */
		public void setC1f8(String c1f8) {
			this.c1f8 = c1f8;
		}

		/**
		 * @return the c1f9
		 */
		public String getC1f9() {
			return c1f9;
		}

		/**
		 * @param c1f9 the c1f9 to set
		 */
		public void setC1f9(String c1f9) {
			this.c1f9 = c1f9;
		}

		/**
		 * @return the c1f10
		 */
		public String getC1f10() {
			return c1f10;
		}

		/**
		 * @param c1f10 the c1f10 to set
		 */
		public void setC1f10(String c1f10) {
			this.c1f10 = c1f10;
		}

		/**
		 * @return the c1f11
		 */
		public String getC1f11() {
			return c1f11;
		}

		/**
		 * @param c1f11 the c1f11 to set
		 */
		public void setC1f11(String c1f11) {
			this.c1f11 = c1f11;
		}

		/**
		 * @return the c1f12
		 */
		public String getC1f12() {
			return c1f12;
		}

		/**
		 * @param c1f12 the c1f12 to set
		 */
		public void setC1f12(String c1f12) {
			this.c1f12 = c1f12;
		}

		/**
		 * @return the c1f13
		 */
		public String getC1f13() {
			return c1f13;
		}

		/**
		 * @param c1f13 the c1f13 to set
		 */
		public void setC1f13(String c1f13) {
			this.c1f13 = c1f13;
		}

		/**
		 * @return the c1f14
		 */
		public String getC1f14() {
			return c1f14;
		}

		/**
		 * @param c1f14 the c1f14 to set
		 */
		public void setC1f14(String c1f14) {
			this.c1f14 = c1f14;
		}

		/**
		 * @return the c1f15
		 */
		public String getC1f15() {
			return c1f15;
		}

		/**
		 * @param c1f15 the c1f15 to set
		 */
		public void setC1f15(String c1f15) {
			this.c1f15 = c1f15;
		}

		/**
		 * @return the c1f16
		 */
		public String getC1f16() {
			return c1f16;
		}

		/**
		 * @param c1f16 the c1f16 to set
		 */
		public void setC1f16(String c1f16) {
			this.c1f16 = c1f16;
		}

		/**
		 * @return the c1f17
		 */
		public String getC1f17() {
			return c1f17;
		}

		/**
		 * @param c1f17 the c1f17 to set
		 */
		public void setC1f17(String c1f17) {
			this.c1f17 = c1f17;
		}

		/**
		 * @return the c1f18
		 */
		public String getC1f18() {
			return c1f18;
		}

		/**
		 * @param c1f18 the c1f18 to set
		 */
		public void setC1f18(String c1f18) {
			this.c1f18 = c1f18;
		}

		/**
		 * @return the c1f19
		 */
		public String getC1f19() {
			return c1f19;
		}

		/**
		 * @param c1f19 the c1f19 to set
		 */
		public void setC1f19(String c1f19) {
			this.c1f19 = c1f19;
		}

		/**
		 * @return the c2f1
		 */
		public String getC2f1() {
			return c2f1;
		}

		/**
		 * @param c2f1 the c2f1 to set
		 */
		public void setC2f1(String c2f1) {
			this.c2f1 = c2f1;
		}

		/**
		 * @return the c2f2
		 */
		public String getC2f2() {
			return c2f2;
		}

		/**
		 * @param c2f2 the c2f2 to set
		 */
		public void setC2f2(String c2f2) {
			this.c2f2 = c2f2;
		}

		/**
		 * @return the c2f3
		 */
		public String getC2f3() {
			return c2f3;
		}

		/**
		 * @param c2f3 the c2f3 to set
		 */
		public void setC2f3(String c2f3) {
			this.c2f3 = c2f3;
		}

		/**
		 * @return the c2f4
		 */
		public String getC2f4() {
			return c2f4;
		}

		/**
		 * @param c2f4 the c2f4 to set
		 */
		public void setC2f4(String c2f4) {
			this.c2f4 = c2f4;
		}

		/**
		 * @return the c2f5
		 */
		public String getC2f5() {
			return c2f5;
		}

		/**
		 * @param c2f5 the c2f5 to set
		 */
		public void setC2f5(String c2f5) {
			this.c2f5 = c2f5;
		}

		/**
		 * @return the c2f6
		 */
		public String getC2f6() {
			return c2f6;
		}

		/**
		 * @param c2f6 the c2f6 to set
		 */
		public void setC2f6(String c2f6) {
			this.c2f6 = c2f6;
		}

		/**
		 * @return the c2f7
		 */
		public String getC2f7() {
			return c2f7;
		}

		/**
		 * @param c2f7 the c2f7 to set
		 */
		public void setC2f7(String c2f7) {
			this.c2f7 = c2f7;
		}

		/**
		 * @return the c2f8
		 */
		public String getC2f8() {
			return c2f8;
		}

		/**
		 * @param c2f8 the c2f8 to set
		 */
		public void setC2f8(String c2f8) {
			this.c2f8 = c2f8;
		}

		/**
		 * @return the c2f9
		 */
		public String getC2f9() {
			return c2f9;
		}

		/**
		 * @param c2f9 the c2f9 to set
		 */
		public void setC2f9(String c2f9) {
			this.c2f9 = c2f9;
		}

		/**
		 * @return the c2f10
		 */
		public String getC2f10() {
			return c2f10;
		}

		/**
		 * @param c2f10 the c2f10 to set
		 */
		public void setC2f10(String c2f10) {
			this.c2f10 = c2f10;
		}

		/**
		 * @return the c2f11
		 */
		public String getC2f11() {
			return c2f11;
		}

		/**
		 * @param c2f11 the c2f11 to set
		 */
		public void setC2f11(String c2f11) {
			this.c2f11 = c2f11;
		}

		/**
		 * @return the c2f12
		 */
		public String getC2f12() {
			return c2f12;
		}

		/**
		 * @param c2f12 the c2f12 to set
		 */
		public void setC2f12(String c2f12) {
			this.c2f12 = c2f12;
		}

		/**
		 * @return the c2f13
		 */
		public String getC2f13() {
			return c2f13;
		}

		/**
		 * @param c2f13 the c2f13 to set
		 */
		public void setC2f13(String c2f13) {
			this.c2f13 = c2f13;
		}

		/**
		 * @return the c2f14
		 */
		public String getC2f14() {
			return c2f14;
		}

		/**
		 * @param c2f14 the c2f14 to set
		 */
		public void setC2f14(String c2f14) {
			this.c2f14 = c2f14;
		}

		/**
		 * @return the c2f15
		 */
		public String getC2f15() {
			return c2f15;
		}

		/**
		 * @param c2f15 the c2f15 to set
		 */
		public void setC2f15(String c2f15) {
			this.c2f15 = c2f15;
		}

		/**
		 * @return the c2f16
		 */
		public String getC2f16() {
			return c2f16;
		}

		/**
		 * @param c2f16 the c2f16 to set
		 */
		public void setC2f16(String c2f16) {
			this.c2f16 = c2f16;
		}

		/**
		 * @return the c2f17
		 */
		public String getC2f17() {
			return c2f17;
		}

		/**
		 * @param c2f17 the c2f17 to set
		 */
		public void setC2f17(String c2f17) {
			this.c2f17 = c2f17;
		}

		/**
		 * @return the c2f18
		 */
		public String getC2f18() {
			return c2f18;
		}

		/**
		 * @param c2f18 the c2f18 to set
		 */
		public void setC2f18(String c2f18) {
			this.c2f18 = c2f18;
		}

		/**
		 * @return the c2f19
		 */
		public String getC2f19() {
			return c2f19;
		}

		/**
		 * @param c2f19 the c2f19 to set
		 */
		public void setC2f19(String c2f19) {
			this.c2f19 = c2f19;
		}

		/**
		 * @return the c3f1
		 */
		public String getC3f1() {
			return c3f1;
		}

		/**
		 * @param c3f1 the c3f1 to set
		 */
		public void setC3f1(String c3f1) {
			this.c3f1 = c3f1;
		}

		/**
		 * @return the c3f2
		 */
		public String getC3f2() {
			return c3f2;
		}

		/**
		 * @param c3f2 the c3f2 to set
		 */
		public void setC3f2(String c3f2) {
			this.c3f2 = c3f2;
		}

		/**
		 * @return the c3f3
		 */
		public String getC3f3() {
			return c3f3;
		}

		/**
		 * @param c3f3 the c3f3 to set
		 */
		public void setC3f3(String c3f3) {
			this.c3f3 = c3f3;
		}

		/**
		 * @return the c3f4
		 */
		public String getC3f4() {
			return c3f4;
		}

		/**
		 * @param c3f4 the c3f4 to set
		 */
		public void setC3f4(String c3f4) {
			this.c3f4 = c3f4;
		}

		/**
		 * @return the c3f5
		 */
		public String getC3f5() {
			return c3f5;
		}

		/**
		 * @param c3f5 the c3f5 to set
		 */
		public void setC3f5(String c3f5) {
			this.c3f5 = c3f5;
		}

		/**
		 * @return the c3f6
		 */
		public String getC3f6() {
			return c3f6;
		}

		/**
		 * @param c3f6 the c3f6 to set
		 */
		public void setC3f6(String c3f6) {
			this.c3f6 = c3f6;
		}

		/**
		 * @return the c3f7
		 */
		public String getC3f7() {
			return c3f7;
		}

		/**
		 * @param c3f7 the c3f7 to set
		 */
		public void setC3f7(String c3f7) {
			this.c3f7 = c3f7;
		}

		/**
		 * @return the c3f8
		 */
		public String getC3f8() {
			return c3f8;
		}

		/**
		 * @param c3f8 the c3f8 to set
		 */
		public void setC3f8(String c3f8) {
			this.c3f8 = c3f8;
		}

		/**
		 * @return the c3f9
		 */
		public String getC3f9() {
			return c3f9;
		}

		/**
		 * @param c3f9 the c3f9 to set
		 */
		public void setC3f9(String c3f9) {
			this.c3f9 = c3f9;
		}

		/**
		 * @return the c3f10
		 */
		public String getC3f10() {
			return c3f10;
		}

		/**
		 * @param c3f10 the c3f10 to set
		 */
		public void setC3f10(String c3f10) {
			this.c3f10 = c3f10;
		}

		/**
		 * @return the c3f11
		 */
		public String getC3f11() {
			return c3f11;
		}

		/**
		 * @param c3f11 the c3f11 to set
		 */
		public void setC3f11(String c3f11) {
			this.c3f11 = c3f11;
		}

		/**
		 * @return the c3f12
		 */
		public String getC3f12() {
			return c3f12;
		}

		/**
		 * @param c3f12 the c3f12 to set
		 */
		public void setC3f12(String c3f12) {
			this.c3f12 = c3f12;
		}

		/**
		 * @return the c3f13
		 */
		public String getC3f13() {
			return c3f13;
		}

		/**
		 * @param c3f13 the c3f13 to set
		 */
		public void setC3f13(String c3f13) {
			this.c3f13 = c3f13;
		}

		/**
		 * @return the c3f14
		 */
		public String getC3f14() {
			return c3f14;
		}

		/**
		 * @param c3f14 the c3f14 to set
		 */
		public void setC3f14(String c3f14) {
			this.c3f14 = c3f14;
		}

		/**
		 * @return the c3f15
		 */
		public String getC3f15() {
			return c3f15;
		}

		/**
		 * @param c3f15 the c3f15 to set
		 */
		public void setC3f15(String c3f15) {
			this.c3f15 = c3f15;
		}

		/**
		 * @return the c3f16
		 */
		public String getC3f16() {
			return c3f16;
		}

		/**
		 * @param c3f16 the c3f16 to set
		 */
		public void setC3f16(String c3f16) {
			this.c3f16 = c3f16;
		}

		/**
		 * @return the c3f17
		 */
		public String getC3f17() {
			return c3f17;
		}

		/**
		 * @param c3f17 the c3f17 to set
		 */
		public void setC3f17(String c3f17) {
			this.c3f17 = c3f17;
		}

		/**
		 * @return the c3f18
		 */
		public String getC3f18() {
			return c3f18;
		}

		/**
		 * @param c3f18 the c3f18 to set
		 */
		public void setC3f18(String c3f18) {
			this.c3f18 = c3f18;
		}

		/**
		 * @return the c3f19
		 */
		public String getC3f19() {
			return c3f19;
		}

		/**
		 * @param c3f19 the c3f19 to set
		 */
		public void setC3f19(String c3f19) {
			this.c3f19 = c3f19;
		}

		/**
		 * @return the c4f1
		 */
		public String getC4f1() {
			return c4f1;
		}

		/**
		 * @param c4f1 the c4f1 to set
		 */
		public void setC4f1(String c4f1) {
			this.c4f1 = c4f1;
		}

		/**
		 * @return the c4f2
		 */
		public String getC4f2() {
			return c4f2;
		}

		/**
		 * @param c4f2 the c4f2 to set
		 */
		public void setC4f2(String c4f2) {
			this.c4f2 = c4f2;
		}

		/**
		 * @return the c4f3
		 */
		public String getC4f3() {
			return c4f3;
		}

		/**
		 * @param c4f3 the c4f3 to set
		 */
		public void setC4f3(String c4f3) {
			this.c4f3 = c4f3;
		}

		/**
		 * @return the c4f4
		 */
		public String getC4f4() {
			return c4f4;
		}

		/**
		 * @param c4f4 the c4f4 to set
		 */
		public void setC4f4(String c4f4) {
			this.c4f4 = c4f4;
		}

		/**
		 * @return the c4f5
		 */
		public String getC4f5() {
			return c4f5;
		}

		/**
		 * @param c4f5 the c4f5 to set
		 */
		public void setC4f5(String c4f5) {
			this.c4f5 = c4f5;
		}

		/**
		 * @return the c4f6
		 */
		public String getC4f6() {
			return c4f6;
		}

		/**
		 * @param c4f6 the c4f6 to set
		 */
		public void setC4f6(String c4f6) {
			this.c4f6 = c4f6;
		}

		/**
		 * @return the c4f7
		 */
		public String getC4f7() {
			return c4f7;
		}

		/**
		 * @param c4f7 the c4f7 to set
		 */
		public void setC4f7(String c4f7) {
			this.c4f7 = c4f7;
		}

		/**
		 * @return the c4f8
		 */
		public String getC4f8() {
			return c4f8;
		}

		/**
		 * @param c4f8 the c4f8 to set
		 */
		public void setC4f8(String c4f8) {
			this.c4f8 = c4f8;
		}

		/**
		 * @return the c4f9
		 */
		public String getC4f9() {
			return c4f9;
		}

		/**
		 * @param c4f9 the c4f9 to set
		 */
		public void setC4f9(String c4f9) {
			this.c4f9 = c4f9;
		}

		/**
		 * @return the c4f10
		 */
		public String getC4f10() {
			return c4f10;
		}

		/**
		 * @param c4f10 the c4f10 to set
		 */
		public void setC4f10(String c4f10) {
			this.c4f10 = c4f10;
		}

		/**
		 * @return the c4f11
		 */
		public String getC4f11() {
			return c4f11;
		}

		/**
		 * @param c4f11 the c4f11 to set
		 */
		public void setC4f11(String c4f11) {
			this.c4f11 = c4f11;
		}

		/**
		 * @return the c4f12
		 */
		public String getC4f12() {
			return c4f12;
		}

		/**
		 * @param c4f12 the c4f12 to set
		 */
		public void setC4f12(String c4f12) {
			this.c4f12 = c4f12;
		}

		/**
		 * @return the c4f13
		 */
		public String getC4f13() {
			return c4f13;
		}

		/**
		 * @param c4f13 the c4f13 to set
		 */
		public void setC4f13(String c4f13) {
			this.c4f13 = c4f13;
		}

		/**
		 * @return the c4f14
		 */
		public String getC4f14() {
			return c4f14;
		}

		/**
		 * @param c4f14 the c4f14 to set
		 */
		public void setC4f14(String c4f14) {
			this.c4f14 = c4f14;
		}

		/**
		 * @return the c4f15
		 */
		public String getC4f15() {
			return c4f15;
		}

		/**
		 * @param c4f15 the c4f15 to set
		 */
		public void setC4f15(String c4f15) {
			this.c4f15 = c4f15;
		}

		/**
		 * @return the c4f16
		 */
		public String getC4f16() {
			return c4f16;
		}

		/**
		 * @param c4f16 the c4f16 to set
		 */
		public void setC4f16(String c4f16) {
			this.c4f16 = c4f16;
		}

		/**
		 * @return the c4f17
		 */
		public String getC4f17() {
			return c4f17;
		}

		/**
		 * @param c4f17 the c4f17 to set
		 */
		public void setC4f17(String c4f17) {
			this.c4f17 = c4f17;
		}

		/**
		 * @return the c4f18
		 */
		public String getC4f18() {
			return c4f18;
		}

		/**
		 * @param c4f18 the c4f18 to set
		 */
		public void setC4f18(String c4f18) {
			this.c4f18 = c4f18;
		}

		/**
		 * @return the c4f19
		 */
		public String getC4f19() {
			return c4f19;
		}

		/**
		 * @param c4f19 the c4f19 to set
		 */
		public void setC4f19(String c4f19) {
			this.c4f19 = c4f19;
		}

		/**
		 * @return the c5f1
		 */
		public String getC5f1() {
			return c5f1;
		}

		/**
		 * @param c5f1 the c5f1 to set
		 */
		public void setC5f1(String c5f1) {
			this.c5f1 = c5f1;
		}

		/**
		 * @return the c5f2
		 */
		public String getC5f2() {
			return c5f2;
		}

		/**
		 * @param c5f2 the c5f2 to set
		 */
		public void setC5f2(String c5f2) {
			this.c5f2 = c5f2;
		}

		/**
		 * @return the c5f3
		 */
		public String getC5f3() {
			return c5f3;
		}

		/**
		 * @param c5f3 the c5f3 to set
		 */
		public void setC5f3(String c5f3) {
			this.c5f3 = c5f3;
		}

		/**
		 * @return the c5f4
		 */
		public String getC5f4() {
			return c5f4;
		}

		/**
		 * @param c5f4 the c5f4 to set
		 */
		public void setC5f4(String c5f4) {
			this.c5f4 = c5f4;
		}

		/**
		 * @return the c5f5
		 */
		public String getC5f5() {
			return c5f5;
		}

		/**
		 * @param c5f5 the c5f5 to set
		 */
		public void setC5f5(String c5f5) {
			this.c5f5 = c5f5;
		}

		/**
		 * @return the c5f6
		 */
		public String getC5f6() {
			return c5f6;
		}

		/**
		 * @param c5f6 the c5f6 to set
		 */
		public void setC5f6(String c5f6) {
			this.c5f6 = c5f6;
		}

		/**
		 * @return the c5f7
		 */
		public String getC5f7() {
			return c5f7;
		}

		/**
		 * @param c5f7 the c5f7 to set
		 */
		public void setC5f7(String c5f7) {
			this.c5f7 = c5f7;
		}

		/**
		 * @return the c5f8
		 */
		public String getC5f8() {
			return c5f8;
		}

		/**
		 * @param c5f8 the c5f8 to set
		 */
		public void setC5f8(String c5f8) {
			this.c5f8 = c5f8;
		}

		/**
		 * @return the c5f9
		 */
		public String getC5f9() {
			return c5f9;
		}

		/**
		 * @param c5f9 the c5f9 to set
		 */
		public void setC5f9(String c5f9) {
			this.c5f9 = c5f9;
		}

		/**
		 * @return the c5f10
		 */
		public String getC5f10() {
			return c5f10;
		}

		/**
		 * @param c5f10 the c5f10 to set
		 */
		public void setC5f10(String c5f10) {
			this.c5f10 = c5f10;
		}

		/**
		 * @return the c5f11
		 */
		public String getC5f11() {
			return c5f11;
		}

		/**
		 * @param c5f11 the c5f11 to set
		 */
		public void setC5f11(String c5f11) {
			this.c5f11 = c5f11;
		}

		/**
		 * @return the c5f12
		 */
		public String getC5f12() {
			return c5f12;
		}

		/**
		 * @param c5f12 the c5f12 to set
		 */
		public void setC5f12(String c5f12) {
			this.c5f12 = c5f12;
		}

		/**
		 * @return the c5f13
		 */
		public String getC5f13() {
			return c5f13;
		}

		/**
		 * @param c5f13 the c5f13 to set
		 */
		public void setC5f13(String c5f13) {
			this.c5f13 = c5f13;
		}

		/**
		 * @return the c5f14
		 */
		public String getC5f14() {
			return c5f14;
		}

		/**
		 * @param c5f14 the c5f14 to set
		 */
		public void setC5f14(String c5f14) {
			this.c5f14 = c5f14;
		}

		/**
		 * @return the c5f15
		 */
		public String getC5f15() {
			return c5f15;
		}

		/**
		 * @param c5f15 the c5f15 to set
		 */
		public void setC5f15(String c5f15) {
			this.c5f15 = c5f15;
		}

		/**
		 * @return the c5f16
		 */
		public String getC5f16() {
			return c5f16;
		}

		/**
		 * @param c5f16 the c5f16 to set
		 */
		public void setC5f16(String c5f16) {
			this.c5f16 = c5f16;
		}

		/**
		 * @return the c5f17
		 */
		public String getC5f17() {
			return c5f17;
		}

		/**
		 * @param c5f17 the c5f17 to set
		 */
		public void setC5f17(String c5f17) {
			this.c5f17 = c5f17;
		}

		/**
		 * @return the c5f18
		 */
		public String getC5f18() {
			return c5f18;
		}

		/**
		 * @param c5f18 the c5f18 to set
		 */
		public void setC5f18(String c5f18) {
			this.c5f18 = c5f18;
		}

		/**
		 * @return the c5f19
		 */
		public String getC5f19() {
			return c5f19;
		}

		/**
		 * @param c5f19 the c5f19 to set
		 */
		public void setC5f19(String c5f19) {
			this.c5f19 = c5f19;
		}

		/**
		 * @return the c6f1
		 */
		public String getC6f1() {
			return c6f1;
		}

		/**
		 * @param c6f1 the c6f1 to set
		 */
		public void setC6f1(String c6f1) {
			this.c6f1 = c6f1;
		}

		/**
		 * @return the c6f2
		 */
		public String getC6f2() {
			return c6f2;
		}

		/**
		 * @param c6f2 the c6f2 to set
		 */
		public void setC6f2(String c6f2) {
			this.c6f2 = c6f2;
		}

		/**
		 * @return the c6f3
		 */
		public String getC6f3() {
			return c6f3;
		}

		/**
		 * @param c6f3 the c6f3 to set
		 */
		public void setC6f3(String c6f3) {
			this.c6f3 = c6f3;
		}

		/**
		 * @return the c6f4
		 */
		public String getC6f4() {
			return c6f4;
		}

		/**
		 * @param c6f4 the c6f4 to set
		 */
		public void setC6f4(String c6f4) {
			this.c6f4 = c6f4;
		}

		/**
		 * @return the c6f5
		 */
		public String getC6f5() {
			return c6f5;
		}

		/**
		 * @param c6f5 the c6f5 to set
		 */
		public void setC6f5(String c6f5) {
			this.c6f5 = c6f5;
		}

		/**
		 * @return the c6f6
		 */
		public String getC6f6() {
			return c6f6;
		}

		/**
		 * @param c6f6 the c6f6 to set
		 */
		public void setC6f6(String c6f6) {
			this.c6f6 = c6f6;
		}

		/**
		 * @return the c6f7
		 */
		public String getC6f7() {
			return c6f7;
		}

		/**
		 * @param c6f7 the c6f7 to set
		 */
		public void setC6f7(String c6f7) {
			this.c6f7 = c6f7;
		}

		/**
		 * @return the c6f8
		 */
		public String getC6f8() {
			return c6f8;
		}

		/**
		 * @param c6f8 the c6f8 to set
		 */
		public void setC6f8(String c6f8) {
			this.c6f8 = c6f8;
		}

		/**
		 * @return the c6f9
		 */
		public String getC6f9() {
			return c6f9;
		}

		/**
		 * @param c6f9 the c6f9 to set
		 */
		public void setC6f9(String c6f9) {
			this.c6f9 = c6f9;
		}

		/**
		 * @return the c6f10
		 */
		public String getC6f10() {
			return c6f10;
		}

		/**
		 * @param c6f10 the c6f10 to set
		 */
		public void setC6f10(String c6f10) {
			this.c6f10 = c6f10;
		}

		/**
		 * @return the c6f11
		 */
		public String getC6f11() {
			return c6f11;
		}

		/**
		 * @param c6f11 the c6f11 to set
		 */
		public void setC6f11(String c6f11) {
			this.c6f11 = c6f11;
		}

		/**
		 * @return the c6f12
		 */
		public String getC6f12() {
			return c6f12;
		}

		/**
		 * @param c6f12 the c6f12 to set
		 */
		public void setC6f12(String c6f12) {
			this.c6f12 = c6f12;
		}

		/**
		 * @return the c6f13
		 */
		public String getC6f13() {
			return c6f13;
		}

		/**
		 * @param c6f13 the c6f13 to set
		 */
		public void setC6f13(String c6f13) {
			this.c6f13 = c6f13;
		}

		/**
		 * @return the c6f14
		 */
		public String getC6f14() {
			return c6f14;
		}

		/**
		 * @param c6f14 the c6f14 to set
		 */
		public void setC6f14(String c6f14) {
			this.c6f14 = c6f14;
		}

		/**
		 * @return the c6f15
		 */
		public String getC6f15() {
			return c6f15;
		}

		/**
		 * @param c6f15 the c6f15 to set
		 */
		public void setC6f15(String c6f15) {
			this.c6f15 = c6f15;
		}

		/**
		 * @return the c6f16
		 */
		public String getC6f16() {
			return c6f16;
		}

		/**
		 * @param c6f16 the c6f16 to set
		 */
		public void setC6f16(String c6f16) {
			this.c6f16 = c6f16;
		}

		/**
		 * @return the c6f17
		 */
		public String getC6f17() {
			return c6f17;
		}

		/**
		 * @param c6f17 the c6f17 to set
		 */
		public void setC6f17(String c6f17) {
			this.c6f17 = c6f17;
		}

		/**
		 * @return the c6f18
		 */
		public String getC6f18() {
			return c6f18;
		}

		/**
		 * @param c6f18 the c6f18 to set
		 */
		public void setC6f18(String c6f18) {
			this.c6f18 = c6f18;
		}

		/**
		 * @return the c6f19
		 */
		public String getC6f19() {
			return c6f19;
		}

		/**
		 * @param c6f19 the c6f19 to set
		 */
		public void setC6f19(String c6f19) {
			this.c6f19 = c6f19;
		}

		/**
		 * @return the c1f1v
		 */
		public String getC1f1v() {
			return c1f1v;
		}

		/**
		 * @param c1f1v the c1f1v to set
		 */
		public void setC1f1v(String c1f1v) {
			this.c1f1v = c1f1v;
		}

		/**
		 * @return the c1f2v
		 */
		public String getC1f2v() {
			return c1f2v;
		}

		/**
		 * @param c1f2v the c1f2v to set
		 */
		public void setC1f2v(String c1f2v) {
			this.c1f2v = c1f2v;
		}

		/**
		 * @return the c1f3v
		 */
		public String getC1f3v() {
			return c1f3v;
		}

		/**
		 * @param c1f3v the c1f3v to set
		 */
		public void setC1f3v(String c1f3v) {
			this.c1f3v = c1f3v;
		}

		/**
		 * @return the c1f4v
		 */
		public String getC1f4v() {
			return c1f4v;
		}

		/**
		 * @param c1f4v the c1f4v to set
		 */
		public void setC1f4v(String c1f4v) {
			this.c1f4v = c1f4v;
		}

		/**
		 * @return the c1f5v
		 */
		public String getC1f5v() {
			return c1f5v;
		}

		/**
		 * @param c1f5v the c1f5v to set
		 */
		public void setC1f5v(String c1f5v) {
			this.c1f5v = c1f5v;
		}

		/**
		 * @return the c1f6v
		 */
		public String getC1f6v() {
			return c1f6v;
		}

		/**
		 * @param c1f6v the c1f6v to set
		 */
		public void setC1f6v(String c1f6v) {
			this.c1f6v = c1f6v;
		}

		/**
		 * @return the c1f7v
		 */
		public String getC1f7v() {
			return c1f7v;
		}

		/**
		 * @param c1f7v the c1f7v to set
		 */
		public void setC1f7v(String c1f7v) {
			this.c1f7v = c1f7v;
		}

		/**
		 * @return the c1f8v
		 */
		public String getC1f8v() {
			return c1f8v;
		}

		/**
		 * @param c1f8v the c1f8v to set
		 */
		public void setC1f8v(String c1f8v) {
			this.c1f8v = c1f8v;
		}

		/**
		 * @return the c1f9v
		 */
		public String getC1f9v() {
			return c1f9v;
		}

		/**
		 * @param c1f9v the c1f9v to set
		 */
		public void setC1f9v(String c1f9v) {
			this.c1f9v = c1f9v;
		}

		/**
		 * @return the c1f10v
		 */
		public String getC1f10v() {
			return c1f10v;
		}

		/**
		 * @param c1f10v the c1f10v to set
		 */
		public void setC1f10v(String c1f10v) {
			this.c1f10v = c1f10v;
		}

		/**
		 * @return the c1f11v
		 */
		public String getC1f11v() {
			return c1f11v;
		}

		/**
		 * @param c1f11v the c1f11v to set
		 */
		public void setC1f11v(String c1f11v) {
			this.c1f11v = c1f11v;
		}

		/**
		 * @return the c1f12v
		 */
		public String getC1f12v() {
			return c1f12v;
		}

		/**
		 * @param c1f12v the c1f12v to set
		 */
		public void setC1f12v(String c1f12v) {
			this.c1f12v = c1f12v;
		}

		/**
		 * @return the c1f13v
		 */
		public String getC1f13v() {
			return c1f13v;
		}

		/**
		 * @param c1f13v the c1f13v to set
		 */
		public void setC1f13v(String c1f13v) {
			this.c1f13v = c1f13v;
		}

		/**
		 * @return the c1f14v
		 */
		public String getC1f14v() {
			return c1f14v;
		}

		/**
		 * @param c1f14v the c1f14v to set
		 */
		public void setC1f14v(String c1f14v) {
			this.c1f14v = c1f14v;
		}

		/**
		 * @return the c1f15v
		 */
		public String getC1f15v() {
			return c1f15v;
		}

		/**
		 * @param c1f15v the c1f15v to set
		 */
		public void setC1f15v(String c1f15v) {
			this.c1f15v = c1f15v;
		}

		/**
		 * @return the c1f16v
		 */
		public String getC1f16v() {
			return c1f16v;
		}

		/**
		 * @param c1f16v the c1f16v to set
		 */
		public void setC1f16v(String c1f16v) {
			this.c1f16v = c1f16v;
		}

		/**
		 * @return the c1f17v
		 */
		public String getC1f17v() {
			return c1f17v;
		}

		/**
		 * @param c1f17v the c1f17v to set
		 */
		public void setC1f17v(String c1f17v) {
			this.c1f17v = c1f17v;
		}

		/**
		 * @return the c1f18v
		 */
		public String getC1f18v() {
			return c1f18v;
		}

		/**
		 * @param c1f18v the c1f18v to set
		 */
		public void setC1f18v(String c1f18v) {
			this.c1f18v = c1f18v;
		}

		/**
		 * @return the c1f19v
		 */
		public String getC1f19v() {
			return c1f19v;
		}

		/**
		 * @param c1f19v the c1f19v to set
		 */
		public void setC1f19v(String c1f19v) {
			this.c1f19v = c1f19v;
		}

		/**
		 * @return the c2f1v
		 */
		public String getC2f1v() {
			return c2f1v;
		}

		/**
		 * @param c2f1v the c2f1v to set
		 */
		public void setC2f1v(String c2f1v) {
			this.c2f1v = c2f1v;
		}

		/**
		 * @return the c2f2v
		 */
		public String getC2f2v() {
			return c2f2v;
		}

		/**
		 * @param c2f2v the c2f2v to set
		 */
		public void setC2f2v(String c2f2v) {
			this.c2f2v = c2f2v;
		}

		/**
		 * @return the c2f3v
		 */
		public String getC2f3v() {
			return c2f3v;
		}

		/**
		 * @param c2f3v the c2f3v to set
		 */
		public void setC2f3v(String c2f3v) {
			this.c2f3v = c2f3v;
		}

		/**
		 * @return the c2f4v
		 */
		public String getC2f4v() {
			return c2f4v;
		}

		/**
		 * @param c2f4v the c2f4v to set
		 */
		public void setC2f4v(String c2f4v) {
			this.c2f4v = c2f4v;
		}

		/**
		 * @return the c2f5v
		 */
		public String getC2f5v() {
			return c2f5v;
		}

		/**
		 * @param c2f5v the c2f5v to set
		 */
		public void setC2f5v(String c2f5v) {
			this.c2f5v = c2f5v;
		}

		/**
		 * @return the c2f6v
		 */
		public String getC2f6v() {
			return c2f6v;
		}

		/**
		 * @param c2f6v the c2f6v to set
		 */
		public void setC2f6v(String c2f6v) {
			this.c2f6v = c2f6v;
		}

		/**
		 * @return the c2f7v
		 */
		public String getC2f7v() {
			return c2f7v;
		}

		/**
		 * @param c2f7v the c2f7v to set
		 */
		public void setC2f7v(String c2f7v) {
			this.c2f7v = c2f7v;
		}

		/**
		 * @return the c2f8v
		 */
		public String getC2f8v() {
			return c2f8v;
		}

		/**
		 * @param c2f8v the c2f8v to set
		 */
		public void setC2f8v(String c2f8v) {
			this.c2f8v = c2f8v;
		}

		/**
		 * @return the c2f9v
		 */
		public String getC2f9v() {
			return c2f9v;
		}

		/**
		 * @param c2f9v the c2f9v to set
		 */
		public void setC2f9v(String c2f9v) {
			this.c2f9v = c2f9v;
		}

		/**
		 * @return the c2f10v
		 */
		public String getC2f10v() {
			return c2f10v;
		}

		/**
		 * @param c2f10v the c2f10v to set
		 */
		public void setC2f10v(String c2f10v) {
			this.c2f10v = c2f10v;
		}

		/**
		 * @return the c2f11v
		 */
		public String getC2f11v() {
			return c2f11v;
		}

		/**
		 * @param c2f11v the c2f11v to set
		 */
		public void setC2f11v(String c2f11v) {
			this.c2f11v = c2f11v;
		}

		/**
		 * @return the c2f12v
		 */
		public String getC2f12v() {
			return c2f12v;
		}

		/**
		 * @param c2f12v the c2f12v to set
		 */
		public void setC2f12v(String c2f12v) {
			this.c2f12v = c2f12v;
		}

		/**
		 * @return the c2f13v
		 */
		public String getC2f13v() {
			return c2f13v;
		}

		/**
		 * @param c2f13v the c2f13v to set
		 */
		public void setC2f13v(String c2f13v) {
			this.c2f13v = c2f13v;
		}

		/**
		 * @return the c2f14v
		 */
		public String getC2f14v() {
			return c2f14v;
		}

		/**
		 * @param c2f14v the c2f14v to set
		 */
		public void setC2f14v(String c2f14v) {
			this.c2f14v = c2f14v;
		}

		/**
		 * @return the c2f15v
		 */
		public String getC2f15v() {
			return c2f15v;
		}

		/**
		 * @param c2f15v the c2f15v to set
		 */
		public void setC2f15v(String c2f15v) {
			this.c2f15v = c2f15v;
		}

		/**
		 * @return the c2f16v
		 */
		public String getC2f16v() {
			return c2f16v;
		}

		/**
		 * @param c2f16v the c2f16v to set
		 */
		public void setC2f16v(String c2f16v) {
			this.c2f16v = c2f16v;
		}

		/**
		 * @return the c2f17v
		 */
		public String getC2f17v() {
			return c2f17v;
		}

		/**
		 * @param c2f17v the c2f17v to set
		 */
		public void setC2f17v(String c2f17v) {
			this.c2f17v = c2f17v;
		}

		/**
		 * @return the c2f18v
		 */
		public String getC2f18v() {
			return c2f18v;
		}

		/**
		 * @param c2f18v the c2f18v to set
		 */
		public void setC2f18v(String c2f18v) {
			this.c2f18v = c2f18v;
		}

		/**
		 * @return the c2f19v
		 */
		public String getC2f19v() {
			return c2f19v;
		}

		/**
		 * @param c2f19v the c2f19v to set
		 */
		public void setC2f19v(String c2f19v) {
			this.c2f19v = c2f19v;
		}

		/**
		 * @return the c3f1v
		 */
		public String getC3f1v() {
			return c3f1v;
		}

		/**
		 * @param c3f1v the c3f1v to set
		 */
		public void setC3f1v(String c3f1v) {
			this.c3f1v = c3f1v;
		}

		/**
		 * @return the c3f2v
		 */
		public String getC3f2v() {
			return c3f2v;
		}

		/**
		 * @param c3f2v the c3f2v to set
		 */
		public void setC3f2v(String c3f2v) {
			this.c3f2v = c3f2v;
		}

		/**
		 * @return the c3f3v
		 */
		public String getC3f3v() {
			return c3f3v;
		}

		/**
		 * @param c3f3v the c3f3v to set
		 */
		public void setC3f3v(String c3f3v) {
			this.c3f3v = c3f3v;
		}

		/**
		 * @return the c3f4v
		 */
		public String getC3f4v() {
			return c3f4v;
		}

		/**
		 * @param c3f4v the c3f4v to set
		 */
		public void setC3f4v(String c3f4v) {
			this.c3f4v = c3f4v;
		}

		/**
		 * @return the c3f5v
		 */
		public String getC3f5v() {
			return c3f5v;
		}

		/**
		 * @param c3f5v the c3f5v to set
		 */
		public void setC3f5v(String c3f5v) {
			this.c3f5v = c3f5v;
		}

		/**
		 * @return the c3f6v
		 */
		public String getC3f6v() {
			return c3f6v;
		}

		/**
		 * @param c3f6v the c3f6v to set
		 */
		public void setC3f6v(String c3f6v) {
			this.c3f6v = c3f6v;
		}

		/**
		 * @return the c3f7v
		 */
		public String getC3f7v() {
			return c3f7v;
		}

		/**
		 * @param c3f7v the c3f7v to set
		 */
		public void setC3f7v(String c3f7v) {
			this.c3f7v = c3f7v;
		}

		/**
		 * @return the c3f8v
		 */
		public String getC3f8v() {
			return c3f8v;
		}

		/**
		 * @param c3f8v the c3f8v to set
		 */
		public void setC3f8v(String c3f8v) {
			this.c3f8v = c3f8v;
		}

		/**
		 * @return the c3f9v
		 */
		public String getC3f9v() {
			return c3f9v;
		}

		/**
		 * @param c3f9v the c3f9v to set
		 */
		public void setC3f9v(String c3f9v) {
			this.c3f9v = c3f9v;
		}

		/**
		 * @return the c3f10v
		 */
		public String getC3f10v() {
			return c3f10v;
		}

		/**
		 * @param c3f10v the c3f10v to set
		 */
		public void setC3f10v(String c3f10v) {
			this.c3f10v = c3f10v;
		}

		/**
		 * @return the c3f11v
		 */
		public String getC3f11v() {
			return c3f11v;
		}

		/**
		 * @param c3f11v the c3f11v to set
		 */
		public void setC3f11v(String c3f11v) {
			this.c3f11v = c3f11v;
		}

		/**
		 * @return the c3f12v
		 */
		public String getC3f12v() {
			return c3f12v;
		}

		/**
		 * @param c3f12v the c3f12v to set
		 */
		public void setC3f12v(String c3f12v) {
			this.c3f12v = c3f12v;
		}

		/**
		 * @return the c3f13v
		 */
		public String getC3f13v() {
			return c3f13v;
		}

		/**
		 * @param c3f13v the c3f13v to set
		 */
		public void setC3f13v(String c3f13v) {
			this.c3f13v = c3f13v;
		}

		/**
		 * @return the c3f14v
		 */
		public String getC3f14v() {
			return c3f14v;
		}

		/**
		 * @param c3f14v the c3f14v to set
		 */
		public void setC3f14v(String c3f14v) {
			this.c3f14v = c3f14v;
		}

		/**
		 * @return the c3f15v
		 */
		public String getC3f15v() {
			return c3f15v;
		}

		/**
		 * @param c3f15v the c3f15v to set
		 */
		public void setC3f15v(String c3f15v) {
			this.c3f15v = c3f15v;
		}

		/**
		 * @return the c3f16v
		 */
		public String getC3f16v() {
			return c3f16v;
		}

		/**
		 * @param c3f16v the c3f16v to set
		 */
		public void setC3f16v(String c3f16v) {
			this.c3f16v = c3f16v;
		}

		/**
		 * @return the c3f17v
		 */
		public String getC3f17v() {
			return c3f17v;
		}

		/**
		 * @param c3f17v the c3f17v to set
		 */
		public void setC3f17v(String c3f17v) {
			this.c3f17v = c3f17v;
		}

		/**
		 * @return the c3f18v
		 */
		public String getC3f18v() {
			return c3f18v;
		}

		/**
		 * @param c3f18v the c3f18v to set
		 */
		public void setC3f18v(String c3f18v) {
			this.c3f18v = c3f18v;
		}

		/**
		 * @return the c3f19v
		 */
		public String getC3f19v() {
			return c3f19v;
		}

		/**
		 * @param c3f19v the c3f19v to set
		 */
		public void setC3f19v(String c3f19v) {
			this.c3f19v = c3f19v;
		}

		/**
		 * @return the c4f1v
		 */
		public String getC4f1v() {
			return c4f1v;
		}

		/**
		 * @param c4f1v the c4f1v to set
		 */
		public void setC4f1v(String c4f1v) {
			this.c4f1v = c4f1v;
		}

		/**
		 * @return the c4f2v
		 */
		public String getC4f2v() {
			return c4f2v;
		}

		/**
		 * @param c4f2v the c4f2v to set
		 */
		public void setC4f2v(String c4f2v) {
			this.c4f2v = c4f2v;
		}

		/**
		 * @return the c4f3v
		 */
		public String getC4f3v() {
			return c4f3v;
		}

		/**
		 * @param c4f3v the c4f3v to set
		 */
		public void setC4f3v(String c4f3v) {
			this.c4f3v = c4f3v;
		}

		/**
		 * @return the c4f4v
		 */
		public String getC4f4v() {
			return c4f4v;
		}

		/**
		 * @param c4f4v the c4f4v to set
		 */
		public void setC4f4v(String c4f4v) {
			this.c4f4v = c4f4v;
		}

		/**
		 * @return the c4f5v
		 */
		public String getC4f5v() {
			return c4f5v;
		}

		/**
		 * @param c4f5v the c4f5v to set
		 */
		public void setC4f5v(String c4f5v) {
			this.c4f5v = c4f5v;
		}

		/**
		 * @return the c4f6v
		 */
		public String getC4f6v() {
			return c4f6v;
		}

		/**
		 * @param c4f6v the c4f6v to set
		 */
		public void setC4f6v(String c4f6v) {
			this.c4f6v = c4f6v;
		}

		/**
		 * @return the c4f7v
		 */
		public String getC4f7v() {
			return c4f7v;
		}

		/**
		 * @param c4f7v the c4f7v to set
		 */
		public void setC4f7v(String c4f7v) {
			this.c4f7v = c4f7v;
		}

		/**
		 * @return the c4f8v
		 */
		public String getC4f8v() {
			return c4f8v;
		}

		/**
		 * @param c4f8v the c4f8v to set
		 */
		public void setC4f8v(String c4f8v) {
			this.c4f8v = c4f8v;
		}

		/**
		 * @return the c4f9v
		 */
		public String getC4f9v() {
			return c4f9v;
		}

		/**
		 * @param c4f9v the c4f9v to set
		 */
		public void setC4f9v(String c4f9v) {
			this.c4f9v = c4f9v;
		}

		/**
		 * @return the c4f10v
		 */
		public String getC4f10v() {
			return c4f10v;
		}

		/**
		 * @param c4f10v the c4f10v to set
		 */
		public void setC4f10v(String c4f10v) {
			this.c4f10v = c4f10v;
		}

		/**
		 * @return the c4f11v
		 */
		public String getC4f11v() {
			return c4f11v;
		}

		/**
		 * @param c4f11v the c4f11v to set
		 */
		public void setC4f11v(String c4f11v) {
			this.c4f11v = c4f11v;
		}

		/**
		 * @return the c4f12v
		 */
		public String getC4f12v() {
			return c4f12v;
		}

		/**
		 * @param c4f12v the c4f12v to set
		 */
		public void setC4f12v(String c4f12v) {
			this.c4f12v = c4f12v;
		}

		/**
		 * @return the c4f13v
		 */
		public String getC4f13v() {
			return c4f13v;
		}

		/**
		 * @param c4f13v the c4f13v to set
		 */
		public void setC4f13v(String c4f13v) {
			this.c4f13v = c4f13v;
		}

		/**
		 * @return the c4f14v
		 */
		public String getC4f14v() {
			return c4f14v;
		}

		/**
		 * @param c4f14v the c4f14v to set
		 */
		public void setC4f14v(String c4f14v) {
			this.c4f14v = c4f14v;
		}

		/**
		 * @return the c4f15v
		 */
		public String getC4f15v() {
			return c4f15v;
		}

		/**
		 * @param c4f15v the c4f15v to set
		 */
		public void setC4f15v(String c4f15v) {
			this.c4f15v = c4f15v;
		}

		/**
		 * @return the c4f16v
		 */
		public String getC4f16v() {
			return c4f16v;
		}

		/**
		 * @param c4f16v the c4f16v to set
		 */
		public void setC4f16v(String c4f16v) {
			this.c4f16v = c4f16v;
		}

		/**
		 * @return the c4f17v
		 */
		public String getC4f17v() {
			return c4f17v;
		}

		/**
		 * @param c4f17v the c4f17v to set
		 */
		public void setC4f17v(String c4f17v) {
			this.c4f17v = c4f17v;
		}

		/**
		 * @return the c4f18v
		 */
		public String getC4f18v() {
			return c4f18v;
		}

		/**
		 * @param c4f18v the c4f18v to set
		 */
		public void setC4f18v(String c4f18v) {
			this.c4f18v = c4f18v;
		}

		/**
		 * @return the c4f19v
		 */
		public String getC4f19v() {
			return c4f19v;
		}

		/**
		 * @param c4f19v the c4f19v to set
		 */
		public void setC4f19v(String c4f19v) {
			this.c4f19v = c4f19v;
		}

		/**
		 * @return the c5f1v
		 */
		public String getC5f1v() {
			return c5f1v;
		}

		/**
		 * @param c5f1v the c5f1v to set
		 */
		public void setC5f1v(String c5f1v) {
			this.c5f1v = c5f1v;
		}

		/**
		 * @return the c5f2v
		 */
		public String getC5f2v() {
			return c5f2v;
		}

		/**
		 * @param c5f2v the c5f2v to set
		 */
		public void setC5f2v(String c5f2v) {
			this.c5f2v = c5f2v;
		}

		/**
		 * @return the c5f3v
		 */
		public String getC5f3v() {
			return c5f3v;
		}

		/**
		 * @param c5f3v the c5f3v to set
		 */
		public void setC5f3v(String c5f3v) {
			this.c5f3v = c5f3v;
		}

		/**
		 * @return the c5f4v
		 */
		public String getC5f4v() {
			return c5f4v;
		}

		/**
		 * @param c5f4v the c5f4v to set
		 */
		public void setC5f4v(String c5f4v) {
			this.c5f4v = c5f4v;
		}

		/**
		 * @return the c5f5v
		 */
		public String getC5f5v() {
			return c5f5v;
		}

		/**
		 * @param c5f5v the c5f5v to set
		 */
		public void setC5f5v(String c5f5v) {
			this.c5f5v = c5f5v;
		}

		/**
		 * @return the c5f6v
		 */
		public String getC5f6v() {
			return c5f6v;
		}

		/**
		 * @param c5f6v the c5f6v to set
		 */
		public void setC5f6v(String c5f6v) {
			this.c5f6v = c5f6v;
		}

		/**
		 * @return the c5f7v
		 */
		public String getC5f7v() {
			return c5f7v;
		}

		/**
		 * @param c5f7v the c5f7v to set
		 */
		public void setC5f7v(String c5f7v) {
			this.c5f7v = c5f7v;
		}

		/**
		 * @return the c5f8v
		 */
		public String getC5f8v() {
			return c5f8v;
		}

		/**
		 * @param c5f8v the c5f8v to set
		 */
		public void setC5f8v(String c5f8v) {
			this.c5f8v = c5f8v;
		}

		/**
		 * @return the c5f9v
		 */
		public String getC5f9v() {
			return c5f9v;
		}

		/**
		 * @param c5f9v the c5f9v to set
		 */
		public void setC5f9v(String c5f9v) {
			this.c5f9v = c5f9v;
		}

		/**
		 * @return the c5f10v
		 */
		public String getC5f10v() {
			return c5f10v;
		}

		/**
		 * @param c5f10v the c5f10v to set
		 */
		public void setC5f10v(String c5f10v) {
			this.c5f10v = c5f10v;
		}

		/**
		 * @return the c5f11v
		 */
		public String getC5f11v() {
			return c5f11v;
		}

		/**
		 * @param c5f11v the c5f11v to set
		 */
		public void setC5f11v(String c5f11v) {
			this.c5f11v = c5f11v;
		}

		/**
		 * @return the c5f12v
		 */
		public String getC5f12v() {
			return c5f12v;
		}

		/**
		 * @param c5f12v the c5f12v to set
		 */
		public void setC5f12v(String c5f12v) {
			this.c5f12v = c5f12v;
		}

		/**
		 * @return the c5f13v
		 */
		public String getC5f13v() {
			return c5f13v;
		}

		/**
		 * @param c5f13v the c5f13v to set
		 */
		public void setC5f13v(String c5f13v) {
			this.c5f13v = c5f13v;
		}

		/**
		 * @return the c5f14v
		 */
		public String getC5f14v() {
			return c5f14v;
		}

		/**
		 * @param c5f14v the c5f14v to set
		 */
		public void setC5f14v(String c5f14v) {
			this.c5f14v = c5f14v;
		}

		/**
		 * @return the c5f15v
		 */
		public String getC5f15v() {
			return c5f15v;
		}

		/**
		 * @param c5f15v the c5f15v to set
		 */
		public void setC5f15v(String c5f15v) {
			this.c5f15v = c5f15v;
		}

		/**
		 * @return the c5f16v
		 */
		public String getC5f16v() {
			return c5f16v;
		}

		/**
		 * @param c5f16v the c5f16v to set
		 */
		public void setC5f16v(String c5f16v) {
			this.c5f16v = c5f16v;
		}

		/**
		 * @return the c5f17v
		 */
		public String getC5f17v() {
			return c5f17v;
		}

		/**
		 * @param c5f17v the c5f17v to set
		 */
		public void setC5f17v(String c5f17v) {
			this.c5f17v = c5f17v;
		}

		/**
		 * @return the c5f18v
		 */
		public String getC5f18v() {
			return c5f18v;
		}

		/**
		 * @param c5f18v the c5f18v to set
		 */
		public void setC5f18v(String c5f18v) {
			this.c5f18v = c5f18v;
		}

		/**
		 * @return the c5f19v
		 */
		public String getC5f19v() {
			return c5f19v;
		}

		/**
		 * @param c5f19v the c5f19v to set
		 */
		public void setC5f19v(String c5f19v) {
			this.c5f19v = c5f19v;
		}

		/**
		 * @return the c6f1v
		 */
		public String getC6f1v() {
			return c6f1v;
		}

		/**
		 * @param c6f1v the c6f1v to set
		 */
		public void setC6f1v(String c6f1v) {
			this.c6f1v = c6f1v;
		}

		/**
		 * @return the c6f2v
		 */
		public String getC6f2v() {
			return c6f2v;
		}

		/**
		 * @param c6f2v the c6f2v to set
		 */
		public void setC6f2v(String c6f2v) {
			this.c6f2v = c6f2v;
		}

		/**
		 * @return the c6f3v
		 */
		public String getC6f3v() {
			return c6f3v;
		}

		/**
		 * @param c6f3v the c6f3v to set
		 */
		public void setC6f3v(String c6f3v) {
			this.c6f3v = c6f3v;
		}

		/**
		 * @return the c6f4v
		 */
		public String getC6f4v() {
			return c6f4v;
		}

		/**
		 * @param c6f4v the c6f4v to set
		 */
		public void setC6f4v(String c6f4v) {
			this.c6f4v = c6f4v;
		}

		/**
		 * @return the c6f5v
		 */
		public String getC6f5v() {
			return c6f5v;
		}

		/**
		 * @param c6f5v the c6f5v to set
		 */
		public void setC6f5v(String c6f5v) {
			this.c6f5v = c6f5v;
		}

		/**
		 * @return the c6f6v
		 */
		public String getC6f6v() {
			return c6f6v;
		}

		/**
		 * @param c6f6v the c6f6v to set
		 */
		public void setC6f6v(String c6f6v) {
			this.c6f6v = c6f6v;
		}

		/**
		 * @return the c6f7v
		 */
		public String getC6f7v() {
			return c6f7v;
		}

		/**
		 * @param c6f7v the c6f7v to set
		 */
		public void setC6f7v(String c6f7v) {
			this.c6f7v = c6f7v;
		}

		/**
		 * @return the c6f8v
		 */
		public String getC6f8v() {
			return c6f8v;
		}

		/**
		 * @param c6f8v the c6f8v to set
		 */
		public void setC6f8v(String c6f8v) {
			this.c6f8v = c6f8v;
		}

		/**
		 * @return the c6f9v
		 */
		public String getC6f9v() {
			return c6f9v;
		}

		/**
		 * @param c6f9v the c6f9v to set
		 */
		public void setC6f9v(String c6f9v) {
			this.c6f9v = c6f9v;
		}

		/**
		 * @return the c6f10v
		 */
		public String getC6f10v() {
			return c6f10v;
		}

		/**
		 * @param c6f10v the c6f10v to set
		 */
		public void setC6f10v(String c6f10v) {
			this.c6f10v = c6f10v;
		}

		/**
		 * @return the c6f11v
		 */
		public String getC6f11v() {
			return c6f11v;
		}

		/**
		 * @param c6f11v the c6f11v to set
		 */
		public void setC6f11v(String c6f11v) {
			this.c6f11v = c6f11v;
		}

		/**
		 * @return the c6f12v
		 */
		public String getC6f12v() {
			return c6f12v;
		}

		/**
		 * @param c6f12v the c6f12v to set
		 */
		public void setC6f12v(String c6f12v) {
			this.c6f12v = c6f12v;
		}

		/**
		 * @return the c6f13v
		 */
		public String getC6f13v() {
			return c6f13v;
		}

		/**
		 * @param c6f13v the c6f13v to set
		 */
		public void setC6f13v(String c6f13v) {
			this.c6f13v = c6f13v;
		}

		/**
		 * @return the c6f14v
		 */
		public String getC6f14v() {
			return c6f14v;
		}

		/**
		 * @param c6f14v the c6f14v to set
		 */
		public void setC6f14v(String c6f14v) {
			this.c6f14v = c6f14v;
		}

		/**
		 * @return the c6f15v
		 */
		public String getC6f15v() {
			return c6f15v;
		}

		/**
		 * @param c6f15v the c6f15v to set
		 */
		public void setC6f15v(String c6f15v) {
			this.c6f15v = c6f15v;
		}

		/**
		 * @return the c6f16v
		 */
		public String getC6f16v() {
			return c6f16v;
		}

		/**
		 * @param c6f16v the c6f16v to set
		 */
		public void setC6f16v(String c6f16v) {
			this.c6f16v = c6f16v;
		}

		/**
		 * @return the c6f17v
		 */
		public String getC6f17v() {
			return c6f17v;
		}

		/**
		 * @param c6f17v the c6f17v to set
		 */
		public void setC6f17v(String c6f17v) {
			this.c6f17v = c6f17v;
		}

		/**
		 * @return the c6f18v
		 */
		public String getC6f18v() {
			return c6f18v;
		}

		/**
		 * @param c6f18v the c6f18v to set
		 */
		public void setC6f18v(String c6f18v) {
			this.c6f18v = c6f18v;
		}

		/**
		 * @return the c6f19v
		 */
		public String getC6f19v() {
			return c6f19v;
		}

		/**
		 * @param c6f19v the c6f19v to set
		 */
		public void setC6f19v(String c6f19v) {
			this.c6f19v = c6f19v;
		}

		/**
		 * @return the pk1
		 */
		public String getPk1() {
			return pk1;
		}

		/**
		 * @param pk1 the pk1 to set
		 */
		public void setPk1(String pk1) {
			this.pk1 = pk1;
		}

		/**
		 * @return the pk2
		 */
		public String getPk2() {
			return pk2;
		}

		/**
		 * @param pk2 the pk2 to set
		 */
		public void setPk2(String pk2) {
			this.pk2 = pk2;
		}

		/**
		 * @return the pk3
		 */
		public String getPk3() {
			return pk3;
		}

		/**
		 * @param pk3 the pk3 to set
		 */
		public void setPk3(String pk3) {
			this.pk3 = pk3;
		}

		/**
		 * @return the pk4
		 */
		public String getPk4() {
			return pk4;
		}

		/**
		 * @param pk4 the pk4 to set
		 */
		public void setPk4(String pk4) {
			this.pk4 = pk4;
		}

		/**
		 * @return the pk5
		 */
		public String getPk5() {
			return pk5;
		}

		/**
		 * @param pk5 the pk5 to set
		 */
		public void setPk5(String pk5) {
			this.pk5 = pk5;
		}

		/**
		 * @return the pk6
		 */
		public String getPk6() {
			return pk6;
		}

		/**
		 * @param pk6 the pk6 to set
		 */
		public void setPk6(String pk6) {
			this.pk6 = pk6;
		}

		/**
		 * @return the c1
		 */
		public String getC1() {
			return c1;
		}

		/**
		 * @param c1 the c1 to set
		 */
		public void setC1(String c1) {
			this.c1 = c1;
		}

		/**
		 * @return the c2
		 */
		public String getC2() {
			return c2;
		}

		/**
		 * @param c2 the c2 to set
		 */
		public void setC2(String c2) {
			this.c2 = c2;
		}

		/**
		 * @return the c3
		 */
		public String getC3() {
			return c3;
		}

		/**
		 * @param c3 the c3 to set
		 */
		public void setC3(String c3) {
			this.c3 = c3;
		}

		/**
		 * @return the c4
		 */
		public String getC4() {
			return c4;
		}

		/**
		 * @param c4 the c4 to set
		 */
		public void setC4(String c4) {
			this.c4 = c4;
		}

		/**
		 * @return the c5
		 */
		public String getC5() {
			return c5;
		}

		/**
		 * @param c5 the c5 to set
		 */
		public void setC5(String c5) {
			this.c5 = c5;
		}

		/**
		 * @return the c6
		 */
		public String getC6() {
			return c6;
		}

		/**
		 * @param c6 the c6 to set
		 */
		public void setC6(String c6) {
			this.c6 = c6;
		}

		/**
		 * @return the f1
		 */
		public String getF1() {
			return f1;
		}

		/**
		 * @param f1 the f1 to set
		 */
		public void setF1(String f1) {
			this.f1 = f1;
		}

		/**
		 * @return the f2
		 */
		public String getF2() {
			return f2;
		}

		/**
		 * @param f2 the f2 to set
		 */
		public void setF2(String f2) {
			this.f2 = f2;
		}

		/**
		 * @return the f3
		 */
		public String getF3() {
			return f3;
		}

		/**
		 * @param f3 the f3 to set
		 */
		public void setF3(String f3) {
			this.f3 = f3;
		}

		/**
		 * @return the f4
		 */
		public String getF4() {
			return f4;
		}

		/**
		 * @param f4 the f4 to set
		 */
		public void setF4(String f4) {
			this.f4 = f4;
		}

		/**
		 * @return the f5
		 */
		public String getF5() {
			return f5;
		}

		/**
		 * @param f5 the f5 to set
		 */
		public void setF5(String f5) {
			this.f5 = f5;
		}

		/**
		 * @return the f6
		 */
		public String getF6() {
			return f6;
		}

		/**
		 * @param f6 the f6 to set
		 */
		public void setF6(String f6) {
			this.f6 = f6;
		}

		/**
		 * @return the f7
		 */
		public String getF7() {
			return f7;
		}

		/**
		 * @param f7 the f7 to set
		 */
		public void setF7(String f7) {
			this.f7 = f7;
		}

		/**
		 * @return the f8
		 */
		public String getF8() {
			return f8;
		}

		/**
		 * @param f8 the f8 to set
		 */
		public void setF8(String f8) {
			this.f8 = f8;
		}

		/**
		 * @return the f9
		 */
		public String getF9() {
			return f9;
		}

		/**
		 * @param f9 the f9 to set
		 */
		public void setF9(String f9) {
			this.f9 = f9;
		}

		/**
		 * @return the f10
		 */
		public String getF10() {
			return f10;
		}

		/**
		 * @param f10 the f10 to set
		 */
		public void setF10(String f10) {
			this.f10 = f10;
		}

		/**
		 * @return the f11
		 */
		public String getF11() {
			return f11;
		}

		/**
		 * @param f11 the f11 to set
		 */
		public void setF11(String f11) {
			this.f11 = f11;
		}

		/**
		 * @return the f12
		 */
		public String getF12() {
			return f12;
		}

		/**
		 * @param f12 the f12 to set
		 */
		public void setF12(String f12) {
			this.f12 = f12;
		}

		/**
		 * @return the f13
		 */
		public String getF13() {
			return f13;
		}

		/**
		 * @param f13 the f13 to set
		 */
		public void setF13(String f13) {
			this.f13 = f13;
		}

		/**
		 * @return the f14
		 */
		public String getF14() {
			return f14;
		}

		/**
		 * @param f14 the f14 to set
		 */
		public void setF14(String f14) {
			this.f14 = f14;
		}

		/**
		 * @return the f15
		 */
		public String getF15() {
			return f15;
		}

		/**
		 * @param f15 the f15 to set
		 */
		public void setF15(String f15) {
			this.f15 = f15;
		}

		/**
		 * @return the f16
		 */
		public String getF16() {
			return f16;
		}

		/**
		 * @param f16 the f16 to set
		 */
		public void setF16(String f16) {
			this.f16 = f16;
		}

		/**
		 * @return the f17
		 */
		public String getF17() {
			return f17;
		}

		/**
		 * @param f17 the f17 to set
		 */
		public void setF17(String f17) {
			this.f17 = f17;
		}

		/**
		 * @return the f18
		 */
		public String getF18() {
			return f18;
		}

		/**
		 * @param f18 the f18 to set
		 */
		public void setF18(String f18) {
			this.f18 = f18;
		}

		/**
		 * @return the f19
		 */
		public String getF19() {
			return f19;
		}

		/**
		 * @param f19 the f19 to set
		 */
		public void setF19(String f19) {
			this.f19 = f19;
		}
}
