package br.arida.ufc.core.entity.impl;

import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.util.FileUtils;
import junit.framework.TestCase;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.StmtIteratorImpl;
import org.apache.jena.util.PrintUtil;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by gabriellopes9102 on 09/12/2016.
 */
public class JenaOntologyTest extends TestCase{

    public void testJenaOntology() throws Exception {

        JenaOntologyModelImpl _jena = new JenaOntologyModelImpl(FileUtils.getFileFromResource(this.getClass(),"gissa.ttl"));
        String GISSA = "http://www.atlantico.com.br#";
        Resource _class = (Resource) _jena.getOntoClass(GISSA+"Pessoa");
        OntModel model = _jena.getOntoModel();

        Resource subclass = model.createResource(GISSA + "Fumante123");
        Property prop = model.createProperty(GISSA+"subclass");
        model.add(subclass,prop,_class);


        OntClass o = model.getOntClass(GISSA+"PortadorDeDoencaRenal");
        o.addProperty(prop,"Teste");

        String tmp = (GISSA+"Pessoa").split("#")[0];
        String prefix = model.getNsURIPrefix((GISSA+"Pessoa").split("#")[0]+"#");


        Map<String,String> tmp5 = model.getNsPrefixMap();
        tmp5.put("arida","http://ufc.br/arida#");
        model.setNsPrefixes(tmp5);

        model.createClass("http://ufc.br/arida#Fumante12356");
        Individual p1 = _jena.getOntoModel().createIndividual( GISSA + "Gabriel", _class );

        OntClass c = model.getOntClass(GISSA+"Pessoa");

        c.remove();

        model.write(new FileWriter("teste.owl"),"TURTLE");
        Dataset dataset = DatasetFactory.create(model);

        System.out.println(dataset.toString());
        dataset.commit();
        PrintUtil.printOut(dataset.asDatasetGraph().find());
//        System.out.println(dataset.asDatasetGraph().getContext().toString());

        /*for (Iterator<OntClass> c = model.listNamedClasses(); c.hasNext(); ) {
            System.out.println( model.getOntClass().getRDFType()+ " is asserted in class " + c.next() );
        }
        _jena.getOntoModel().*/


    }

    public void testOntology() throws Exception{
        File owl = FileUtils.getFileFromResource(this.getClass(),"gissa.owl");
        JenaOntologyModelImpl jena = new JenaOntologyModelImpl(owl);
        String GISSA = "http://www.atlantico.com.br#";
        //jena.listClasses();

        System.out.println("############ ####################");

        OntClass c = jena.getOntoModel().getOntClass(GISSA+"Pessoa");

        owl = FileUtils.getFileFromResource(this.getClass(),"app_onto_gissa.owl");
        JenaOntologyModelImpl jena2 = new JenaOntologyModelImpl(owl);

        //OntClass b = jena.getOntoModel().createClass(GISSA+"Pessoa");
        //b.setSubClass(c.getSubClass());
        //b.setEquivalentClass(c.getEquivalentClass());

        for(Iterator<Statement> i = c.listProperties(); i.hasNext(); ) {
            Statement s = i.next();
            jena2.getOntoModel().add(s);
        };

        jena2.getOntoModel().write(new FileWriter("teste12345.owl"),"TURTLE");
        Dataset dataset = DatasetFactory.create(jena2.getOntoModel());

        System.out.println(dataset.toString());
        dataset.commit();
        PrintUtil.printOut(dataset.asDatasetGraph().find());

        //jena.getIntersectionWithOntology(jena2);
        //jena.listClasses();

    }

    public void testIntersection() throws Exception {

    }

    public void testGetIntersectionWithOntology() throws Exception {
        File owl = FileUtils.getFileFromResource(this.getClass(),"gissa.owl");
        JenaOntologyModelImpl jena = new JenaOntologyModelImpl(owl);

        owl = FileUtils.getFileFromResource(this.getClass(),"esus2.owl");
        JenaOntologyModelImpl jena2 = new JenaOntologyModelImpl(owl);

       /* OntModel m = ModelFactory.createOntologyModel();

        for(Iterator<Statement> i = jena.getModel().listStatements(); i.hasNext(); ) {
            Statement s = i.next();

            m.add(s);
        };

        m.write(new FileWriter("output.owl"),"TURTLE");
        Dataset dataset = DatasetFactory.create(jena2.getOntoModel());

        System.out.println(dataset.toString());
        dataset.commit();
        PrintUtil.printOut(dataset.asDatasetGraph().find());
*/
        OntologyModel tmp = jena.getIntersectionWithOntology(jena2);

        System.out.println(tmp.toString());

        tmp.generateFile(".//output.owl");
    }
}
