package org.openmrs.module.haiticore;

import org.openmrs.api.context.Context;
import org.openmrs.module.haiticore.metadata.HaitiAddressBundle;
import org.openmrs.module.haiticore.metadata.HaitiEncounterTypeBundle;
import org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypeBundle;
import org.openmrs.module.haiticore.metadata.patientidentifiertypebundles.HaitiBiometricPatientIdentifierTypeBundle;
import org.openmrs.module.haiticore.metadata.patientidentifiertypebundles.HaitiNationalPatientIdentifierTypeBundle;
import org.openmrs.module.haiticore.metadata.patientidentifiertypebundles.HaitiSedishMpiPatientIdentifierTypeBundle;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HaitiCoreInstaller {

    @Autowired
    private MetadataDeployService metadataDeployService;

    public void installAllMetadataBundles() {
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiPersonAttributeTypeBundle.class).get(0));
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiEncounterTypeBundle.class).get(0));
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiAddressBundle.class).get(0));
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiBiometricPatientIdentifierTypeBundle.class).get(0));
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiSedishMpiPatientIdentifierTypeBundle.class).get(0));
        metadataDeployService.installBundle(Context.getRegisteredComponents(HaitiNationalPatientIdentifierTypeBundle.class).get(0));
    }

}
