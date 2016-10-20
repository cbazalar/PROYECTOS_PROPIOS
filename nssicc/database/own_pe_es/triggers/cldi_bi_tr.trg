CREATE OR REPLACE TRIGGER "CLDI_BI_TR" 
BEFORE INSERT
ON MAE_CLIEN_DIREC
REFERENCING NEW AS New OLD AS Old
FOR EACH ROW
DECLARE
 cont NUMBER;
BEGIN
   cont := 0;

   select count(*) into cont from mae_clien_direc c where c.TIDC_OID_TIPO_DIRE = :New.TIDC_OID_TIPO_DIRE
   and c.CLIE_OID_CLIE = :New.clie_oid_clie and c.ind_elim = 0;

   if (cont > 0) then
       RAISE_APPLICATION_ERROR( -20001, 'NO SE PUEDE AGREGAR 2 TIPOS DE DIRECCIONES IGUALES ACTIVOS PARA UN CLIENTE' );
   end if;
END;
/

