CREATE OR REPLACE PACKAGE "REC_PKG_REPOR" IS

   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;


   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;

    TYPE tRegUnidadesFacturadas IS RECORD (
     NUM_UNID_DEMA   PED_SOLIC_POSIC.NUM_UNID_DEMA%TYPE,
     VAL_PREC_FACT_TOTA_LOCA   PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA%TYPE,
     NUM_PEDI        NUMBER
   );
   TYPE tablaUnidadesFacturadas IS TABLE OF tRegUnidadesFacturadas;
  /***************************************************************************
  Descripcion       : Recupera el numero y monto de las unidades facturadas de un
                      producto especifico en un determinado periodo
  Fecha Creacion    : 23/01/2007
  psValorRetorno
          NUM_UNID_DEMA  : Devuelve el numero de las unidades facturadas de ese
                           producto en un determinado periodo
          VAL_PREC_FACT_TOTA_LOCA  : Devuelve Monto de las unidades facturadas
                                    de ese producto en un determinado periodo
  Autor             : Marco Agurto
  ***************************************************************************/

   FUNCTION REC_FN_OBTIE_UNID_FACT(psCodigoPeriodo VARCHAR2,
                                    psCodigoProducto VARCHAR2,
                                    psValorRetorno VARCHAR2)
   RETURN NUMBER;
    /***************************************************************************
    Descripcion       : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
                        undidades facturadas, monto facturado y el numero de pedidos
                        por producto y  periodo
    Fecha Creacion    : 23/01/2007

    Autor             : Marco Agurto
    ***************************************************************************/
    PROCEDURE  REC_PR_UPDAT_UNID_FACT_MASRE (psOidPeriodoInicio         NUMBER,
  psOidPeriodoFinal  NUMBER,
  lsSecuencial     OUT NUMBER);

    /***************************************************************************
    Descripcion       : Actualiza la tabla REC_REPOR_RECLAMOS los valores de
                        undidades facturadas, monto facturado y el numero de pedidos
                        por producto y  periodo
    Fecha Creacion    : 30/01/2007

    Autor             : Marco Agurto
    ***************************************************************************/
    PROCEDURE  REC_PR_UPDAT_UNID_FACT;


    /***************************************************************************
    Descripcion       : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
                        undidades facturadas, monto facturado y el numero de pedidos
                        por producto y  periodo
                        - Reporte de Reclamos por Motivos de Devoluci¿n
    Fecha Creacion    : 28/06/2007

    Autor             : Marco Agurto
    ***************************************************************************/
    PROCEDURE  REC_PR_UPDAT_UNID_FACT_MOTIV;

    /***************************************************************************
    Descripcion       : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
                        undidades facturadas, monto facturado y el numero de pedidos
                        por producto y  periodo
                        - Reporte de Reclamos de Operaciones por Unidad Administrativa
    Fecha Creacion    : 28/06/2007

    Autor             : Marco Agurto
    ***************************************************************************/
    PROCEDURE  REC_PR_UPDAT_UNID_FACT_UNIDA (psCodigoPais varchar2);
    /***************************************************************************
    Descripcion       : Recupera numero de unidades atendidas
    Fecha Creacion    : 27/01/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_UNID_ATE(psOidTipoSoliPais  NUMBER,
                               psOidOperaReclamo NUMBER,
                                psOidProducto NUMBER)
    RETURN NUMBER;


    /***************************************************************************
    Descripcion       : Recupera numero de unidades faltantes
    Fecha Creacion    : 27/01/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_UNID_FAL(psOidTipoSoliPais  NUMBER,
                               psOidOperaReclamo NUMBER,
                                psOidProducto NUMBER)
    RETURN NUMBER;

    /***************************************************************************
    Descripcion       : Recupera Toma la solicitud asociada al precio correspondiente
                        Si es mercaderia, mostrar Precio Catalogo Unitario Total
                        Si es incentivo(premio), mostrar Precio Contable
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_PRECIO(psOidSoliPosi NUMBER)
    RETURN NUMBER ;
    /***************************************************************************
    Descripcion       : Recupera Stock de un determinado periodo
                        producto, de un almacen y un determinado codigo de venta
    Fecha Creacion    : 08/02/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_STOCK(psOidPeriodo  NUMBER,
                                psOidEstado NUMBER,
                                psOidProducto NUMBER,
                                psOidAlmacen NUMBER,
                                psValCodiVenta VARCHAR2)
    RETURN NUMBER;

    /***************************************************************************
    Descripcion       : Recupera el codigo de venta  a partir de un codigo de la
                        matriz de facturacion, en nuestro caso este se obteiene de
                        la linea de reclamo
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_CODI_VENTA(psOidMatrizFact NUMBER  )
    RETURN VARCHAR2;
    /***************************************************************************
    Descripcion       : Recupera las unidades reclamadas por producto de una determinada
                        abecera de reclamos
                        la linea de reclamo
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_UNID_RECL_PROD(psOidProducto NUMBER,
                                     psOidOperRecla NUMBER,
                                     psTipoMovimiento NUMBER)
    RETURN NUMBER ;
    /***************************************************************************
    Descripcion       : Recupera la descripcion de producto que se encuentra
                        en la tabla OFERTA_DETALLE
    Parametro         :             psCodigoPeriodo Periodo
                                    psCodigoVenta   Codigo de Venta
    Fecha Creacion    : 12/02/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION OCR_FN_DEV_DES_PROD_OFER(psCodigoPeriodo VARCHAR2,
                                      psCodigoVenta VARCHAR2)
                                      RETURN VARCHAR2;
    /***************************************************************************
    Descripcion       : Recupera los datos de REC_OPERA, segun el ID ingresado
                        como parametro
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OPERA_DATOS(psOidOpera NUMBER , psTipo VARCHAR2 )
    RETURN VARCHAR2     ;
    /***************************************************************************
    Descripcion       : Recupera los datos de REC_TIPOS_OPERA, segun el ID ingresado
                        como parametro
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_TIPO_OPERA_DATOS(psOidTipoOperacion NUMBER  )
    RETURN VARCHAR2;
    /***************************************************************************
    Descripcion       : Recupera el documento de referencia de PED_SOLIC_CABE
    Fecha Creacion    : 16/04/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_DOCU_REFE(psOidSolicitudCabecera  NUMBER  )
    RETURN NUMBER;
    /***************************************************************************
    Descripcion       : Recupera el numero de pedidos de una zona y periodo
                        seleccionado
    Fecha Creacion    : 26/07/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_NUMER_PEDID(psOidZona NUMBER, psOidPeriodo NUMBER)
    RETURN NUMBER;
    /***************************************************************************
    Descripcion       : Recupera el numero de pedidos de un producto y periodo
                        seleccionado
    Fecha Creacion    : 06/08/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_NUMER_PEDID_PRODU(psOidProducto NUMBER, psOidPeriodo NUMBER)
    RETURN NUMBER ;
    /***************************************************************************
    Descripcion       : Recupera el importe reclamado de un producto
                        seleccionado
    Fecha Creacion    : 06/08/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_IMPOR_RECLA_PRODU(psOidProducto NUMBER)
    RETURN NUMBER    ;
    /***************************************************************************
    Descripcion       : Recupera el numero de unidades reclamadas de un producto
                      seleccionado
    Fecha Creacion    : 06/08/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_NUMER_RECLA_PRODU(psOidProducto NUMBER)
    RETURN NUMBER ;
    /***************************************************************************
    Descripcion       : Recupera el numero de guias reclamadas de un producto
                        seleccionado
    Fecha Creacion    : 06/08/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_NUMER_GUIA_PRODU(psOidProducto NUMBER)
    RETURN NUMBER ;
    /***************************************************************************
    Descripcion       : Recupera el numero de pedidos por Oid Terri Admin
                      seleccionado
    Fecha Creacion    : 17/09/2007
    Autor             : Marco Agurto
    ***************************************************************************/
    FUNCTION REC_FN_OBTIE_PEDID_TERRI_ADMIN(psOidTerrAdmin NUMBER, psOidPeriodo NUMBER)
    RETURN NUMBER;
    /***************************************************************************
    Descripcion       : Devuelve el oid_tipo_solic_pais
    Fecha Creacion    : 25/01/2008
    Autor             : jose Antonio
    ***************************************************************************/
    FUNCTION GEN_FN_DEVUE_OID_TSOLI_PAIS(psCodigoTipoSolicitud VARCHAR2)
    RETURN NUMBER;
    /***************************************************************************
Descripcion       : Devuelve le codigo de Venta Ficticon del Reclamo
Fecha Creacion    : 10/04/2008
Autor             : CRISTHIAn ROMAN
***************************************************************************/
FUNCTION GEN_FN_COD_VENTA_FICT_RECLA( psPrdOid IN NUMBER,
		 						psConcurso IN NUMBER,
								psNivePrem IN NUMBER,
								psLotePrem IN NUMBER
								)
RETURN VARCHAR2;
/***************************************************************************
	Descripcion       : Carga las tablas ped_bole_cabec Y ped_bole_deta
	Fecha Creacion    : 10/04/2008
	Autor             : CRISTHIAn ROMAN
	***************************************************************************/
	PROCEDURE REC_PR_CARGA_BOREC
	(--pcod_vent IN VARCHAR2,
	pcod_peri IN VARCHAR2, pcod_pais IN VARCHAR2, pcod_Iso IN VARCHAR2);
	/***************************************************************************
    Descripcion       : Verifica que no exista registros iguales en la tabla
					  	REC_BOREC_ESPEC_CABEC
    Fecha Creacion    : 20/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    FUNCTION REC_FN_VERI_BOREC(pcod_peri  VARCHAR2, pcod_pais VARCHAR2,
			 				pcod_clie  VARCHAR2, psOidSolicitud NUMBER)
    RETURN NUMBER;
    /***************************************************************************
    Descripcion       : Verifica que no exista registros iguales en la tabla
					  	REC_BOREC_ESPEC_DETA
    Fecha Creacion    : 20/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    FUNCTION REC_FN_VERI_BOREC_DETA(pcod_peri  VARCHAR2, pcod_pais VARCHAR2,
			 				pcod_clie  VARCHAR2, psOidSolicitud NUMBER, psCodVenta VARCHAR2)
    RETURN NUMBER;
	/***************************************************************************
    Descripcion       : Verifica que no exista registros iguales en la tabla
					  	REC_BOREC_ESPEC_DETA
    Fecha Creacion    : 29/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    FUNCTION REC_FN_DEVU_NUM_BOLE(pcod_pais VARCHAR2 )
    RETURN NUMBER;
     /***************************************************************************
    Descripcion       : Actualiza el valor de  numero de boleta en la tabla
					  	REC_BOREC_ESPEC_CONTR
    Fecha Creacion    : 29/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    PROCEDURE REC_PR_UPDAT_NUM_BOLE(pcod_pais VARCHAR2,val_nume_bole NUMBER);
    /***************************************************************************
    Descripcion       : Recupera el OID_BOREC_ESPEC_CABE  de la  tabla
					  	REC_BOREC_ESPEC_CABEC
    Fecha Creacion    : 29/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    FUNCTION REC_FN_DEVUE_OID_BOLE_CABEC(pcod_pais VARCHAR2, pcod_clie VARCHAR2,
			 							pcod_peri VARCHAR2, ps_nume_soli NUMBER)
    RETURN NUMBER;
	/***************************************************************************
    Descripcion       : Recupera el VAL_NUME_SOLI de la  tabla
					  	PED_SOLIC_CABEC
    Fecha Creacion    : 29/05/2008
    Autor             : Cristhian Roman
    ***************************************************************************/
    FUNCTION REC_FN_DEVUE_VAL_NUME_SOLI( ps_soca_oid_soli_cabe NUMBER)
    RETURN NUMBER;

/***************************************************************************
Descripcion       : Actualiza la tabla REC_REPOR_OPERA_UNIDA_ADMIN los valores de
                    tipos de pedido
                    - Reporte de Operaciones por Unidad Administrativa
Fecha Creacion    : 23/11/2010

Autor             : Carlos Diaz Valverde
***************************************************************************/
PROCEDURE  REC_PR_UPDAT_OPERA_UNIDA_ADMIN;

/***************************************************************************
Descripcion : Carga la tabla temporal para el reporte de Operaciones de Reclamos por Pedidos
Parametros :   psCodigoPais        Codigo Pais
               psOidPeriInicial    Id Periodo Inicial
               psOidPeriFinal      Id Periodo Final
               psFechaDesde        Fecha de facturación inicio
               psFechaHasta        Fecha de facturación final
               psCodigoSecuencia   Secuencia de tabla temporal
Fecha Creacion : 05/10/2011
Autor : Nicolás López Ramos
***************************************************************************/
PROCEDURE REC_PR_CARGA_TEMPO_REPOR_OPREC
(
  psCodigoPais        VARCHAR2,
  psOidPeriInicial    VARCHAR2,
  psOidPeriFinal      VARCHAR2,
  psFechaDesde        VARCHAR2,
  psFechaHasta        VARCHAR2,
  psCodigoSecuencia   VARCHAR2,
  psTipoMovimiento    VARCHAR2,
  psCondicion         VARCHAR2,
  psFlagTipoOpera     VARCHAR2,
  psCondicionTipoOper VARCHAR2
);

END REC_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY "REC_PKG_REPOR" IS

VALOR_PROMEDIO CONSTANT VARCHAR2(50):= ' Promedio';
VALOR_TOTAL    CONSTANT VARCHAR2(50):= '  Total';
VALOR_COMPLETO CONSTANT VARCHAR2(50):= '(Completo)';
VALOR_TIPO_MOVIMIENTO_UNO CONSTANT VARCHAR2(1) := '1';

/***************************************************************************
Descripcion : Recupera el numero y monto de las unidades facturadas de un
 producto especifico en un determinado periodo
Fecha Creacion : 23/01/2007
psValorRetorno
 NUM_UNID_DEMA : Devuelve el numero de las unidades facturadas de ese
 producto en un determinado periodo
 VAL_PREC_FACT_TOTA_LOCA : Devuelve Monto de las unidades facturadas
 de ese producto en un determinado periodo
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_UNID_FACT(psCodigoPeriodo VARCHAR2,
 psCodigoProducto VARCHAR2,
 psValorRetorno VARCHAR2)
RETURN NUMBER
IS
 registro tRegUnidadesFacturadas;
BEGIN

 SELECT SUM(PED_SOLIC_POSIC.NUM_UNID_DEMA) ,
 SUM(PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA)
 INTO registro.NUM_UNID_DEMA, registro.VAL_PREC_FACT_TOTA_LOCA
 FROM PED_SOLIC_POSIC,
 PED_SOLIC_CABEC
 WHERE PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
 AND PED_SOLIC_CABEC.PERD_OID_PERI = psCodigoPeriodo
 AND PED_SOLIC_POSIC.PROD_OID_PROD = psCodigoProducto
 AND PED_SOLIC_CABEC.IND_OC= 1
 AND PED_SOLIC_CABEC.IND_PEDI_PRUE = 0
 AND PED_SOLIC_POSIC.NUM_UNID_DEMA - PED_SOLIC_POSIC.NUM_UNID_ATEN > 0
 AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI <> 2;

 IF psValorRetorno= 'NUM_UNID_DEMA' THEN
 RETURN registro.NUM_UNID_DEMA;
 ELSE
 RETURN registro.VAL_PREC_FACT_TOTA_LOCA;
 END IF;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RETURN 0;
 --RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_UNID_FACT: '||ls_sqlerrm);
END REC_FN_OBTIE_UNID_FACT;

/***************************************************************************
Descripcion : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
 undidades facturadas, monto facturado y el numero de pedidos
              por producto y periodo.
Fecha Creacion : 23/01/2007
Autor : Marco Agurto
Fecha Modificación : 30/03/2011
Modificado por     : Jorge Angulo
***************************************************************************/
PROCEDURE REC_PR_UPDAT_UNID_FACT_MASRE
(
psOidPeriodoInicio NUMBER,
 psOidPeriodoFinal NUMBER,
lsSecuencial OUT NUMBER
)
IS
CURSOR c_unidad_admin IS
SELECT *
  FROM REC_REPOR_CONSO_PRODU;

 tablaUnidadAdmin c_unidad_admin%ROWTYPE;
 registro tRegUnidadesFacturadas;
BEGIN
 /* DELETE FROM REC_REPOR_CONSO_PRODU;*/
 /*
 Se inserta en una tabla temporal de
 Consolidadod e Productos.
 */
 /* INSERT INTO REC_REPOR_CONSO_PRODU
(PERD_OID_PERI ,
 PROD_OID_PROD ,
 NUM_UNID_DEMA ,
 VAL_PREC_FACT_TOTA_LOCA ,
 NUM_PEDI )
 SELECT temp.perd_oid_peri,
 temp.prod_oid_prod,
 SUM(a.NUM_UNID_FACT) ,
 SUM(a.imp_mont_fact),
 ( select count(b.oid_soli_cabe)
 from ped_solic_cabec b
 where b.perd_oid_peri=temp.perd_oid_peri
 and exists (select 1 from ped_solic_posic where prod_oid_prod = temp.prod_oid_prod
						 and soca_oid_soli_cabe=b.oid_soli_cabe)
 and b.tspa_oid_tipo_soli_pais=2037
 and b.fec_fact is not null )
 FROM PRE_MATRI_ESTAD_CODIG_VENTA a,
 pre_matri_factu b,
 pre_matri_factu_cabec c,
 pre_ofert_detal d,
 (SELECT DISTINCT rec_repor_opera_unida_admin.perd_oid_peri,
 rec_repor_opera_unida_admin.prod_oid_prod
 FROM rec_repor_opera_unida_admin) temp
 where a.MAFA_OID_MATR_FACT=b.OID_MATR_FACT
 AND b.MFCA_OID_CABE=c.OID_CABE
 AND b.OFDE_OID_DETA_OFER=d.OID_DETA_OFER
 	 AND c.PERD_OID_PERI=temp.Perd_Oid_Peri
 AND d.PROD_OID_PROD=temp.prod_oid_prod
 GROUP BY temp.perd_oid_peri,
 temp.prod_oid_prod;*/
 /*Se reconrre el cursor con los datos a mostrar
 y se actualiza los valores de
 -Candidad de Pedidos
 -Cantidad de Productos
 -Importe Facturado
 Los mismos que se calcularon en el paso anterior
 */
 /*OPEN c_unidad_admin;
 LOOP
 FETCH c_unidad_admin
 INTO tablaUnidadAdmin;
 EXIT WHEN c_unidad_admin%NOTFOUND;

 UPDATE REC_REPOR_OPERA_UNIDA_ADMIN
 SET NUM_UNID_DEMA= tablaUnidadAdmin.NUM_UNID_DEMA,
 VAL_PREC_FACT_TOTA_LOCA= tablaUnidadAdmin.VAL_PREC_FACT_TOTA_LOCA,
 NUM_PEDI = tablaUnidadAdmin.NUM_PEDI
 WHERE PROD_OID_PROD = tablaUnidadAdmin.PROD_OID_PROD
 AND PERD_OID_PERI = tablaUnidadAdmin.Perd_Oid_Peri ;

 END LOOP;
 CLOSE c_unidad_admin;*/

 SELECT REC_SEQ_PRODU_MAS_RECLA.NEXTVAL
 INTO lsSecuencial
 FROM dual ;

 EXECUTE IMMEDIATE '
 CREATE TABLE REC_REPOR_PROD_RECLA_'||lsSecuencial||' AS
 SELECT Decode(b.VAL_CODI_VENT,null,b.VAL_CODI_VENT_FICT,b.VAL_CODI_VENT) as val_codi_vent,
 	 a.perd_oid_peri,
 SUM(B.NUM_UNID_ATEN) CANT_FACT,
 SUM(DECODE (B.VAL_PREC_CATA_UNIT_LOCA,0, 0,B.VAL_PREC_FACT_UNIT_LOCA)* B.NUM_UNID_ATEN) AS MONTO_FACT,
 	 COUNT(a.oid_soli_cabe) num_pedi
  FROM ped_solic_cabec c,
       ped_solic_cabec a,
       PED_TIPO_SOLIC_PAIS TSP,
       PED_TIPO_SOLIC TS,
 	 ped_solic_posic b
 WHERE c.fec_fact IS NOT NULL
   and c.PERD_OID_PERI >='||psOidPeriodoInicio||'
   and c.PERD_OID_PERI <='||psOidPeriodoFinal||'
   and c.oid_soli_cabe = a.soca_oid_soli_cabe
   and A.ESSO_OID_ESTA_SOLI in (1,5)
   and TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
   and TS.COD_TIPO_SOLI = ''C1''
   and TSP.OID_TIPO_SOLI_PAIS = c.tspa_oid_tipo_soli_pais
   and a.oid_soli_cabe = b.soca_oid_soli_cabe
   and B.ESPO_OID_ESTA_POSI<>2
 GROUP BY Decode(b.VAL_CODI_VENT,null,b.VAL_CODI_VENT_FICT,b.VAL_CODI_VENT),a.perd_oid_peri';

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_UPDAT_UNID_FACT_MASRE: '||ls_sqlerrm);

END REC_PR_UPDAT_UNID_FACT_MASRE;

/***************************************************************************
Descripcion : Actualiza la tabla REC_REPOR_RECLAMOS los valores de
 undidades facturadas, monto facturado y el numero de pedidos
 por producto y periodo
Fecha Creacion : 30/01/2007

Autor : Marco Agurto
***************************************************************************/
PROCEDURE REC_PR_UPDAT_UNID_FACT

IS
CURSOR c_unidad_admin IS
SELECT *
FROM REC_REPOR_RECLA;

 tablaUnidadAdmin c_unidad_admin%ROWTYPE;
 registro tRegUnidadesFacturadas;
BEGIN
 OPEN c_unidad_admin;
 LOOP
 FETCH c_unidad_admin
 INTO tablaUnidadAdmin;
 EXIT WHEN c_unidad_admin%NOTFOUND;
 BEGIN

 SELECT nvl(SUM(NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA,0)),0) ,
 nvl(SUM(NVL(PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA,0)),0) ,
 COUNT(DISTINCT PED_SOLIC_CABEC.OID_SOLI_CABE)
 INTO registro.NUM_UNID_DEMA, registro.VAL_PREC_FACT_TOTA_LOCA , registro.NUM_PEDI
 FROM PED_SOLIC_POSIC,
 PED_SOLIC_CABEC
 WHERE PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
 AND PED_SOLIC_CABEC.PERD_OID_PERI = tablaUnidadAdmin.Peri_Recl
 AND PED_SOLIC_POSIC.PROD_OID_PROD = tablaUnidadAdmin.PROD_OID_PROD
 AND PED_SOLIC_POSIC.OID_SOLI_POSI = tablaUnidadAdmin.Sopo_Oid_Soli_Posi
 AND PED_SOLIC_CABEC.IND_OC= 1
 AND PED_SOLIC_CABEC.IND_PEDI_PRUE = 0
 AND PED_SOLIC_POSIC.NUM_UNID_DEMA - PED_SOLIC_POSIC.NUM_UNID_ATEN > 0
 AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI <> 2;--PARA NO CONSIDERAR LAS ANULACIONES

 UPDATE REC_REPOR_RECLA
 SET NUM_UNID_DEMA= registro.NUM_UNID_DEMA,
 VAL_PREC_FACT_TOTA_LOCA= registro.VAL_PREC_FACT_TOTA_LOCA,
 NUM_PEDI = registro.NUM_PEDI
 WHERE
 COD_OPER = tablaUnidadAdmin.COD_OPER
 AND VAL_TIPO_OPER = tablaUnidadAdmin.VAL_TIPO_OPER
 AND OID_OPER_RECL = tablaUnidadAdmin.Oid_Oper_Recl
 AND PROD_OID_PROD = tablaUnidadAdmin.PROD_OID_PROD ;
 END;
 END LOOP;
 CLOSE c_unidad_admin;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_UPDAT_UNID_FACT: '||ls_sqlerrm);
END REC_PR_UPDAT_UNID_FACT;

/***************************************************************************
Descripcion : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
 undidades facturadas, monto facturado y el numero de pedidos
 por producto y periodo
 - Reporte de Reclamos por Motivos de Devolución
Fecha Creacion : 28/06/2007

Autor : Marco Agurto
***************************************************************************/
PROCEDURE REC_PR_UPDAT_UNID_FACT_MOTIV

IS
CURSOR c_unidad_admin IS
SELECT *
FROM REC_REPOR_CONSO_PRODU
 ;

 tablaUnidadAdmin c_unidad_admin%ROWTYPE;
 registro tRegUnidadesFacturadas;
BEGIN
 DELETE FROM REC_REPOR_CONSO_PRODU;
 DELETE FROM per_gtt_proce_progr;
 /* Se inserta en una tabla temporal de Consolidadod e Productos. */
 INSERT INTO per_gtt_proce_progr (OID_PROC, OID_SUBP )
 SELECT temp.perd_oid_peri,
 (select count(*)
 from ped_solic_cabec
 where tspa_oid_tipo_soli_pais=REC_PKG_REPOR.GEN_FN_DEVUE_OID_TSOLI_PAIS('SOC')
 and perd_oid_peri=temp.perd_oid_peri
 and fec_fact is not null
 and esso_oid_esta_soli in (1,5))
 FROM (SELECT DISTINCT rec_repor_opera_unida_admin.perd_oid_peri
 FROM rec_repor_opera_unida_admin) temp;

 /* Se inserta en una tabla temporal de Consolidadod e Productos. */

 INSERT INTO REC_REPOR_CONSO_PRODU
 (PERD_OID_PERI ,
 PROD_OID_PROD ,
 NUM_UNID_DEMA ,
 VAL_PREC_FACT_TOTA_LOCA ,
 NUM_PEDI )
 SELECT temp.perd_oid_peri,
 temp.prod_oid_prod,
 SUM(a.NUM_UNID_FACT) ,
 SUM(a.imp_mont_fact),
 (SELECT OID_SUBP FROM per_gtt_proce_progr WHERE OID_PROC = temp.perd_oid_peri)
 FROM PRE_MATRI_ESTAD_CODIG_VENTA a,
 pre_matri_factu b,
 pre_matri_factu_cabec c,
 pre_ofert_detal d,
 (SELECT DISTINCT rec_repor_opera_unida_admin.perd_oid_peri,
 prod_oid_prod
 FROM rec_repor_opera_unida_admin) temp
 where a.MAFA_OID_MATR_FACT=b.OID_MATR_FACT
 AND b.MFCA_OID_CABE=c.OID_CABE
 AND b.OFDE_OID_DETA_OFER=d.OID_DETA_OFER
 	 AND c.PERD_OID_PERI=temp.Perd_Oid_Peri
 --AND d.PROD_OID_PROD=temp.prod_oid_prod
 GROUP BY temp.perd_oid_peri,
 temp.prod_oid_prod;
 /*Se reconrre el cursor con los datos a mostrar
 y se actualiza los valores de
 -Candidad de Pedidos
 -Cantidad de Productos
 -Importe Facturado
 Los mismos que se calcularon en el paso anterior
 */
 OPEN c_unidad_admin;
 LOOP
 FETCH c_unidad_admin
 INTO tablaUnidadAdmin;
 EXIT WHEN c_unidad_admin%NOTFOUND;

 UPDATE REC_REPOR_OPERA_UNIDA_ADMIN
 SET NUM_UNID_DEMA= tablaUnidadAdmin.NUM_UNID_DEMA,
 VAL_PREC_FACT_TOTA_LOCA= tablaUnidadAdmin.VAL_PREC_FACT_TOTA_LOCA,
 NUM_PEDI =tablaUnidadAdmin.Num_Pedi
 WHERE PROD_OID_PROD = tablaUnidadAdmin.PROD_OID_PROD
 AND PERD_OID_PERI = tablaUnidadAdmin.Perd_Oid_Peri ;

 END LOOP;
 CLOSE c_unidad_admin;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_UPDAT_UNID_FACT_MOTIV: '||ls_sqlerrm);
END REC_PR_UPDAT_UNID_FACT_MOTIV;

/***************************************************************************
Descripcion : Actualiza la tabla REC_REPOR_OPER_UNID_ADM los valores de
 undidades facturadas, monto facturado y el numero de pedidos
 por producto y periodo
 - Reporte de Reclamos de Operaciones por Unidad Administrativa
Fecha Creacion : 28/06/2007

Autor : Marco Agurto
***************************************************************************/
PROCEDURE REC_PR_UPDAT_UNID_FACT_UNIDA (psCodigoPais varchar2)
IS

CURSOR c_unidad_admin IS
SELECT *
FROM REC_REPOR_CONSO_PRODU
 ;
 tablaUnidadAdmin c_unidad_admin%ROWTYPE;
 registro tRegUnidadesFacturadas;
BEGIN
 DELETE FROM REC_REPOR_CONSO_PRODU;
 DELETE FROM per_gtt_proce_progr;
 /* Se inserta en una tabla temporal de Consolidadod e Productos. */
 /*OID_PROC == se almacena el oid periodo
 OID_SUBP == se almacena el oid zon_terri_admin
 OID_TIPO_ABON_SUBP == se almacena la cantidad de pedidos por zona
 */
 INSERT INTO per_gtt_proce_progr ( OID_PROC, OID_SUBP , OID_TIPO_ABON_SUBP )
 SELECT temp.perd_oid_peri,
 temp.oid_zona,
 (select count(*)
 from ped_solic_cabec
 where tspa_oid_tipo_soli_pais=REC_PKG_REPOR.GEN_FN_DEVUE_OID_TSOLI_PAIS('SOC')
 and perd_oid_peri=temp.perd_oid_peri
 and ped_solic_cabec.zzon_oid_zona = temp.oid_zona
 and fec_fact is not null
 and (esso_oid_esta_soli = 1 OR
 esso_oid_esta_soli = 5)
 )
 FROM (SELECT DISTINCT rec_repor_opera_unida_admin.perd_oid_peri,
 zon_zona.oid_zona
 FROM rec_repor_opera_unida_admin,
 zon_zona
 where int_pkg_recla.GEN_FN_OID_ZONA_BYZON_TERRI(rec_repor_opera_unida_admin.ztad_oid_terr_admi, psCodigoPais) = zon_zona.oid_zona ) temp;

 /* Se inserta en una tabla temporal de Consolidadod e Productos. */

 INSERT INTO REC_REPOR_CONSO_PRODU (
 PERD_OID_PERI ,
 zzon_oid_zona,
 NUM_UNID_DEMA ,
 VAL_PREC_FACT_TOTA_LOCA )

 SELECT temp.perd_oid_peri,
 temp.oid_zona,
 sum(b.NUM_UNID_ATEN),
 sum(decode(b.VAL_PREC_CATA_UNIT_LOCA,0,0,b.VAL_PREC_FACT_UNIT_LOCA)*b.NUM_UNID_ATEN)
 from ped_solic_cabec a,
 ped_solic_posic b,
 ped_tipo_solic_pais c,
 ped_tipo_solic d ,
 (SELECT DISTINCT rec_repor_opera_unida_admin.perd_oid_peri,
 zon_zona.oid_zona
 FROM rec_repor_opera_unida_admin,
 zon_zona
 where int_pkg_recla.GEN_FN_OID_ZONA_BYZON_TERRI(rec_repor_opera_unida_admin.ztad_oid_terr_admi, psCodigoPais) = zon_zona.oid_zona ) temp
 where a.OID_SOLI_CABE=b.SOCA_OID_SOLI_CABE
 and a.FEC_FACT is not null
 and (a.ESSO_OID_ESTA_SOLI = 1 OR
 a.ESSO_OID_ESTA_SOLI = 5)
 and a.PERD_OID_PERI=temp.perd_oid_peri
 and a.IND_OC=1
 and a.TSPA_OID_TIPO_SOLI_PAIS=c.OID_TIPO_SOLI_PAIS
 and c.TSOL_OID_TIPO_SOLI=d.OID_TIPO_SOLI
 and nvl(d.IND_SOLI_NEGA,0)=0
 and a.zzon_oid_zona = temp.oid_zona
 GROUP BY temp.perd_oid_peri,
 temp.oid_zona;

 /*Se reconrre el cursor con los datos a mostrar
 y se actualiza los valores de
 -Candidad de Pedidos
 -Cantidad de Productos
 -Importe Facturado
 Los mismos que se calcularon en el paso anterior
 */
 OPEN c_unidad_admin;
 LOOP
 FETCH c_unidad_admin
 INTO tablaUnidadAdmin;
 EXIT WHEN c_unidad_admin%NOTFOUND;

 UPDATE REC_REPOR_OPERA_UNIDA_ADMIN
 SET NUM_UNID_DEMA= tablaUnidadAdmin.NUM_UNID_DEMA,
 VAL_PREC_FACT_TOTA_LOCA= tablaUnidadAdmin.VAL_PREC_FACT_TOTA_LOCA,
 NUM_PEDI = nvl((SELECT OID_TIPO_ABON_SUBP
 FROM per_gtt_proce_progr
 WHERE OID_PROC = perd_oid_peri
 and OID_SUBP = int_pkg_recla.GEN_FN_OID_ZONA_BYZON_TERRI(ztad_oid_terr_admi, psCodigoPais)),0),
 DES_REGI = int_pkg_recla.GEN_FN_DESC_REGIO_BYZON_TERRI(ztad_oid_terr_admi, psCodigoPais),
 DES_ZONA = int_pkg_recla.GEN_FN_DESC_ZONA_BYZON_TERRI(ztad_oid_terr_admi, psCodigoPais)
 WHERE int_pkg_recla.GEN_FN_OID_ZONA_BYZON_TERRI(ztad_oid_terr_admi, psCodigoPais)=tablaUnidadAdmin.zzon_oid_zona
 AND PERD_OID_PERI = tablaUnidadAdmin.Perd_Oid_Peri ;

 END LOOP;
 CLOSE c_unidad_admin;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_UPDAT_UNID_FACT_UNIDA: '||ls_sqlerrm);
END REC_PR_UPDAT_UNID_FACT_UNIDA;
/***************************************************************************
Descripcion : Recupera numero de unidades atendidas
Fecha Creacion : 27/01/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_UNID_ATE(psOidTipoSoliPais NUMBER,
 psOidOperaReclamo NUMBER,
 psOidProducto NUMBER)
RETURN NUMBER
IS
 unidadesAtendidas NUMBER;
BEGIN

 SELECT NVL(spo.num_unid_aten ,0)
INTO unidadesAtendidas
 FROM rec_solic_opera solop,
 rec_opera_recla orec,
 rec_linea_opera_recla rec,
 ped_solic_cabec sc,
 ped_solic_posic spo
 WHERE ( orec.oid_oper_recl = psOidOperaReclamo)
 AND (rec.PROD_OID_PROD = psOidProducto)
 AND (orec.oid_oper_recl = solop.opre_oid_oper_recl)
 AND (orec.oid_oper_recl = rec.opre_oid_oper_recl)
 AND (sc.oid_soli_cabe = solop.soca_oid_soli_cabe)
 AND (sc.oid_soli_cabe = spo.soca_oid_soli_cabe)
 AND (solop.tspa_oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais)
 AND (sc.tspa_oid_tipo_soli_pais = psOidTipoSoliPais)
 AND (rec.timo_oid_tipo_movi = 1)
 AND (sc.fec_fact IS NOT NULL)
 AND (spo.ESPO_OID_ESTA_POSI <> 2);
 IF unidadesAtendidas IS NULL THEN
 RETURN 0;
 ELSE
 RETURN unidadesAtendidas;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 --RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_UNID_ATE: '||ls_sqlerrm);
 RETURN 0;
END REC_FN_OBTIE_UNID_ATE;


/***************************************************************************
Descripcion : Recupera numero de unidades faltantes
Fecha Creacion : 27/01/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_UNID_FAL(psOidTipoSoliPais NUMBER,
 psOidOperaReclamo NUMBER,
 psOidProducto NUMBER )
RETURN NUMBER
IS
 unidadesFaltantes NUMBER;
BEGIN

SELECT NVL(sum(spo.num_unid_dema - spo.num_unid_aten) ,0)
INTO unidadesFaltantes
 FROM rec_solic_opera solop,
 rec_opera_recla orec,
 rec_linea_opera_recla rec,
 ped_solic_cabec sc,
 ped_solic_posic spo
 WHERE ( orec.oid_oper_recl = psOidOperaReclamo)
 AND (rec.PROD_OID_PROD = psOidProducto)
 AND (orec.oid_oper_recl = solop.opre_oid_oper_recl)
 AND (orec.oid_oper_recl = rec.opre_oid_oper_recl)
 AND (sc.oid_soli_cabe = solop.soca_oid_soli_cabe)
 AND (sc.oid_soli_cabe = spo.soca_oid_soli_cabe)
 AND (solop.tspa_oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais)
 AND (rec.timo_oid_tipo_movi = 1)
 AND (sc.fec_fact IS NOT NULL)
 AND (spo.ESPO_OID_ESTA_POSI <> 2)
 AND (spo.prod_oid_prod = rec.prod_oid_prod);

--- sqa SELECT NVL(spo.num_unid_dema - spo.num_unid_aten ,0)
--- SQA 06.01.2011 AND (sc.tspa_oid_tipo_soli_pais = psOidTipoSoliPais)

 IF unidadesFaltantes IS NULL THEN
 RETURN 0;
 ELSE
 RETURN unidadesFaltantes;
 END IF;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 -- RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_UNID_FAL: '||ls_sqlerrm);
 RETURN 0;
END REC_FN_OBTIE_UNID_FAL;

/***************************************************************************
Descripcion : Recupera Stock de un determinado periodo
 producto, de un almacen y un determinado codigo de venta
Fecha Creacion : 08/02/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_STOCK(psOidPeriodo NUMBER,
 psOidEstado NUMBER,
 psOidProducto NUMBER,
 psOidAlmacen NUMBER,
 psValCodiVenta VARCHAR2)
RETURN NUMBER IS

lsResultado NUMBER;
BEGIN

 lsResultado:=0;
 SELECT stock.val_sald
 INTO lsResultado
 FROM pre_ofert pre,
 pre_ofert_detal ofertadetalle,
 pre_matri_factu_cabec factucabec,
 pre_matri_factu matrifactu,
 bel_stock stock
 WHERE (pre.oid_ofer = ofertadetalle.ofer_oid_ofer)
 AND (factucabec.oid_cabe = pre.mfca_oid_cabe)
 AND (ofertadetalle.oid_deta_ofer = matrifactu.ofde_oid_deta_ofer)
 AND (factucabec.oid_cabe = matrifactu.mfca_oid_cabe)
 AND (stock.prod_oid_prod = ofertadetalle.prod_oid_prod)
 AND (stock.almc_oid_alma = psOidAlmacen)
 AND (stock.esme_oid_esta_merc = psOidEstado)
 AND (stock.prod_oid_prod = psOidProducto)
 AND (ofertadetalle.val_codi_vent = psValCodiVenta)
 AND (factucabec.perd_oid_peri = psOidPeriodo);

 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_STOCK: '||ls_sqlerrm);
 RETURN 0;
END REC_FN_OBTIE_STOCK;

/***************************************************************************
Descripcion : Recupera el codigo de venta a partir de un codigo de la
 matriz de facturacion, en nuestro caso este se obteiene de
 la linea de reclamo
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_CODI_VENTA(psOidMatrizFact NUMBER )
RETURN VARCHAR2 IS

lsResultado PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE;
BEGIN
 lsResultado:='';

 SELECT PRE_OFERT_DETAL.VAL_CODI_VENT
 INTO lsResultado
				FROM PRE_MATRI_FACTU,
					 PRE_MATRI_FACTU_CABEC,
					 PRE_OFERT,
					 PRE_OFERT_DETAL
				WHERE PRE_MATRI_FACTU.OFDE_OID_DETA_OFER = PRE_OFERT_DETAL.OID_DETA_OFER
					AND PRE_MATRI_FACTU.OID_MATR_FACT = psOidMatrizFact
					AND PRE_MATRI_FACTU.MFCA_OID_CABE = PRE_MATRI_FACTU_CABEC.OID_CABE
					AND PRE_OFERT.MFCA_OID_CABE = PRE_MATRI_FACTU_CABEC.OID_CABE
					AND PRE_OFERT_DETAL.OFER_OID_OFER = PRE_OFERT.OID_OFER ;

 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_CODI_VENTA: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_CODI_VENTA;
/***************************************************************************
Descripcion : Recupera las unidades reclamadas por producto de una determinada
 abecera de reclamos
 la linea de reclamo
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_UNID_RECL_PROD(psOidProducto NUMBER,
 psOidOperRecla NUMBER,
 psTipoMovimiento NUMBER)
RETURN NUMBER IS

lsResultado REC_LINEA_OPERA_RECLA.NUM_UNID_RECL%TYPE;
BEGIN
 lsResultado:=0;

 select NVL(sum(A.NUM_UNID_RECL), 0)
 INTO lsResultado
 from REC_LINEA_OPERA_RECLA A
 where A.Opre_Oid_Oper_Recl= psOidOperRecla
 AND A.Prod_Oid_Prod = psOidProducto
 AND A.TIMO_OID_TIPO_MOVI =psTipoMovimiento;

 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_UNID_RECL_PROD: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_UNID_RECL_PROD;


/***************************************************************************
Descripcion : Recupera la descripcion de producto que se encuentra
 en la tabla OFERTA_DETALLE
Parametro : psCodigoPeriodo Periodo
 psCodigoVenta Codigo de Venta
Fecha Creacion : 12/02/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION OCR_FN_DEV_DES_PROD_OFER(psCodigoPeriodo VARCHAR2,
 psCodigoVenta VARCHAR2)
 RETURN VARCHAR2 IS
lsRetorno VARCHAR2(200);
BEGIN
 lsRetorno := '';
 SELECT OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(pre_ofert_detal.prod_oid_prod)
 INTO lsRetorno
 FROM pre_ofert_detal
 WHERE pre_ofert_detal.oid_deta_ofer = OCR_SOLIC_PEDIDOS.GEN_FN_DEV_OID_OFER_DETAL(psCodigoPeriodo, psCodigoVenta) ;

 RETURN lsRetorno;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR OCR_FN_DEV_DES_PROD_OFER: '||ls_sqlerrm);

END OCR_FN_DEV_DES_PROD_OFER;
/***************************************************************************
Descripcion : Recupera los datos de REC_OPERA, segun el ID ingresado
 como parametro
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OPERA_DATOS(psOidOpera NUMBER , psTipo VARCHAR2 )
RETURN VARCHAR2 IS

lsResultado VARCHAR2(40);
BEGIN
 lsResultado:='';
 IF (psTipo = 'COD_OPER') THEN
 SELECT REC_OPERA.COD_OPER
 INTO lsResultado
				FROM REC_OPERA
				WHERE REC_OPERA.OID_OPER = psOidOpera;
 END IF;
 IF (psTipo = 'DES_OPER') THEN
 SELECT REC_OPERA.VAL_DESC_LARG
 INTO lsResultado
				FROM REC_OPERA
				WHERE REC_OPERA.OID_OPER = psOidOpera;
 END IF;
 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OPERA_DATOS: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OPERA_DATOS;

/***************************************************************************
Descripcion : Recupera los datos de REC_TIPOS_OPERA, segun el ID ingresado
 como parametro
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_TIPO_OPERA_DATOS(psOidTipoOperacion NUMBER )
RETURN VARCHAR2 IS

lsResultado VARCHAR2(20);
BEGIN
 lsResultado:='';

 SELECT REC_TIPOS_OPERA.VAL_TIPO_OPER
 INTO lsResultado
 FROM REC_TIPOS_OPERA
 WHERE REC_TIPOS_OPERA.OID_TIPO_OPER = psOidTipoOperacion ;

 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_TIPO_OPERA_DATOS: '||ls_sqlerrm);
 RETURN '';
END REC_FN_TIPO_OPERA_DATOS;


/***************************************************************************
Descripcion : Recupera el documento de referencia de PED_SOLIC_CABE
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_DOCU_REFE(psOidSolicitudCabecera NUMBER )
RETURN NUMBER IS

lsResultado PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
BEGIN
 lsResultado:='';

 SELECT PED_SOLIC_CABEC.VAL_NUME_SOLI
 INTO lsResultado
 FROM PED_SOLIC_CABEC
 WHERE PED_SOLIC_CABEC.OID_SOLI_CABE = psOidSolicitudCabecera ;

 RETURN lsResultado;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN '';
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_DOCU_REFE: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_DOCU_REFE;
/***************************************************************************
Descripcion : Recupera Toma la solicitud asociada al precio correspondiente
 Si es mercaderia, mostrar Precio Catalogo Unitario Total
 Si es incentivo(premio), mostrar Precio Contable
Fecha Creacion : 16/04/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_PRECIO(psOidSoliPosi NUMBER)
RETURN NUMBER IS

lsResultado NUMBER;
lsResultado1 NUMBER;
lsResultado2 NUMBER;
BEGIN
 lsResultado:=0;

 SELECT NVL(A.VAL_PREC_CATA_UNIT_LOCA,0),
 NVL(A.VAL_PREC_CATA_TOTA_LOCA,0),
 NVL(A.VAL_PREC_CONT_TOTA_LOCA,0)
 INTO lsResultado, lsResultado1, lsResultado2
 from PED_SOLIC_POSIC A
 where A.OID_SOLI_POSI= psOidSoliPosi;

 IF (lsResultado <> 0) THEN
 RETURN lsResultado1;
 ELSE
 RETURN lsResultado2;
 END IF;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_PRECIO: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_PRECIO;
/***************************************************************************
Descripcion : Recupera el numero de pedidos de una zona y periodo
 seleccionado
Fecha Creacion : 26/07/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_NUMER_PEDID(psOidZona NUMBER, psOidPeriodo NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select count(*)
 INTO ls_cantidad
 from ped_solic_cabec
 where tspa_oid_tipo_soli_pais=REC_PKG_REPOR.GEN_FN_DEVUE_OID_TSOLI_PAIS('SOC')
 and perd_oid_peri=psOidPeriodo
 and ped_solic_cabec.zzon_oid_zona = psOidZona
 and fec_fact is not null
 and (esso_oid_esta_soli = 1 OR
 esso_oid_esta_soli = 5);
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_NUMER_PEDID: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_NUMER_PEDID;
/***************************************************************************
Descripcion : Recupera el numero de pedidos de un producto y periodo
 seleccionado
Fecha Creacion : 06/08/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_NUMER_PEDID_PRODU(psOidProducto NUMBER, psOidPeriodo NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select count(b.oid_soli_cabe)
 INTO ls_cantidad
 from ped_solic_cabec b
 where b.perd_oid_peri=psOidPeriodo
 and exists (select 1 from ped_solic_posic where prod_oid_prod = psOidProducto
 and soca_oid_soli_cabe=b.oid_soli_cabe)
 and b.tspa_oid_tipo_soli_pais=REC_PKG_REPOR.GEN_FN_DEVUE_OID_TSOLI_PAIS('SOC')
 and b.fec_fact is not NULL;
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_NUMER_PEDID_PRODU: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_NUMER_PEDID_PRODU;
/***************************************************************************
Descripcion : Recupera el importe reclamado de un producto
 seleccionado
Fecha Creacion : 06/08/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_IMPOR_RECLA_PRODU(psOidProducto NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select SUM(a.IMP_ABON)
 INTO ls_cantidad
 from REC_REPOR_OPERA_UNIDA_ADMIN a
 where a.prod_oid_prod = psOidProducto;
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_IMPOR_RECLA_PRODU: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_IMPOR_RECLA_PRODU;
/***************************************************************************
Descripcion : Recupera el numero de unidades reclamadas de un producto
 seleccionado
Fecha Creacion : 06/08/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_NUMER_RECLA_PRODU(psOidProducto NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select SUM(a.NUM_UNID_RECL)
 INTO ls_cantidad
 from REC_REPOR_OPERA_UNIDA_ADMIN a
 where a.prod_oid_prod = psOidProducto;
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_NUMER_RECLA_PRODU: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_NUMER_RECLA_PRODU;
/***************************************************************************
Descripcion : Recupera el numero de guias reclamadas de un producto
 seleccionado
Fecha Creacion : 06/08/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_NUMER_GUIA_PRODU(psOidProducto NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select SUM(a.num_guia)
 INTO ls_cantidad
 from REC_REPOR_OPERA_UNIDA_ADMIN a
 where a.prod_oid_prod = psOidProducto;
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_NUMER_GUIA_PRODU: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_NUMER_GUIA_PRODU;
/***************************************************************************
Descripcion : Recupera el numero de pedidos por Oid Terri Admin
 seleccionado
Fecha Creacion : 17/09/2007
Autor : Marco Agurto
***************************************************************************/
FUNCTION REC_FN_OBTIE_PEDID_TERRI_ADMIN(psOidTerrAdmin NUMBER, psOidPeriodo NUMBER)
RETURN NUMBER IS
ls_cantidad NUMBER;
BEGIN
 ls_cantidad :=0;
 select count(*)
 INTO ls_cantidad
 from ped_solic_cabec
 where tspa_oid_tipo_soli_pais=REC_PKG_REPOR.GEN_FN_DEVUE_OID_TSOLI_PAIS('SOC')
 and perd_oid_peri=psOidPeriodo
 and ped_solic_cabec.ZTAD_OID_TERR_ADMI = psOidTerrAdmin
 and fec_fact is not null
 and (esso_oid_esta_soli = 1 OR
 esso_oid_esta_soli = 5);
 RETURN ls_cantidad;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_OBTIE_PEDID_TERRI_ADMIN: '||ls_sqlerrm);
 RETURN '';
END REC_FN_OBTIE_PEDID_TERRI_ADMIN;
/***************************************************************************
Descripcion : Devuelve el oid_tipo_solic_pais
Fecha Creacion : 25/01/2008
Autor : jose Antonio
***************************************************************************/
FUNCTION GEN_FN_DEVUE_OID_TSOLI_PAIS(psCodigoTipoSolicitud VARCHAR2)
RETURN NUMBER
IS
 lnIdTipoSolic NUMBER;
BEGIN

 SELECT OID_TIPO_SOLI_PAIS
 INTO lnIdTipoSolic
 FROM PED_TIPO_SOLIC_PAIS TSP, PED_TIPO_SOLIC TS
 WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
 AND TS.COD_TIPO_SOLI = psCodigoTipoSolicitud;

 RETURN lnIdTipoSolic;

EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN NULL;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_DEVUE_OID_TSOLI_PAIS: '||ls_sqlerrm);
END GEN_FN_DEVUE_OID_TSOLI_PAIS;
/***************************************************************************
Descripcion : Devuelve le codigo de Venta Ficticon del Reclamo
Fecha Creacion : 10/04/2008
Autor : CRISTHIAn ROMAN
***************************************************************************/
FUNCTION GEN_FN_COD_VENTA_FICT_RECLA( psPrdOid IN NUMBER,
		 						psConcurso IN NUMBER,
								psNivePrem IN NUMBER,
								psLotePrem IN NUMBER
								)
RETURN VARCHAR2
IS
 lsValor VARCHAR2(100);
BEGIN
 SELECT I.COD_VENT_FICT
 INTO lsValor
 FROM INC_ARTIC_LOTE I,
 INC_LOTE_PREMI_ARTIC L,
 INC_PREMI_ARTIC P,
 INC_PARAM_NIVEL_PREMI N,
 INC_PARAM_GENER_PREMI G,
 INC_CONCU_PARAM_GENER C
 WHERE I.PROD_OID_PROD = PSPRDOID
 AND I.LOPA_OID_LOTE_PREM_ARTI = L.OID_LOTE_PREM_ARTI
 AND I.LOPA_OID_LOTE_PREM_ARTI = psLotePrem
 AND L.PRAR_OID_PREM_ARTI = P.OID_PREM_ARTI
 AND P.PANP_OID_PARA_NIVE_PREM = N.OID_PARA_NIVE_PREM
 AND P.PANP_OID_PARA_NIVE_PREM = psNivePrem
 AND N.PAGP_OID_PARA_GENE_PREM = G.OID_PARA_GENE_PREM
 AND G.COPA_OID_PARA_GRAL = C.OID_PARA_GRAL
 AND C.OID_PARA_GRAL = psConcurso;
 RETURN lsValor;

 EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN NULL;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_COD_VENTA_FICT_RECLA: '||ls_sqlerrm);

END GEN_FN_COD_VENTA_FICT_RECLA;
/***************************************************************************
Descripcion : Carga las tablas ped_bole_cabec Y ped_bole_deta
Fecha Creacion : 10/04/2008
Autor : CRISTHIAn ROMAN
***************************************************************************/
PROCEDURE REC_PR_CARGA_BOREC
(--pcod_vent IN VARCHAR2,
 pcod_peri IN VARCHAR2, pcod_pais IN VARCHAR2, pcod_Iso IN VARCHAR2) IS

 CURSOR curBoleReco
 IS
 SELECT
				 a.des_soci as sociedad,

				 pcod_peri,

				 f.cod_clie,
	 			 TRIM(g.val_nomb_via) || ' ' || TRIM(g.num_ppal) || ' ' || TRIM(g.val_obse) direccion,
	 		 GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(f.oid_clie,'TF') ,

				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'COD_REGI') as cod_regi,
				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'DES_REGI') as des_regi,
				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'COD_ZONA') as cod_zona,
				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'DES_ZONA') as des_zona,
				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'COD_SECC') as cod_secc,

				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'DES_SECC') as des_secci,
				 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(f.oid_clie, 'COD_TERR') as cod_terr,
				 a.FEC_FACT,
				 pcod_pais as cod_pais,
				 a.oid_soli_cabe,
				 a.soca_oid_soli_cabe,
				 a.ictp_oid_tipo_prog

		FROM 	 rec_repor_pedid_solic_cabec a,

	 			 mae_clien f,
	 			 mae_clien_direc g


		WHERE
				 a.clie_oid_clie= f.OID_CLIE
				 and a.pais_oid_pais=f.pais_oid_pais
				 and f.oid_clie = g.clie_oid_clie
 				 AND g.IND_DIRE_PPAL = 1
				 AND g.IND_ELIM = 0


 				 order by 6,
				 	 	 8,
						 3;

TYPE cargaRecordType IS RECORD
(

 t_val_deno		 REC_BOREC_ESPEC_CABEC.DES_SOCI%type,
 t_cod_peri		 REC_BOREC_ESPEC_CABEC.cod_peri%type,
 t_cod_clie		 REC_BOREC_ESPEC_CABEC.cod_clie%type,
 t_val_direc		 REC_BOREC_ESPEC_CABEC.DES_DIRE%type,
 t_val_text_comu	 REC_BOREC_ESPEC_CABEC.DES_TELE%type,
 t_cod_regi		 REC_BOREC_ESPEC_CABEC.cod_regi%type,
 t_des_regi		 REC_BOREC_ESPEC_CABEC.des_regi%type,
 t_cod_zona		 REC_BOREC_ESPEC_CABEC.cod_zona%type,
 t_des_zona		 REC_BOREC_ESPEC_CABEC.des_zona%type,
 t_cod_secc		 REC_BOREC_ESPEC_CABEC.cod_secc%type,
 t_des_secci		 REC_BOREC_ESPEC_CABEC.des_secc%type,
 t_cod_terr		 REC_BOREC_ESPEC_CABEC.cod_terr%type,
 t_fec_fact		 REC_BOREC_ESPEC_CABEC.FEC_FACT%type,
 t_cod_pais		 REC_BOREC_ESPEC_CABEC.cod_pais%type,
 t_oid_soli_cabe	 ped_solic_cabec.oid_soli_cabe%type,
 t_soca_oid_soli_cabe ped_solic_cabec.soca_oid_soli_cabe%type,
 t_ictp_oid_tipo_prog ped_solic_cabec.ictp_oid_tipo_prog%type

 );

 TYPE cargaRecordTab IS TABLE OF cargaRecordType;
 cargaRecord cargaRecordTab;
--SEGUNDO CURSOR

 CURSOR curIntermedio(vsoidsolicabecera number,vsictpoidtipoprog number)
 	is
	SELECT

		 NVL(b.val_codi_vent, b.val_codi_vent_fict) cuv,
		 b.num_unid_aten	,
		 b.PROD_OID_PROD
	FROM
 		 ped_solic_posic b,

			REC_BOREC_CODVE R
	WHERE
			b.SOCA_OID_SOLI_CABE=vsoidsolicabecera
			--AND z.OID_SOLI_CABE = vssocaoidsolicabe
			AND ((vsictpoidtipoprog is null and b.val_codi_vent=R.COD_VENT) or
 (vsictpoidtipoprog is not null and b.VAL_CODI_VENT_FICT=R.COD_VENT))

		 AND b.num_unid_aten >0

	order by 1,2,3;

TYPE cargaRecordIntermedioType IS RECORD
(

 t_val_codi_vent	 REC_BOREC_ESPEC_DETAL.val_codi_vent%type,
 t_num_unid_aten 	 REC_BOREC_ESPEC_DETAL.NUM_UNID_ATEN%type,
 t_prod_oid_prod ped_solic_posic.PROD_OID_PROD%type
 );

 TYPE cargaRecordIntermedioTab IS TABLE OF cargaRecordIntermedioType;
 cargaRecordInternedio cargaRecordIntermedioTab;



	rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;

 EXISTE NUMBER:=0;
	EXISTE_DETALLE NUMBER :=0;
	NUM_BOLE NUMBER := 0;
	OID_CABEC NUMBER :=0;
	NUM_VERI NUMBER:=0;

	lsoidperiodo cra_perio.OID_PERI%type;
	lsoid_soli_cabe ped_solic_cabec.oid_soli_cabe%type;
	lssoca_oid_soli_cabe ped_solic_cabec.soca_oid_soli_cabe%type;
	lsictp_oid_tipo_prog ped_solic_cabec.ictp_oid_tipo_prog%type;
	lsprod_oid_prod gen_i18n_sicc_pais.VAL_I18N%type;
	lsval_nume_soli 	ped_solic_cabec.VAL_NUME_SOLI%type;
	contador number :=0;
	contadorCabeceras number:=0;
	ls_oid_cabec	 REC_BOREC_ESPEC_CABEC.OID_BORE_ESPE_CABE%type;
	ls_cod_sap mae_produ.cod_sap%type;

 BEGIN

 DELETE FROM rec_repor_pedid_solic_cabec;
 lsoidperiodo:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pcod_peri);
 -- LLENAMOS LA TABLA TEMPORAL rec_repor_pedid_solic_cabec

 INSERT INTO rec_repor_pedid_solic_cabec
 (OID_SOLI_CABE,
 CLIE_OID_CLIE,
 PERD_OID_PERI,
 PAIS_OID_PAIS,
 FEC_FACT,
 SOCA_OID_SOLI_CABE,
 ICTP_OID_TIPO_PROG,
 DES_SOCI
 )
 SELECT A.OID_SOLI_CABE,
 		A.CLIE_OID_CLIE,
		A.PERD_OID_PERI,
		A.PAIS_OID_PAIS,
		A.FEC_FACT,
		A.SOCA_OID_SOLI_CABE,
		A.ICTP_OID_TIPO_PROG,
		GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(A.soci_oid_soci,'DES_SOCIE','1')
 FROM PED_SOLIC_CABEC A
 WHERE A.PERD_OID_PERI=lsoidperiodo
 	 AND a.esso_oid_esta_soli IN (1,5);



 -- Abrimos el cursor
 	 OPEN curBoleReco;
	 		LOOP
			-- Bulk Collect
				FETCH curBoleReco BULK COLLECT INTO cargaRecord LIMIT rows;

					 IF cargaRecord.COUNT > 0 THEN

					 FOR i IN cargaRecord.FIRST..cargaRecord.LAST LOOP

						 lsoid_soli_cabe:=cargaRecord(i).t_oid_soli_cabe;
						 lssoca_oid_soli_cabe:=cargaRecord(i).t_soca_oid_soli_cabe;
						 lsictp_oid_tipo_prog:=cargaRecord(i).t_ictp_oid_tipo_prog;

						contadorCabeceras:=0;
						 --Abrimos el cursor Intermedio

						 OPEN curIntermedio(lsoid_soli_cabe,lsictp_oid_tipo_prog);
	 					 	 LOOP
							 -- Bulk Collect
							 	 FETCH curIntermedio BULK COLLECT INTO cargaRecordInternedio LIMIT rows;

					 			 		IF cargaRecordInternedio.COUNT > 0 THEN

					 					 FOR j IN cargaRecordInternedio.FIRST..cargaRecordInternedio.LAST LOOP


						 				 lsval_nume_soli := REC_PKG_REPOR.REC_FN_DEVUE_VAL_NUME_SOLI(cargaRecord(i).t_soca_oid_soli_cabe);




									IF(lsval_nume_soli<>0) THEN

									IF(contadorCabeceras=0) THEN


										 SELECT REC_SEQ_BOREC_ESPEC_CABEC.nextval
										 INTO ls_oid_cabec
										 FROM dual;

						 				NUM_BOLE:=REC_PKG_REPOR.REC_FN_DEVU_NUM_BOLE(cargaRecord(i).t_cod_pais)+1;


										-- IF(EXISTE=0) THEN
							 		 insert into REC_BOREC_ESPEC_CABEC
							 			 ( OID_BORE_ESPE_CABE,
							 		 	 COD_PAIS,
 										 COD_PERI,
 										 VAL_NUME_SOLI,
 										 COD_CLIE,
 										 DES_DIRE,
 										 DES_TELE,
 										 COD_REGI,
 										 DES_REGI,
 										 COD_ZONA,
 										 DES_ZONA,
 										 COD_SECC,
 										 DES_SECC,
 										 COD_TERR,
 										 DES_SOCI,
 										 VAL_NUME_BOLE,
 										 FEC_FACT
								 		 )
							 	 		 values
 										 ( ls_oid_cabec,
 cargaRecord(i).t_cod_pais,
 												cargaRecord(i).t_cod_peri,
 												lsval_nume_soli,
 												cargaRecord(i).t_cod_clie,
 												cargaRecord(i).t_val_direc,
 												cargaRecord(i).t_val_text_comu,
 												cargaRecord(i).t_cod_regi,
 												cargaRecord(i).t_des_regi,
 												cargaRecord(i).t_cod_zona,
 												cargaRecord(i).t_des_zona,
 												cargaRecord(i).t_cod_secc,
 												cargaRecord(i).t_des_secci,
 												cargaRecord(i).t_cod_terr,
 												cargaRecord(i).t_val_deno,
 												NUM_BOLE,
 												cargaRecord(i).t_fec_fact
							 			 );
						 END IF;

									IF(contadorCabeceras=0) THEN

									 SELECT COUNT(1)
									 INTO NUM_VERI
									 FROM REC_BOREC_ESPEC_CONTR
									 WHERE REC_BOREC_ESPEC_CONTR.cod_pais=pcod_pais;

									 IF(NUM_VERI=0)THEN
									 INSERT INTO REC_BOREC_ESPEC_CONTR(
										 cod_pais,
										 VAL_NUME_BOLE
										 )VALUES
										 (pcod_pais,
										 NUM_BOLE
										 );
									 ELSE
									 update REC_BOREC_ESPEC_CONTR
									 set REC_BOREC_ESPEC_CONTR.VAL_NUME_BOLE=NUM_BOLE
									 where REC_BOREC_ESPEC_CONTR.cod_pais=pcod_pais;
									 END IF;

									 contadorCabeceras:=contadorCabeceras+1;
									 END IF;

										lsprod_oid_prod:= GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(cargaRecordInternedio(j).t_prod_oid_prod,'MAE_PRODU',pcod_Iso);
									 ls_cod_sap:=INT_PKG_RECLA.GEN_FN_CODSAP_PROD(cargaRecordInternedio(j).t_prod_oid_prod);
											--IF(EXISTE_DETALLE=0) THEN

												IF(lsprod_oid_prod IS NOT NULL) THEN
										 		 insert into REC_BOREC_ESPEC_DETAL
												 (
											 		 OID_BORE_ESPE_DETA,
													 DES_PROD,
													 VAL_CODI_VENT,
													 NUM_UNID_ATEN,
													 IND_RECO,
													 FEC_RECO,
													 NUM_UNID_RECO,
													 BREC_OID_BORE_ESPE_CABE,
 COD_SAP,
 OID_PROD
										 		 	 )
										 			 values
													 (	 REC_SEQ_BOREC_ESPEC_DETAL.nextval,
															 lsprod_oid_prod,
													 		 cargaRecordInternedio(j).t_val_codi_vent,
															 cargaRecordInternedio(j).t_num_unid_aten,
															 'N',
															 TO_DATE(SYSDATE),
															 0,
															 ls_oid_cabec,
 ls_cod_sap,
 cargaRecordInternedio(j).t_prod_oid_prod
													 );

												--END IF;
										END IF;

							END IF;

						 END LOOP;
						 END IF;
 					 EXIT WHEN curIntermedio%NOTFOUND;
 						 END LOOP;
					CLOSE curIntermedio;

		END LOOP;
		END IF;
 EXIT WHEN curBoleReco%NOTFOUND;
 END LOOP;
CLOSE curBoleReco;

DELETE FROM REC_BOREC_CODVE;

END REC_PR_CARGA_BOREC;
/***************************************************************************
 Descripcion : Verifica que no exista registros iguales en la tabla
					 	REC_BOREC_ESPEC_CABEC
 Fecha Creacion : 20/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 FUNCTION REC_FN_VERI_BOREC(pcod_peri VARCHAR2, pcod_pais VARCHAR2,
			 				pcod_clie VARCHAR2, psOidSolicitud NUMBER)
 RETURN NUMBER

	IS

 EXISTE NUMBER;
 BEGIN

 SELECT COUNT(1)
 INTO EXISTE
		FROM 	 REC_BOREC_ESPEC_CABEC a
		WHERE 	 a.cod_peri=pcod_peri
 			 	 and a.val_nume_soli=psOidSolicitud
 				 and a.cod_clie=pcod_clie
 				 and a.cod_pais=pcod_pais;

RETURN EXISTE;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;


END REC_FN_VERI_BOREC;

/***************************************************************************
 Descripcion : Verifica que no exista registros iguales en la tabla
					 	REC_BOREC_ESPEC_DETA
 Fecha Creacion : 20/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 FUNCTION REC_FN_VERI_BOREC_DETA(pcod_peri VARCHAR2, pcod_pais VARCHAR2,
			 				pcod_clie VARCHAR2, psOidSolicitud NUMBER, psCodVenta VARCHAR2)
 RETURN NUMBER

	IS

 EXISTE NUMBER;
 BEGIN

 SELECT COUNT(1)
 INTO EXISTE
		FROM 	 REC_BOREC_ESPEC_DETAL a
		WHERE 	/* a.cod_peri=pcod_peri
 			 	 and a.val_nume_soli=psOidSolicitud
 				 and a.cod_clie=pcod_clie
 				 and a.cod_pais=pcod_pais
				 and */ a.VAL_CODI_VENT=psCodVenta;

RETURN EXISTE;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;


END REC_FN_VERI_BOREC_DETA;

/***************************************************************************
 Descripcion : Verifica que no exista registros iguales en la tabla
					 	REC_BOREC_ESPEC_DETA
 Fecha Creacion : 29/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 FUNCTION REC_FN_DEVU_NUM_BOLE(pcod_pais VARCHAR2 )
 RETURN NUMBER

IS

 EXISTE NUMBER;
 BEGIN

 SELECT VAL_NUME_BOLE
 INTO EXISTE
		FROM 	 REC_BOREC_ESPEC_CONTR a
		WHERE 	 a.cod_pais=pcod_pais;


RETURN EXISTE;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 1;


END REC_FN_DEVU_NUM_BOLE;

/***************************************************************************
 Descripcion : Verifica que no exista registros iguales en la tabla
					 	REC_PR_UPDAT_NUM_BOLE
 Fecha Creacion : 29/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 PROCEDURE REC_PR_UPDAT_NUM_BOLE(pcod_pais VARCHAR2,val_nume_bole NUMBER)

	is

 BEGIN

 	 update REC_BOREC_ESPEC_CONTR
	 set REC_BOREC_ESPEC_CONTR.VAL_NUME_BOLE=val_nume_bole
	 where REC_BOREC_ESPEC_CONTR.cod_pais=pcod_pais;
 COMMIT;

	END REC_PR_UPDAT_NUM_BOLE;
 /***************************************************************************
 Descripcion : Recupera el OID_BOREC_ESPEC_CABE de la tabla
					 	REC_BOREC_ESPEC_CABEC
 Fecha Creacion : 29/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 FUNCTION REC_FN_DEVUE_OID_BOLE_CABEC(pcod_pais VARCHAR2, pcod_clie VARCHAR2,
			 							pcod_peri VARCHAR2, ps_nume_soli NUMBER)
 RETURN NUMBER

	IS
	NUM_BOLE NUMBER;

	BEGIN

	SELECT OID_BORE_ESPE_CABE
	INTO NUM_BOLE
	FROM REC_BOREC_ESPEC_CABEC
	WHERE COD_PAIS=pcod_pais and
		 COD_PERI=pcod_peri and
		 COD_CLIE=pcod_clie and
		 VAL_NUME_SOLI=ps_nume_soli;

	RETURN NUM_BOLE;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;

	END REC_FN_DEVUE_OID_BOLE_CABEC;

/***************************************************************************
 Descripcion : Recupera el VAL_NUME_SOLI de la tabla
					 	PED_SOLIC_CABEC
 Fecha Creacion : 29/05/2008
 Autor : Cristhian Roman
 ***************************************************************************/
 FUNCTION REC_FN_DEVUE_VAL_NUME_SOLI( ps_soca_oid_soli_cabe NUMBER)
 RETURN NUMBER

	IS
	NUM_BOLE NUMBER;

	BEGIN

	SELECT VAL_NUME_SOLI
	INTO NUM_BOLE
	FROM PED_SOLIC_CABEC A
	WHERE A.OID_SOLI_CABE=ps_soca_oid_soli_cabe
		 AND A.ESSO_OID_ESTA_SOLI IN (1, 5);

	RETURN NUM_BOLE;

EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RETURN 0;

	END REC_FN_DEVUE_VAL_NUME_SOLI;
/***************************************************************************
Descripcion       : Actualiza la tabla REC_REPOR_OPERA_UNIDA_ADMIN los valores de
                    tipos de pedido
                    - Reporte de Operaciones por Unidad Administrativa
Fecha Creacion    : 23/11/2010

Autor             : Carlos Diaz Valverde
***************************************************************************/
PROCEDURE REC_PR_UPDAT_OPERA_UNIDA_ADMIN
IS
  /* Variables generales */
 	rows NATURAL := 1000; -- Number of rows to process at a time
  i BINARY_INTEGER := 0;

  /* cursor,record,table */
  CURSOR curRepoOperaUnidaAdmin
  IS
  SELECT    NUM_ITEM,
            SOCA_OID_SOLI_CABE
  FROM      REC_REPOR_OPERA_UNIDA_ADMIN;

  TYPE cargaRecordType IS RECORD(
    T_NUM_ITEM REC_REPOR_OPERA_UNIDA_ADMIN.NUM_ITEM%TYPE,
    T_SOCA_OID_SOLI_CABE REC_REPOR_OPERA_UNIDA_ADMIN.SOCA_OID_SOLI_CABE%TYPE
  );

  TYPE cargaRecordTab IS TABLE OF cargaRecordType;
  cargaRecord cargaRecordTab;

  /* variables columnas */
  V_VAL_TIPO_PEDI VARCHAR2(15);
  V_IND_OC NUMBER;

BEGIN

  OPEN curRepoOperaUnidaAdmin;
  LOOP

    FETCH curRepoOperaUnidaAdmin BULK COLLECT INTO cargaRecord LIMIT rows;

    IF cargaRecord.COUNT > 0 THEN
      FOR i IN cargaRecord.FIRST..cargaRecord.LAST LOOP

        -- Consultar indicador
        BEGIN
          select    sum(ind_oc)
            into    V_IND_OC
          from      ped_solic_cabec
          where     soca_oid_soli_cabe = cargaRecord(i).T_SOCA_OID_SOLI_CABE;
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            V_IND_OC := 0;
        END;

        -- Capturar valor requerido
        IF V_IND_OC = 0 THEN
          V_VAL_TIPO_PEDI := 'SERVICIO';
        ELSE
          V_VAL_TIPO_PEDI := 'NORMAL';
        END IF;

        -- Actualizar valor en columna
        UPDATE    REC_REPOR_OPERA_UNIDA_ADMIN
           SET    VAL_TIPO_PEDI = V_VAL_TIPO_PEDI
        WHERE     NUM_ITEM = cargaRecord(i).T_NUM_ITEM;

      END LOOP;
    END IF;

  EXIT WHEN curRepoOperaUnidaAdmin%NOTFOUND;
  END LOOP;
  CLOSE curRepoOperaUnidaAdmin;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_UPDAT_OPERA_UNIDA_ADMIN: '||ls_sqlerrm);

END REC_PR_UPDAT_OPERA_UNIDA_ADMIN;

/***************************************************************************
Descripcion : Carga la tabla temporal para el reporte de Operaciones de Reclamos por Pedidos
Parametros :   psCodigoPais        Codigo Pais
               psOidPeriInicial    Id Periodo Inicial
               psOidPeriFinal      Id Periodo Final
               psFechaDesde        Fecha de facturación inicio
               psFechaHasta        Fecha de facturación final
               psCodigoSecuencia   Secuencia de tabla temporal
Fecha Creacion : 05/10/2011
Autor : Nicolás López Ramos
***************************************************************************/
PROCEDURE REC_PR_CARGA_TEMPO_REPOR_OPREC
(
  psCodigoPais        VARCHAR2,
  psOidPeriInicial    VARCHAR2,
  psOidPeriFinal      VARCHAR2,
  psFechaDesde        VARCHAR2,
  psFechaHasta        VARCHAR2,
  psCodigoSecuencia   VARCHAR2,
  psTipoMovimiento    VARCHAR2,
  psCondicion         VARCHAR2,
  psFlagTipoOpera     VARCHAR2,
  psCondicionTipoOper VARCHAR2
)
IS
  W_FILAS             NUMBER := 5000;
  ---lnIdPais            NUMBER;

 TYPE tmptablaOperaReclamoPedidos IS RECORD
 (
  oidPerio                      CRA_PERIO.OID_PERI %TYPE,
  fecFact                       PED_SOLIC_CABEC.FEC_FACT %TYPE
 );

 TYPE tablaRegOperaReclamoPedidos IS TABLE OF tmptablaOperaReclamoPedidos;
  tablaRegOperaReclPedidorecord tablaRegOperaReclamoPedidos;

  lsCodPeri                     SEG_PERIO_CORPO.COD_PERI %TYPE;
  lnNumPedidoFact               NUMBER(12);
  lnNumPedidoConFaltante        NUMBER(12);
  lnNumPedidoConFaltaSinMav     NUMBER(12);
  lnNumPedConFaltSMSTo21STo16   NUMBER(12);
  lnNumPedConFaltSMSTo21STo78   NUMBER(12);
  lnNumPedFalSMavSTO21S16SLBel  NUMBER(12);
  lnTotalCDRs                   NUMBER(12);

 -- Se obtienen los datos
 CURSOR REPOR_OPERA_RECLA IS
  SELECT a.perd_oid_peri                  OID_PERI,
         a.fec_fact                       FEC_FACT
    FROM ped_solic_cabec a,
         cra_perio       c
   WHERE a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                        FROM ped_tipo_solic_pais tsp,
                                             ped_tipo_solic ts
                                       WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                         AND ts.cod_tipo_soli = 'SOC')
     AND a.fec_fact IS NOT NULL
     AND a.esso_oid_esta_soli <> 4
     AND c.oid_peri = a.perd_oid_peri
     AND a.perd_oid_peri >= TO_NUMBER(psOidPeriInicial)
     AND a.perd_oid_peri <= TO_NUMBER(psOidPeriFinal)
     AND a.fec_fact >= to_date(psFechaDesde,'DD/MM/YYYY')
     AND a.fec_fact <= to_date(psFechaHasta,'DD/MM/YYYY')
  GROUP BY a.perd_oid_peri, a.fec_fact;

BEGIN

 OPEN REPOR_OPERA_RECLA;
 LOOP
   FETCH REPOR_OPERA_RECLA BULK COLLECT INTO tablaRegOperaReclPedidorecord LIMIT W_FILAS;
   IF tablaRegOperaReclPedidorecord.COUNT > 0 THEN
   FOR x IN tablaRegOperaReclPedidorecord.FIRST .. tablaRegOperaReclPedidorecord.LAST LOOP

       -- Obtenemos el código de la campaña
       SELECT per.cod_peri INTO lsCodPeri
         FROM SEG_PERIO_CORPO per,
              CRA_PERIO cra
        WHERE per.oid_peri = cra.peri_oid_peri
          AND cra.oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio;

       -- Obtenemos el número de pedidos facturados
        BEGIN
          SELECT COUNT(a.oid_soli_cabe) INTO lnNumPedidoFact
            FROM ped_solic_cabec a, cra_perio c
           WHERE a.tspa_oid_tipo_soli_pais =
                 (SELECT oid_tipo_soli_pais
                    FROM ped_tipo_solic_pais tsp, ped_tipo_solic ts
                   WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                     AND ts.cod_tipo_soli = 'SOC')
             AND a.fec_fact IS NOT NULL
             AND a.esso_oid_esta_soli <> 4
             AND c.oid_peri = a.perd_oid_peri
             AND a.perd_oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio
             AND a.fec_fact = tablaRegOperaReclPedidorecord(x).fecFact
           GROUP BY c.val_nomb_peri;
        EXCEPTION WHEN NO_DATA_FOUND THEN
              lnNumPedidoFact :=0;
        END;

       -- Obtenemos el número de pedidos con faltante
        BEGIN
           SELECT COUNT(DATOS.clie_oid_clie) INTO lnNumPedidoConFaltante
           FROM (
            SELECT DISTINCT a.clie_oid_clie
              FROM ped_solic_cabec a,
                   ped_solic_posic b,
                   cra_perio c
             WHERE a.fec_fact IS NOT NULL
               AND a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                                  FROM ped_tipo_solic_pais tsp,
                                                       ped_tipo_solic ts
                                                 WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                   AND ts.cod_tipo_soli = 'SOC')
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND nvl(b.ind_limi_vent,0) <> 1
               AND b.num_unid_dema_real - b.num_unid_aten > 0
               AND c.oid_peri=a.perd_oid_peri
               AND a.perd_oid_peri   = tablaRegOperaReclPedidorecord(x).oidPerio
               AND trunc(a.fec_fact) = tablaRegOperaReclPedidorecord(x).fecFact
               ) DATOS;
        EXCEPTION WHEN NO_DATA_FOUND THEN
             lnNumPedidoConFaltante:=0;
        END;

        -- Obtenemos el Número de pedidos con faltante sin Mav
        BEGIN
          SELECT COUNT(DATOS.clie_oid_clie) INTO lnNumPedidoConFaltaSinMav
          FROM (
              SELECT DISTINCT a.clie_oid_clie
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     cra_perio       c,
                     pre_ofert_detal d
               WHERE a.fec_fact IS NOT NULL
                 AND a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                                    FROM ped_tipo_solic_pais tsp,
                                                         ped_tipo_solic ts
                                                   WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                     AND ts.cod_tipo_soli = 'SOC')
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND nvl(b.ind_limi_vent,0) <> 1
                 AND b.num_unid_dema_real - b.num_unid_aten > 0
                 AND c.oid_peri = a.perd_oid_peri
                 AND b.ofde_oid_deta_ofer = d.oid_deta_ofer
                 AND ((d.cod_orig = 'MAV' AND d.imp_prec_cata > 0) OR d.cod_orig <> 'MAV')
                 AND a.perd_oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio
                 AND a.fec_fact = tablaRegOperaReclPedidorecord(x).fecFact
                ) DATOS;
        EXCEPTION WHEN NO_DATA_FOUND THEN
             lnNumPedidoConFaltaSinMav:=0;
        END;

         -- Obtenemos el Número de pedidos con faltante sin MAV, sin TO 21 y sin TO 16
        BEGIN
          SELECT COUNT(DATOS.clie_oid_clie) INTO lnNumPedConFaltSMSTo21STo16
          FROM (
                SELECT DISTINCT a.clie_oid_clie
                  FROM ped_solic_cabec a,
                       ped_solic_posic b,
                       cra_perio c,
                       pre_ofert_detal d
                 WHERE a.fec_fact IS NOT NULL
                   AND a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                                      FROM ped_tipo_solic_pais tsp,
                                                           ped_tipo_solic ts
                                                     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                       AND ts.cod_tipo_soli = 'SOC')
                   AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                   AND nvl(b.ind_limi_vent,0) <> 1
                   AND b.num_unid_dema_real - b.num_unid_aten > 0
                   AND c.oid_peri = a.perd_oid_peri
                   AND b.ofde_oid_deta_ofer = d.oid_deta_ofer
                   AND ((d.cod_orig = 'MAV' AND d.imp_prec_cata > 0) OR d.cod_orig <> 'MAV')
                   AND a.perd_oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio
                   AND d.tofe_oid_tipo_ofer NOT IN (2016, 2021)
                   AND a.fec_fact = tablaRegOperaReclPedidorecord(x).fecFact
                ) DATOS;
        EXCEPTION WHEN NO_DATA_FOUND THEN
               lnNumPedConFaltSMSTo21STo16:=0;
        END;

       -- Número de pedidos con Faltante sin MAV, sin TO 21, sin TO 16, sin TO 71, sin TO 72 y sin TO 78
       BEGIN
          SELECT COUNT(DATOS.clie_oid_clie) INTO lnNumPedConFaltSMSTo21STo78
          FROM (
                SELECT DISTINCT a.clie_oid_clie
                  FROM ped_solic_cabec a,
                       ped_solic_posic b,
                       cra_perio c,
                       pre_ofert_detal d
                 WHERE a.fec_fact IS NOT NULL
                   AND a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                                      FROM ped_tipo_solic_pais tsp,
                                                           ped_tipo_solic ts
                                                     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                       AND ts.cod_tipo_soli = 'SOC')
                   AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                   AND nvl(b.ind_limi_vent,0) <> 1
                   AND b.num_unid_dema_real - b.num_unid_aten > 0
                   AND c.oid_peri = a.perd_oid_peri
                   AND b.ofde_oid_deta_ofer = d.oid_deta_ofer
                   AND ((d.cod_orig = 'MAV' AND d.imp_prec_cata > 0) OR d.cod_orig <> 'MAV')
                   AND a.perd_oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio
                   AND d.tofe_oid_tipo_ofer NOT IN (2016, 2021, 2069, 2070, 2076)
                   AND a.fec_fact = tablaRegOperaReclPedidorecord(x).fecFact
                 )DATOS;

        EXCEPTION WHEN NO_DATA_FOUND THEN
             lnNumPedConFaltSMSTo21STo78 := 0;
        END;

        -- Número de pedidos con Faltante: sin MAV, sin TO 21 y sin TO 16, sin Lbel
        BEGIN
          SELECT COUNT(DATOS.clie_oid_clie) INTO lnNumPedFalSMavSTO21S16SLBel
          FROM (
                SELECT DISTINCT a.clie_oid_clie
                  FROM ped_solic_cabec a,
                       ped_solic_posic b,
                       cra_perio       c,
                       pre_ofert_detal d,
                       mae_produ       e
                 WHERE a.fec_fact IS NOT NULL
                   AND a.tspa_oid_tipo_soli_pais = (SELECT oid_tipo_soli_pais
                                                      FROM ped_tipo_solic_pais tsp,
                                                           ped_tipo_solic ts
                                                     WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                       AND ts.cod_tipo_soli = 'SOC')
                   AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                   AND nvl(b.ind_limi_vent,0) <> 1
                   AND b.num_unid_dema_real - b.num_unid_aten > 0
                   AND c.oid_peri = a.perd_oid_peri
                   AND b.ofde_oid_deta_ofer = d.oid_deta_ofer
                   AND ((d.cod_orig = 'MAV' AND d.imp_prec_cata > 0) OR d.cod_orig <> 'MAV')
                   AND a.perd_oid_peri = tablaRegOperaReclPedidorecord(x).oidPerio
                   AND d.tofe_oid_tipo_ofer NOT IN (2016, 2021)
                   AND d.prod_oid_prod = e.oid_prod
                   AND e.mapr_oid_marc_prod NOT IN (1)
                   AND a.fec_fact = tablaRegOperaReclPedidorecord(x).fecFact
                 )DATOS;
        EXCEPTION WHEN NO_DATA_FOUND THEN
             lnNumPedFalSMavSTO21S16SLBel:=0;
        END;

        -- Total de CDRs
        BEGIN
            SELECT COUNT(DISTINCT rec_cabec_recla.oid_cabe_recl) INTO lnTotalCDRs
              FROM rec_cabec_recla,
                   rec_opera_recla,
                   rec_linea_opera_recla,
                   rec_tipos_opera,
                   rec_opera,
                   cra_perio
             WHERE rec_linea_opera_recla.opre_oid_oper_recl =
                   rec_opera_recla.oid_oper_recl
               AND rec_opera_recla.care_oid_cabe_recl = rec_cabec_recla.oid_cabe_recl
               AND rec_opera_recla.tiop_oid_tipo_oper = rec_tipos_opera.oid_tipo_oper
               AND rec_tipos_opera.rope_oid_oper = rec_opera.oid_oper
               AND rec_linea_opera_recla.timo_oid_tipo_movi = TO_NUMBER(psTipoMovimiento)
               AND rec_cabec_recla.fec_ingr = tablaRegOperaReclPedidorecord(x).fecFact
               AND rec_opera_recla.perd_oid_peri_recl = tablaRegOperaReclPedidorecord(x).oidPerio
               AND cra_perio.oid_peri = rec_opera_recla.perd_oid_peri_recl
               AND ((REC_OPERA.COD_OPER IN (psCondicion)) OR
                   (psFlagTipoOpera = VALOR_TIPO_MOVIMIENTO_UNO AND (REC_OPERA.COD_OPER || '-' || REC_TIPOS_OPERA.VAL_TIPO_OPER  IN (psCondicionTipoOper))))
             GROUP BY cra_perio.val_nomb_peri;
        EXCEPTION WHEN NO_DATA_FOUND THEN
             lnTotalCDRs := 0;
        END;

       -- Se procede a insertar los valores a la tabla de reporte Operaciones de Reclamos por Pedidos
       INSERT INTO REC_TEMPO_REPOR_OPREC(
           OID_REPO_OPRE,
           COD_PERI,
           FEC_FACT,
           NUM_PEDI_FACT,
           NUM_PEDI_FALT_SMAV,
           NUM_PEDI_FALT,
           NUM_PEDI_FALT_ST21_ST16,
           NUM_PEDI_FALT_ST21_ST16_SLBE,
           NUM_PEDI_FALT_SMAV_VARI,
           VAL_TOTA_CDRS)
       VALUES(psCodigoSecuencia,
              lsCodPeri,
              TO_CHAR(tablaRegOperaReclPedidorecord(x).fecFact,'dd/MM/yyyy'),
              lnNumPedidoFact,
              lnNumPedidoConFaltaSinMav,
              lnNumPedidoConFaltante,
              lnNumPedConFaltSMSTo21STo16,
              lnNumPedFalSMavSTO21S16SLBel,
              lnNumPedConFaltSMSTo21STo78,
              lnTotalCDRs);

   END LOOP;
   END IF;
  EXIT WHEN  REPOR_OPERA_RECLA%NOTFOUND;
 END LOOP;

 CLOSE REPOR_OPERA_RECLA;

 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_CARGA_TEMPO_REPOR_OPREC: '||ls_sqlerrm);
END REC_PR_CARGA_TEMPO_REPOR_OPREC;

END REC_PKG_REPOR;
/
