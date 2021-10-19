package br.arida.ufc.plugins.silk.impl;

import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.plugins.silk.SilkLayout;
import br.arida.ufc.plugins.silk.SilkMaterializer;
import br.arida.ufc.plugins.silk.entity.Interlink;
import br.arida.ufc.plugins.silk.entity.Prefix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

/*
* package br.arida.ufc.plugins.silk.impl;

import br.arida.ufc.core.step.Materializer;
import br.arida.ufc.core.step.MashupStepMaterializer;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.core.util.XMLReader;
import br.arida.ufc.linksetview.LinksetViewMaterializer;
import br.arida.ufc.linksetview.LinksetViewSpec;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMtarget;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by gabriellopes9102 on 06/12/2016.


public class SilkMaterializerImpl implements Materializer {
    private File _silk_layout_xml;
    private LinksetViewMaterializerImpl _linkset_materializer;

    public SilkMaterializerImpl(LinksetViewMaterializerImpl linkset_materializer) {
        this._linkset_materializer = linkset_materializer;
        try {
            this._silk_layout_xml = FileUtils.getFileFromResource(this.getClass(), "silkLayout.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void materialize() {
        LinksetViewSpec ls_spec = _linkset_materializer.getLinksetView().getLinksetViewSpec();
        Element root, interlinks, interlink, source_dataset, target_dataset,
                restrictTo_source, restrictTo_target,
                linkage_rule, aggregate, compare, input_T, input_U;
        String var_a, var_b,metric,property;
        /*
        Ler arquivo layout e modificar apenas os nós de acordo com as exported view.
         */

/**
 * Created by gabriellopes9102 on 06/12/2016.
 */

/*
* Método para materializar para o SILK
 */
public class SilkMaterializerImpl implements SilkMaterializer {

    private File _silk_layout_xml;
    private SilkLayout _silk_layout;
//    private String _output_path;

    public SilkMaterializerImpl() {
        try {
            this._silk_layout_xml = FileUtils.getFileFromResource(SilkMaterializerImpl.class,"silkLayout.xml");
            _silk_layout = loadLayout();
//            _output_path = output_path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private SilkLayout loadLayout() {
        return new SilkLayoutImpl(_silk_layout_xml);
    }
    /*
    @param: id_interlink; ev_name_T; ev_name_U; Class_source_T; class_source_T; type_aggregate;
            metric_compare; property_EV_T; property_EV_U
     */
    public void run() {

    }


    public void materialize(String output_path, Map<String,String> prefixes, String id_interlink, String s_dataSource, String s_class_restriction,
                            String t_dataSource, String t_class_restriction, String type_aggregate,
                            String metric, String[] input_props) {


        _silk_layout.getSilk().setPrefixes(prefixes);

        Interlink interlink = _silk_layout.getSilk().getInterlinks()[0];
        interlink.setId(id_interlink);
        //source
        interlink.getSourceDataset().setDataSource(s_dataSource);
        interlink.getSourceDataset().getRestrictTo().setClassRestriction(s_class_restriction);
        //target
        interlink.getTargetDataset().setDataSource(t_dataSource);
        interlink.getTargetDataset().getRestrictTo().setClassRestriction(t_class_restriction);

        interlink.getLinkageRule().getAggregate().setType(type_aggregate);
        interlink.getLinkageRule().getAggregate().getCompare().setMetric(metric);

        interlink.getLinkageRule().getAggregate().getCompare().getInputList()[0].setProperty(input_props[0]);
        interlink.getLinkageRule().getAggregate().getCompare().getInputList()[1].setProperty(input_props[1]);

        //Implementar
        _silk_layout.generateSilkFile(output_path);
    }
}
        /*try {
            root = (Element) new XMLReader().readXML(_silk_layout_xml);
            interlinks = (Element) root.getElementsByTagName("Interlinks").item(0);
            interlink = (Element) interlinks.getElementsByTagName("Interlink").item(0);

            //Pega as Classes Source das EV a partir de uma Classe Target;
            Object class_source_EVT = ls_spec.getExportedView_T().getClassByType(ls_spec.getMatchPredicate().getClassTarget());
            Object class_source_EVU = ls_spec.getExportedView_U().getClassByType(ls_spec.getMatchPredicate().getClassTarget());

            //Adiciona o nome da ExportedView T no SourceDataSet;
            interlink.getAttributes().getNamedItem("id").setNodeValue(ls_spec.getName());
            source_dataset = (Element) interlink.getElementsByTagName(SilkTerms.SOURCE_DATASET_NODE).item(0);
            source_dataset.getAttributes().getNamedItem(SilkTerms.DATA_SOURCE_ATTR).setNodeValue(ls_spec.getExportedView_T().getName());
            var_a = source_dataset.getAttributes().getNamedItem("var").getNodeValue();

            //Monta RestrictTo da ExportedViewT ?+var + rdf:type + class_source
            restrictTo_source = (Element) source_dataset.getElementsByTagName("RestrictTo").item(0);
            restrictTo_source.getFirstChild().setNodeValue("?"+var_a+" rdf:type "+class_source_EVT.toString());

            //Adiciona o nome da ExportedView U no TargetDataSet
            interlink.getAttributes().getNamedItem("id").setNodeValue(ls_spec.getName());
            target_dataset = (Element) interlink.getElementsByTagName(SilkTerms.TARGET_DATASET_NODE).item(0);
            target_dataset.getAttributes().getNamedItem(SilkTerms.DATA_SOURCE_ATTR).setNodeValue(ls_spec.getExportedView_U().getName());
            var_b = target_dataset.getAttributes().getNamedItem("var").getNodeValue();

            //Monta RestrictTo da ExportedViewU ?+var + rdf:type + class_source
            restrictTo_target = (Element) target_dataset.getElementsByTagName("RestrictTo").item(0);
            restrictTo_target.getFirstChild().setNodeValue("?"+var_b+" rdf:type "+class_source_EVU.toString());

            //Montar Cláusula LinkageRule -> Aggregate (default) -> Compare -> Input1 & Input2.
            metric = ls_spec.getMatchPredicate().getMetric();
            property = ls_spec.getMatchPredicate().getProperty();

            linkage_rule = (Element) interlink.getElementsByTagName(SilkTerms.LINKAGE_RULE).item(0);
            aggregate = (Element) linkage_rule.getElementsByTagName(SilkTerms.AGGREGATE).item(0);

            //Definir métrica de comparação
            compare = (Element) aggregate.getElementsByTagName(SilkTerms.COMPARE).item(0);
            compare.getAttributes().getNamedItem("metric").setNodeValue(metric);

            input_T = (Element) compare.getElementsByTagName(SilkTerms.INPUT).item(0); //Input1 para EV_T
            input_U = (Element) compare.getElementsByTagName(SilkTerms.INPUT).item(1);//Input1 para EV_U

            //Montar os paths dos inputs na forma: ?+var + / + property
            input_T.getAttributes().getNamedItem("path").setNodeValue("?"+var_a+"/"+property);
            input_U.getAttributes().getNamedItem("path").setNodeValue("?"+var_b+"/"+property);

            //Monta o arquivo de output com o conteúdo SILK
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(root.getOwnerDocument());
            StreamResult result = new StreamResult(new File("C:\\Users\\gabriellopes9102\\Documents\\IdeaProjects\\semantic-framework\\file.xml"));
            transformer.transform(source, result);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

    private class SilkTerms {
        protected static final String INTERLINKS = "Interlinks";
        protected static final String INTERLINK = "Interlink";

        protected static final String SOURCE_DATASET_NODE = "SourceDataset";
        protected static final String DATA_SOURCE_ATTR = "dataSource";

        protected static final String TARGET_DATASET_NODE = "TargetDataset";

        protected static final String LINKAGE_RULE = "LinkageRule";
        protected static final String AGGREGATE = "Aggregate";
        protected static final String COMPARE = "Compare";
        protected static final String INPUT = "Input";
    }
*/
    /* XML SILK

    <Silk>
    <Interlinks>
        <Interlink id="genes"> -> nome do LinksetView
            <LinkType>owl:sameAs</LinkType> -> Tipo LinksetView

            <!-- a dummy dataset, the real dataset is defined at runtime -->
            <SourceDataset dataSource="KEGG_GENES" var="a"> -> Exported View
                <RestrictTo>?a rdf:type smwcat:Disease</RestrictTo> -> Classe source
            </SourceDataset>

            <TargetDataset dataSource="Wiki" var="b"> -> Exported View
                <RestrictTo>?b rdf:type smwcat:Disease</RestrictTo> -> Classe source
            </TargetDataset>

            <LinkageRule> -> Por enquanto, só serão comparados uma propriedade
                <Aggregate type="max"> -> default
                    <Compare metric="equality">
                        <Input path="?a/smwprop:KeggDiseaseId" /> -> Propriedade EV_T
                        <Input path="?b/smwprop:KeggDiseaseId" /> -> Propriedade EV_U
                    </Compare>
                </Aggregate>
            </LinkageRule>

            <Filter threshold="1.0" />

        </Interlink>

    </Interlinks>
</Silk>

     */
