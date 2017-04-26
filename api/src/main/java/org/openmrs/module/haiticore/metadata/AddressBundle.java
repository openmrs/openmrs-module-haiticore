package org.openmrs.module.haiticore.metadata;

import org.apache.commons.io.IOUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.openmrs.api.SerializationService;
import org.openmrs.api.context.Context;
import org.openmrs.api.APIException;
import org.openmrs.module.addresshierarchy.AddressHierarchyLevel;
import org.openmrs.module.addresshierarchy.service.AddressHierarchyService;
import org.openmrs.module.addresshierarchy.util.AddressHierarchyImportUtil;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AddressBundle extends VersionedMetadataBundle {

    @Autowired
    SerializationService serializationService;

    /**
     * @return the ordered list of address components that make up the address configuration
     */
    public abstract List<AddressComponent> getAddressComponents();

    /**
     * @return the line-by-line format needed by the address template
     */
    public abstract List<String> getLineByLineFormat();

    /**
     * @return the location on the classpath from which to load the address hierarchy entries
     */
    public abstract String getAddressHierarchyEntryPath();

    @Override
    protected void installEveryTime() throws Exception {
        installAddressTemplate();
    }

    @Override
    protected void installNewVersion() throws Exception {

        AddressHierarchyService service = Context.getService(AddressHierarchyService.class);

        // currently we only install the levels if they haven't been installed; no built-in way to edit anything other than "required" at this point
        int numberOfLevels = service.getAddressHierarchyLevelsCount();
        if (numberOfLevels == 0) {
            installAddressHierarchyLevels();
        }
        else {
            updateRequiredProperty();
        }

        installAddressHierarchyEntries();
    }

    /**
     * Install the appropriate address template
     */
    public void installAddressTemplate() throws Exception {
        log.info("Installing Address Template");
        String template = serializationService.getDefaultSerializer().serialize(getAddressTemplate());
        setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_ADDRESS_TEMPLATE, template);
    }

    /**
     * @return a new AddressTemplate instance for the given configuration
     */
    /* This Object utilizes the reflection method to identify the correct constructing class because the API
     * for address template changed from "org.openmrs.layout.web.address.AddressTemplate" prior to OpenMRS platform v1.12
     * and "org.openmrs.layout.address.AddressTemplate" thereafter.
     */
    public Object getAddressTemplate() {
    	Object addressTemplate = null;
        try {
        	Constructor constructor = Context.loadClass("org.openmrs.layout.web.address.AddressTemplate").getConstructor(String.class);
			addressTemplate = constructor.newInstance("");
        }
        /* Multi-catch exceptions were added to Java 1.7. We need to do a work-around to keep compatibility for 1.6
    	Ideal Multi-catch would be catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) 
    	*/
		catch (Exception e){
			if(e instanceof ClassNotFoundException || e instanceof InstantiationException || e instanceof IllegalAccessException || e instanceof NoSuchMethodException || e instanceof InvocationTargetException) {
				
        	try {
        		Constructor constructor = Context.loadClass("org.openmrs.layout.address.AddressTemplate").getConstructor(String.class);
        		addressTemplate = constructor.newInstance("");
				}
	        	/* Multi-catch exceptions were added to Java 1.7. We need to do a work-around to keep compatibility for 1.6
	        	Ideal Multi-catch would be catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) 
	        	*/
				catch (Exception ex){
					if(ex instanceof ClassNotFoundException || ex instanceof InstantiationException || ex instanceof IllegalAccessException || ex instanceof NoSuchMethodException || ex instanceof InvocationTargetException) {
						throw new APIException("Error while getting address template", ex);
					}
				}
			}
        }
        
        Map<String, String> nameMappings = new HashMap<String, String>();
        Map<String, String> sizeMappings = new HashMap<String, String>();
        Map<String, String> elementDefaults = new HashMap<String, String>();
        for (AddressComponent c : getAddressComponents()) {
            nameMappings.put(c.getField().getName(), c.getNameMapping());
            sizeMappings.put(c.getField().getName(), Integer.toString(c.getSizeMapping()));
            if (c.getElementDefault() != null) {
                elementDefaults.put(c.getField().getName(), c.getElementDefault());
            }
        }
       
        try {
			MethodUtils.invokeExactMethod(addressTemplate, "setNameMappings", new Object[]{ nameMappings }, new Class[] { Map.class });
			MethodUtils.invokeExactMethod(addressTemplate, "setSizeMappings", new Object[]{ sizeMappings }, new Class[] { Map.class });
	        MethodUtils.invokeExactMethod(addressTemplate, "setElementDefaults", new Object[]{ elementDefaults }, new Class[] { Map.class });
	        MethodUtils.invokeExactMethod(addressTemplate, "setLineByLineFormat", new Object[]{ getLineByLineFormat() }, new Class[] { List.class });
		}
        /* Multi-catch exceptions were added to Java 1.7. We need to do a work-around to keep compatibility for 1.6
        	Ideal Multi-catch would be catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        */
		catch (Exception e) {
			if (e instanceof NoSuchMethodException || e instanceof IllegalAccessException || e instanceof InvocationTargetException){
				throw new APIException("Error while getting address template", e);
			}
		}
        
        return addressTemplate;
    }

    /**
     * Installed the address hierarchy levels
     */
    public void installAddressHierarchyLevels() {
        AddressHierarchyService service = Context.getService(AddressHierarchyService.class);
        log.info("Installing Address Levels");
        AddressHierarchyLevel lastLevel = null;
        for (AddressComponent component : getAddressComponents()) {
            AddressHierarchyLevel level = new AddressHierarchyLevel();
            level.setAddressField(component.getField());
            level.setRequired(component.isRequiredInHierarchy());
            level.setParent(lastLevel);
            service.saveAddressHierarchyLevel(level);
            lastLevel = level;
        }
    }

    /**
     * Iterate through the levels and update the "required" property in case it has changed
     */
    public void updateRequiredProperty() {
        AddressHierarchyService service = Context.getService(AddressHierarchyService.class);
        log.info("Installing Address Levels");
        for (AddressComponent component : getAddressComponents()) {
            AddressHierarchyLevel level = service.getAddressHierarchyLevelByAddressField(component.getField());
            level.setRequired(component.isRequiredInHierarchy());
            service.saveAddressHierarchyLevel(level);
        }
    }


    /**
     * Installs a new version of the address hierarchy entries
     */
    public void installAddressHierarchyEntries() {
        log.info("Installing Address Hierarchy Entries");
        Context.getService(AddressHierarchyService.class).deleteAllAddressHierarchyEntries();
        InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream(getAddressHierarchyEntryPath());
            AddressHierarchyImportUtil.importAddressHierarchyFile(is, "\\|", "\\^");
        }
        finally {
            IOUtils.closeQuietly(is);
        }
    }

}
