package br.arida.ufc.domain;

import br.arida.ufc.domain.impl.DomainOntologyImpl;

/**
 * Created by gabriellopes9102 on 23/12/16.
 */
public class DomainOntologyFactory {

    public static DomainOntology getDomainImpl(DomainOntologyConfig config) {
        return new DomainOntologyImpl(config);
    }
}
