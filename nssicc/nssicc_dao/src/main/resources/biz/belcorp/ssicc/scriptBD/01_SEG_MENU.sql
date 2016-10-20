-- Add/modify columns 
alter table SSICC_COMUN.SEG_MENU add PAG_XML VARCHAR2(300);
-- Add comments to the columns 
comment on column SSICC_COMUN.SEG_MENU.PAG_XML
  is 'Pagina XHTML asociada a la opcion del Menu (Actualizacion Framework)';
  
  
  
  