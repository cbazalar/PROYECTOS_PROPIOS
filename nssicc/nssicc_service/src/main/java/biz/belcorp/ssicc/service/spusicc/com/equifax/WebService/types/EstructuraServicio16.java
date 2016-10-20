/**
 * EstructuraServicio16.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types;

public class EstructuraServicio16  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6119374259554991169L;

	private biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header;

    private biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante[] integrantesServicio;

    public EstructuraServicio16() {
    }

    public EstructuraServicio16(
           biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header,
           biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante[] integrantesServicio) {
           this.header = header;
           this.integrantesServicio = integrantesServicio;
    }


    /**
     * Gets the header value for this EstructuraServicio16.
     * 
     * @return header
     */
    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header getHeader() {
        return header;
    }


    /**
     * Sets the header value for this EstructuraServicio16.
     * 
     * @param header
     */
    public void setHeader(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header header) {
        this.header = header;
    }


    /**
     * Gets the integrantesServicio value for this EstructuraServicio16.
     * 
     * @return integrantesServicio
     */
    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante[] getIntegrantesServicio() {
        return integrantesServicio;
    }


    /**
     * Sets the integrantesServicio value for this EstructuraServicio16.
     * 
     * @param integrantesServicio
     */
    public void setIntegrantesServicio(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante[] integrantesServicio) {
        this.integrantesServicio = integrantesServicio;
    }

    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante getIntegrantesServicio(int i) {
        return this.integrantesServicio[i];
    }

    public void setIntegrantesServicio(int i, biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante _value) {
        this.integrantesServicio[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstructuraServicio16)) return false;
        EstructuraServicio16 other = (EstructuraServicio16) obj;
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
            ((this.integrantesServicio==null && other.getIntegrantesServicio()==null) || 
             (this.integrantesServicio!=null &&
              java.util.Arrays.equals(this.integrantesServicio, other.getIntegrantesServicio())));
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
        if (getIntegrantesServicio() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIntegrantesServicio());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIntegrantesServicio(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstructuraServicio16.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "EstructuraServicio16"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("", "header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Header"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrantesServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "integrantesServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Integrante"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
