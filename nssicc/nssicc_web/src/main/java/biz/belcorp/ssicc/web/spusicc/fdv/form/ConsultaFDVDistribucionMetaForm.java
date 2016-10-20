package biz.belcorp.ssicc.web.spusicc.fdv.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


public class ConsultaFDVDistribucionMetaForm extends BaseSearchForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codProc;
	private String nomProc;
	private String estProc;
	private String staProc;
	private String codigoPais;
    private String descPais;
	private String camProc;
    private String anyProc;
    private String camAnyProc;
    
    private String desCx1;
    private String desCx2;
    private String desCx3;
    private String desCx4;
    private String desCx5;
    private String desCx6;
	
    private Integer versionProceso;
    private String nombreProcesoDetallado;
    private String anyoPerProc;
    private String perProc;
    
	public ConsultaFDVDistribucionMetaForm() {}
	
	public String getCodProc() {
		return codProc;
	}

	public void setCodProc(String codProc) {
		this.codProc = codProc;
	}

	public String getNomProc() {
		return nomProc;
	}

	public void setNomProc(String nomProc) {
		this.nomProc = nomProc;
	}

	public String getEstProc() {
		return estProc;
	}

	public void setEstProc(String estProc) {
		this.estProc = estProc;
	}

	public String getStaProc() {
		return staProc;
	}

	public void setStaProc(String staProc) {
		this.staProc = staProc;
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

	public String getCamProc() {
		return camProc;
	}

	public void setCamProc(String camProc) {
		this.camProc = camProc;
	}

	public String getAnyProc() {
		return anyProc;
	}

	public void setAnyProc(String anyProc) {
		this.anyProc = anyProc;
	}

	public String getCamAnyProc() {
		return camAnyProc;
	}

	public void setCamAnyProc(String camAnyProc) {
		this.camAnyProc = camAnyProc;
	}

	public String getDesCx1() {
		return desCx1;
	}

	public void setDesCx1(String desCx1) {
		this.desCx1 = desCx1;
	}

	public String getDesCx2() {
		return desCx2;
	}

	public void setDesCx2(String desCx2) {
		this.desCx2 = desCx2;
	}

	public String getDesCx3() {
		return desCx3;
	}

	public void setDesCx3(String desCx3) {
		this.desCx3 = desCx3;
	}

	public String getDesCx4() {
		return desCx4;
	}

	public void setDesCx4(String desCx4) {
		this.desCx4 = desCx4;
	}

	public String getDesCx5() {
		return desCx5;
	}

	public void setDesCx5(String desCx5) {
		this.desCx5 = desCx5;
	}

	public String getDesCx6() {
		return desCx6;
	}

	public void setDesCx6(String desCx6) {
		this.desCx6 = desCx6;
	}

	/**
	 * Metodo que setea en las variables correspondientes, los valores del texto que ira
	 * en la cabecera de cada columna en la grilla a mostrar para las zonas consolidadas,
	 * dependiendo del indice pasado como parametro
	 * 
	 * @param
	 * 		deValue
	 * 		indice
	 * @author frank.ayala 
	 * */
	public void setDesCx(String deValue, int indice) {

		if(indice == 0){
			this.desCx1 = deValue;
		}else if(indice == 1){
			this.desCx2 = deValue;
		}else if(indice == 2){
			this.desCx3 = deValue;
		}else if(indice == 3){
			this.desCx4 = deValue;
		}else if(indice == 4){
			this.desCx5 = deValue;
		}else if(indice == 5){
			this.desCx6 = deValue;
		}
	}

	/**
	 * @return the versionProceso
	 */
	public Integer getVersionProceso() {
		return versionProceso;
	}

	/**
	 * @param versionProceso the versionProceso to set
	 */
	public void setVersionProceso(Integer versionProceso) {
		this.versionProceso = versionProceso;
	}

	/**
	 * @return the nombreProcesoDetallado
	 */
	public String getNombreProcesoDetallado() {
		String nombre = "";
        if(StringUtils.isBlank(anyoPerProc))
        {
        	nombre = nomProc;
        }
        else
        {
        	nombre = String.format(Constants.FORMATO_NOMBRE_PROCESO_LARGO_FDV, anyoPerProc, perProc, versionProceso, nomProc);
        }
        
        return nombre;
	}

	/**
	 * @param nombreProcesoDetallado the nombreProcesoDetallado to set
	 */
	public void setNombreProcesoDetallado(String nombreProcesoDetallado) {
		this.nombreProcesoDetallado = nombreProcesoDetallado;
	}

	/**
	 * @return the anyoPerProc
	 */
	public String getAnyoPerProc() {
		return anyoPerProc;
	}

	/**
	 * @param anyoPerProc the anyoPerProc to set
	 */
	public void setAnyoPerProc(String anyoPerProc) {
		this.anyoPerProc = anyoPerProc;
	}

	/**
	 * @return the perProc
	 */
	public String getPerProc() {
		return perProc;
	}

	/**
	 * @param perProc the perProc to set
	 */
	public void setPerProc(String perProc) {
		this.perProc = perProc;
	}	
}
