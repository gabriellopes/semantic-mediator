package br.arida.ufc.plugins.sieve.entity.impl;

import br.arida.ufc.plugins.sieve.SieveTerms;
import br.arida.ufc.plugins.sieve.entity.*;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public class SieveImpl extends SieveFieldImpl implements Sieve {
    private QualityAssessment _quality;
    private Fusion _fusion;
    private List<Prefix> _prefixes;

    public SieveImpl(Element sieve) {
        super(sieve);
        initialize(sieve);
    }

    protected void initialize(Element sieve) {
        Element quality, fusion, prefixes;
        NodeList n_prefixes;
        _prefixes = new ArrayList<Prefix>();

        quality = (Element) sieve.getElementsByTagName(SieveTerms.QUALITY_ASSESSMENT).item(0);
        fusion = (Element) sieve.getElementsByTagName(SieveTerms.FUSION).item(0);
        prefixes = (Element) sieve.getElementsByTagName(SieveTerms.PREFIXES).item(0);
        n_prefixes = prefixes.getElementsByTagName(SieveTerms.PREFIX);

        for(int i = 0; i < n_prefixes.getLength(); i++) {
            this._prefixes.add(new PrefixImpl((Element)n_prefixes.item(i)));
        }
        this._quality = new QualityAssessmentImpl(quality);
        this._fusion = new FusionImpl(fusion);
    }

    public String toXML() {
        String str = "";

        str += "<Sieve>" + '\n' +
                '\t' + "<Prefixes>" + '\n';
        for(Prefix p : _prefixes) {
            str += p.toXML();
        }
        str +=  '\t' + "</Prefixes>" + '\n' +
                _quality.toXML()     + '\n' +
                _fusion.toXML()      + '\n' +
                "</Sieve>";

        return str;
    }

    public QualityAssessment getQualityAssessment() {
        return this._quality;
    }

    public Fusion getFusion() {
        return null;
    }

    public Prefix[] getPrefixes() {
        return new Prefix[0];
    }

    public void setPrefixes(List<Prefix> prefixes) {
        this._prefixes = prefixes;
    }

    public void addClass(Object _class, Object property, Object function) {
        _fusion.addClass(_class,property,function);
    }
}
