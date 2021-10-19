package br.arida.ufc.domain.impl;

import br.arida.ufc.core.entity.impl.JenaOntologyModelImpl;
import br.arida.ufc.core.util.FileUtils;
import junit.framework.TestCase;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.PrintUtil;

import java.io.FileWriter;

/**
 * Created by gabriellopes9102 on 08/12/2016.
 */
public class JenaOntologyModelImplTest extends TestCase {

    public void testJenaOntology() throws Exception {
        JenaOntologyModelImpl _jena = new JenaOntologyModelImpl(FileUtils.getFileFromResource(this.getClass(),"gissa.ttl"));
        String GISSA = "http://www.atlantico.com.br#";
        Resource _class = (Resource) _jena.getOntoClass(GISSA+"Pessoa");
        OntModel model = _jena.getOntoModel();

        Resource subclass = model.createResource(GISSA + "Fumante123");
        Property prop = model.createProperty(GISSA+"subclass");
        model.add(subclass,prop,_class);

        model.createClass(GISSA+"Fumante12356");

        OntClass o = model.getOntClass(GISSA+"PortadorDeDoencaRenal");
        o.addProperty(prop,"Teste");

        String prefix = model.getNsURIPrefix(GISSA+"Pessoa");

        Individual p1 = _jena.getOntoModel().createIndividual( GISSA + "Gabriel", _class );

        OntClass c = model.getOntClass(GISSA+"Pessoa");

        c.remove();

        model.write(new FileWriter("teste.owl"),"TURTLE");
        Dataset dataset = DatasetFactory.create(model);

        dataset.commit();
        PrintUtil.printOut(dataset.asDatasetGraph().find());
//        System.out.println(dataset.asDatasetGraph().getContext().toString());

        /*for (Iterator<OntClass> c = model.listNamedClasses(); c.hasNext(); ) {
            System.out.println( model.getOntClass().getRDFType()+ " is asserted in class " + c.next() );
        }
        _jena.getOntoModel().*/


    }
}
