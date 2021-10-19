package br.arida.ufc.plugins.sieve;

import br.arida.ufc.plugins.sieve.entity.Sieve;

import java.io.File;

/**
 * Created by gabriellopes9102 on 20/12/2016.
 */
public interface SieveLayout {

    Sieve getSieve();

    File generateSieveFile(String output_path);
}
