package br.arida.ufc.plugins.sieve.impl;

import br.arida.ufc.core.util.XMLHelper;
import br.arida.ufc.plugins.sieve.SieveLayout;
import br.arida.ufc.plugins.sieve.entity.Sieve;
import br.arida.ufc.plugins.sieve.entity.impl.SieveImpl;
import org.apache.jena.sparql.function.library.leviathan.log;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public class SieveLayoutImpl implements SieveLayout{
    private Logger log = Logger.getLogger(SieveLayoutImpl.class.getName());

    private Sieve _sieve_node;
    private File _sieve_file;
    private Element _root;

    public SieveLayoutImpl(File sieve_file) {
       this._sieve_file = sieve_file;
       initialize(sieve_file);
    }


    // Lê o arquivo file, populando os objetos
    // Também tem que dar um parser no arquivo completo para ver se não está faltando nada.
    private void initialize(File sieve_file) {
        Element sieve;
        log.info("Inicializando arquivo ["+sieve_file.getName()+"]. . .");
        try {
            _root = (Element) XMLHelper.readXML(sieve_file);

            log.info("Adicionando Tag Sieve. . .");
            this._sieve_node = new SieveImpl(_root);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Sieve getSieve() {
        return this._sieve_node;
    }

    public File generateSieveFile(String output_path) {
        String str = "";

        str += _sieve_node.toXML();

        log.info("Sieve string generated: \n");
        System.out.println(str);
        try {
            return XMLHelper.toXML(str, output_path);
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
