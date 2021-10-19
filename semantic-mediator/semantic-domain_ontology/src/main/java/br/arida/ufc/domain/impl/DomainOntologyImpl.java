package br.arida.ufc.domain.impl;

import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.entity.OntologyModel;
import br.arida.ufc.core.step.MashupStep;
import br.arida.ufc.core.step.impl.MashupStepSpecificationImpl;
import br.arida.ufc.domain.DomainOntology;
import br.arida.ufc.domain.DomainOntologyConfig;

/**
 * Created by gabriellopes9102 on 07/12/2016.
 */
public class DomainOntologyImpl extends MashupStepSpecificationImpl implements DomainOntology {
    private DomainOntologyConfig _config;
    private OntologyModel _ontologyModel;


    public DomainOntologyImpl(DomainOntologyConfig config) {
        super(config);
        this._config = config;
    }

    public void setOntology(OntologyModel ontologyModel) {
        this._ontologyModel = ontologyModel;
    }

    public OntologyModel getOntology() {
        return this._ontologyModel;
    }

    public Object getOntoClass(String _class) {
        return _ontologyModel.getOntoClass(_class);
    }

    public static DomainOntology load(Config config){
        return new DomainOntologyConfigImpl(config).load();
    }

    public OntologyModel materializeView(String output_path) {
        this._ontologyModel.generateFile(output_path);
        return this._ontologyModel;
    }
}


    /*OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
    OntClass programme = m.createClass( NS + "Programme" );
    OntClass orgEvent = m.createClass( NS + "OrganizedEvent" );

    ObjectProperty hasProgramme = m.createObjectProperty( NS + "hasProgramme" );

hasProgramme.addDomain( orgEvent );
        body.addRange( programme );
        body.addLabel( "has programme", "en" );*/

/*
    OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
m.read( "http://www.eswc2006.org/technologies/ontology" );

        DatatypeProperty subDeadline = m.getDatatypeProperty( NS + "hasSubmissionDeadline" );
        DatatypeProperty notifyDeadline = m.getDatatypeProperty( NS + "hasNotificationDeadline" );
        DatatypeProperty cameraDeadline = m.getDatatypeProperty( NS + "hasCameraReadyDeadline" );

        DatatypeProperty deadline = m.createDatatypeProperty( NS + "deadline" );
        deadline.addDomain( m.getOntClass( NS + "Call" ) );
        deadline.addRange( XSD.dateTime );

        deadline.addSubProperty( subDeadline );
        deadline.addSubProperty( notifyDeadline );
        deadline.addSubProperty( cameraDeadline );*/
