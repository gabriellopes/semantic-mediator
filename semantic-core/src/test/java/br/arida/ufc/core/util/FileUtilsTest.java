package br.arida.ufc.core.util;

import br.arida.ufc.core.util.FileUtils;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by gabriellopes9102 on 30/11/2016.
 */
public class FileUtilsTest extends TestCase {

    public void testListFiles() throws Exception {
        File[] filesXML = FileUtils.listFiles(new File("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework\\example"),"xml");

        assertEquals(1,filesXML.length);
    }
}
