-- Add/modify columns 
alter table BAS_INTER add FLA_MOVE_ARCH_ENTR_TEMP VARCHAR2(1) default 'S';
-- Add comments to the columns 
comment on column BAS_INTER.FLA_MOVE_ARCH_ENTR_TEMP
  is 'Flag que indica que el Archivo de Entrada es movido al Archivo Temporal. Por defecto  el comportamiento en el SSiCC es S';