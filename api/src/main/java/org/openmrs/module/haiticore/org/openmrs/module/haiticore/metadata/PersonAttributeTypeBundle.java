package org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

@Component
public class PersonAttributeTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing PersonAttributeTypes");
        install(PersonAttributeTypes.TELEPHONE_NUMBER);
        install(PersonAttributeTypes.TEST_PATIENT);
        install(PersonAttributeTypes.UNKNOWN_PATIENT);
        install(PersonAttributeTypes.MOTHERS_FIRST_NAME);

    }
}

