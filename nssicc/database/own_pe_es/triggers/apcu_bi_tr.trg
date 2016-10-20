CREATE OR REPLACE TRIGGER "APCU_BI_TR" 
 BEFORE INSERT OR UPDATE
 ON EDU_APTAS_CURSO  FOR EACH ROW
BEGIN
	 IF ( :new.ind_envi IS NULL OR :new.ind_envi <> 1 ) THEN
	 	   :new.fec_ulti_actu := sysdate;
		   :new.ind_envi := 0;
	 END IF;
END;
/

