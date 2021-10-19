package br.arida.ufc.core.config;

import java.io.File;

/**
 * Created by gabriellopes9102 on 30/11/2016.
 */
public interface Config {

    File getAppViewDir();

    File getExportedViewsDir();

    File getDomainOntoDir();

    File getLinksetViewsDir();

    File getFusionRulesDir();

    String getRootPath();
}
