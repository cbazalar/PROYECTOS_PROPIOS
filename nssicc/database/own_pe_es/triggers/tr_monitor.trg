CREATE OR REPLACE TRIGGER "TR_MONITOR" AFTER
INSERT
OR UPDATE OF "VAL_THRE" ON "GEN_PROCE_BATCH"
FOR EACH ROW
BEGIN
  sys.dbms_application_info.set_client_info(:new.val_thre||'-'||to_char(sysdate,'DDMMYYYYHH24:MI'));
  sys.dbms_application_info.set_module(substr(:new.cod_proc,1,48),substr(:new.val_usua,1,32));
END;
/

