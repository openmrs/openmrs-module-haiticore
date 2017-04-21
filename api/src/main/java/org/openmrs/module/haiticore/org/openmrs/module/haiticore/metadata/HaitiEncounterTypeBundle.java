package org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

@Component
public class HaitiEncounterTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing EncounterTypes");
        install(HaitiEncounterTypes.PATIENT_REGISTRATION);

    }
}

