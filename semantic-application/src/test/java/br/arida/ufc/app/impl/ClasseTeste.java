package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationConfig;
import br.arida.ufc.core.config.impl.MediatorConfig;
import br.arida.ufc.core.util.FileUtils;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by gabriellopes9102 on 10/01/2017.
 */
public class ClasseTeste extends TestCase {
    
    public void testCreateApplication() throws Exception {
        String path = "C:\\Teste12345\\";

        File tmp = new File(path);
        boolean result = false;

        if(!tmp.exists()) {
            result = tmp.mkdir();
        }

    }
}
