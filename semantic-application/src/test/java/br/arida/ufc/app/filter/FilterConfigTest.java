package br.arida.ufc.app.filter;

import br.arida.ufc.core.entity.impl.FilterConfigImpl;
import br.arida.ufc.core.util.FileUtils;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by gabriellopes9102 on 13/12/2016.
 */
public class FilterConfigTest extends TestCase {

    public void testConfig() throws Exception {
        File filter = FileUtils.getFileFromResource(this.getClass(),"filter.txt");
        FilterConfigImpl f = new FilterConfigImpl(filter);

    }
}
