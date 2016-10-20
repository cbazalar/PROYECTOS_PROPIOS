
/**
 * ListaDetallesConcursoRecomendacion_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package biz.belcorp.www.soa.mensajes.gestionconcursos;
            

            /**
            *  ListaDetallesConcursoRecomendacion_type0 bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ListaDetallesConcursoRecomendacion_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = listaDetallesConcursoRecomendacion_type0
                Namespace URI = http://www.belcorp.biz/soa/mensajes/GestionConcursos
                Namespace Prefix = ns7
                */
            

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
                        * field for ParticipadoRecomendacion
                        */

                        
                                    protected biz.belcorp.www.canonico.ffvv.motivar.TParticipadoRecomendacion localParticipadoRecomendacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return biz.belcorp.www.canonico.ffvv.motivar.TParticipadoRecomendacion
                           */
                           public  biz.belcorp.www.canonico.ffvv.motivar.TParticipadoRecomendacion getParticipadoRecomendacion(){
                               return localParticipadoRecomendacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ParticipadoRecomendacion
                               */
                               public void setParticipadoRecomendacion(biz.belcorp.www.canonico.ffvv.motivar.TParticipadoRecomendacion param){
                            
                                            this.localParticipadoRecomendacion=param;
                                    

                               }
                            

                        /**
                        * field for RecomendacionesEfectivas
                        */

                        
                                    protected short localRecomendacionesEfectivas ;
                                

                           /**
                           * Auto generated getter method
                           * @return short
                           */
                           public  short getRecomendacionesEfectivas(){
                               return localRecomendacionesEfectivas;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RecomendacionesEfectivas
                               */
                               public void setRecomendacionesEfectivas(short param){
                            
                                            this.localRecomendacionesEfectivas=param;
                                    

                               }
                            

                        /**
                        * field for RecomendacionesNoEfectivas
                        */

                        
                                    protected short localRecomendacionesNoEfectivas ;
                                

                           /**
                           * Auto generated getter method
                           * @return short
                           */
                           public  short getRecomendacionesNoEfectivas(){
                               return localRecomendacionesNoEfectivas;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param RecomendacionesNoEfectivas
                               */
                               public void setRecomendacionesNoEfectivas(short param){
                            
                                            this.localRecomendacionesNoEfectivas=param;
                                    

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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.belcorp.biz/soa/mensajes/GestionConcursos");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":listaDetallesConcursoRecomendacion_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "listaDetallesConcursoRecomendacion_type0",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localConsultora==null){
                                                 throw new org.apache.axis2.databinding.ADBException("consultora cannot be null!!");
                                            }
                                           localConsultora.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","consultora"),
                                               xmlWriter);
                                        
                                            if (localSeccion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("seccion cannot be null!!");
                                            }
                                           localSeccion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","seccion"),
                                               xmlWriter);
                                        
                                            if (localCampaniaIngreso==null){
                                                 throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                            }
                                           localCampaniaIngreso.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","campaniaIngreso"),
                                               xmlWriter);
                                        
                                            if (localParticipadoRecomendacion==null){
                                                 throw new org.apache.axis2.databinding.ADBException("participadoRecomendacion cannot be null!!");
                                            }
                                           localParticipadoRecomendacion.serialize(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","participadoRecomendacion"),
                                               xmlWriter);
                                        
                                    namespace = "http://www.belcorp.biz/soa/mensajes/GestionConcursos";
                                    writeStartElement(null, namespace, "recomendacionesEfectivas", xmlWriter);
                             
                                               if (localRecomendacionesEfectivas==java.lang.Short.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecomendacionesEfectivas));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.belcorp.biz/soa/mensajes/GestionConcursos";
                                    writeStartElement(null, namespace, "recomendacionesNoEfectivas", xmlWriter);
                             
                                               if (localRecomendacionesNoEfectivas==java.lang.Short.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecomendacionesNoEfectivas));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.belcorp.biz/soa/mensajes/GestionConcursos")){
                return "ns7";
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

                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "consultora"));
                            
                            
                                    if (localConsultora==null){
                                         throw new org.apache.axis2.databinding.ADBException("consultora cannot be null!!");
                                    }
                                    elementList.add(localConsultora);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "seccion"));
                            
                            
                                    if (localSeccion==null){
                                         throw new org.apache.axis2.databinding.ADBException("seccion cannot be null!!");
                                    }
                                    elementList.add(localSeccion);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "campaniaIngreso"));
                            
                            
                                    if (localCampaniaIngreso==null){
                                         throw new org.apache.axis2.databinding.ADBException("campaniaIngreso cannot be null!!");
                                    }
                                    elementList.add(localCampaniaIngreso);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "participadoRecomendacion"));
                            
                            
                                    if (localParticipadoRecomendacion==null){
                                         throw new org.apache.axis2.databinding.ADBException("participadoRecomendacion cannot be null!!");
                                    }
                                    elementList.add(localParticipadoRecomendacion);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "recomendacionesEfectivas"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecomendacionesEfectivas));
                            
                                      elementList.add(new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos",
                                                                      "recomendacionesNoEfectivas"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRecomendacionesNoEfectivas));
                            

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
        public static ListaDetallesConcursoRecomendacion_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ListaDetallesConcursoRecomendacion_type0 object =
                new ListaDetallesConcursoRecomendacion_type0();

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
                    
                            if (!"listaDetallesConcursoRecomendacion_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ListaDetallesConcursoRecomendacion_type0)biz.belcorp.www.soa.business.ffvv.sicc.concursobs.types.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","consultora").equals(reader.getName())){
                                
                                                object.setConsultora(biz.belcorp.www.canonico.ffvv.vender.TConsultora.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","seccion").equals(reader.getName())){
                                
                                                object.setSeccion(biz.belcorp.www.canonico.ffvv.ubicaciongeografica.TSeccion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","campaniaIngreso").equals(reader.getName())){
                                
                                                object.setCampaniaIngreso(biz.belcorp.www.canonico.ffvv.vender.TCampania.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","participadoRecomendacion").equals(reader.getName())){
                                
                                                object.setParticipadoRecomendacion(biz.belcorp.www.canonico.ffvv.motivar.TParticipadoRecomendacion.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","recomendacionesEfectivas").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRecomendacionesEfectivas(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToShort(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setRecomendacionesEfectivas(java.lang.Short.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.belcorp.biz/soa/mensajes/GestionConcursos","recomendacionesNoEfectivas").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setRecomendacionesNoEfectivas(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToShort(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setRecomendacionesNoEfectivas(java.lang.Short.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
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
           
    