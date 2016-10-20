CREATE OR REPLACE PROCEDURE MEN_PR_ACTUA_AUDIT (p_codigoPais IN VARCHAR2,
                                                p_codigoMenu IN VARCHAR2,
                                                p_url IN VARCHAR2,
                                                p_usuario IN VARCHAR2,
                                                p_ip IN VARCHAR2) IS
                                                
lncont NUMBER;                                                
   ln_sqlcode           NUMBER(10);
  ls_sqlerrm           VARCHAR2(1500);

BEGIN

  /* SELECT COUNT(1)
    into lncont
   FROM SEG_AUDIT_MENU
   WHERE  cod_pais= p_codigoPais
     and  cod_menu=p_codigoMenu
     and  val_ip= p_ip
     and  use_usua = p_usuario;*/
  
   --IF(lncont = 0) THEN
    INSERT INTO SEG_AUDIT_MENU (OID_AUDI,cod_pais,cod_menu,VAL_URL,val_ip,use_usua,FEC_ULTI_ACCE)
    VALUES(seg_audit_seq.nextval, p_codigoPais,p_codigoMenu,p_url,p_ip,p_usuario,SYSDATE);
  /* ELSE
     UPDATE SEG_AUDIT_MENU X
     SET X.FEC_ULTI_ACCE = SYSDATE
     WHERE cod_pais= p_codigoPais
     and  cod_menu=p_codigoMenu
     and  val_ip= p_ip
     and  use_usua = p_usuario;
   END IF;*/
EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MEN_PR_ACTUA_AUDIT: '||ls_sqlerrm);
END MEN_PR_ACTUA_AUDIT;
/
