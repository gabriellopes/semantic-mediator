package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.entity.Fusion;
import br.arida.ufc.plugins.sieve.entity.FusionClass;
import br.arida.ufc.plugins.sieve.entity.FusionFunction;
import br.arida.ufc.plugins.sieve.entity.FusionProperty;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class FusionImpl extends SieveFieldImpl implements Fusion {
    private List<FusionClass> _classes;

    public FusionImpl(Element fusion) {
        super(fusion);
        _classes = new ArrayList<FusionClass>();

//        initialize(fusion);
    }

    protected void initialize(Element fusion) {
        Element f_class1,f_class2;

        /*f_class1 = (Element) fusion.getElementsByTagName(SieveTerms.FUSION_CLASS).item(0);
        f_class2 = (Element) fusion.getElementsByTagName(SieveTerms.FUSION_CLASS).item(1);

        _classes.add(new FusionClassImpl(f_class1));
        _classes.add(new FusionClassImpl(f_class2));*/

    }

    public FusionClass[] getClasses() {
        return new FusionClass[0];
    }

    public void addClass(Object _class, Object _prop, Object _function) {
        FusionFunction function = new FusionFunctionImpl(_function.toString(),"");
        FusionProperty prop = new FusionPropertyImpl(_prop.toString(),function);
        FusionClass f = new FusionClassImpl(_class.toString(),prop);

        _classes.add(f);
    }

    public String toXML() {
        String str = "";

        str += "\t" + "<Fusion>" + '\n';
        for(FusionClass fc : _classes) {
            str += fc.toXML() + '\n';
        }
        str += "\t" + "</Fusion>";

        return str;
    }
}
