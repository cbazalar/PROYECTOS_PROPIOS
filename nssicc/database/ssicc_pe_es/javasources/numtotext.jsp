create or replace and compile java source named numtotext as
public class Numtotext

{



    public Numtotext()

    {

        numero = 0;

        flag = 0;

    }



    public Numtotext(int n)

    {

        numero = n;

        flag = 0;

    }



    public String procesar(Float numeroEntrada)

    {

        if(numeroEntrada.floatValue() == (float)0)

            return "CERO";

        String salida = null;

        if(numeroEntrada.floatValue() < (float)0)

        {

            salida = "menos ";

            numeroEntrada = new Float(Math.abs(numeroEntrada.floatValue()));

        } else

        {

            salida = "";

        }

        String decimal = numeroEntrada.toString();

        int posPunto = decimal.lastIndexOf(".");

        if(decimal.length() - posPunto < 3)

            decimal = decimal + "0";

        decimal = decimal.substring(decimal.length() - 2, decimal.length());

        float aux = numeroEntrada.floatValue() - (float)numeroEntrada.intValue();

        if(aux == (float)0)

        {

            salida = salida + convertirLetras(numeroEntrada.intValue());

        } else

        {

            salida = salida + convertirLetras(numeroEntrada.intValue());

            salida = salida + " y " + (new Integer(decimal)).intValue() + "/100";

        }

        return salida;

    }



    private String unidad(int numero)

    {

        switch(numero)

        {

        default:

            break;



        case 9: // '\t'

            num = "nueve";

            break;



        case 8: // '\b'

            num = "ocho";

            break;



        case 7: // '\007'

            num = "siete";

            break;



        case 6: // '\006'

            num = "seis";

            break;



        case 5: // '\005'

            num = "cinco";

            break;



        case 4: // '\004'

            num = "cuatro";

            break;



        case 3: // '\003'

            num = "tres";

            break;



        case 2: // '\002'

            num = "dos";

            break;



        case 1: // '\001'

            if(flag == 0)

                num = "un";

            else

                num = "un";

            break;



        case 0: // '\0'

            num = "";

            break;

        }

        return num;

    }



    private String decena(int numero)

    {

        if(numero >= 90 && numero <= 99)

        {

            num_letra = "noventa ";

            if(numero > 90)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 90));

        } else

        if(numero >= 80 && numero <= 89)

        {

            num_letra = "ochenta ";

            if(numero > 80)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 80));

        } else

        if(numero >= 70 && numero <= 79)

        {

            num_letra = "setenta ";

            if(numero > 70)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 70));

        } else

        if(numero >= 60 && numero <= 69)

        {

            num_letra = "sesenta ";

            if(numero > 60)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 60));

        } else

        if(numero >= 50 && numero <= 59)

        {

            num_letra = "cincuenta ";

            if(numero > 50)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 50));

        } else

        if(numero >= 40 && numero <= 49)

        {

            num_letra = "cuarenta ";

            if(numero > 40)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 40));

        } else

        if(numero >= 30 && numero <= 39)

        {

            num_letra = "treinta ";

            if(numero > 30)

                num_letra = num_letra.concat("y ").concat(unidad(numero - 30));

        } else

        if(numero >= 20 && numero <= 29)

        {

            if(numero == 20)

                num_letra = "veinte ";

            else

                num_letra = "veinti".concat(unidad(numero - 20));

        } else

        if(numero >= 10 && numero <= 19)

            switch(numero)

            {

            case 10: // '\n'

                num_letra = "diez ";

                break;



            case 11: // '\013'

                num_letra = "once ";

                break;



            case 12: // '\f'

                num_letra = "doce ";

                break;



            case 13: // '\r'

                num_letra = "trece ";

                break;



            case 14: // '\016'

                num_letra = "catorce ";

                break;



            case 15: // '\017'

                num_letra = "quince ";

                break;



            case 16: // '\020'

                num_letra = "dieciseis ";

                break;



            case 17: // '\021'

                num_letra = "diecisiete ";

                break;



            case 18: // '\022'

                num_letra = "dieciocho ";

                break;



            case 19: // '\023'

                num_letra = "diecinueve ";

                break;

            }

        else

            num_letra = unidad(numero);

        return num_letra;

    }



    private String centena(int numero)

    {

        if(numero >= 100)

        {

            if(numero >= 900 && numero <= 999)

            {

                num_letra = "novecientos ";

                if(numero > 900)

                    num_letra = num_letra.concat(decena(numero - 900));

            } else

            if(numero >= 800 && numero <= 899)

            {

                num_letra = "ochocientos ";

                if(numero > 800)

                    num_letra = num_letra.concat(decena(numero - 800));

            } else

            if(numero >= 700 && numero <= 799)

            {

                num_letra = "setecientos ";

                if(numero > 700)

                    num_letra = num_letra.concat(decena(numero - 700));

            } else

            if(numero >= 600 && numero <= 699)

            {

                num_letra = "seiscientos ";

                if(numero > 600)

                    num_letra = num_letra.concat(decena(numero - 600));

            } else

            if(numero >= 500 && numero <= 599)

            {

                num_letra = "quinientos ";

                if(numero > 500)

                    num_letra = num_letra.concat(decena(numero - 500));

            } else

            if(numero >= 400 && numero <= 499)

            {

                num_letra = "cuatrocientos ";

                if(numero > 400)

                    num_letra = num_letra.concat(decena(numero - 400));

            } else

            if(numero >= 300 && numero <= 399)

            {

                num_letra = "trescientos ";

                if(numero > 300)

                    num_letra = num_letra.concat(decena(numero - 300));

            } else

            if(numero >= 200 && numero <= 299)

            {

                num_letra = "doscientos ";

                if(numero > 200)

                    num_letra = num_letra.concat(decena(numero - 200));

            } else

            if(numero >= 100 && numero <= 199)

                if(numero == 100)

                    num_letra = "cien ";

                else

                    num_letra = "ciento ".concat(decena(numero - 100));

        } else

        {

            num_letra = decena(numero);

        }

        return num_letra;

    }



    private String miles(int numero)

    {

        if(numero >= 1000 && numero < 2000)

            num_letram = "mil ".concat(centena(numero % 1000));

        if(numero >= 2000 && numero < 10000)

        {

            flag = 1;

            num_letram = unidad(numero / 1000).concat(" mil ").concat(centena(numero % 1000));

        }

        if(numero < 1000)

            num_letram = centena(numero);

        return num_letram;

    }



    private String decmiles(int numero)

    {

        if(numero == 10000)

            num_letradm = "diez mil";

        if(numero > 10000 && numero < 20000)

        {

            flag = 1;

            num_letradm = decena(numero / 1000).concat("mil ").concat(centena(numero % 1000));

        }

        if(numero >= 20000 && numero < 0x186a0)

        {

            flag = 1;

            num_letradm = decena(numero / 1000).concat(" mil ").concat(miles(numero % 1000));

        }

        if(numero < 10000)

            num_letradm = miles(numero);

        return num_letradm;

    }



    private String cienmiles(int numero)

    {

        if(numero == 0x186a0)

            num_letracm = "cien mil";

        if(numero >= 0x186a0 && numero < 0xf4240)

        {

            flag = 1;

            num_letracm = centena(numero / 1000).concat(" mil ").concat(centena(numero % 1000));

        }

        if(numero < 0x186a0)

            num_letracm = decmiles(numero);

        return num_letracm;

    }



    private String millon(int numero)

    {

        if(numero >= 0xf4240 && numero < 0x1e8480)

        {

            flag = 1;

            num_letramm = "Un millon ".concat(cienmiles(numero % 0xf4240));

        }

        if(numero >= 0x1e8480 && numero < 0x989680)

        {

            flag = 1;

            num_letramm = unidad(numero / 0xf4240).concat(" millones ").concat(cienmiles(numero % 0xf4240));

        }

        if(numero < 0xf4240)

            num_letramm = cienmiles(numero);

        return num_letramm;

    }



    private String decmillon(int numero)

    {

        if(numero == 0x989680)

            num_letradmm = "diez millones";

        if(numero > 0x989680 && numero < 0x1312d00)

        {

            flag = 1;

            num_letradmm = decena(numero / 0xf4240).concat("millones ").concat(cienmiles(numero % 0xf4240));

        }

        if(numero >= 0x1312d00 && numero < 0x5f5e100)

        {

            flag = 1;

            num_letradmm = decena(numero / 0xf4240).concat(" milllones ").concat(millon(numero % 0xf4240));

        }

        if(numero < 0x989680)

            num_letradmm = millon(numero);

        return num_letradmm;

    }



    public static String convertirLetras(int numero)

    {

	    Numtotext ntt = new Numtotext();

        String num_letras = ntt.decmillon(numero);

        return num_letras.concat("");

    }



    private int flag;

    public int numero;

    private String num;

    private String num_letra;

    private String num_letras;

    private String num_letram;

    private String num_letradm;

    private String num_letracm;

    private String num_letramm;

    private String num_letradmm;

}
/

