package org.openmrs.module.haiticore;

import org.junit.Test;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.addresshierarchy.AddressField;
import org.openmrs.module.addresshierarchy.AddressHierarchyLevel;
import org.openmrs.module.addresshierarchy.service.AddressHierarchyService;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypeBundle;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypes;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiAddressBundle;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Assert;

public class HaitiCoreActivatorTest extends BaseModuleContextSensitiveTest {

    @Autowired
    private MetadataDeployService deployService;

    @Autowired
    private PersonService personService;

    @Autowired
    private HaitiPersonAttributeTypeBundle personAttributeTypeBundle;

    @Autowired
    private HaitiAddressBundle addressBundle;

    @Test
    public void testMetadataBundles() throws Exception {
       deployService.installBundle(personAttributeTypeBundle);
       deployService.installBundle(addressBundle);

        // just test that a few pieces of data exist
        assertThat(personService.getAllPersonAttributeTypes().size(), is(7)); // the 4 that the bundle installs + the 3 from standard test dataset
        assertNotNull(personService.getPersonAttributeTypeByUuid(HaitiPersonAttributeTypes.MOTHERS_FIRST_NAME.uuid()));
    
        // test the address hierarchy
        verifyAddressHierarchyLevelsCreated();
        verifyAddressHierarchyLoaded();
    }
    
    private void verifyAddressHierarchyLevelsCreated() throws Exception {
        AddressHierarchyService ahService = Context.getService(AddressHierarchyService.class);

        // assert that we now have six address hierarchy levels
        assertEquals(new Integer(6), ahService.getAddressHierarchyLevelsCount());

        // make sure they are mapped correctly
        List<AddressHierarchyLevel> levels = ahService.getOrderedAddressHierarchyLevels(true);
        assertEquals(AddressField.COUNTRY, levels.get(0).getAddressField());
        assertEquals(AddressField.STATE_PROVINCE, levels.get(1).getAddressField());
        assertEquals(AddressField.CITY_VILLAGE, levels.get(2).getAddressField());
        assertEquals(AddressField.ADDRESS_3, levels.get(3).getAddressField());
        assertEquals(AddressField.ADDRESS_1, levels.get(4).getAddressField());
        assertEquals(AddressField.ADDRESS_2, levels.get(5).getAddressField());

    }

    private void verifyAddressHierarchyLoaded() throws Exception {
        AddressHierarchyService ahService = Context.getService(AddressHierarchyService.class);

        // we should now have 26000+ address hierarchy entries
        Assert.assertTrue(ahService.getAddressHierarchyEntryCount() > 26000);

        assertEquals(2, ahService.getAddressHierarchyEntriesAtTopLevel().size());
        assertEquals("Yon lot peyi", ahService.getAddressHierarchyEntriesAtTopLevel().get(0).getName());
        assertEquals("Haiti", ahService.getAddressHierarchyEntriesAtTopLevel().get(1).getName());
    }

}

