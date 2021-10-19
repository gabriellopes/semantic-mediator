package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.SilkTerms;
import br.arida.ufc.plugins.silk.entity.Aggregate;
import br.arida.ufc.plugins.silk.entity.Compare;
import br.arida.ufc.plugins.silk.entity.LinkageRule;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class LinkageRuleImpl extends SilkFieldImpl implements LinkageRule {
    private static final java.util.logging.Logger log = Logger.getLogger(LinkageRuleImpl.class.getName());

    private Aggregate _aggregate;

    public LinkageRuleImpl(Element linkage_rule) {
        super(linkage_rule);
        initialize(linkage_rule);
    }

    protected void initialize(Element linkage_rule) {
        Element aggregate, compare;

        aggregate = (Element) linkage_rule.getElementsByTagName(SilkTerms.AGGREGATE).item(0);

        this._aggregate = new AggregateImpl(aggregate);
    }


    public Aggregate getAggregate() {
        return this._aggregate;
    }

    public String toXML() {
        String str = "";

        str += "\t\t\t"+"<LinkageRule>" +'\n'+
                this.getAggregate().toXML() +
                "\t\t\t"+"</LinkageRule>";

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
