package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>
 * <a href="ConsultaFDVDistribucionMeta.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class ConsultaFDVDistribucionMeta implements Serializable{

	private static final long serialVersionUID = -516324296224116527L;
	private String codRegi;
	private String zonCaps;
	private String codZona;
	private String vaCx1;
	private String vaCx2;
	private String vaCx3;
	private String vaCx4;
	private String vaCx5;
	private String vaCx6;
	private String vaMetaVenta;
	
	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	
	public String getVaCx1() {
		return vaCx1;
	}

	public void setVaCx1(String vaCx1) {
		this.vaCx1 = vaCx1;
	}

	public String getVaCx2() {
		return vaCx2;
	}

	public void setVaCx2(String vaCx2) {
		this.vaCx2 = vaCx2;
	}

	public String getVaCx3() {
		return vaCx3;
	}

	public void setVaCx3(String vaCx3) {
		this.vaCx3 = vaCx3;
	}

	public String getVaCx4() {
		return vaCx4;
	}

	public void setVaCx4(String vaCx4) {
		this.vaCx4 = vaCx4;
	}

	public String getVaCx5() {
		return vaCx5;
	}

	public void setVaCx5(String vaCx5) {
		this.vaCx5 = vaCx5;
	}

	public String getVaCx6() {
		return vaCx6;
	}

	public void setVaCx6(String vaCx6) {
		this.vaCx6 = vaCx6;
	}

	public String getVaMetaVenta() {
		return vaMetaVenta;
	}

	public void setVaMetaVenta(String vaMetaVenta) {
		this.vaMetaVenta = vaMetaVenta;
	}

	/**
	 * @return the codRegi
	 */
	public String getCodRegi() {
		return codRegi;
	}

	/**
	 * @param codRegi the codRegi to set
	 */
	public void setCodRegi(String codRegi) {
		this.codRegi = codRegi;
	}

	/**
	 * @return the zonCaps
	 */
	public String getZonCaps() {
		return zonCaps;
	}

	/**
	 * @param zonCaps the zonCaps to set
	 */
	public void setZonCaps(String zonCaps) {
		this.zonCaps = zonCaps;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ConsultaFDVDistribucionMeta)) {
            return false;
        }
		ConsultaFDVDistribucionMeta rhs = (ConsultaFDVDistribucionMeta) object;
        return new EqualsBuilder().append(this.codZona, rhs.codZona).append(this.vaCx1, rhs.vaCx1)
        .append(this.vaCx2, rhs.vaCx2).append(this.vaCx3, rhs.vaCx3).append(this.vaCx4, rhs.vaCx4)
        .append(this.vaCx5, rhs.vaCx5).append(this.vaCx6, rhs.vaCx6).append(this.vaMetaVenta, rhs.vaMetaVenta).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(57526443, 1334634051).append(this.codZona).append(this.vaCx1)
		.append(this.vaCx2).append(this.vaCx3).append(this.vaCx4).append(this.vaCx5)
		.append(this.vaCx6).append(this.vaMetaVenta).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this)
		.append("codigo de zona", this.codZona).append("campaña 1", this.vaCx1)
		.append("campaña 2", this.vaCx2).append("campaa 3", this.vaCx3)
		.append("campaña 4", this.vaCx4).append("campaa 5", this.vaCx5)
		.append("campaña 6", this.vaCx6).append("valor meta venta", this.vaMetaVenta).toString();
	}
}
