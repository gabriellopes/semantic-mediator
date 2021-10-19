package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.Aggregate;
import br.arida.ufc.plugins.silk.entity.Compare;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class AggregateImpl extends SilkFieldImpl implements Aggregate{
    private static final java.util.logging.Logger log = Logger.getLogger(AggregateImpl.class.getName());

    private String _type;
    private Compare _compare;

    public AggregateImpl(Element aggregate) {
        super(aggregate);
        initialize(aggregate);
    }

    protected void initialize(Element aggregate) {
        Element compare;
        compare = (Element) aggregate.getElementsByTagName(SilkTerms.COMPARE).item(0);

        this._compare = new CompareImpl(compare);
    }

    public String getType() {
        return this._type;
    }

    public void setType(String type) {
        this._type = type;
    }

    public Compare getCompare() {
        return this._compare;
    }


    public String toXML() {
        String str = "";

        str += "\t\t\t\t"+"<Aggregate type=\""+this.getType()+"\">" + '\n' +
                    this.getCompare().toXML() +
                "\t\t\t\t"+"</Aggregate>" + '\n';
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
