package biz.belcorp.ssicc.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ProcesoEDURecepcionarMaestrosForm extends BaseSearchForm
implements Serializable{	
	
	private static final long serialVersionUID = 5722147284633840253L;

	private String codigoPais;
	
	private String codigoProcesoBatch;
	
	private String codigoSistema;
	
    private String codigoEmpresa;
    
    private String id;
    protected String[] selectedItems = {};

    
    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }   
	
	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}
	
	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}  
	
}
