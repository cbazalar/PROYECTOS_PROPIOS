-- Add/modify columns 
alter table BAS_HISTO_LOTES add IND_ERRO_ANTE_INTE VARCHAR2(1);
alter table BAS_HISTO_LOTES add IND_ERRO_INTE VARCHAR2(1);
alter table BAS_HISTO_LOTES add IND_ERRO_POST_INTE VARCHAR2(1);
-- Add comments to the columns 
comment on column BAS_HISTO_LOTES.IND_ERRO_ANTE_INTE
  is 'Indica si hubo error en los procesos anteriores a la ejecucion de la Interfaz: [S]:Si, [N]: No';
comment on column BAS_HISTO_LOTES.IND_ERRO_INTE
  is 'Indica si hubo error en la ejecucion de la Interfaz: [S]:Si, [N]: No';
comment on column BAS_HISTO_LOTES.IND_ERRO_POST_INTE
  is 'Indica si hubo error en los procesos posteriores a la ejecucion de la Interfaz: [S]:Si, [N]: No';
  

-- Add/modify columns 
alter table BAS_HISTO_LOTES add FEC_IPRO_ANTE_INTE DATE;  
alter table BAS_HISTO_LOTES add FEC_FPRO_ANTE_INTE DATE;  
alter table BAS_HISTO_LOTES add FEC_IPRO_INTE DATE;  
alter table BAS_HISTO_LOTES add FEC_FPRO_INTE DATE;  
alter table BAS_HISTO_LOTES add FEC_IPRO_POST_INTE DATE;  
alter table BAS_HISTO_LOTES add FEC_FPRO_POST_INTE DATE;  

-- Add comments to the columns 
comment on column BAS_HISTO_LOTES.FEC_IPRO_ANTE_INTE
  is 'Fecha Inicio de los procesos anteriores a la ejecucion de la Interfaz';
comment on column BAS_HISTO_LOTES.FEC_FPRO_ANTE_INTE
  is 'Fecha Fin de los procesos anteriores a la ejecucion de la Interfaz';  
comment on column BAS_HISTO_LOTES.FEC_IPRO_INTE
  is 'Fecha Inicio de la ejecucion de la Interfaz';
comment on column BAS_HISTO_LOTES.FEC_FPRO_INTE
  is 'Fecha Fin de la ejecucion de la Interfaz';  
comment on column BAS_HISTO_LOTES.FEC_IPRO_POST_INTE
  is 'Fecha Inicio de los procesos posteriores a la ejecucion de la Interfaz';
comment on column BAS_HISTO_LOTES.FEC_FPRO_POST_INTE
  is 'Fecha Fin de los procesos posteriores a la ejecucion de la Interfaz';  
  