package br.arida.ufc.plugins.silk.entity.impl;

import br.arida.ufc.plugins.silk.entity.Interlink;
import br.arida.ufc.plugins.silk.entity.LinkageRule;
import br.arida.ufc.plugins.silk.entity.SourceDataset;
import br.arida.ufc.plugins.silk.entity.TargetDataset;
import br.arida.ufc.plugins.silk.impl.SilkLayoutImpl;
import br.arida.ufc.plugins.silk.SilkTerms;
import org.w3c.dom.Element;

import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class InterlinkImpl extends SilkFieldImpl implements Interlink {
    private static final java.util.logging.Logger log = Logger.getLogger(InterlinkImpl.class.getName());

    private String _id;
    private LinkageRule _linkage_rule;
    private SourceDataset _source_dataset;
    private TargetDataset _target_dataset;

    public InterlinkImpl(Element interlink) {
        super(interlink);
        initialize(interlink);
    }

    //Recebe o elemento Interlink no arquivo XML Silk
    protected void initialize(Element interlink) {
        Element source_dataset, target_dataset, linkage_rule;
        //set Id
        log.info("Adicionando Tag SourceDataset. . .");
        source_dataset = (Element) interlink.getElementsByTagName(SilkTerms.SOURCE_DATASET_NODE).item(0);

        log.info("Adicionando Tag TargetDataset. . .");
        target_dataset = (Element) interlink.getElementsByTagName(SilkTerms.TARGET_DATASET_NODE).item(0);

        log.info("Adicionando Tag LinkageRule. . .");
        linkage_rule = (Element) interlink.getElementsByTagName(SilkTerms.LINKAGE_RULE).item(0);

        this.setId(interlink.getAttributes().getNamedItem("id").getNodeValue());
        this._source_dataset = new SourceDatasetImpl(source_dataset);
        this._target_dataset = new TargetDatasetImpl(target_dataset);
        this._linkage_rule = new LinkageRuleImpl(linkage_rule);
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public LinkageRule getLinkageRule() {
        return this._linkage_rule;
    }

    public SourceDataset getSourceDataset() {
        return this._source_dataset;
    }

    public void setSourceDataset(SourceDataset source_dataset) {
        this._source_dataset = source_dataset;
    }

    public TargetDataset getTargetDataset() {
        return this._target_dataset;
    }

    public void setTargetDataset(TargetDataset target_dataset) {

    }

    public String toXML() {
        String str = "";

        str += "\t\t"+"<Interlink id=\""+this.getId()+"\">" + '\n' +
                  "\t\t\t"+"<LinkType>owl:sameAs</LinkType>"+'\n' +
                    this.getSourceDataset().toXML()+'\n' +
                    this.getTargetDataset().toXML()+'\n' +
                    this.getLinkageRule().toXML()  +'\n' +
                "\t\t"+"</Interlink>";

        return str;
    }
    /*
    <LinkType>owl:sameAs</LinkType> <!-- Tipo LinksetView-->
<SourceDataset dataSource="" var="a"> <!-- Exported View-->
                <RestrictTo>?a rdf:type ex:ClasseSource_EV1</RestrictTo>  <!--Classe source da Exported View 1-->
            </SourceDataset> <!--Essa classe, Cs, é uma classe na ontologia Source de EV1 que tem mapeamento para Ct (Target)-->

            <TargetDataset dataSource="" var="b"> <!-- Exported View-->
                <RestrictTo>?b rdf:type ex:ClassSource_EV2</RestrictTo> <!-- Classe source da Exported View 2-->
            </TargetDataset>

            <LinkageRule> <!-- Por enquanto, só serão comparados uma propriedade-->
                <Aggregate type="max"> <!-- default-->
                    <Compare metric="equality">
                        <Input path="?a/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_T para fazer o match-->
                        <Input path="?b/smwprop:KeggDiseaseId" /> <!-- Propriedade EV_U para fazer o match-->
                    </Compare>
                </Aggregate>
            </LinkageRule>*/
}
