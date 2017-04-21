package org.openmrs.module.haiticore;

import org.junit.Test;
import org.openmrs.api.PersonService;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypeBundle;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypes;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class HaitiCoreActivatorTest extends BaseModuleContextSensitiveTest {

    @Autowired
    private MetadataDeployService deployService;

    @Autowired
    private PersonService personService;

    @Autowired
    private HaitiPersonAttributeTypeBundle personAttributeTypeBundle;
  
    
    @Test
    public void testMetadataBundles() throws Exception {
       deployService.installBundle(personAttributeTypeBundle);

        // just test that a few pieces of data exist
        assertThat(personService.getAllPersonAttributeTypes().size(), is(7)); // the 4 that the bundle installs + the 3 from standard test dataset
        assertNotNull(personService.getPersonAttributeTypeByUuid(HaitiPersonAttributeTypes.MOTHERS_FIRST_NAME.uuid()));
    }


}
