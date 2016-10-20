-- Create table
drop table BAS_REPOR_EJECU_ACTUA;
-- Create table                                                                                                  
create table BAS_REPOR_EJECU_ACTUA                                                                               
(                                                                                                                
  NUM_SEQU        NUMBER(12) not null,                                                                           
  PAIS_COD_PAIS   VARCHAR2(3),                                                                                   
  COD_MENU        VARCHAR2(8),                                                                                   
  COD_REPO        VARCHAR2(100),                                                                                 
  NOM_ARCH_REPO   VARCHAR2(200),                                                                                 
  VAL_IP_MAQU     VARCHAR2(50),                                                                                  
  COD_USUA        VARCHAR2(20),                                                                                  
  COD_PERI        VARCHAR2(6),                                                                                   
  FEC_INIC_REPO   DATE,                                                                                          
  FEC_FINA_REPO   DATE,                                                                                          
  VAL_FORM_REPO   VARCHAR2(10),                                                                                  
  NUM_DURA_SEGU   NUMBER(12),                                                                                    
  IND_MULTI_REPO  VARCHAR2(1) default 'N',                                                                       
  VAL_REPO_GENE   NUMBER(3) default 1,                                                                           
  IND_ENVIO_EMAIL VARCHAR2(1) default 'N',                                                                       
  IND_ERRO        VARCHAR2(1) default 'N'                                                                        
);                                                                                                             
-- Add comments to the columns                                                                                   
comment on column BAS_REPOR_EJECU_ACTUA.NUM_SEQU                                                                 
  is 'Numero Secuencial usado como PK';                                                                          
comment on column BAS_REPOR_EJECU_ACTUA.PAIS_COD_PAIS                                                            
  is 'Codigo de Pais';                                                                                           
comment on column BAS_REPOR_EJECU_ACTUA.COD_MENU                                                                 
  is 'Codigo de Menu asociado al Reporte';                                                                       
comment on column BAS_REPOR_EJECU_ACTUA.COD_REPO                                                                 
  is 'Codigo de Reporte';                                                                                        
comment on column BAS_REPOR_EJECU_ACTUA.NOM_ARCH_REPO                                                            
  is 'Nombre del Reporte Generado';                                                                              
comment on column BAS_REPOR_EJECU_ACTUA.VAL_IP_MAQU                                                              
  is 'IP de la maquina donde se pidio la ejecucion del Reporte';                                                 
comment on column BAS_REPOR_EJECU_ACTUA.FEC_INIC_REPO                                                            
  is 'Fecha Inicio de Generaci?n del Reporte';                                                                   
comment on column BAS_REPOR_EJECU_ACTUA.FEC_FINA_REPO                                                            
  is 'Fecha Fin de Generaci?n del Reporte';                                                                      
comment on column BAS_REPOR_EJECU_ACTUA.VAL_FORM_REPO                                                            
  is 'Formato en que se ha generado el Reporte';                                                                 
comment on column BAS_REPOR_EJECU_ACTUA.NUM_DURA_SEGU                                                            
  is 'Duracion que ha demorado la Generacion del Reporte (En segundos)';                                         
comment on column BAS_REPOR_EJECU_ACTUA.IND_MULTI_REPO                                                           
  is 'Indica si el Reporte Generado es MultiReporte: [S]:Si, [N]: No';                                           
comment on column BAS_REPOR_EJECU_ACTUA.VAL_REPO_GENE                                                            
  is 'Nro de Reportes generados';                                                                                
comment on column BAS_REPOR_EJECU_ACTUA.IND_ENVIO_EMAIL                                                          
  is 'Indica si el Reporte Generado es enviado via e-mail: [S]:Si, [N]: No';                                     
comment on column BAS_REPOR_EJECU_ACTUA.IND_ERRO                                                                 
  is 'Indica si la ejecuion del Reporte termino con Errores';                                                    
-- Create/Recreate primary, unique and foreign key constraints                                                   
alter table BAS_REPOR_EJECU_ACTUA                                                                                
  add constraint BAS_EJAC_PK primary key (NUM_SEQU)                                                              
  using index;                                                                                                             
-- Create/Recreate indexes                                                                                       
create index BAS_EJAC_IX1 on BAS_REPOR_EJECU_ACTUA (COD_MENU, COD_REPO, PAIS_COD_PAIS)  ;                                                                                                             
create index BAS_EJAC_IX2 on BAS_REPOR_EJECU_ACTUA (COD_USUA, COD_MENU, COD_REPO, PAIS_COD_PAIS) ;                                                                                                             
create index BAS_EJAC_IX3 on BAS_REPOR_EJECU_ACTUA (COD_PERI, COD_MENU, COD_REPO, PAIS_COD_PAIS)  ;                                                                                                             
create index BAS_EJAC_IX4 on BAS_REPOR_EJECU_ACTUA (TO_CHAR(FEC_INIC_REPO,'DD/MM/YYYY'), COD_MENU, PAIS_COD_PAIS);  

drop table BAS_REPOR_EJECU_HISTO;
-- Create table
-- Create table
create table BAS_REPOR_EJECU_HISTO
(
  NUM_SEQU        NUMBER(12) not null,
  PAIS_COD_PAIS   VARCHAR2(3),
  COD_MENU        VARCHAR2(8),
  COD_REPO        VARCHAR2(100),
  NOM_ARCH_REPO   VARCHAR2(200),
  VAL_IP_MAQU     VARCHAR2(50),
  COD_USUA        VARCHAR2(20),
  COD_PERI        VARCHAR2(6),
  FEC_INIC_REPO   DATE,
  FEC_FINA_REPO   DATE,
  VAL_FORM_REPO   VARCHAR2(10),
  NUM_DURA_SEGU   NUMBER(12),
  IND_MULTI_REPO  VARCHAR2(1) default 'N',
  VAL_REPO_GENE   NUMBER(3) default 1,
  IND_ENVIO_EMAIL VARCHAR2(1) default 'N',
  IND_ERRO        VARCHAR2(1) default 'N'
);
-- Add comments to the columns 
comment on column BAS_REPOR_EJECU_HISTO.NUM_SEQU
  is 'Numero Secuencial usado como PK';
comment on column BAS_REPOR_EJECU_HISTO.PAIS_COD_PAIS
  is 'Codigo de Pais';
comment on column BAS_REPOR_EJECU_HISTO.COD_MENU
  is 'Codigo de Menu asociado al Reporte';
comment on column BAS_REPOR_EJECU_HISTO.COD_REPO
  is 'Codigo de Reporte';
comment on column BAS_REPOR_EJECU_HISTO.NOM_ARCH_REPO
  is 'Nombre del Reporte Generado';
comment on column BAS_REPOR_EJECU_HISTO.VAL_IP_MAQU
  is 'IP de la maquina donde se pidio la ejecucion del Reporte';
comment on column BAS_REPOR_EJECU_HISTO.FEC_INIC_REPO
  is 'Fecha Inicio de Generaci?n del Reporte';
comment on column BAS_REPOR_EJECU_HISTO.FEC_FINA_REPO
  is 'Fecha Fin de Generaci?n del Reporte';
comment on column BAS_REPOR_EJECU_HISTO.VAL_FORM_REPO
  is 'Formato en que se ha generado el Reporte';
comment on column BAS_REPOR_EJECU_HISTO.NUM_DURA_SEGU
  is 'Duracion que ha demorado la Generacion del Reporte (En segundos)';
comment on column BAS_REPOR_EJECU_HISTO.IND_MULTI_REPO
  is 'Indica si el Reporte Generado es MultiReporte: [S]:Si, [N]: No';
comment on column BAS_REPOR_EJECU_HISTO.VAL_REPO_GENE
  is 'Nro de Reportes generados';
comment on column BAS_REPOR_EJECU_HISTO.IND_ENVIO_EMAIL
  is 'Indica si el Reporte Generado es enviado via e-mail: [S]:Si, [N]: No';
comment on column BAS_REPOR_EJECU_HISTO.IND_ERRO
  is 'Indica si la ejecuion del Reporte termino con Errores';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAS_REPOR_EJECU_HISTO
  add constraint BAS_EJHI_PK primary key (NUM_SEQU)
  using index ;
-- Create/Recreate indexes 
create index BAS_EJHI_IX1 on BAS_REPOR_EJECU_HISTO (COD_MENU, COD_REPO, PAIS_COD_PAIS);
create index BAS_EJHI_IX2 on BAS_REPOR_EJECU_HISTO (COD_USUA, COD_MENU, COD_REPO, PAIS_COD_PAIS);
create index BAS_EJHI_IX3 on BAS_REPOR_EJECU_HISTO (COD_PERI, COD_MENU, COD_REPO, PAIS_COD_PAIS);
create index BAS_EJHI_IX4 on BAS_REPOR_EJECU_HISTO (TO_CHAR(FEC_INIC_REPO,'DD/MM/YYYY'), COD_MENU, PAIS_COD_PAIS);
                                                                                                           