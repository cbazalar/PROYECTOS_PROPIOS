CREATE OR REPLACE PROCEDURE MSG_PR_ELIMI_BUZON_MENSA(
  psCodigoPais       VARCHAR2
)
IS
  W_FILAS      NUMBER:=5000;
  ln_sqlcode           NUMBER(10);
  ls_sqlerrm           VARCHAR2(1500);
  CURSOR c_buzon_histo IS
    SELECT oid_buzo_mens, num_secu, dato_vari_10,
          dato_vari_11, dato_vari_12, dato_vari_13,
          dato_vari_14, dato_vari_15, dato_vari_16,
          dato_vari_17, dato_vari_18, dato_vari_19,
          dato_vari_20, ind_esta_mens, clie_oid_clie,
          mens_oid_mens, modu_oid_modu_orig, val_nom1_clie,
          val_nom2_clie, val_ape1_clie, val_ape2_clie,
          val_apel_casa_clie, dato_vari_01, dato_vari_02,
          dato_vari_03, dato_vari_04, dato_vari_05,
          dato_vari_06, dato_vari_07, dato_vari_08,
          dato_vari_09, num_lote_impr, fec_grab,
          fec_impr, ind_list_cons, peri_oid_peri,
          ind_acti
     FROM msg_buzon_mensa
    WHERE ind_acti = 0
      AND fec_impr IS NOT NULL;

    TYPE buzonHistoricoRecord IS RECORD(

        oid_buzo_mens       msg_buzon_mensa.oid_buzo_mens%TYPE,
        num_secu            msg_buzon_mensa.num_secu%TYPE,
        dato_vari_10        msg_buzon_mensa.dato_vari_10%TYPE,
        dato_vari_11        msg_buzon_mensa.dato_vari_11%TYPE,
        dato_vari_12        msg_buzon_mensa.dato_vari_12%TYPE,
        dato_vari_13        msg_buzon_mensa.dato_vari_13%TYPE,
        dato_vari_14        msg_buzon_mensa.dato_vari_14%TYPE,
        dato_vari_15        msg_buzon_mensa.dato_vari_15%TYPE,
        dato_vari_16        msg_buzon_mensa.dato_vari_16%TYPE,
        dato_vari_17        msg_buzon_mensa.dato_vari_17%TYPE,
        dato_vari_18        msg_buzon_mensa.dato_vari_18%TYPE,
        dato_vari_19        msg_buzon_mensa.dato_vari_19%TYPE,
        dato_vari_20        msg_buzon_mensa.dato_vari_20%TYPE,
        ind_esta_mens       msg_buzon_mensa.ind_esta_mens%TYPE,
        clie_oid_clie       msg_buzon_mensa.clie_oid_clie%TYPE,
        mens_oid_mens       msg_buzon_mensa.mens_oid_mens%TYPE,
        modu_oid_modu_orig  msg_buzon_mensa.modu_oid_modu_orig%TYPE,
        val_nom1_clie       msg_buzon_mensa.val_nom1_clie%TYPE,
        val_nom2_clie       msg_buzon_mensa.val_nom2_clie%TYPE,
        val_ape1_clie       msg_buzon_mensa.val_ape1_clie%TYPE,
        val_ape2_clie       msg_buzon_mensa.val_ape2_clie%TYPE,
        val_apel_casa_clie  msg_buzon_mensa.val_apel_casa_clie%TYPE,
        dato_vari_01        msg_buzon_mensa.dato_vari_01%TYPE,
        dato_vari_02        msg_buzon_mensa.dato_vari_02%TYPE,
        dato_vari_03        msg_buzon_mensa.dato_vari_03%TYPE,
        dato_vari_04        msg_buzon_mensa.dato_vari_04%TYPE,
        dato_vari_05        msg_buzon_mensa.dato_vari_05%TYPE,
        dato_vari_06        msg_buzon_mensa.dato_vari_06%TYPE,
        dato_vari_07        msg_buzon_mensa.dato_vari_07%TYPE,
        dato_vari_08        msg_buzon_mensa.dato_vari_08%TYPE,
        dato_vari_09        msg_buzon_mensa.dato_vari_09%TYPE,
        num_lote_impr       msg_buzon_mensa.num_lote_impr%TYPE,
        fec_grab            msg_buzon_mensa.fec_grab%TYPE,
        fec_impr            msg_buzon_mensa.fec_impr%TYPE,
        ind_list_cons       msg_buzon_mensa.ind_list_cons%TYPE,
        peri_oid_peri       msg_buzon_mensa.peri_oid_peri%TYPE,
        ind_acti            msg_buzon_mensa.ind_acti%TYPE
    );

    TYPE buzonHistoricoTab IS TABLE OF buzonHistoricoRecord;
    buzonHistorico buzonHistoricoTab;

  CURSOR c_buzon IS
    SELECT rowid ID
      FROM msg_buzon_mensa
     WHERE ind_acti = 0
       AND fec_impr IS NOT NULL;

    TYPE vROWID IS TABLE OF VARCHAR2(30) INDEX BY BINARY_INTEGER;
    tROWID vROWID;

BEGIN

  -- Insertamos mensajes Impresos en el Buzon de Mensajes Historico ---
  OPEN c_buzon_histo;
    LOOP
      FETCH c_buzon_histo BULK COLLECT INTO buzonHistorico LIMIT W_FILAS;

        IF buzonHistorico.COUNT > 0 THEN

          FORALL x IN buzonHistorico.FIRST .. buzonHistorico.LAST

            INSERT INTO msg_buzon_mensa_histo VALUES buzonHistorico(x);

        END IF;

      EXIT WHEN c_buzon_histo%NOTFOUND;

    END LOOP;

  CLOSE c_buzon_histo;

  -- Eliminamos mensajes impresos del buzon de mensajes ---
  OPEN c_buzon;
    LOOP
      FETCH c_buzon BULK COLLECT INTO tROWID LIMIT W_FILAS;

        IF tROWID.COUNT > 0 THEN

          FORALL I IN tROWID.FIRST .. tROWID.LAST

            DELETE msg_buzon_mensa WHERE rowid = tROWID(i);

        END IF;

      EXIT WHEN c_buzon%NOTFOUND;

    END LOOP;

  CLOSE c_buzon;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MSG_PR_ELIMI_BUZON_MENSA: '||ls_sqlerrm);
END MSG_PR_ELIMI_BUZON_MENSA;
/

