package org.openmrs.module.haiticore.metadata.bundles;

import org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypes;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

@Component
public class HaitiPersonAttributeTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing PersonAttributeTypes");
        install(HaitiPersonAttributeTypes.TELEPHONE_NUMBER);
        install(HaitiPersonAttributeTypes.TEST_PATIENT);
        install(HaitiPersonAttributeTypes.UNKNOWN_PATIENT);
        install(HaitiPersonAttributeTypes.MOTHERS_FIRST_NAME);

    }
}

