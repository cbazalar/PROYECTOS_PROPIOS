-- Add/modify columns 
alter table SSICC_COMUN.SEG_MENU add IND_OCUL_MENU VARCHAR2(1) default 'N';
-- Add comments to the columns 
comment on column SSICC_COMUN.SEG_MENU.IND_OCUL_MENU
  is 'Indicador para ocultar menu al momento de hacer click en dicha opcion en el Menu';
  
  
UPDATE SSICC_COMUN.SEG_MENU
SET IND_OCUL_MENU = 'N';

COMMIT;


UPDATE SEG_MENU  x
SET x.ind_ocul_menu = 'S'
WHERE X.COD_MENU IN
(
'20460100',
'20010119',
'20200500',
'20010744',
'20010906',
'20010908',
'20050200',
'20080600',
'20081500',
'20090100',
'20090200',
'20080100',
'20080200',
'20080300',
'20200100',
'20250900'
);

COMMIT;