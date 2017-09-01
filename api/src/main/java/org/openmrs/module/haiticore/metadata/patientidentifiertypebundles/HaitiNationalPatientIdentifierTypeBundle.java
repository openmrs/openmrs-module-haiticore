package org.openmrs.module.haiticore.metadata.patientidentifiertypebundles;

import org.openmrs.module.haiticore.metadata.HaitiPatientIdentifierTypes;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

@Component
public class HaitiNationalPatientIdentifierTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing Haitian National PatientIdentifierType");

        install(HaitiPatientIdentifierTypes.NIF_ID);
        install(HaitiPatientIdentifierTypes.CIN_ID);
    }

}
