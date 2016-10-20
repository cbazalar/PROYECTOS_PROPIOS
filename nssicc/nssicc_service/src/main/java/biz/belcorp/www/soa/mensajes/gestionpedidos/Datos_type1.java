
/**
 * Datos_type1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.soa.mensajes.gestionpedidos;
            

            /**
            *  Datos_type1 bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Datos_type1
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = datos_type1
                Namespace URI = http://www.belcorp.biz/soa/mensajes/GestionPedidos
                Namespace Prefix = ns6
                */
            

                        /**
                        * field for Pais
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TPais localPais ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TPais
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TPais getPais(){
                               return localPais;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Pais
                               */
                               public void setPais(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TPais param){
                            
                                            this.localPais=param;
                                    

                               }
                            

                        /**
                        * field for Region
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion localRegion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion getRegion(){
                               return localRegion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Region
                               */
                               public void setRegion(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion param){
                            
                                            this.localRegion=param;
                                    

                               }
                            

                        /**
                        * field for Zona
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona localZona ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona getZona(){
                               return localZona;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Zona
                               */
                               public void setZona(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona param){
                            
                                            this.localZona=param;
                                    

                               }
                            

                        /**
                        * field for Consultora
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TConsultora localConsultora ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TConsultora
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TConsultora getConsultora(){
                               return localConsultora;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Consultora
                               */
                               public void setConsultora(biz.belcorp.www.canonico.ffvv.vender.TConsultora param){
                            
                                            this.localConsultora=param;
                                    

                               }
                            

                        /**
                        * field for PedidoWeb
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TPedido localPedidoWeb ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TPedido
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TPedido getPedidoWeb(){
                               return localPedidoWeb;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PedidoWeb
                               */
                               public void setPedidoWeb(biz.belcorp.www.canonico.ffvv.vender.TPedido param){
                            
                                            this.localPedidoWeb=param;
                                    

                               }
                            

                        /**
                        * field for Origen
                        */

                        
                                    protected java.lang.String localOrigen ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getOrigen(){
                               return localOrigen;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Origen
                               */
                               public void setOrigen(java.lang.String param){
                            
                                            this.localOrigen=param;
                                    

                               }
                            

                        /**
                        * field for Campania
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampania ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampania(){
                               return localCampania;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Campania
                               */
                               public void setCampania(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampania=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/soa/mensajes/GestionPedidos");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":datos_type1",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "datos_type1",
                           xmlWriter);
                   }

               
                   }
               
                                    if (localPais==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "pais", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPais.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","pais"),
                                        xmlWriter);
                                    }
                                
                                    if (localRegion==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "region", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localRegion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","region"),
                                        xmlWriter);
                                    }
                                
                                    if (localZona==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "zona", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localZona.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","zona"),
                                        xmlWriter);
                                    }
                                
                                    if (localConsultora==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "consultora", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localConsultora.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","consultora"),
                                        xmlWriter);
                                    }
                                
                                    if (localPedidoWeb==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "pedidoWeb", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPedidoWeb.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","pedidoWeb"),
                                        xmlWriter);
                                    }
                                
                                    namespace = "http://www.belcorp.biz/soa/mensajes/GestionPedidos";
                                    writeStartElement(null, namespace, "origen", xmlWriter);
                             

                                          if (localOrigen==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localOrigen);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localCampania==null){

                                        writeStartElement(null, "http://www.belcorp.biz/soa/mensajes/GestionPedidos", "campania", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localCampania.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","campania"),
                                        xmlWriter);
                                    }
                                
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/soa/mensajes/GestionPedidos")){
                return "ns6";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "pais"));
                            
                            
                                    elementList.add(localPais==null?null:
                                    localPais);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "region"));
                            
                            
                                    elementList.add(localRegion==null?null:
                                    localRegion);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "zona"));
                            
                            
                                    elementList.add(localZona==null?null:
                                    localZona);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "consultora"));
                            
                            
                                    elementList.add(localConsultora==null?null:
                                    localConsultora);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "pedidoWeb"));
                            
                            
                                    elementList.add(localPedidoWeb==null?null:
                                    localPedidoWeb);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "origen"));
                                 
                                         elementList.add(localOrigen==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrigen));
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos",
                                                                      "campania"));
                            
                            
                                    elementList.add(localCampania==null?null:
                                    localCampania);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Datos_type1 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Datos_type1 object =
                new Datos_type1();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"datos_type1".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Datos_type1)biz.belcorp.www.soa.business.ffvv.sicc.pedidosbs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","pais").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPais(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPais(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TPais.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","region").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setRegion(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setRegion(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TRegion.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","zona").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setZona(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setZona(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TZona.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","consultora").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setConsultora(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setConsultora(biz.belcorp.www.canonico.ffvv.vender.TConsultora.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","pedidoWeb").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPedidoWeb(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPedidoWeb(biz.belcorp.www.canonico.ffvv.vender.TPedido.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","origen").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setOrigen(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionPedidos","campania").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setCampania(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setCampania(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                              
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    