CREATE OR REPLACE PROCEDURE "CCC_GENE_NUME_LINE_DETA_CAB"
AS
CURSOR CUR_CABECERA IS
           SELECT C.OID_CABE_CARG
           FROM CCC_CABEC_CARGA_ABONO_DIREC C
           WHERE EXISTS(SELECT 1
                           FROM CCC_DETAL_CARGO_ABONO_DIREC D
                         WHERE D.CCAD_OID_CABE_CARG = C.OID_CABE_CARG AND D.NUM_LINE IS NULL);
BEGIN
--Agregado de Gacevedo para los detalles de cargos y abonos
--segun lo conversado con Juan Pablo.
  FOR CUR IN CUR_CABECERA LOOP
   UPDATE (SELECT C.OID_DETA_CARG_ABON_DIRE, C.NUM_LINE
       FROM CCC_DETAL_CARGO_ABONO_DIREC C
	WHERE C.CCAD_OID_CABE_CARG = CUR.OID_CABE_CARG
    )
   SET NUM_LINE = ROWNUM;
  END LOOP;
  DELETE CCC_CABEC_ABONO_DIREC_TEMP;
END;
/

