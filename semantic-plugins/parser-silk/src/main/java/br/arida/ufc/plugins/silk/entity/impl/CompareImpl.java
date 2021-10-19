package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.Compare;
import br.arida.ufc.plugins.silk.entity.Input;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class CompareImpl extends SilkFieldImpl implements Compare {
    private static final java.util.logging.Logger log = Logger.getLogger(CompareImpl.class.getName());

    private List<Input> _inputs;
    private String _metric;

    public CompareImpl(Element compare) {
        super(compare);
        initialize(compare);
    }

    protected void initialize(Element compare) {
        Element input;
        this._inputs = new ArrayList<Input>();
        this.setMetric(compare.getAttributes().getNamedItem("metric").getNodeValue());

        for(int i = 0; i < compare.getElementsByTagName(SilkTerms.INPUT).getLength(); i++) {
            input = (Element) compare.getElementsByTagName(SilkTerms.INPUT).item(i);
            this._inputs.add(new InputImpl(input));
        }
    }

    public String getMetric() {
        return this._metric;
    }

    public void setMetric(String metric) {
        this._metric = metric;
    }

    public Input[] getInputList() {
        return _inputs.toArray(new Input[_inputs.size()]);
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t\t\t"+"<Compare metric=\""+this.getMetric()+"\">" + '\n';
        for(int i = 0; i < this.getInputList().length; i++) {
            str += this.getInputList()[i].toXML();
        }
        str += "\t\t\t\t\t"+"</Compare>" + '\n';
        return str;
    }

    /*<LinkageRule> <!-- Por enquanto, só serão comparados uma propriedade-->
                <Aggregate type="max"> <!-- default-->
                    <Compare metric="equality">
                        <Input path="?a/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_T para fazer o match-->
                        <Input path="?b/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_U para fazer o match-->
                    </Compare>
                </Aggregate>
            </LinkageRule>*/
}
