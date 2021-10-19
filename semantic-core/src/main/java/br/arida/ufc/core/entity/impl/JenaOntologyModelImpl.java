package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.JenaOntologyModel;
import br.arida.ufc.core.entity.OntoClass;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.entity.Prefix;
import br.arida.ufc.core.util.Log4jHelper;
import org.apache.jena.atlas.logging.Log;
import org.apache.jena.ext.com.google.common.util.concurrent.Service;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.PrintUtil;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public class JenaOntologyModelImpl extends OntologyModelImpl implements JenaOntologyModel{
    private static final java.util.logging.Logger log = Logger.getLogger(JenaOntologyModelImpl.class.getName());

    private String _ontology_path;
    private OntModel _onto_model;
    private String _name;

    public JenaOntologyModelImpl(File onto_owl_file) {
        super(onto_owl_file);

        this._ontology_path = onto_owl_file.toURI().toString();
        init();
    }

    public JenaOntologyModelImpl(String onto_owl_path) {
        super(onto_owl_path);
        this._ontology_path = onto_owl_path;
        init();
    }

    public JenaOntologyModelImpl(String onto_owl_path, OntModel model){
        super(onto_owl_path);
        this._ontology_path = onto_owl_path;
        this._onto_model = model;
    }

    public JenaOntologyModelImpl(OntModel onto_model){
        super("");
        this._onto_model = onto_model;
    }


    public Object getOntoClass(String _class) {
        return this._onto_model.getOntClass(_class);
    }

    public void createClass(String _class) {
        this._onto_model.createClass(_class);
    }

    public void removeClass(String _class) {
        this._onto_model.getOntClass(_class).remove();
    }

    public OntModel getOntoModel() {
        return this._onto_model;
    }

    public void listClasses() {
        /*for (Iterator<OntClass> i = this._onto_model.listClasses(); i.hasNext(); ) {
            OntClass c = i.next();
            if(c.getURI() != null && c.getURI().contains("MalFormacao")) {
                JOptionPane.showMessageDialog(null,"Removendo MalFormacao");
                c.remove();
            }
            System.out.println( c.getURI() );
        }*/
    }

    public OntModel getModel() {
        return this._onto_model;
    }



    /**
     *
     * @param ontology
     * @return uma ontologia que representa a interseção entre a ontologia atual e ontology, passada por @param;
     */
    @Override
    public OntologyModel getIntersectionWithOntology(OntologyModel ontology) {
        int k = 0;
        OntologyModel new_onto = new JenaOntologyModelImpl(ModelFactory.createOntologyModel());

        ((OntModel) new_onto.getModel()).setNsPrefixes(this.getModel().getNsPrefixMap());

        for(Iterator<Statement> i = this.getModel().listStatements(); i.hasNext(); ) {
            Statement stm = i.next();
            if(ontology.hasStatement(stm)) {
                new_onto.addStatement(stm);
                k++;
            }
        };

        log.info("Found ["+k+"] Intersections between ["+this.getName()+"] and ["+ontology.getName()+"]. . .");
        return new_onto;
    }

/*        for(int i = 0 ; i < classes_onto_a.length; i++) {
            class_a = classes_onto_a[i].getURI();
            for(int j = 0; j < classes_onto_b.length; j++) {
                class_b = classes_onto_b[j].getURI();

                if(class_a != null && class_b != null) {
                    if (class_a.contains(class_b) ||
                        class_b.contains(class_a)) {

                        if(!new_onto.hasPrefix(classes_onto_a[i].getPrefix())) {
                            new_onto.addPrefix(classes_onto_a[i].getPrefix());
                        }

                        new_onto.addClass(classes_onto_a[i].as(OntClass.class)); //Adiciona A URI. Se o prefixo for conhecido, já o utiliza

                        k++;
                        log.info("Intersection found between [" + class_a + "] from ["+this.getName()+"] " +
                                 "and [" + class_b + "] from ["+ontology.getName()+"]. . .");
                    }
                }

            }
        }*/


    public void addClass(Object obj) {
        OntClass c = ((OntClass) obj);
        for(Iterator<Statement> i = c.listProperties(); i.hasNext(); ) {
            Statement s = i.next();
            this.getOntoModel().add(s);
        };
    }

    @Override
    public String getPrefixFromURI(String uri) {
        return this._onto_model.getNsURIPrefix(uri);
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public void setName(String name) {
        this._name = name;
    }

    public void addPrefix(Prefix prefix) {
        String key = prefix.getKey();
        String URI = prefix.getURI();

        if(key.equals("")){
            key = this._onto_model.getNsPrefixURI(URI);
        }
        Map<String,String> tmp = this._onto_model.getNsPrefixMap();
        tmp.put(key,URI);
        this._onto_model.setNsPrefixes(tmp);

        log.info("Prefix ["+key+"] with URI ["+URI+"] added to ["+this._ontology_path+"]. . .");
    }

    public Object getPrefixes() {
        return this._onto_model.getNsPrefixMap();
    }

    public void getClassByIndex(int i) {
        for (Iterator<OntClass> j = this._onto_model.listClasses(); j.hasNext(); ) {
            OntClass c = j.next();
            if(c.getURI() != null && c.getURI().contains("MalFormacao")) {
                JOptionPane.showMessageDialog(null,"Removendo MalFormacao");
                c.remove();
            }
            System.out.println( c.getURI() );
        }
    }

    @Override
    public OntoClass[] getOntoClasses() {
        List<OntoClass> classes = new ArrayList<OntoClass>();

        for (Iterator<OntClass> i = this._onto_model.listClasses(); i.hasNext(); ) {
            OntClass c = i.next();

            if(isURIValid(c.getURI())) //Tentativa para ver se é uma URI válida
                classes.add(new JenaOntoClassImpl(this,c.getURI()));
        }

        return classes.toArray(new JenaOntoClassImpl[classes.size()]);
    }

    public boolean isURIValid(String uri) {
        boolean isValid = false;
        if(uri == null || uri.isEmpty())
            return false;

        if(uri.contains("#")) //tentativa para ver se a uri é válida
            isValid = true;
        return isValid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix;

        for(OntoClass o : getOntoClasses()) {
            sb.append("@"+o.getPrefix().getKey()+": "+o.getPrefix().getURI());
        }

        for(OntoClass o : getOntoClasses()) {
            sb.append(o.toString() + "\n");

        }

        return sb.toString();
    }

    @Override
    public void generateFile(String output) {
        output += ".owl";
        File file = new File(output);
        try {
            this._onto_model.write(new FileWriter(file), "TURTLE");
            Dataset dataset = DatasetFactory.create(this._onto_model);

            dataset.commit();
            PrintUtil.printOut(dataset.asDatasetGraph().find());
        } catch (IOException e) {
            e.printStackTrace();
            log.warning("Error while generating file ["+output+"]. . . ");
            log.warning(e.getMessage());
        }
    }

    public boolean hasPrefix(Prefix prefix) {
        String str = this._onto_model.getNsPrefixURI(prefix.getKey());

        return str == null ? false : true ;
    }

    @Override
    public boolean hasStatement(Object stm) {
        for(Iterator<Statement> i = this.getModel().listStatements(); i.hasNext();) {
            Statement s = i.next();

            if(s.equals((Statement)stm)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addStatement(Object stm) {
        if(stm instanceof Statement) {
            this.getModel().add((Statement)stm);
        }
    }

    private void init(){
        this._onto_model = ModelFactory.createOntologyModel();
        this._onto_model.setDerivationLogging(false);
//        log4j.logger.org.apache.jena.arq.exec=INFO


        _onto_model.read(this._ontology_path,"TURTLE");
        this._name = new File(_ontology_path).getName();

    }

    /*public OntologyModel getIntersectionOntology(OntologyModel ontology) {
        OntModel new_model = ModelFactory.createOntologyModel();
        //Verifica as classes que estão em ambas ontologias, cria novo modelo e retorna.

        *//*for (Iterator<OntClass> i = this._onto_model.listClasses(); i.hasNext(); ) {
            OntClass c = i.next();
            if(c.getURI() != null && c.getURI().contains("MalFormacao")) {
                JOptionPane.showMessageDialog(null,"Removendo");
                c.remove();
            }
            System.out.println( c.getURI() );
        }

        for (StmtIterator i = this._onto_model.listStatements(); i.hasNext(); ) {
            StmtIterator c = i.next();
            c.
        }

        //this._onto_model;
*//*
        return null;
    }*/


}
