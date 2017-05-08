package org.openmrs.module.haiticore.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.haiticore.metadata.HaitiPatientIdentifierTypes;
import org.springframework.stereotype.Component;

@Component
public class HaitiPatientIdentifierTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing PatientIdentifierTypes");

        install(HaitiPatientIdentifierTypes.BIOMETRIC_REF_NUMBER);
    }

}
