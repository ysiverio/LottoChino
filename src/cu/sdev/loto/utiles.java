package cu.sdev.loto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Yaisel on 03/10/2017.
 */

public class utiles{
    Directorio dir = new Directorio();


    //----------------------------------------------------------------------------------------------
    // Método que retorna la ruta de todos los ficheros de un directorio pasado por parámetros
    // by ScorpJr-----------------------------------------------------------------------------------

    public ArrayList<String> getRutaFicheros(String ruta)
    {
        ArrayList<String> te = new ArrayList<>();
        ArrayList<String> listaRutasArchivos = new ArrayList<>();
        File directorioActual = new File(ruta);
        File[] listaArchivos = directorioActual.listFiles();

        // Almacenamos las rutas de todos los archivos y carpetas del directorio
        for (File archivo : listaArchivos) {
            listaRutasArchivos.add(archivo.getPath());
        }

        // Ordenamos la lista de archivos para que se muestren en orden alfabetico
        Collections.sort(listaRutasArchivos, String.CASE_INSENSITIVE_ORDER);

        for(int i=0;i<listaRutasArchivos.size();i++)
        {
            // Listado de fucheros separados por ;
            te.add(listaRutasArchivos.get(i));

        }

        return te;

    }

    //----------------------------------------------------------------------------------------------
    // Método para leer el contenido de varios ficheros de texto, recive como parametros
    // la ruta donde están los ficheros y una lista con los nombres de los ficheros
    // retorna una lista con el contenido de todos los ficheros.
    // by ScorpJr-----------------------------------------------------------------------------------

    public ArrayList<String> leerFichero(ArrayList<String> ficheros )
    {
        ArrayList<String> fichero= new ArrayList<String>();

        for(int i = 0; i<ficheros.size(); i++)
        {
            try
            {

                File f = new File(ficheros.get(i).toString());

                BufferedReader fin =
                        new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(f)));

                String texto = fin.readLine();
                fichero.add(texto);
                fin.close();

//                Log.i("Ficheros", "Fichero SD leido!");
//                Log.i("Ficheros", "Texto: " + texto);
            }
            catch (Exception ex)
            {
//                Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
            }
        }

        return fichero;
    }

    /**
     * Elimina los archivos con una determinada extensión de una carpeta
     * @param path Carpeta de la cual eliminar los archivos
    * @param extension Extensión de los archivos a eliminar
    */

    public static void eliminarPorExtension(String path, final String extension){

        File[] archivos = new File(path).listFiles(new FileFilter() {

            public boolean accept(File archivo) {

                if (archivo.isFile())
                    return archivo.getName().endsWith('.' + extension);
                return false;

            }

        });

        for (File archivo : archivos)
            archivo.delete();
    }

    /**
     * Elimina los archivos con una determinadas letras o palabras iniciales
     * @param path Carpeta de la cual eliminar los archivos
     * @param frase palabra o letras con que comiensa el nombre del fichero
     */
    public static void eliminarPorFrase(String path, final String frase){

        File[] archivos = new File(path).listFiles(new FileFilter() {

            public boolean accept(File archivo) {

                if (archivo.isFile())
                    return archivo.getName().startsWith(frase);
                return false;

            }

        });

        for (File archivo : archivos)
            archivo.delete();
    }

  
      /**
     * Generar el tiro del dia q devuelve la cantidad a pagar
     * @param numeros Numeros que salieron en el tiro
     * @param jugada Todas las jugadas que se hicieron en la jornada
     * @param apuesta La apuesta a la jugada (Fijo, Corrido, Parlet, Camdado, Centena)
     * @param dineroApuesta Cantidad de dinero apostado a la jugada
     * @param listaBote Tipo de jugada (Lista, Bote)
     * @param preciosBote Precio por el que se paga al ganador en el Bote
     * @param preciosLista Precio por el que se paga al ganador en la Lista
     * @param preciosLimitadosLista
     * @param preciosLimitadosBote
     * @param numerosLimitadoLista
     * @param numerosLimitadoBote
     * @return 
     */

  public double[] generarTiro(String[] numeros, ArrayList<String> jugada, ArrayList<String> apuesta, ArrayList<Double> dineroApuesta
            , ArrayList<String> listaBote, ArrayList<Integer> preciosLista, ArrayList<Integer> preciosBote, ArrayList<Integer> preciosLimitadosLista
            , ArrayList<Integer> preciosLimitadosBote, ArrayList<String> numerosLimitadoLista, ArrayList<String> numerosLimitadoBote, Boolean tipoParlet) {

        double ganadorL = 0, ganadorB = 0;
        double[] result = new double[2];
        String a = "";


        ArrayList<String> jugadaG = new ArrayList<>();
        ArrayList<String> apuestaG = new ArrayList<>();
        ArrayList<Double> dineroGanador = new ArrayList<>();
        ArrayList<Integer> lbGanador = new ArrayList<>();

        // Ciclo para añadir los ganadores a una lista
        for (int i = 0; i < jugada.size(); i++) {

            a = numeros[0].substring(1, 3);
            if (apuesta.get(i).equals("Candado")) {
                String jug[] = jugada.get(i).split("-");
                int pos = 0, pos1 = 0, pos2 = 0;
                boolean cont = false, cont1 = false, cont2 = false;
                for (int y = 0; y < jug.length; y++) {
                    if (jug[y].equals(numeros[0].substring(1, 3)) && !cont) {
                        cont = true;
                        pos = y;
                    } else if (jug[y].equals(numeros[1]) && !cont1) {
                        cont1 = true;
                        pos1 = y;
                    } else if (jug[y].equals(numeros[2]) && !cont2) {
                        cont2 = true;
                        pos2 = y;
                    }

                }

                if (cont && cont1 || cont && cont2 || cont1 && cont2) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }

            } else if (apuesta.get(i).equals("PlayF")) {
                if (jugada.get(i).contains(numeros[1] + "-" + numeros[2])) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }
            }
            else if (apuesta.get(i).equals("Mill")) {
                if (jugada.get(i).equals(numeros[0] + "-" + numeros[1] + "-" + numeros[2])) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }
            }
            else if (apuesta.get(i).equals("Pik")) {
                if (jugada.get(i).equals(numeros[0].substring(0, 1))) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }
            }else if (apuesta.get(i).equals("Parlet")) {
                String jug[] = jugada.get(i).split("-");
                int pos = 0, pos1 = 0, pos2 = 0;
                boolean cont = false, cont1 = false, cont2 = false;
                for (int y = 0; y < jug.length; y++) {
                    if (jug[y].equals(numeros[0].substring(1, 3)) && !cont) {
                        cont = true;
                        pos = y;
                    } else if (jug[y].equals(numeros[1]) && !cont1) {
                        cont1 = true;
                        pos1 = y;
                    } else if (jug[y].equals(numeros[2]) && !cont2) {
                        cont2 = true;
                        pos2 = y;
                    }

                }

                if (cont && cont1 || cont && cont2 || cont1 && cont2) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }

            }
            else {
                if (jugada.get(i).equals(numeros[0].substring(1, 3)) || jugada.get(i).equals(numeros[0]) || jugada.get(i).equals(numeros[1]) || jugada.get(i).equals(numeros[2])) {
                    System.out.println("Entro a los numero------------------------------------------------------------------");
                    jugadaG.add(jugada.get(i));
                    apuestaG.add(apuesta.get(i));
                    dineroGanador.add(dineroApuesta.get(i));
                    lbGanador.add(Integer.parseInt(listaBote.get(i)));
                }
            }


        }

        /*
        * Esto es para poner las jugadas ganadoras en un fichero y poder mostrarlas después de generar el tiro
        * */
        String ganadoresBote = "";
        String ganadoresLista = "";

        try {
            if (jugadaG.size() > 0) {
                System.out.println("Entro a jugada ganadora ----------------------------------------------------------");
                for (int i = 0; i < apuestaG.size(); i++) {
                    // Para el fijo eso solo el primer numero del tiro
                    // para los demas son los tres numeros del tiro
                    if ((apuestaG.get(i).equals("Fijo") || apuestaG.get(i).equals("FijoCorridoF")) && jugadaG.get(i).equals(numeros[0].substring(1, 3))) {
                        if (lbGanador.get(i) == 1) // pago por la lista
                        {
                            if (numerosLimitadoLista.get(0).contains(jugadaG.get(i).toString())) {
                                System.out.println("Entro al ganador Lista ----------------------------------------------------------");
                                ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(0);
                                ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            } else {
                                System.out.println("Entro al ganador Lista ----------------------------------------------------------");
                                ganadorL += dineroGanador.get(i) * preciosLista.get(0);
                                ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            }

                        } else // pago por el bote
                        {
                            if (numerosLimitadoBote.get(0).contains(jugadaG.get(i).toString())) {
                                System.out.println("Entro al ganador Bote ----------------------------------------------------------");
                                ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(0);
                                ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            } else {
                                System.out.println("Entro al ganador Bote ----------------------------------------------------------");
                                ganadorB += dineroGanador.get(i) * preciosBote.get(0);
                                ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            }

                        }
                        System.out.println("Entro a al fijo ----------------------------------------------------------");
                        // tengo que hacer una lista aparte de los ganadores en el fijo

                    } else if ((apuestaG.get(i).equals("Corrido") || apuestaG.get(i).equals("FijoCorridoC")) && (jugadaG.get(i).equals(numeros[0].substring(1, 3)) || jugadaG.get(i).equals(numeros[1]) || jugadaG.get(i).equals(numeros[2]))) {

                        if (jugadaG.get(i).equals(numeros[0].substring(1, 3)) && jugadaG.get(i).equals(numeros[1]) && jugadaG.get(i).equals(numeros[2])) {
                            if (lbGanador.get(i) == 1) // pago por la lista
                            {
                                if (numerosLimitadoLista.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(1) * 3;
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(1) * 3;
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            } else // pago por el bote
                            {
                                if (numerosLimitadoBote.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(1) * 3;
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else // pago por el bote
                                {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(1) * 3;
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }


                        } else if (jugadaG.get(i).equals(numeros[0].substring(1, 3)) && jugadaG.get(i).equals(numeros[1]) || jugadaG.get(i).equals(numeros[1]) && jugadaG.get(i).equals(numeros[2]) || jugadaG.get(i).equals(numeros[0].substring(1, 3)) && jugadaG.get(i).equals(numeros[2])) {
                            if (lbGanador.get(i) == 1) // pago por la lista
                            {
                                if (numerosLimitadoLista.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(1) * 2;
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(1) * 2;
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            } else // pago por el bote
                            {
                                if (numerosLimitadoBote.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(1) * 2;
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else // pago por el bote
                                {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(1) * 2;
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }

                        } else {
                            System.out.println("Entro al ganador corrido -----------------------------------------------------------");
                            // fijarme los que coninciden con el corrido
                            if (lbGanador.get(i) == 1) // pago por la lista
                            {
                                if (numerosLimitadoLista.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(1);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(1);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            } else // pago por el bote
                            {
                                if (numerosLimitadoBote.get(1).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(1);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else // pago por el bote
                                {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(1);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }


                        }
                    } else if (apuestaG.get(i).equals("FijoCorrido")) {
                        System.out.println("Entro al ganador Fijocorrido ----------------------------------------------------------");
                        // fijarme los que coninciden con el corrido
                        if ((jugadaG.get(i).contains(numeros[0].substring(1, 3)))) {
                            if (lbGanador.get(i) == 1) // pago por la lista
                            {
                                ganadorL += ((dineroGanador.get(i) / 2) * preciosLista.get(0)) + ((dineroGanador.get(i) / 2) * preciosLista.get(1));
                                ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            } else // pago por el bote
                            {
                                ganadorB += ((dineroGanador.get(i) / 2) * preciosBote.get(0)) + ((dineroGanador.get(i) / 2) * preciosBote.get(1));
                                ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            }
                        } else if ((jugadaG.get(i).contains(numeros[1])) || (jugadaG.get(i).contains(numeros[2]))) {
                            if (lbGanador.get(i) == 1) // pago por la lista
                            {
                                ganadorL += ((dineroGanador.get(i) / 2) * preciosLista.get(1));
                                ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            } else // pago por el bote
                            {
                                ganadorB += ((dineroGanador.get(i) / 2) * preciosBote.get(1));
                                ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                            }
                        }
                    } else if (apuestaG.get(i).equals("Parlet")) {
                        // si la jugada del parlet es < 3 numero, fijarme cuales son los ganadores
                        if (lbGanador.get(i) == 1) {
                            if (jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[1])
                                    || jugadaG.get(i).contains(numeros[1]) && jugadaG.get(i).contains(numeros[2]) ||
                                    jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[2])) {
                                if (numerosLimitadoLista.get(2).contains(jugadaG.get(i).toString())
                                        || numerosLimitadoLista.get(2).contains(jugadaG.get(i).toString().split("-")[1]+"-"+jugadaG.get(i).toString().split("-")[0])) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(2);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(2);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }

                        } else {
                            if (jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[1])
                                    || jugadaG.get(i).contains(numeros[1]) && jugadaG.get(i).contains(numeros[2]) ||
                                    jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[2])) {
                                if (numerosLimitadoBote.get(2).contains(jugadaG.get(i).toString())
                                        || numerosLimitadoLista.get(2).contains(jugadaG.get(i).toString().split("-")[1]+"-"+jugadaG.get(i).toString().split("-")[0])) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(2);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(2);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }
                        }
                    } else if (apuestaG.get(i).equals("PlayF")) {
                        // si la jugada del parlet es < 3 numero, fijarme cuales son los ganadores
                        if (lbGanador.get(i) == 1) {
                            if (jugadaG.get(i).equals(numeros[1] + "-" + numeros[2])) {
                                if (numerosLimitadoLista.get(4).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(4);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(4);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }

                        } else {
                            if (jugadaG.get(i).equals(numeros[1] + "-" + numeros[2])) {
                                if (numerosLimitadoBote.get(4).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(4);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(4);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }
                        }
                    }
                    else if (apuestaG.get(i).equals("Mill")) {
                        // si la jugada del parlet es < 3 numero, fijarme cuales son los ganadores
                        if (lbGanador.get(i) == 1) {
                            if (jugadaG.get(i).equals(numeros[0]+ "-" + numeros[1]+"-"+numeros[2])) {
                                if (numerosLimitadoLista.get(6).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(6);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(6);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }

                        } else {
                            if (jugadaG.get(i).equals(numeros[0]+ "-" + numeros[1]+"-"+numeros[2])) {
                                if (numerosLimitadoBote.get(6).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(6);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(6);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }
                        }
                    }
                    else if (apuestaG.get(i).equals("Candado")) {
                      
                         // el candado es cuando es una apuesta parlet >= 3 numeros
                        int contNum = 0;
                        String num = jugadaG.get(i);
                        for (int k = 0; k < num.length(); k++) {
                            if (num.charAt(k) == '-') {
                                contNum += 1;
                            }
                        }
                        if (jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[1]) && jugadaG.get(i).contains(numeros[2])
                                || (jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[1])
                                || jugadaG.get(i).contains(numeros[1]) && jugadaG.get(i).contains(numeros[2]) ||
                                jugadaG.get(i).contains(numeros[0].substring(1, 3)) && jugadaG.get(i).contains(numeros[2]))) {
                            if (lbGanador.get(i) == 1) {
                                if (contNum == 1) {
                                    ganadorL += dineroGanador.get(i) * (preciosLista.get(2));
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else if (contNum == 2) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/3 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/3 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/3)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/3)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 3) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/6 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/6 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/6 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/6 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/6)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/6)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 4) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/10 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/10 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/10 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/10 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/10)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/10)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 5) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/15 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/15 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/15 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/15 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/15)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/15)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 6) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/21 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/21 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/21 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/21 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/21)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/21)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 7) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/28 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/28 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/28 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/28 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/28)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/28)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 8) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/36 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/36 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/36 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/36 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/36)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/36)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 9) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/45 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/45 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/45 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/45 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/45)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/45)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 10) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/55 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/55 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/55 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/55 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/55)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/55)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 11) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/66 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) );
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/66 : dineroGanador.get(i) * (preciosLista.get(2) );
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/66 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/66 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/66)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/66)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 12) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/78 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/78 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/78 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/78 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/78)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/78)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 13) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/91 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/91 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/91 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/91 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/91)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/91)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 14) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105 :dineroGanador.get(i) * (preciosLimitadosLista.get(2));
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/105 : dineroGanador.get(i) * (preciosLista.get(2));
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/105 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/105)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                } else if (contNum == 15) {
                                    if (cantNumLista(jugadaG, numeros, i) == 1) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 1)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) );
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/105 : dineroGanador.get(i) * (preciosLista.get(2) );
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 2)
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 2);
                                        else
                                            ganadorL += tipoParlet ? (dineroGanador.get(i) * (preciosLista.get(2)))/105 : dineroGanador.get(i) * (preciosLista.get(2) * 2);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                        if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoLista.get(2)) == 3)
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLimitadosLista.get(2)))/105)*3 :dineroGanador.get(i) * (preciosLimitadosLista.get(2) * 3);
                                        else
                                            ganadorL += tipoParlet ? ((dineroGanador.get(i) * (preciosLista.get(2)))/105)*3 : dineroGanador.get(i) * (preciosLista.get(2) * 3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    }
                                }

                            } else {
                                if (lbGanador.get(i) == 0) {
                                    if (contNum == 1) {
                                        ganadorB += dineroGanador.get(i) * (preciosBote.get(2));
                                        ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                    } else if (contNum == 2) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2));
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/3 : dineroGanador.get(i) * (preciosBote.get(2));
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? dineroGanador.get(i) * (preciosLimitadosBote.get(2))/3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? dineroGanador.get(i) * (preciosBote.get(2))/3 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/3)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/3)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 3) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/6 : dineroGanador.get(i) * (preciosLimitadosBote.get(2));
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/6 : dineroGanador.get(i) * (preciosBote.get(2));
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/6 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/6 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/6)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/6)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 4) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/10 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/10 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/10 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/10 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/10)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/10)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 5) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/15 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/15 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/15 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/15 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/15)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/15)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 6) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/21 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/21 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/21 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/21 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/21)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/21)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 7) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/28 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/28 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/28 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/28 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/28)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/28)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 8) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/36 : dineroGanador.get(i) * (preciosLimitadosBote.get(2));
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/36 : dineroGanador.get(i) * (preciosBote.get(2));
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/36 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/36 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/36)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/36)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 9) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/45 : dineroGanador.get(i) * (preciosLimitadosBote.get(2));
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/45 : dineroGanador.get(i) * (preciosBote.get(2));
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/45 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/45 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/45)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/45)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 10) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/55 : dineroGanador.get(i) * (preciosLimitadosBote.get(2));
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/55 : dineroGanador.get(i) * (preciosBote.get(2));
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/55 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/55 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/55)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/55)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 11) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/66 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/66 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/66 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/66 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/66)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/66)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 12) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/78 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/78 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/78 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/78 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/78)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/78)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 13) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/91 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/91 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/91 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/91 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/91)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/91)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 14) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/105 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/105 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/105 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/105 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/105)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/105)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    } else if (contNum == 15) {
                                        if (cantNumLista(jugadaG, numeros, i) == 1) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 1)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/105 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) );
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/105 : dineroGanador.get(i) * (preciosBote.get(2) );
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 2) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 2)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2)))/105 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 2);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2)))/105 : dineroGanador.get(i) * (preciosBote.get(2) * 2);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        } else if (cantNumLista(jugadaG, numeros, i) == 3) {
                                            if(cantidadLimitadosCandadoV1(jugadaG,numeros,numerosLimitadoBote.get(2)) == 3)
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosLimitadosBote.get(2))/105)*3 : dineroGanador.get(i) * (preciosLimitadosBote.get(2) * 3);
                                            else
                                                ganadorB += tipoParlet ? (dineroGanador.get(i) * (preciosBote.get(2))/105)*3 : dineroGanador.get(i) * (preciosBote.get(2) * 3);
                                            ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                        }
                                    }
                                }
                            }

                        }
                        
                    } 
                    else if (apuestaG.get(i).equals("Pik")) {
                        if (lbGanador.get(i) == 1) // pago por la lista
                        {
                            if (jugadaG.get(i).equals(numeros[0].substring(0, 1))) {

                                if (numerosLimitadoLista.get(5).contains(jugadaG.get(i).toString())) {
                                    ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(5);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorL += dineroGanador.get(i) * preciosLista.get(5);
                                    ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }


                            }
                        } else // pago por el bote
                        {
                            if (jugadaG.get(i).equals(numeros[0].substring(0, 1))) {
                                if (numerosLimitadoBote.get(5).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(5);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(5);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }
                        }
                    } else {
                        // fijarme la centena que salio
                        System.out.println("Entro al ganador centana ----------------------------------------------------------");
                        if (lbGanador.get(i) == 1) // pago por la lista
                        {
                            if (jugadaG.get(i).equals(numeros[0])) {
                                if (numerosLimitadoLista.get(3).contains(jugadaG.get(i).toString())) {
                                        ganadorL += dineroGanador.get(i) * preciosLimitadosLista.get(3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }
                                else
                                {
                                        ganadorL += dineroGanador.get(i) * preciosLista.get(3);
                                        ganadoresLista += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }

                            }
                        } else // pago por el bote
                        {
                            if (jugadaG.get(i).equals(numeros[0])) {
                                if (numerosLimitadoBote.get(3).contains(jugadaG.get(i).toString())) {
                                    ganadorB += dineroGanador.get(i) * preciosLimitadosBote.get(3);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                } else {
                                    ganadorB += dineroGanador.get(i) * preciosBote.get(3);
                                    ganadoresBote += "" + jugadaG.get(i).toString() + "," + apuestaG.get(i).toString() + "," + dineroGanador.get(i).toString() + ";";
                                }
                            }
                        }
                    }

                }

            }
        } catch (Exception e) {
            //Toast.makeText(getBaseContext(),"Inserte bien los números del tiro", Toast.LENGTH_LONG).show();
        }

        result[0] = ganadorB;
        result[1] = ganadorL;


        return result;
    }

   
    /**
    * Metodo para escribir el reporte.html en la sd
    * @param html Contenido html que va a ser escrito en el fichero
    * */

    public void generarReporteHtml(String html)
    {

            try
            {
               File f = new File(dir.get().getPath()+"/ReporteCompleto/", "index.html");

                OutputStreamWriter fout =
                        new OutputStreamWriter(
                                new FileOutputStream(f));

                fout.write(html);
                fout.close();

              //  Log.i("Ficheros", "Fichero SD creado!");
            }
            catch (Exception ex)
            {
                //Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            }
        

//        Toast.makeText(getBaseContext(), "Reporte Creado Correctamente",Toast.LENGTH_LONG).show();
    }


    /**
     * Metodo que devuelve el reporte html
     * @param filasTablaLista Contenido html que genera las filas de la tabla de las Listas
     * @param totalFilaTablaLista Contenido html que genera los totales de la tabla Listas
     * @param filasTablaBote Contenido html que genera las filas de la tabla del Bote
     * @param totalFilaTablaBote Contenido html que genera os totales de la tabla Bote
     *
     * */

  public String getHtmlReporte(String filasTablaLista, String totalFilaTablaLista, String filasTablaBote, String totalFilaTablaBote)
  {
    String html = "<html>\n    <header>\n        <style>\n#contenedor {\n    margin: 40px auto;\n    width: 750px;  /* Ancho del contenedor */\nbox-sizing: border-box;\n-moz-box-sizing: border-box;\n}\n\n#contenedor input {\nheight: 32px;\nvisibility: hidden;\n}\n\n#contenedor label {\nfloat: left;\ncursor: pointer;\nfont-size: 15px;  /* Tama�o del texto de las pesta�as */\nline-height: 40px;\nheight: 40px;\npadding: 0 20px;\ndisplay: block;\ncolor: #888;  /* Color del texto de las pesta�as */\ntext-align: center;\nborder-radius: 5px 5px 0 0;\nbackground: #eee;  /* Fondo de las pesta�as */\nmargin-right: 5px;\n}\n\n#contenedor input:hover + label {\nbackground: #ddd;  /* Fondo de las pesta�as al pasar el cursor por encima */\ncolor: #666;  /* Color del texto de las pesta�as al pasar el cursor por encima */\n}\n\n#contenedor input:checked + label {\nbackground: #FFDA91;  /* Fondo de las pesta�as al presionar */\ncolor: #444; /* Color de las pesta�as al presionar */\nz-index: 6;\nline-height: 45px;\nheight: 45px;\nposition: relative;\ntop: -5px;\n-webkit-transition: .1s;\n-moz-transition: .1s;\n-o-transition: .1s;\n-ms-transition: .1s;\n}\n\n.content {\nbackground: #FFDA91;  /* Fondo del contenido */\nposition: relative;\nwidth: auto;\nheight: auto;  /* Alto del contenido */\n/*padding: 10px;*/\nz-index: 5;\nborder-radius: 0 5px 5px 5px;\n}\n\n.content div {\nposition: absolute;\nz-index: -100;\nopacity: 0;\ntransition: all linear 0.1s;\n}\n\n#contenedor input.tab-selector-1:checked ~ .content .content-1,\n#contenedor input.tab-selector-2:checked ~ .content .content-2,\n#contenedor input.tab-selector-3:checked ~ .content .content-3,\n#contenedor input.tab-selector-4:checked ~ .content .content-4 {\n    z-index: 100;\n    opacity: 1;\n    -webkit-transition: all ease-out 0.2s 0.1s;\n-moz-transition: all ease-out 0.2s 0.1s;\n-o-transition: all ease-out 0.2s 0.1s;\n-ms-transition: all ease-out 0.2s 0.1s;\n}\n        </style>\n    </header>\n<div style=\"text-align: center\"><strong>Nota:</strong> A continuación se muestran los resultados de la lista y el bote generados automaticamente para la aplicación Lotto-Desktop 1.2.9 Beta.</div>\n<div id=\"contenedor\">\n    <input id=\"tab-1\" type=\"radio\" name=\"radio-set\" class=\"tab-selector-1\" checked=\"checked\" />\n    <label for=\"tab-1\" class=\"tab-label-1\">Bote</label>\n    \n    <input id=\"tab-2\" type=\"radio\" name=\"radio-set\" class=\"tab-selector-2\" />\n    <label for=\"tab-2\" class=\"tab-label-2\">Lista</label>\n                            \n    <div class=\"content\">\n        <div class=\"content-1\">\n            <table id=\"content-1\" class=\"content\">\n  <tr>\n    <th scope=\"col\" style=\"padding-right: 30px\">Lista</th>\n    <th scope=\"col\" style=\"padding-right: 30px\">Bruto</th>\n    <th scope=\"col\" style=\"padding-right: 30px\">Limpio</th>\n    <th scope=\"col\" style=\"padding-right: 30px\">Premio</th>\n    <th scope=\"col\" style=\"padding-right: 30px\">Gane</th>\n    <th scope=\"col\" style=\"padding-right: 30px\">Pierde</th>\n  </tr>\n \n" + filasTablaBote + "  \n" + " <tr>\n" + totalFilaTablaBote + "</table>\n" + "        </div>\n" + "        <div class=\"content-2\">\n" + "           <table id=\"content-1\" class=\"content\">\n" + "  <tr>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Lista</th>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Bruto</th>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Limpio</th>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Premio</th>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Gane</th>\n" + "    <th scope=\"col\" style=\"padding-right: 30px\">Pierde</th>\n" + "  </tr>\n" + " \n" + "  <tr>\n" + filasTablaLista + "      \n" + " <tr>\n" + totalFilaTablaLista + "\n" + "</table>\n" + "        </div>\n" + "     \n" + "    </div>\n" + "</div>\n" + "</html>";
    
    return html;
  }
//    public ArrayList<String> jugadaLimpia(ArrayList<String> jugada, ArrayList<Double> apuesta, ArrayList<String> tipoJugada)
//    {
//        ArrayList<String> jugadaLimpia = new ArrayList<String>();
//
//            for (int i =0; i<jugada.size();i++)
//            {
//
//                if(tipoJugada.get(i).equals("Candado") || tipoJugada.get(i).equals("Corrido") || tipoJugada.get(i).equals("Parlet"))
//                {
//                    String cadena = "--" + "("+String.valueOf(apuesta.get(i)).replace(".0","")+")";
//                    jugadaLimpia.add(jugada.get(i).replace(cadena,""));
//                }
//                else
//                {
//                    jugadaLimpia.add(jugada.get(i).replace("-(" + apuesta.get(i)+")",""));
//                }
//
//            }
//
//        return jugadaLimpia;
//
//    }

     public ArrayList<String> jugadaLimpia(ArrayList<String> jugada, ArrayList<Double> apuesta, ArrayList<String> tipoJugada)
    {
        ArrayList<String> jugadaLimpia = new ArrayList<String>();

            for (int i =0; i<jugada.size();i++)
            {

                if(tipoJugada.get(i).equals("Corrido"))
                {
                    String cadena = "-X" + "("+String.valueOf(apuesta.get(i)).replace(".0","")+")";
                    jugadaLimpia.add(jugada.get(i).replace(cadena,""));
                }
                else if(tipoJugada.get(i).equals("Candado"))
                {
                    String cadena = "-("+String.valueOf(apuesta.get(i)).replace(".0","")+")";
                    jugadaLimpia.add(jugada.get(i).replace(cadena,""));
                }
                else
                {
                    jugadaLimpia.add(jugada.get(i).replace("-("/* +String.valueOf(apuesta.get(i)).replace(".0","")+*/ +apuesta.get(i)+")",""));
                }

            }

        return jugadaLimpia;

    }

    public String leerFicheroTexto(ArrayList<String> ficheros )
    {
        String texto ="";

        for(int i = 0; i<ficheros.size(); i++)
        {
            try
            {

                File f = new File(ficheros.get(i).toString());

                BufferedReader fin =
                        new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(f)));

                texto = fin.readLine();

                //Log.i("Ficheros", "Fichero SD leido!");
                ///Log.i("Ficheros", "Texto: " + texto);
            }
            catch (Exception ex)
            {
                //Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
            }
        }

        return texto;
    }

    
    public String replaceCharacter(String cad)
    {
        String ncad="";
        int cont=0;
        for(int i=0; i<cad.length();i++)
        {
            if(cad.charAt(i)=='-'||cad.charAt(i)=='X')
            {
             cont = i;
            }
        }

        ncad = cad.substring(0, cont)+"_"+cad.substring(cont+1,cad.length());
        return ncad;
    }

    public ArrayList<String> jugadaPicad(String [] jugada)
    {
        ArrayList<String> picada= new ArrayList<>();

        for(int i=0; i<jugada.length;i++)
        {
            if(jugada[i].equals("(") || jugada[i].equals(".")|| jugada[i].equals("")
                    || jugada[i].contains("(") || jugada[i].contains(".")/*|| jugada[i].contains("")*/)
            {
                System.out.println("");
            }
            else
            {
                picada.add(jugada[i]);
            }



        }

        return picada;
    }

   public int cantNumLista(ArrayList<String> picada, String [] num, int i)
    {
        int cantidad = 0, pos1 = 0, pos2 =0, pos3=0;
        boolean f1 = false, f2 = false, f3 = false;

        String []numP = picada.get(i).split("-");

        for (int k =0; k< numP.length; k++)
        {
            if (numP[k].equals(num[0].substring(1,3)))
            {
                f1 = true;
                pos1 = k;
                break;
            }
        }
        for (int k =0; k< numP.length; k++)
        {
            if (numP[k].equals(num[1]) && k!= pos1)
            {
                f2 = true;
                pos2 = k;
                break;
            }
        }
        for (int k =0; k< numP.length; k++)
        {
            if (numP[k].equals(num[2]) && k!= pos1 && k!=pos2)
            {
                f3 = true;
                pos3 = k;
                break;
            }
        }

        /*
        * Ver si en la jugada hay algun numero de los que salio doble
        * */
        int cont =0, cont1=0, cont2=0;
        for(int k =0; k< numP.length; k++)
        {
            if (numP[k].equals(num[0].substring(1,3)))
            {
                cont++;
            }
            if (numP[k].equals(num[1]))
            {
                cont1++;
            }
            if (numP[k].equals(num[2]))
            {
                cont2++;
            }
        }

        if((f1&&f2&&f3)&&(pos1!=pos2 && pos1!=pos3 && pos2!=pos3))
            cantidad = 3;
        else if((cont > 1 && cont1 == 1) || (cont > 1 && cont2 == 1) || (cont1 > 1 && cont2 == 1) || (cont1 > 1 && cont == 1)
                || (cont2 > 1 && cont == 1) || (cont2 > 1 && cont1 == 1))
            cantidad = 2;
        else
            cantidad = 1;

        return cantidad;
    }

    public String [] loadConfiguration(String ruta)
    {
        String [] config = null;
        
        String texto ="";

            try
            {

                File f = new File(ruta);

                BufferedReader fin =
                        new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(f)));

                texto = fin.readLine();

                //Log.i("Ficheros", "Fichero SD leido!");
                ///Log.i("Ficheros", "Texto: " + texto);
            }
            catch (Exception ex)
            {
                //Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
            }
            
            config = texto.split(";");
           
        return config;
    
    }
    
      public int cantidadLimitadosCandadoV1(ArrayList<String> jugada, String[] num ,String limitados)
    {
        int cont =0;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> jugadaG = new ArrayList<>();

        String[] split = jugada.get(0).split("-");

        for(int i=0; i<split.length;i++)
        {
            jugadaG.add(split[i]);
        }

        if (jugadaG.size() == 2) {
            result.add(jugadaG.get(0) + "-" + jugadaG.get(1));
        } else {
            for (int i = 0; i < jugadaG.size(); i++) {
                for (int j = 0; j < jugadaG.size(); j++) {
                    if (i != j) {
                        result.add(jugadaG.get(i) + "-" + jugadaG.get(j));
                        System.out.println(jugadaG.get(i) + "-" + jugadaG.get(j));
                    }
                }
            }
        }

        String tiro = num[0].substring(1,3)+"-"+num[1]+","+
                num[0].substring(1,3)+"-"+num[2]+","+
                num[1]+"-"+num[2]+","+
                num[1]+"-"+num[0].substring(1,3)+","+
                num[2]+"-"+num[0].substring(1,3)+","+
                num[2]+"-"+num[1];

        for(int i=0; i<result.size();i++)
        {
            if (limitados.contains(result.get(i)) && tiro.contains(result.get(i))) {
                cont++;
            }
        }
        return cont;
    }


    













}

