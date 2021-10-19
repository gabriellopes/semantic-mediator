package br.arida.ufc.core.util;


import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 09/01/2017.
 */
public class Log4jHelper {

    public static void disableLogs() {
        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getLogger("br.arida.ufc").setLevel(Level.OFF);
    }
}
