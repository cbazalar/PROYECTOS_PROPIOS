package biz.belcorp.ssicc.dao.spusicc.fdv.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * <a href="GenericBean.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a> 
 */

public class GenericBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String descripcion;	
	
    private String flaZonc;
    private String flaZofi;
    private String flaZoco;
    private String selected;

    private int contador;
    private String procCodProc;
	private String codZona;
    
	private String deCabecera1;
	private String deCabecera2;
	private String deCabecera3;
	private String deCabecera4;
	private String deCabecera5;	
    private String deVariable1;
    private String deVariable2;
    private String deVariable3;
    private String deVariable4;
    private String deVariable5;
    private String cluAsigSist;
    private String cluAsigPais;
    private boolean stVisible1;
    private boolean stVisible2;
    private boolean stVisible3;
    private boolean stVisible4;
    private boolean stVisible5;
	
    private BigDecimal valor;
    
	public GenericBean() {
		stVisible1 = false;
		stVisible2 = false;
		stVisible3 = false;
		stVisible4 = false;
		stVisible5 = false;
		selected = "false";
		contador = 0;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public String getFlaZonc() {
		return flaZonc;
	}

	public void setFlaZonc(String flaZonc) {
		this.flaZonc = flaZonc;
	}

	public String getFlaZofi() {
		return flaZofi;
	}

	public void setFlaZofi(String flaZofi) {
		this.flaZofi = flaZofi;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getProcCodProc() {
		return procCodProc;
	}

	public void setProcCodProc(String procCodProc) {
		this.procCodProc = procCodProc;
	}

	public String getFlaZoco() {
		return flaZoco;
	}

	public void setFlaZoco(String flaZoco) {
		this.flaZoco = flaZoco;
	}

	public String getDeVariable1() {
		return deVariable1;
	}

	public void setDeVariable1(String deVariable1) {
		this.deVariable1 = deVariable1;
	}

	public String getDeVariable2() {
		return deVariable2;
	}

	public void setDeVariable2(String deVariable2) {
		this.deVariable2 = deVariable2;
	}

	public String getDeVariable3() {
		return deVariable3;
	}

	public void setDeVariable3(String deVariable3) {
		this.deVariable3 = deVariable3;
	}

	public String getDeVariable4() {
		return deVariable4;
	}

	public void setDeVariable4(String deVariable4) {
		this.deVariable4 = deVariable4;
	}

	public String getDeVariable5() {
		return deVariable5;
	}

	public void setDeVariable5(String deVariable5) {
		this.deVariable5 = deVariable5;
	}

	public String getCluAsigSist() {
		return cluAsigSist;
	}

	public void setCluAsigSist(String cluAsigSist) {
		this.cluAsigSist = cluAsigSist;
	}

	public String getCluAsigPais() {
		return cluAsigPais;
	}

	public void setCluAsigPais(String cluAsigPais) {
		this.cluAsigPais = cluAsigPais;
	}

	public boolean isStVisible1() {
		return stVisible1;
	}

	public void setStVisible1(boolean stVisible1) {
		this.stVisible1 = stVisible1;
	}

	public boolean isStVisible2() {
		return stVisible2;
	}

	public void setStVisible2(boolean stVisible2) {
		this.stVisible2 = stVisible2;
	}

	public boolean isStVisible3() {
		return stVisible3;
	}

	public void setStVisible3(boolean stVisible3) {
		this.stVisible3 = stVisible3;
	}

	public boolean isStVisible4() {
		return stVisible4;
	}

	public void setStVisible4(boolean stVisible4) {
		this.stVisible4 = stVisible4;
	}

	public boolean isStVisible5() {
		return stVisible5;
	}

	public void setStVisible5(boolean stVisible5) {
		this.stVisible5 = stVisible5;
	}

	public String getDeCabecera1() {
		return deCabecera1;
	}

	public void setDeCabecera1(String deCabecera1) {
		this.deCabecera1 = deCabecera1;
	}

	public String getDeCabecera2() {
		return deCabecera2;
	}

	public void setDeCabecera2(String deCabecera2) {
		this.deCabecera2 = deCabecera2;
	}

	public String getDeCabecera3() {
		return deCabecera3;
	}

	public void setDeCabecera3(String deCabecera3) {
		this.deCabecera3 = deCabecera3;
	}

	public String getDeCabecera4() {
		return deCabecera4;
	}

	public void setDeCabecera4(String deCabecera4) {
		this.deCabecera4 = deCabecera4;
	}

	public String getDeCabecera5() {
		return deCabecera5;
	}

	public void setDeCabecera5(String deCabecera5) {
		this.deCabecera5 = deCabecera5;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Metodo que setea en las variables correspondientes, los valores capturados
	 * de las variables exogenas, dependiendo del indice pasado como parametro
	 * y a su vez indica mediante un campo, si el campo sera visible en la grilla
	 * 
	 * @param
	 * 		deValue
	 * 		indice
	 * @author frank.ayala 
	 * */
	
	public void setDeVariable(String deValue, int indice) {

		if(indice == 0){
			this.deVariable1 = deValue;
			this.stVisible1  = true;
		}else if(indice == 1){
			this.deVariable2 = deValue;
			this.stVisible2  = true;
		}else if(indice == 2){
			this.deVariable3 = deValue;
			this.stVisible3  = true;
		}else if(indice == 3){
			this.deVariable4 = deValue;
			this.stVisible4  = true;
		}else if(indice == 4){
			this.deVariable5 = deValue;
			this.stVisible5  = true;
		}
	}

	/**
	 * Metodo que setea en las variables correspondientes, los valores del texto que ira
	 * en la cabecera de cada columna en la grilla a mostrar para las variables exogenas,
	 * dependiendo del indice pasado como parametro
	 * 
	 * @param
	 * 		deValue
	 * 		indice
	 * @author frank.ayala 
	 * */
	public void setDeCabecera(String deValue, int indice) {
		
		if(indice == 0){
			this.deCabecera1 = deValue;
		}else if(indice == 1){
			this.deCabecera2 = deValue;
		}else if(indice == 2){
			this.deCabecera3 = deValue;
		}else if(indice == 3){
			this.deCabecera4 = deValue;
		}else if(indice == 4){
			this.deCabecera5 = deValue;
		}
	}
}
