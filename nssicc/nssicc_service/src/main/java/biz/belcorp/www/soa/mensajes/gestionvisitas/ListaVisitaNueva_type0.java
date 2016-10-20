
/**
 * ListaVisitaNueva_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.soa.mensajes.gestionvisitas;
            

            /**
            *  ListaVisitaNueva_type0 bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ListaVisitaNueva_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = listaVisitaNueva_type0
                Namespace URI = http://www.belcorp.biz/soa/mensajes/GestionVisitas
                Namespace Prefix = ns6
                */
            

                        /**
                        * field for Seccion
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion localSeccion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion getSeccion(){
                               return localSeccion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Seccion
                               */
                               public void setSeccion(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion param){
                            
                                            this.localSeccion=param;
                                    

                               }
                            

                        /**
                        * field for Territorio
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio localTerritorio ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio getTerritorio(){
                               return localTerritorio;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Territorio
                               */
                               public void setTerritorio(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio param){
                            
                                            this.localTerritorio=param;
                                    

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
                        * field for Palanca
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TPalanca localPalanca ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TPalanca
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TPalanca getPalanca(){
                               return localPalanca;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Palanca
                               */
                               public void setPalanca(biz.belcorp.www.canonico.ffvv.vender.TPalanca param){
                            
                                            this.localPalanca=param;
                                    

                               }
                            

                        /**
                        * field for TipoVisita
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.vender.TTipoVisita localTipoVisita ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.vender.TTipoVisita
                           */
                           public  biz.belcorp.www.canonico.ffvv.vender.TTipoVisita getTipoVisita(){
                               return localTipoVisita;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoVisita
                               */
                               public void setTipoVisita(biz.belcorp.www.canonico.ffvv.vender.TTipoVisita param){
                            
                                            this.localTipoVisita=param;
                                    

                               }
                            

                        /**
                        * field for Moneda
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda localMoneda ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda
                           */
                           public  biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda getMoneda(){
                               return localMoneda;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Moneda
                               */
                               public void setMoneda(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda param){
                            
                                            this.localMoneda=param;
                                    

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/soa/mensajes/GestionVisitas");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":listaVisitaNueva_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "listaVisitaNueva_type0",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localSeccion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("seccion cannot be null!!");
                                            }
                                           localSeccion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","seccion"),
                                               xmlWriter);
                                        
                                            if (localTerritorio==null){
                                                 throw new org.apache.axis2.databinding.ADBException("territorio cannot be null!!");
                                            }
                                           localTerritorio.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","territorio"),
                                               xmlWriter);
                                        
                                            if (localCampaniaIngreso==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                            }
                                           localCampaniaIngreso.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","campaniaIngreso"),
                                               xmlWriter);
                                        
                                            if (localConsultora==null){
                                                 throw new org.apache.axis2.databinding.ADBException("consultora cannot be null!!");
                                            }
                                           localConsultora.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","consultora"),
                                               xmlWriter);
                                        
                                            if (localPalanca==null){
                                                 throw new org.apache.axis2.databinding.ADBException("palanca cannot be null!!");
                                            }
                                           localPalanca.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","palanca"),
                                               xmlWriter);
                                        
                                            if (localTipoVisita==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoVisita cannot be null!!");
                                            }
                                           localTipoVisita.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","tipoVisita"),
                                               xmlWriter);
                                        
                                            if (localMoneda==null){
                                                 throw new org.apache.axis2.databinding.ADBException("moneda cannot be null!!");
                                            }
                                           localMoneda.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","moneda"),
                                               xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/soa/mensajes/GestionVisitas")){
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

                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "seccion"));
                            
                            
                                    if (localSeccion==null){
                                         throw new org.apache.axis2.databinding.ADBException("seccion cannot be null!!");
                                    }
                                    elementList.add(localSeccion);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "territorio"));
                            
                            
                                    if (localTerritorio==null){
                                         throw new org.apache.axis2.databinding.ADBException("territorio cannot be null!!");
                                    }
                                    elementList.add(localTerritorio);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "campaniaIngreso"));
                            
                            
                                    if (localCampaniaIngreso==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                    }
                                    elementList.add(localCampaniaIngreso);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "consultora"));
                            
                            
                                    if (localConsultora==null){
                                         throw new org.apache.axis2.databinding.ADBException("consultora cannot be null!!");
                                    }
                                    elementList.add(localConsultora);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "palanca"));
                            
                            
                                    if (localPalanca==null){
                                         throw new org.apache.axis2.databinding.ADBException("palanca cannot be null!!");
                                    }
                                    elementList.add(localPalanca);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "tipoVisita"));
                            
                            
                                    if (localTipoVisita==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoVisita cannot be null!!");
                                    }
                                    elementList.add(localTipoVisita);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas",
                                                                      "moneda"));
                            
                            
                                    if (localMoneda==null){
                                         throw new org.apache.axis2.databinding.ADBException("moneda cannot be null!!");
                                    }
                                    elementList.add(localMoneda);
                                

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
        public static ListaVisitaNueva_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ListaVisitaNueva_type0 object =
                new ListaVisitaNueva_type0();

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
                    
                            if (!"listaVisitaNueva_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ListaVisitaNueva_type0)biz.belcorp.www.soa.business.ffvv.sicc.visitasbs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","seccion").equals(reader.getName())){
                                
                                                object.setSeccion(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","territorio").equals(reader.getName())){
                                
                                                object.setTerritorio(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TTerritorio.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","campaniaIngreso").equals(reader.getName())){
                                
                                                object.setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","consultora").equals(reader.getName())){
                                
                                                object.setConsultora(biz.belcorp.www.canonico.ffvv.vender.TConsultora.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","palanca").equals(reader.getName())){
                                
                                                object.setPalanca(biz.belcorp.www.canonico.ffvv.vender.TPalanca.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","tipoVisita").equals(reader.getName())){
                                
                                                object.setTipoVisita(biz.belcorp.www.canonico.ffvv.vender.TTipoVisita.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionVisitas","moneda").equals(reader.getName())){
                                
                                                object.setMoneda(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TMoneda.Factory.parse(reader));
                                              
                                        reader.next();
                                    
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
           
    