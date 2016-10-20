/**
 * EstructuraServicio40.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types;

public class EstructuraServicio40  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8236494749306489478L;

	private biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header;

    private java.lang.String numeroOperacion;

    public EstructuraServicio40() {
    }

    public EstructuraServicio40(
    		biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header,
           java.lang.String numeroOperacion) {
           this.header = header;
           this.numeroOperacion = numeroOperacion;
    }


    /**
     * Gets the header value for this EstructuraServicio40.
     * 
     * @return header
     */
    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header getHeader() {
        return header;
    }


    /**
     * Sets the header value for this EstructuraServicio40.
     * 
     * @param header
     */
    public void setHeader(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header) {
        this.header = header;
    }


    /**
     * Gets the numeroOperacion value for this EstructuraServicio40.
     * 
     * @return numeroOperacion
     */
    public java.lang.String getNumeroOperacion() {
        return numeroOperacion;
    }


    /**
     * Sets the numeroOperacion value for this EstructuraServicio40.
     * 
     * @param numeroOperacion
     */
    public void setNumeroOperacion(java.lang.String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstructuraServicio40)) return false;
        EstructuraServicio40 other = (EstructuraServicio40) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.numeroOperacion==null && other.getNumeroOperacion()==null) || 
             (this.numeroOperacion!=null &&
              this.numeroOperacion.equals(other.getNumeroOperacion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getNumeroOperacion() != null) {
            _hashCode += getNumeroOperacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstructuraServicio40.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "EstructuraServicio40"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Header"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
