-- Create table
drop table BAS_AUDI_PROCE_USUA_ACTUA;

create table BAS_AUDI_PROCE_USUA_ACTUA
(
  NUM_SEQU        NUMBER(12) not null,
  PAIS_COD_PAIS   VARCHAR2(3),
  COD_MENU        VARCHAR2(8),
  COD_ACCI        VARCHAR2(100),
  VAL_IP_MAQU     VARCHAR2(50),
  COD_USUA        VARCHAR2(6),
  COD_PERI        VARCHAR2(6),
  FEC_INIC_PROC   DATE,
  FEC_FINA_PROC   DATE,
  NUM_DURA_SEGU   NUMBER(12),
  IND_ESTA_PROC   VARCHAR2(10)
);

-- Add comments to the columns 
comment on column BAS_AUDI_PROCE_USUA_ACTUA.NUM_SEQU
  is 'Numero Secuencial usado como PK';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.PAIS_COD_PAIS
  is 'Codigo de Pais';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.COD_MENU
  is 'Codigo de Menu asociado al Reporte';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.COD_ACCI
  is 'Codigo de Acci�n';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.VAL_IP_MAQU
  is 'IP de la maquina donde se pidio la ejecucion del Reporte';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.FEC_INIC_PROC
  is 'Fecha Inicio de Generaci�n del proceso';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.FEC_FINA_PROC
  is 'Fecha Fin de Generaci�n del proceso';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.NUM_DURA_SEGU
  is 'Duracion que ha demorado la Generacion del proceso (En segundos)';
comment on column BAS_AUDI_PROCE_USUA_ACTUA.IND_ESTA_PROC
  is 'Indicador del Estado del proceso';
  
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAS_AUDI_PROCE_USUA_ACTUA
  add constraint BAS_PRUA_PK primary key (NUM_SEQU)
  using index;
-- Create/Recreate indexes 
create index BAS_PRUA_IX1 on BAS_AUDI_PROCE_USUA_ACTUA (COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUA_IX2 on BAS_AUDI_PROCE_USUA_ACTUA (COD_USUA, COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUA_IX3 on BAS_AUDI_PROCE_USUA_ACTUA (COD_PERI, COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUA_IX4 on BAS_AUDI_PROCE_USUA_ACTUA (TO_CHAR(FEC_INIC_PROC,'DD/MM/YYYY'), COD_MENU, PAIS_COD_PAIS);


drop table BAS_AUDI_PROCE_USUA_HISTO;
-- Create table
create table BAS_AUDI_PROCE_USUA_HISTO
(
  NUM_SEQU        NUMBER(12) not null,
  PAIS_COD_PAIS   VARCHAR2(3),
  COD_MENU        VARCHAR2(8),
  COD_ACCI        VARCHAR2(100),
  VAL_IP_MAQU     VARCHAR2(50),
  COD_USUA        VARCHAR2(6),
  COD_PERI        VARCHAR2(6),
  FEC_INIC_PROC   DATE,
  FEC_FINA_PROC   DATE,
  NUM_DURA_SEGU   NUMBER(12),
  IND_ESTA_PROC   VARCHAR2(10)
);
-- Add comments to the columns 
comment on column BAS_AUDI_PROCE_USUA_HISTO.NUM_SEQU
  is 'Numero Secuencial usado como PK';
comment on column BAS_AUDI_PROCE_USUA_HISTO.PAIS_COD_PAIS
  is 'Codigo de Pais';
comment on column BAS_AUDI_PROCE_USUA_HISTO.COD_MENU
  is 'Codigo de Menu asociado al Reporte';
comment on column BAS_AUDI_PROCE_USUA_HISTO.COD_ACCI
  is 'Codigo de Acci�n';
comment on column BAS_AUDI_PROCE_USUA_HISTO.VAL_IP_MAQU
  is 'IP de la maquina donde se pidio la ejecucion del Reporte';
comment on column BAS_AUDI_PROCE_USUA_HISTO.FEC_INIC_PROC
  is 'Fecha Inicio de Generaci�n del proceso';
comment on column BAS_AUDI_PROCE_USUA_HISTO.FEC_FINA_PROC
  is 'Fecha Fin de Generaci�n del proceso';
comment on column BAS_AUDI_PROCE_USUA_HISTO.NUM_DURA_SEGU
  is 'Duracion que ha demorado la Generacion del proceso (En segundos)';
comment on column BAS_AUDI_PROCE_USUA_HISTO.IND_ESTA_PROC
  is 'Indicador del Estado del proceso';
 
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAS_AUDI_PROCE_USUA_HISTO
  add constraint BAS_PRUH_PK primary key (NUM_SEQU)
  using index;
-- Create/Recreate indexes 
create index BAS_PRUH_IX1 on BAS_AUDI_PROCE_USUA_HISTO (COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUH_IX2 on BAS_AUDI_PROCE_USUA_HISTO (COD_USUA, COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUH_IX3 on BAS_AUDI_PROCE_USUA_HISTO (COD_PERI, COD_MENU, COD_ACCI, PAIS_COD_PAIS);
create index BAS_PRUH_IX4 on BAS_AUDI_PROCE_USUA_HISTO (TO_CHAR(FEC_INIC_PROC,'DD/MM/YYYY'), COD_MENU, PAIS_COD_PAIS);

alter table BAS_AUDI_PROCE_USUA_ACTUA modify COD_USUA varchar2(15);
alter table BAS_AUDI_PROCE_USUA_HISTO modify COD_USUA varchar2(15);