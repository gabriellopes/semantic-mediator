package br.arida.ufc.linksetview.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.step.impl.MashupStepConfigImpl;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.core.util.XMLHelper;
import br.arida.ufc.linksetview.LinksetViewConfig;
import br.arida.ufc.linksetview.LinksetViewFactory;
import br.arida.ufc.linksetview.LinksetViewSpec;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gabriellopes9102 on 01/12/2016.
 */

/*
* Essa classe conhece o schema do XML linksetview.
 */
public class LinksetViewConfigImpl extends MashupStepConfigImpl implements LinksetViewConfig{
    private Config _config;

    protected File[] _linkset_views;

    public LinksetViewConfigImpl(Config config) {
        super(config);
        this._config = config;
    }

    public LinksetViewSpec[] loadConfig() {
        System.out.println(new File(_config.getRootPath()).getAbsolutePath());
        File linkset_dir = _config.getLinksetViewsDir();
        File[] filesXML = FileUtils.listFiles(linkset_dir,"xml");
        List<LinksetViewSpec> ls_specs = new ArrayList<LinksetViewSpec>();
        Map<String,String> prefixes_map = new HashMap<String,String>();

        Node class_target = null, class_source_T = null, class_source_U = null,
             exported_view_T = null, exported_view_U = null, match_predicate = null,
             property;

        String name = "",metric = "";
        NodeList prefixes;
        try {
            Node root = XMLHelper.readXML(filesXML[0]);
            NodeList childs = root.getChildNodes();

            //Tags de LinksetView
            for(int i = 0; i < childs.getLength(); i++) {
                Node t = childs.item(i);

                //Nó Prefixos
                if(t.getNodeName().equals("Prefixes")) {
                    prefixes = ((Element) t).getElementsByTagName("Prefix");

                    for(int j = 0; j < prefixes.getLength() ; j++) {
                        Node p = prefixes.item(j);
                        String key = p.getAttributes().getNamedItem("key").getNodeValue();
                        String uri = p.getAttributes().getNamedItem("uri").getNodeValue();
                        prefixes_map.put(key,uri);
                    }

                }

                //Nó Linksets
                if(t.getNodeName().equals("Linksets")) {
                    NodeList linksets = ((Element) t).getElementsByTagName("Linkset");

                    //Filhos de Linksets
                    for(int j = 0; j < linksets.getLength(); j++ ){
                        Node linkset = linksets.item(j);

                        //Filhos de um nó Linkset, que é filho de Linksets
                        if(linkset.getNodeName().equals("Linkset")) {
                            name = linkset.getAttributes().getNamedItem("id").getNodeValue();

                            Element no_e_views = (Element) ((Element)linkset).getElementsByTagName("ExportedViews").item(0);

                            exported_view_T = no_e_views.getElementsByTagName("ExportedView").item(0);; //Filho 1 de EViews
                            exported_view_U = no_e_views.getElementsByTagName("ExportedView").item(1);; //Filho2 de EViews

                            match_predicate = ((Element)linkset).getElementsByTagName("MatchPredicate").item(0); //Nó matchPredicate
                            metric = match_predicate.getAttributes().getNamedItem("metric").getNodeValue();

                            class_target = ((Element)match_predicate).getElementsByTagName("Class").item(0); //<Class>mo:Track</Class>
                            property = ((Element)match_predicate).getElementsByTagName("Property").item(0);

                            MatchPredicateImpl m_predicate = new MatchPredicateImpl(class_target.getFirstChild().getNodeValue(),
                                                         metric,property.getFirstChild().getNodeValue());

                            LinksetViewSpec ls_tmp = getSpecImpl();

                            ls_tmp.setPrefixes(prefixes_map);
                            ls_tmp.setName(name);
                            ls_tmp.setExportedViewsByName(exported_view_T.getFirstChild().getNodeValue(), exported_view_U.getFirstChild().getNodeValue());
                            ls_tmp.setMatchPredicate(m_predicate);

                            ls_specs.add((LinksetViewSpecImpl)ls_tmp);
                        }

                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return ls_specs.toArray(new LinksetViewSpecImpl[ls_specs.size()]);
    }

    private LinksetViewSpec getSpecImpl() {
        return LinksetViewFactory.getSpecImpl(this);
    }

    /*private MatchPredicate getMatchImpl() {

    }*/
}
