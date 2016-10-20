/**
 * Dato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types;

public class Dato  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2053484225683319966L;

	private java.lang.String nombreDato;

    private java.lang.String tipoDato;

    private java.lang.String valor;

    public Dato() {
    }

    public Dato(
           java.lang.String nombreDato,
           java.lang.String tipoDato,
           java.lang.String valor) {
           this.nombreDato = nombreDato;
           this.tipoDato = tipoDato;
           this.valor = valor;
           
    }


    /**
     * Gets the nombreDato value for this Dato.
     * 
     * @return nombreDato
     */
    public java.lang.String getNombreDato() {
        return nombreDato;
    }


    /**
     * Sets the nombreDato value for this Dato.
     * 
     * @param nombreDato
     */
    public void setNombreDato(java.lang.String nombreDato) {
        this.nombreDato = nombreDato;
    }


    /**
     * Gets the tipoDato value for this Dato.
     * 
     * @return tipoDato
     */
    public java.lang.String getTipoDato() {
        return tipoDato;
    }


    /**
     * Sets the tipoDato value for this Dato.
     * 
     * @param tipoDato
     */
    public void setTipoDato(java.lang.String tipoDato) {
        this.tipoDato = tipoDato;
    }


    /**
     * Gets the valor value for this Dato.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this Dato.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dato)) return false;
        Dato other = (Dato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nombreDato==null && other.getNombreDato()==null) || 
             (this.nombreDato!=null &&
              this.nombreDato.equals(other.getNombreDato()))) &&
            ((this.tipoDato==null && other.getTipoDato()==null) || 
             (this.tipoDato!=null &&
              this.tipoDato.equals(other.getTipoDato()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor())));
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
        if (getNombreDato() != null) {
            _hashCode += getNombreDato().hashCode();
        }
        if (getTipoDato() != null) {
            _hashCode += getTipoDato().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://equifax.com/WebService/types", "Dato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreDato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valor"));
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
