/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.sdev.loto;

import java.io.File;
import java.net.URL;

/**
 *
 * @author Yaisel
 */
public class Directorio {

    private static File WORKING_DIRECTORY;

    public static File get() {
        String Recurso = Directorio.class.getSimpleName() + ".class";
        if (WORKING_DIRECTORY == null) {
            try {
                URL url = Directorio.class.getResource(Recurso);
                if (url.getProtocol().equals("file")) {
                    File f = new File(url.toURI());
                    do {
                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    WORKING_DIRECTORY = f;
                } else if (url.getProtocol().equals("jar")) {
                    String expected = "!/" + Recurso;
                    String s = url.toString();
                    s = s.substring(4);
                    s = s.substring(0, s.length() - expected.length());
                    File f = new File(new URL(s).toURI());
                    do {
                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    WORKING_DIRECTORY = f;
                }
            } catch (Exception e) {
                WORKING_DIRECTORY = new File(".");
            }
        }
        return WORKING_DIRECTORY;
    }
}
