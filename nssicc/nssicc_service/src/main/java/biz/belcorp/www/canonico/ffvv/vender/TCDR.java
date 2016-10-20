
/**
 * TCDR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.canonico.ffvv.vender;
            

            /**
            *  TCDR bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class TCDR
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = tCDR
                Namespace URI = http://www.belcorp.biz/canonico/ffvv/Vender
                Namespace Prefix = ns5
                */
            

                        /**
                        * field for Codigo
                        */

                        
                                    protected java.lang.String localCodigo ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCodigo(){
                               return localCodigo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Codigo
                               */
                               public void setCodigo(java.lang.String param){
                            
                                            this.localCodigo=param;
                                    

                               }
                            

                        /**
                        * field for Estado
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCDREstado localEstado ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCDREstado
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCDREstado getEstado(){
                               return localEstado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Estado
                               */
                               public void setEstado(biz.belcorp.www.canonico.ffvv.vender.TCDREstado param){
                            
                                            this.localEstado=param;
                                    

                               }
                            

                        /**
                        * field for Relacionado
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.Relacionado_type0 localRelacionado ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.Relacionado_type0
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.Relacionado_type0 getRelacionado(){
                               return localRelacionado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Relacionado
                               */
                               public void setRelacionado(biz.belcorp.www.canonico.ffvv.vender.Relacionado_type0 param){
                            
                                            this.localRelacionado=param;
                                    

                               }
                            

                        /**
                        * field for CampaniaIngreso
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampaniaIngreso ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampaniaIngreso(){
                               return localCampaniaIngreso;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CampaniaIngreso
                               */
                               public void setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampaniaIngreso=param;
                                    

                               }
                            

                        /**
                        * field for CampaniaReferencia
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCampania localCampaniaReferencia ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCampania
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCampania getCampaniaReferencia(){
                               return localCampaniaReferencia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CampaniaReferencia
                               */
                               public void setCampaniaReferencia(biz.belcorp.www.canonico.ffvv.vender.TCampania param){
                            
                                            this.localCampaniaReferencia=param;
                                    

                               }
                            

                        /**
                        * field for CDRDetalle
                        * This was an Array!
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[] localCDRDetalle ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[]
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[] getCDRDetalle(){
                               return localCDRDetalle;
                           }

                           
                        


                               
                              /**
                               * validate the array for CDRDetalle
                               */
                              protected void validateCDRDetalle(biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param CDRDetalle
                              */
                              public void setCDRDetalle(biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[] param){
                              
                                   validateCDRDetalle(param);

                               
                                      this.localCDRDetalle=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle
                             */
                             public void addCDRDetalle(biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle param){
                                   if (localCDRDetalle == null){
                                   localCDRDetalle = new biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localCDRDetalle);
                               list.add(param);
                               this.localCDRDetalle =
                             (biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[])list.toArray(
                            new biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[list.size()]);

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/canonico/ffvv/Vender");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":tCDR",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "tCDR",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "http://www.belcorp.biz/canonico/ffvv/Vender";
                                    writeStartElement(null, namespace, "codigo", xmlWriter);
                             

                                          if (localCodigo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCodigo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    if (localEstado==null){

                                        writeStartElement(null, "http://www.belcorp.biz/canonico/ffvv/Vender", "estado", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localEstado.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estado"),
                                        xmlWriter);
                                    }
                                
                                            if (localRelacionado==null){
                                                 throw new org.apache.axis2.databinding.ADBException("relacionado cannot be null!!");
                                            }
                                           localRelacionado.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","relacionado"),
                                               xmlWriter);
                                        
                                            if (localCampaniaIngreso==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                            }
                                           localCampaniaIngreso.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaIngreso"),
                                               xmlWriter);
                                        
                                            if (localCampaniaReferencia==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaReferencia cannot be null!!");
                                            }
                                           localCampaniaReferencia.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaReferencia"),
                                               xmlWriter);
                                        
                                       if (localCDRDetalle!=null){
                                            for (int i = 0;i < localCDRDetalle.length;i++){
                                                if (localCDRDetalle[i] != null){
                                                 localCDRDetalle[i].serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","CDRDetalle"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("CDRDetalle cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("CDRDetalle cannot be null!!");
                                        
                                    }
                                 
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/canonico/ffvv/Vender")){
                return "ns5";
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

                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "codigo"));
                                 
                                         elementList.add(localCodigo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCodigo));
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "estado"));
                            
                            
                                    elementList.add(localEstado==null?null:
                                    localEstado);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "relacionado"));
                            
                            
                                    if (localRelacionado==null){
                                         throw new org.apache.axis2.databinding.ADBException("relacionado cannot be null!!");
                                    }
                                    elementList.add(localRelacionado);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "campaniaIngreso"));
                            
                            
                                    if (localCampaniaIngreso==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                    }
                                    elementList.add(localCampaniaIngreso);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                      "campaniaReferencia"));
                            
                            
                                    if (localCampaniaReferencia==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaReferencia cannot be null!!");
                                    }
                                    elementList.add(localCampaniaReferencia);
                                
                             if (localCDRDetalle!=null) {
                                 for (int i = 0;i < localCDRDetalle.length;i++){

                                    if (localCDRDetalle[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender",
                                                                          "CDRDetalle"));
                                         elementList.add(localCDRDetalle[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("CDRDetalle cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("CDRDetalle cannot be null!!");
                                    
                             }

                        

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
        public static TCDR parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TCDR object =
                new TCDR();

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
                    
                            if (!"tCDR".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TCDR)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list6 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","codigo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCodigo(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","estado").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setEstado(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setEstado(biz.belcorp.www.canonico.ffvv.vender.TCDREstado.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","relacionado").equals(reader.getName())){
                                
                                                object.setRelacionado(biz.belcorp.www.canonico.ffvv.vender.Relacionado_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaIngreso").equals(reader.getName())){
                                
                                                object.setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","campaniaReferencia").equals(reader.getName())){
                                
                                                object.setCampaniaReferencia(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","CDRDetalle").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list6.add(biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone6 = false;
                                                        while(!loopDone6){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone6 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.belcorp.biz/canonico/ffvv/Vender","CDRDetalle").equals(reader.getName())){
                                                                    list6.add(biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone6 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setCDRDetalle((biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                biz.belcorp.www.canonico.ffvv.vender.TCDRDetalle.class,
                                                                list6));
                                                            
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
           
    