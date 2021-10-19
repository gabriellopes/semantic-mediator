package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.Interlink;
import br.arida.ufc.plugins.silk.entity.Prefix;
import br.arida.ufc.plugins.silk.entity.Silk;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gabriellopes9102 on 12/01/2017.
 */
public class SilkImpl extends SilkFieldImpl implements Silk {
    private List<Prefix> _prefixes;
    private List<Interlink> _interlinks;

    public SilkImpl(Element silk_field) {
        super(silk_field);
        initialize(silk_field);
    }

    protected void initialize(Element silk) {
        Element prefixes, interlinks;
        NodeList n_prefixes, n_interlinks;
        _prefixes = new ArrayList<Prefix>();
        _interlinks = new ArrayList<Interlink>();

        prefixes = (Element) silk.getElementsByTagName(SilkTerms.PREFIXES).item(0);
        interlinks = (Element) silk.getElementsByTagName(SilkTerms.INTERLINKS).item(0);

        n_prefixes = prefixes.getElementsByTagName(SilkTerms.PREFIX);
        n_interlinks = interlinks.getElementsByTagName(SilkTerms.INTERLINK);

        for(int i = 0; i < n_prefixes.getLength(); i++) {
            this._prefixes.add(new PrefixImpl((Element)n_prefixes.item(i)));
        }
        for(int i = 0; i < n_interlinks.getLength(); i++) {
            this._interlinks.add(new InterlinkImpl((Element)n_interlinks.item(i)));
        }
    }

    public String toXML() {
        String str = "";
        str += "<Silk>" + '\n' +
                '\t' + "<Prefixes>"   + '\n';
                for(Prefix p : _prefixes) {
                    str += p.toXML() + '\n';
                }

        str +=  '\t' + "</Prefixes>" + '\n' +
                '\t' + "<Interlinks>" + '\n';

               for(Interlink i : _interlinks) {
                   str += i.toXML() + '\n';
               }
        str += '\t' + "</Interlinks>" + '\n' +
               "</Silk>";

        return str;
    }

    public Prefix[] getPrefixes() {
        return _prefixes.toArray(new Prefix[_prefixes.size()]);
    }

    public void setPrefixes(Map<String, String> prefixes) {
        _prefixes = new ArrayList<Prefix>();
        for(String key : prefixes.keySet()) {
            String uri = prefixes.get(key);

            _prefixes.add(new PrefixImpl(key,uri));
        }
    }
    public Interlink[] getInterlinks() {
        return _interlinks.toArray(new Interlink[_interlinks.size()]);
    }
}
