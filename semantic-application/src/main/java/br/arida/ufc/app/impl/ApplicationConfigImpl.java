package br.arida.ufc.app.impl;

import br.arida.ufc.app.ApplicationConfig;
import br.arida.ufc.app.ApplicationSpec;
import br.arida.ufc.core.entity.FilterConfig.FilterFactory;
import br.arida.ufc.core.config.Config;
import br.arida.ufc.core.entity.impl.OntologyModelFactory;
import br.arida.ufc.core.step.impl.MashupStepConfigImpl;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by gabriellopes9102 on 12/12/2016.
 */
public class ApplicationConfigImpl extends MashupStepConfigImpl implements ApplicationConfig {
    private static final Logger log = Logger.getLogger(ApplicationConfigImpl.class.getName());

    private Config _config;

    private String _root_path;
    private String _result_path;
    private String _semantic_view_path;


    public ApplicationConfigImpl(Config config) {
        super(config);
        this._config = config;
        init();
    }

    /******************
    * MÉTODOS ESTÁTICOS
    *******************/

    public File getExportedViewsDir() {
        String tmp = _semantic_view_path + _config.getExportedViewsDir().getName();
        return new File(tmp);
    }

    public File getLinksetViewsDir(){
        String tmp = _semantic_view_path + _config.getLinksetViewsDir().getName();
        return new File(tmp);
    }

    public File getFusionRulesDir() {
        String tmp = _semantic_view_path + _config.getFusionRulesDir().getName();
        return new File(tmp);
    }

    public String getRootPath() {
        return this._result_path;
    }

    public File getAppViewDir() {
        return new File(_root_path);
    }

    public File getDomainOntoDir() {
        String tmp = _semantic_view_path + _config.getDomainOntoDir().getName();
        return new File(_semantic_view_path + _config.getDomainOntoDir().getName());
    }


    private void init() {
        setPaths();
        createDirs();
    }

    private void setPaths() {
        this._root_path = _config.getAppViewDir().getPath();
        this._result_path = _root_path + "\\result\\";
        this._semantic_view_path = _result_path + "semantic_integrated_view\\";
    }


    private void createDirs() {
        File tmp = new File(this._result_path);
        boolean result = false;

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+_result_path+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }

        tmp = new File(this._semantic_view_path);

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+_semantic_view_path+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }

        tmp = this.getExportedViewsDir();

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+getExportedViewsDir().getName()+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }

        tmp = this.getLinksetViewsDir();

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+getLinksetViewsDir().getName()+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }

        tmp = this.getDomainOntoDir();

        if(!tmp.exists()) {
            result = tmp.mkdir();

            if(!result) log.warning("Error while creating the ["+getDomainOntoDir().getName()+"] Directory");
            else        log.info("Directory ["+tmp.getAbsolutePath()+"] created. . .");
        }
    }


    protected ApplicationSpec loadConfig() {
        File app_dir = _config.getAppViewDir();
        File[] app_files = app_dir.listFiles();
        File onto = app_files[0];
        File filter = app_files[1];
        ApplicationSpec app_spec = new ApplicationSpecImpl(this);

        log.info("Carregando Ontologia ["+app_files[0].getName()+"] para Ontologia de Aplicação. . .");
        app_spec.setAppOntology(OntologyModelFactory.getOntologyModel(app_files[0]));

        log.info("Carregando Filtros do arquivo["+app_files[1].getName()+"] para a Aplicação. . .");
        app_spec.setFilter(FilterFactory.getFilterImpl(app_files[1]));

        log.info("Retornando Objeto ApplicationSpec");
        return app_spec;
    }


}
