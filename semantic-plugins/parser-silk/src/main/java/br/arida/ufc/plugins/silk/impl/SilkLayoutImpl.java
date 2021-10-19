package br.arida.ufc.plugins.silk.impl;

import br.arida.ufc.core.util.XMLHelper;
import br.arida.ufc.plugins.silk.entity.Interlink;
import br.arida.ufc.plugins.silk.SilkLayout;
import br.arida.ufc.plugins.silk.entity.Silk;
import br.arida.ufc.plugins.silk.entity.impl.InterlinkImpl;
import br.arida.ufc.plugins.silk.entity.impl.SilkImpl;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 19/12/2016.
 */
public class SilkLayoutImpl implements SilkLayout {
    private static final Logger log = Logger.getLogger(SilkLayoutImpl.class.getName());

    private File _silk_file;
    private Silk _silk;

    public SilkLayoutImpl(File silk_file) {
        this._silk_file = silk_file;
        initialize(silk_file);
    }


    // Lê o arquivo file, populando os objetos
    // Também tem que dar um parser no arquivo completo para ver se não está faltando nada.
    private void initialize(File silk_file) {
        Element silk;
        log.info("Inicializando arquivo ["+silk_file.getName()+"]. . .");
        try {
            silk = (Element) XMLHelper.readXML(silk_file);
            this._silk = new SilkImpl(silk);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public Silk getSilk() {
        return this._silk;
    }

    public File generateSilkFile(String output_path) {
        String str = "";

        str += _silk.toXML();
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
