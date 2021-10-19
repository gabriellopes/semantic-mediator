package br.arida.ufc.fusion.impl;


import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.fusion.FusionAssertion;
import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 13/01/2017.
 */
public class FusionAssertionTest extends TestCase {

    public void testRead() throws Exception {
        String str = "FPA: moa:labelName[mo:Record] = KeepSingleValueByReputation/moa:labelName";
        FusionAssertion f = new FusionAssertionImpl(str);
    }

    public void testSpec() throws Exception {
        File f = FileUtils.getFileFromResource(this.getClass(),"persons.fpa");
        String fpa = FileUtils.readFile(f);
        List<FusionAssertion> f_spec = new ArrayList<FusionAssertion>();

        String[] set_fpa = fpa.split("\n");

        for(String fp : set_fpa) {
            f_spec.add(new FusionAssertionImpl(fp));
        }
    }
}
