CREATE OR REPLACE PACKAGE aco_pkg_proce IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  w_filas    NUMBER := 1000;

/***************************************************************************
Descripcion       : Ejecuta shell aco segun codigo de interfaz
Fecha Creacion    : 19/11/2014
Autor             : Sergio Buchelli
Parametros        :
            psCodPais : codigo Pais
            psCodInterfaz : codigo Interfaz

***************************************************************************/
PROCEDURE ACO_PR_EJECU_SHELL(
    psCodPais VARCHAR2,
    psCodInterfaz VARCHAR2
    );

END aco_pkg_proce;
/
CREATE OR REPLACE PACKAGE BODY aco_pkg_proce IS

/***************************************************************************
Descripcion       : Ejecuta shell aco segun codigo de interfaz
Fecha Creacion    : 19/11/2014
Autor             : Sergio Buchelli
Parametros        :
            psCodPais : codigo Pais
            psCodInterfaz : codigo Interfaz

***************************************************************************/
PROCEDURE ACO_PR_EJECU_SHELL(
    psCodPais VARCHAR2,
    psCodInterfaz VARCHAR2
    )
IS
FOLIO_LESS EXCEPTION;
lnValorIni NUMBER(12);
lnValorFin NUMBER(12);
lsAlert    VARCHAR2(200);
BEGIN
   if(psCodInterfaz = 'ACO-01') then
    --Elimina_MSG_Puntos.sh
    --Este script elimina mensajes de INC originados por devoluciones y de PED.
    DELETE FROM msg_buzon_mensa a
    WHERE a.MENS_OID_MENS IN (SELECT a.OID_MENS FROM msg_mensa a
    WHERE a.cod_mens IN ('INC01','INC21','INC22','INC23','INC97','INC99','PED07','PED20','PED21','EDU04'))
    AND a.fec_impr IS NULL;

    UPDATE pre_ofert a SET a.IND_DESP_COMPL=0
     WHERE a.IND_DESP_COMPL=1 AND a.COES_OID_ESTR=2014;


   end if;

  if(psCodInterfaz = 'ACO-02') then
    --Rutas.sh
    update app_rutas_trans set num_secu = to_number(cod_ruta);


   end if;

  if(psCodInterfaz = 'ACO-03') then
    --RUV_FACT_PREMIO.SH
    DELETE FAC_REGIS_UNICO_VENTA
                   WHERE TIDO_OID_TIPO_DOCU = 28;


   end if;

  if(psCodInterfaz = 'ACO-04') then
   -- BoletasHonorarios.sh
  
   begin
    select 'Quedan menos de 15000 folios habilitados, enviar correo alertando a Cl.Soporte Aplicaciones TI <cl.SoporteAppTI@belcorp.biz>' as alerta , 
      a.val_actu, a.val_fin INTO lsAlert, lnValorIni, lnValorFin
    from imp_folio a where a.val_fin - a.val_actu < 15000;
   EXCEPTION
   when others then
      lsAlert:='';
   end;
    IF(lsAlert <> '' and LENGTH(lsAlert) > 0) THEN
         RAISE FOLIO_LESS;
    END IF ;
   end if;

  if(psCodInterfaz = 'ACO-05') then
    -- VELBACTUACUPANTIG.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-06') then
    -- MSG_nuevas_2_3_4_ped_VELB.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-07') then
    -- Elimina_Msg_Puntos.sh
    -- MSG_INC9-34-CRLB.sh
    -- MSG-INC95-CRLB.sh
    -- RUV_FACT_PREMIO.sh
    -- RUTAS.sh
    -- UPDATE_REC_BORE.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-08') then
    -- RUTAS.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-09') then
    -- DAT-INC.sh  DD MM AAAA SSSS

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-10') then
    -- MarcaPedidosChequear.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-11') then   
  -- nuevas_Mylbel_cierre_periodo_EC_LB.sh
    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-12') then
    -- ACT_DEUDA_EC_ESIKA.sh
    -- ACTUALIZA_DEUDA_LBEL.sh
    -- Act_Dir_Rec_PEES.sh
    -- Act_Dir_Rec_ECLB.sh
    -- UNIDADES_STOCK.sh
    -- actualiza_ind_caja_gp4.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-13') then
    -- ACTIVACION_GERENTES.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-14') then
    -- fin_cra_perio.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-15') then
    -- Desbloqueo_Consultoras_Bloqueadas.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

  if(psCodInterfaz = 'ACO-16') then
    -- Nuevas_Mylbel_cierre_periodo.sh

    DBMS_OUTPUT.PUT_LINE('TO DO');

   end if;

EXCEPTION
  WHEN  FOLIO_LESS THEN
    raise_application_error(-20123,'ERROR ACO_PR_EJECU_SHELL: ' || psCodInterfaz || ' --> '|| lsAlert);
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,1,250);
    raise_application_error(-20123,'ERROR ACO_PR_EJECU_SHELL: ' || psCodInterfaz || ' --> '|| ls_sqlerrm);
END ACO_PR_EJECU_SHELL;


END aco_pkg_proce;
/
