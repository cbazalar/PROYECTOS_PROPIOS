package biz.belcorp.ssicc.web.scdf.form;

import java.io.Serializable;

import org.apache.struts.util.ImageButtonBean;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProductoSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 * @struts.form name="productoSearchForm"
 */

public class ProductoSearchForm extends BaseSearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String codigoPaisProducto;

	protected String codigoProducto;

	protected String descripcionProducto;

	protected String estadoProducto;

	protected String statusTransferenciaProducto;

	protected String indicadorGeneracionStickersProducto;

	protected ImageButtonBean refreshButton = new ImageButtonBean();

	/** Default empty constructor. */
	public ProductoSearchForm() {
	}

	/**
	 * @return Returns the codigoPaisProducto.
	 */
	public String getCodigoPaisProducto() {
		return codigoPaisProducto;
	}

	/**
	 * @param codigoPaisProducto
	 *            The codigoPaisProducto to set.
	 */
	public void setCodigoPaisProducto(String codigoPaisProducto) {
		this.codigoPaisProducto = codigoPaisProducto;
	}

	/**
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 *            The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto
	 *            The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return Returns the estadoProducto.
	 */
	public String getEstadoProducto() {
		return estadoProducto;
	}

	/**
	 * @param estadoProducto
	 *            The estadoProducto to set.
	 */
	public void setEstadoProducto(String estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	/**
	 * @return Returns the indicadorGeneracionStickersProducto.
	 */
	public String getIndicadorGeneracionStickersProducto() {
		return indicadorGeneracionStickersProducto;
	}

	/**
	 * @param indicadorGeneracionStickersProducto
	 *            The indicadorGeneracionStickersProducto to set.
	 */
	public void setIndicadorGeneracionStickersProducto(
			String indicadorGeneracionStickersProducto) {
		this.indicadorGeneracionStickersProducto = indicadorGeneracionStickersProducto;
	}

	/**
	 * @return Returns the statusTransferenciaProducto.
	 */
	public String getStatusTransferenciaProducto() {
		return statusTransferenciaProducto;
	}

	/**
	 * @param statusTransferenciaProducto
	 *            The statusTransferenciaProducto to set.
	 */
	public void setStatusTransferenciaProducto(String statusTransferenciaProducto) {
		this.statusTransferenciaProducto = statusTransferenciaProducto;
	}

	/**
	 * @return Returns the refreshButton.
	 */
	public ImageButtonBean getRefreshButton() {
		return refreshButton;
	}

	/**
	 * @param refreshButton
	 *            The refreshButton to set.
	 */
	public void setRefreshButton(ImageButtonBean refreshButton) {
		this.refreshButton = refreshButton;
	}
}
