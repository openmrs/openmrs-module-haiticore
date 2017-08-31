package org.openmrs.module.haiticore.metadata.patientidentifiertypebundles;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.haiticore.metadata.HaitiPatientIdentifierTypes;
import org.springframework.stereotype.Component;

@Component
public class HaitiBiometricPatientIdentifierTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing Biometric PatientIdentifierType");

        install(HaitiPatientIdentifierTypes.BIOMETRIC_REF_NUMBER);
    }

}
