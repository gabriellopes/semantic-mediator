package br.arida.ufc.exportedview.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.entity.impl.MappingFactory;
import br.arida.ufc.core.entity.impl.OntologyModelFactory;
import br.arida.ufc.core.step.impl.MashupStepConfigImpl;
import br.arida.ufc.core.util.FileUtils;
import br.arida.ufc.core.util.XMLHelper;

import br.arida.ufc.exportedview.ExportedViewConfig;
import br.arida.ufc.exportedview.ExportedViewFactory;
import br.arida.ufc.exportedview.ExportedViewSpec;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */
public class ExportedViewConfigImpl extends MashupStepConfigImpl implements ExportedViewConfig{
    private Config _config;

    public ExportedViewConfigImpl(Config config) {
        super(config);
        _config = config;
    }

    protected ExportedViewSpec[] loadConfig() {
        File ev_config_file = _config.getExportedViewsDir();
        File[] filesXML = FileUtils.listFiles(ev_config_file,"xml");
        List<ExportedViewSpec> ev_specs = new ArrayList<ExportedViewSpec>();

        try {
            Node root = XMLHelper.readXML(filesXML[0]);
            Element t = (Element)root;
            NodeList e_views = ((Element)root).getElementsByTagName("ExportedView");

            for(int i = 0; i < e_views.getLength(); i++) {
                Node tmp = e_views.item(i), dir = null, onto_s = null, mapping = null;
                if(tmp.getNodeName().equals("ExportedView")) {
                    NodeList childs = tmp.getChildNodes();


                    for(int j = 0; j < childs.getLength(); j++){
                        Node n = childs.item(j);

                        if(n.getNodeName().equals("name")) {
                            dir = n.getFirstChild();
                        }
                        if(n.getNodeName().equals("OntoSource")) {
                            onto_s = n.getFirstChild();
                        }
                        if(n.getNodeName().equals("Mappings")) {
                            mapping = n.getFirstChild();
                        }
                    }

                    File onto_s_file = new File(ev_config_file.getAbsolutePath() + "/" + dir.getNodeValue() + "/" + onto_s.getNodeValue());
                    File mapping_file = new File(ev_config_file.getAbsolutePath() + "/" + dir.getNodeValue() + "/" + mapping.getNodeValue());

                    ExportedViewSpec e_tmp = getSpecImpl();

                    e_tmp.setName(dir.getNodeValue());
                    e_tmp.setOntoSource(OntologyModelFactory.getOntologyModel(onto_s_file));
                    e_tmp.setMapping(MappingFactory.getMappingManager(mapping_file));

                    ev_specs.add(e_tmp);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return ev_specs.toArray(new ExportedViewSpecImpl[ev_specs.size()]);
    }


    private ExportedViewSpec getSpecImpl() {
        return ExportedViewFactory.getSpecImpl(this);
    }
}
