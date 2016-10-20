/**
 * Integrante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types;

public class Integrante  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4249827547850936905L;

	private biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato[] datosEntrada;

    private java.lang.String numeroDocumento;

    private java.lang.Integer tipoDocumento;

    public Integrante() {
    }

    public Integrante(
    		biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato[] datosEntrada,
           java.lang.String numeroDocumento,
           java.lang.Integer tipoDocumento) {
           this.datosEntrada = datosEntrada;
           this.numeroDocumento = numeroDocumento;
           this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the datosEntrada value for this Integrante.
     * 
     * @return datosEntrada
     */
    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato[] getDatosEntrada() {
        return datosEntrada;
    }


    /**
     * Sets the datosEntrada value for this Integrante.
     * 
     * @param datosEntrada
     */
    public void setDatosEntrada(biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato[] datosEntrada) {
        this.datosEntrada = datosEntrada;
    }

    public biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato getDatosEntrada(int i) {
        return this.datosEntrada[i];
    }

    public void setDatosEntrada(int i, biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato _value) {
        this.datosEntrada[i] = _value;
    }


    /**
     * Gets the numeroDocumento value for this Integrante.
     * 
     * @return numeroDocumento
     */
    public java.lang.String getNumeroDocumento() {
        return numeroDocumento;
    }


    /**
     * Sets the numeroDocumento value for this Integrante.
     * 
     * @param numeroDocumento
     */
    public void setNumeroDocumento(java.lang.String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    /**
     * Gets the tipoDocumento value for this Integrante.
     * 
     * @return tipoDocumento
     */
    public java.lang.Integer getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this Integrante.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(java.lang.Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Integrante)) return false;
        Integrante other = (Integrante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosEntrada==null && other.getDatosEntrada()==null) || 
             (this.datosEntrada!=null &&
              java.util.Arrays.equals(this.datosEntrada, other.getDatosEntrada()))) &&
            ((this.numeroDocumento==null && other.getNumeroDocumento()==null) || 
             (this.numeroDocumento!=null &&
              this.numeroDocumento.equals(other.getNumeroDocumento()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento())));
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
        if (getDatosEntrada() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosEntrada());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosEntrada(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroDocumento() != null) {
            _hashCode += getNumeroDocumento().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Integrante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Integrante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosEntrada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosEntrada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Dato"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
