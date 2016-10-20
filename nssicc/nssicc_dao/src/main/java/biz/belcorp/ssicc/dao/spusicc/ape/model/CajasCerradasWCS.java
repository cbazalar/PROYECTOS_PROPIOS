package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Luis Rodriguez
 */

public class CajasCerradasWCS implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2367139654285844583L;
	private List <CajaCerradaWCS>cajas = new ArrayList<CajaCerradaWCS>();

	/**
	 * @return the cajas
	 */
	public List<CajaCerradaWCS> getCajas() {
		return cajas;
	}

	/**
	 * @param cajas the cajas to set
	 */
	public void setCajas(List<CajaCerradaWCS> cajas) {
		this.cajas = cajas;
	}

	
}