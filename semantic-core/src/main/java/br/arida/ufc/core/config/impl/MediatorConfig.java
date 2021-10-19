package br.arida.ufc.core.config.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.util.XMLHelper;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 29/11/2016.
 */

/*
*  Armazena todos diretórios do Mediador.
 */
public class MediatorConfig implements Config {
    private static final Logger log = Logger.getLogger(MediatorConfig.class.getName());

    private String _config_path = "example/mediator_config.xml";
    private String _root_path = "example";

    private static MediatorConfig _instance = null;

    private Node _root;

    private File _mediated_view_dir;
    private File _app_view_dir;
    private File _domain_onto_dir;
    private File _exported_views_dir;
    private File _linkset_views_dir;
    private File _fusion_rules_dir;

    protected MediatorConfig() {
        initConfig();
        log.info("Teste");
    }

    //Singleton
    public static MediatorConfig getInstance() {
        if(_instance == null){
            _instance = new MediatorConfig();
        }
        return _instance;
    }

    protected void initConfig() {
        try {
            this._root = XMLHelper.readXML(_config_path);

            this._app_view_dir = getDirFromXML(_root,"AppView");
            this._mediated_view_dir = getDirFromXML(_root,"MediatedView");

            this._domain_onto_dir = getDirFromXML(_root,"DomainOntology"); //fazer só com uma ev e então depois fazer com todas
            this._exported_views_dir = getDirFromXML(_root,"ExportedViews");
            this._fusion_rules_dir = getDirFromXML(_root,"FusionRules");
            this._linkset_views_dir = getDirFromXML(_root,"LinksetViews");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public File getMediatedViewDir() {
        return this._mediated_view_dir;
    }

    public File getAppViewDir() {
        return this._app_view_dir;
    }

    public File getExportedViewsDir() {
        return this._exported_views_dir;
    }

    public File getLinksetViewsDir(){
        return this._linkset_views_dir;
    }

    @Override
    public File getFusionRulesDir() {
        return this._fusion_rules_dir;
    }

    public File getDomainOntoDir() { return this._domain_onto_dir; }

    public String getRootPath() { return this._root_path; }


    private File getDirFromXML(Node root, String key) {
        NodeList childs = root.getChildNodes();

        for(int i = 0; i < childs.getLength(); i++) {
            Node tmp = childs.item(i);
            String dir = "",med_dir = "",app_dir = "";

            //Nó MediatedView
            if(tmp.getNodeName().equals("MediatedView")){
                med_dir = tmp.getAttributes().getNamedItem("dir").getNodeValue();
                if(key.equals("MediatedView")){
                    return new File(_root_path + "/" + med_dir);
                }
                //Filhos do Nó MediatedView
                for(int j = 0; j < tmp.getChildNodes().getLength(); j++) {
                    Node n = tmp.getChildNodes().item(j);
                    if(n.getNodeName().equals(key)) {
                        return new File(_root_path + "/" + med_dir + "/" + n.getFirstChild().getNodeValue());
                    }
                }
            }

            //Nó AppViews
            if(tmp.getNodeName().equals("AppViews")){
                app_dir = tmp.getAttributes().getNamedItem("dir").getNodeValue();
                if(key.equals("AppViews")){
                    return new File(_root_path + "/" + app_dir);
                }
                //Filhos do Nó AppViews
                for(int j = 0; j < tmp.getChildNodes().getLength(); j++) {
                    Node n = tmp.getChildNodes().item(j);
                    //Nó AppView
                    if(n.getNodeName().equals("AppView")) {
                        //Filhos do Nó AppView
                        for(int k = 0; k < n.getChildNodes().getLength(); k++) {
                            Node n2 = n.getChildNodes().item(k);
                            String app = "";
                            if(n2.getNodeName().equals("name")) {
                                app_dir = app_dir + "/"+ n2.getFirstChild().getNodeValue();
                                return new File(_root_path + "/" + app_dir);
                            }
                            if(n2.getNodeName().equals(key)) {
                                return new File(_root_path + "/" + app_dir + "/" + n2.getFirstChild().getNodeValue());
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
