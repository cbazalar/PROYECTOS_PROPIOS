package org.sistema.sisgen.web._ejemplos.manage;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.sistema.framework.web.util.DataTableModel;
import org.sistema.sisgen.web._framework.manage.MBaseSistemaSisgenAbstractJSF;
import org.sistema.sisgen.web.base.manage.MPantallaPrincipalBean;

@ManagedBean
@RequestScoped
public class MEjemploVersion_0_0 extends MBaseSistemaSisgenAbstractJSF {

	private static final long serialVersionUID = -6711841667418094107L;
	
	protected List listaBusqueda; 
	protected DataTableModel datatableBusqueda; 
	protected Object beanRegistroSeleccionado ; 
	protected String valorPrueba;
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.generarDataDatatable();
		this.valorPrueba = "2222222";
	}
	
	
	/**
	 * 
	 */
	private void generarDataDatatable() {
		this.listaBusqueda = new ArrayList();
		
		Map bean = new HashMap<String,String>();
		bean.put("id", "348c0eea");
		bean.put("year","1991");
		bean.put("brand","Jaguar	  ");
		bean.put("color","Blue  ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "e78689bb");
		bean.put("year","1962");
		bean.put("brand","BMW	    ");
		bean.put("color","Black ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "9f2a05e3");
		bean.put("year","1976");
		bean.put("brand","Ford	    ");
		bean.put("color","Yellow");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "45b0a517");
		bean.put("year","1967");
		bean.put("brand","Audi	    ");
		bean.put("color","White ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "5a1d2dd8");
		bean.put("year","2003");
		bean.put("brand","Mercedes ");
		bean.put("color","Yellow");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "ec814c32");
		bean.put("year","2009");
		bean.put("brand","Honda	  ");
		bean.put("color","Brown ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1ba372fa");
		bean.put("year","1974");
		bean.put("brand","Volvo	  ");
		bean.put("color","Black ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "ef9e847d");
		bean.put("year","2008");
		bean.put("brand","Jaguar	  ");
		bean.put("color","Orange");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "73e23544");
		bean.put("year","2007");
		bean.put("brand","Audi	    ");
		bean.put("color","Red   ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f673");
		bean.put("year","2001");
		bean.put("brand","Volvo	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f673");
		bean.put("year","2001");
		bean.put("brand","Volvo	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f674");
		bean.put("year","2021");
		bean.put("brand","Volvo2	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f675");
		bean.put("year","2010");
		bean.put("brand","Volvo3  ");
		bean.put("color","Red ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f676");
		bean.put("year","2008");
		bean.put("brand","Volvo4	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f677");
		bean.put("year","2002");
		bean.put("brand","Mazda	  ");
		bean.put("color","Blue ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f673");
		bean.put("year","2001");
		bean.put("brand","Volvo	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f673");
		bean.put("year","2001");
		bean.put("brand","Volvo	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		bean = new HashMap<String,String>();
		bean.put("id", "1a84f673");
		bean.put("year","2001");
		bean.put("brand","Volvo	  ");
		bean.put("color","Green ");
		this.listaBusqueda.add(bean);
		
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}
	
	/**
	 * @param actionEvent
	 */
	public void modificar(ActionEvent actionEvent) {
		String detail = "Seleccione Registro!!";
		if (this.beanRegistroSeleccionado != null) {
			Map bean = (Map) this.beanRegistroSeleccionado;
			detail = "id: " + (String)bean.get("id") + 
					        " Year: " + (String)bean.get("year") +
					        " brand: " + (String)bean.get("brand") +
					        " brand: " + (String)bean.get("brand") +
					        " brand: " + (String)bean.get("brand") +
					        " brand: " + (String)bean.get("brand") +
					        " brand: " + (String)bean.get("brand") +
					        " brand: " + (String)bean.get("brand") + " es un erorro de oracle no controlado ORA.0001: no_data found prueba de estres debe ser controlado peor no ha podido se controlado <br/><br/> la epidemia" +
					        " color: " + (String)bean.get("color");
		}
		this.addInfo("Mensaje", detail);
		return;
	}
	
	/**
	 * @param actionEvent
	 */
	public void nuevo(ActionEvent actionEvent) {
		String detail = "Seleccione Registro!!";
		try {
			MPantallaPrincipalBean beanPantalla = (MPantallaPrincipalBean)this.findManageBean("mPantallaPrincipalBean");
			InetAddress address = beanPantalla.getAddress();
			
			
			if (this.beanRegistroSeleccionado != null) {
				Map bean = (Map) this.beanRegistroSeleccionado;
				this.redireccionarPagina("indexVersion0-1a.xhtml");
			}
			this.addInfo("Mensaje", detail);
		}
		catch(Exception e) {
			this.addError("Mensaje", this.obtieneMensajeErrorException(e));
		}
		return;
	}

	public void consultar(ActionEvent actionEvent) {
		try {
			this.mensajeAlertaDefault = "La prueba se ejecutó con Exito, sin embargo debe realizar mas acciones al respecto. <br /><strong>De esta forma poder llegar al 100% de Factibilidad</strong>";
			String ventana = "PF('alertaDialogPrincipal').show()";
			this.getRequestContext().execute(ventana);
		}
		catch(Exception e) {
			this.addError("Mensaje", this.obtieneMensajeErrorException(e));
		}
		return;
	}


	@Override
	protected void setRegresarPantalla() throws Exception {
		this.mensajeAlertaActionDefault = "Regresando a 1era Pantalla <br/>Haga click en Aceptar";
		String ventana = "PF('alertaDialogActionPrincipal').show()";
		this.getRequestContext().execute(ventana);
	}
	
	
	protected void setAceptarAlertaDefaultAction(String accion) throws Exception{
		this.redireccionarPagina("indexVersion0-0a.xhtml");
		return;
	}
	
	
	
	
	/**
	 * @return the listaBusqueda
	 */
	public List getListaBusqueda() {
		return listaBusqueda;
	}


	/**
	 * @param listaBusqueda the listaBusqueda to set
	 */
	public void setListaBusqueda(List listaBusqueda) {
		this.listaBusqueda = listaBusqueda;
	}


	/**
	 * @return the datatableBusqueda
	 */
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}


	/**
	 * @param datatableBusqueda the datatableBusqueda to set
	 */
	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}


	/**
	 * @return the beanRegistroSeleccionado
	 */
	public Object getBeanRegistroSeleccionado() {
		return beanRegistroSeleccionado;
	}


	/**
	 * @param beanRegistroSeleccionado the beanRegistroSeleccionado to set
	 */
	public void setBeanRegistroSeleccionado(Object beanRegistroSeleccionado) {
		this.beanRegistroSeleccionado = beanRegistroSeleccionado;
	}


	/**
	 * @return the valorPrueba
	 */
	public String getValorPrueba() {
		return valorPrueba;
	}


	/**
	 * @param valorPrueba the valorPrueba to set
	 */
	public void setValorPrueba(String valorPrueba) {
		this.valorPrueba = valorPrueba;
	}
	
	
	
	
	

}
