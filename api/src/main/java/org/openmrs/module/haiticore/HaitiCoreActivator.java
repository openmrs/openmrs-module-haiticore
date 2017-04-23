/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.haiticore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypeBundle;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiEncounterTypeBundle;
import org.openmrs.module.haiticore.org.openmrs.module.haiticore.metadata.HaitiAddressBundle;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class HaitiCoreActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see #started()
	 */
	public void started() {
		log.info("Started Haiti Core");
		installMetadataBundles();
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown Haiti Core");
	}

	private void installMetadataBundles() {
		MetadataDeployService deployService = Context.getService(MetadataDeployService.class);
		deployService.installBundle(Context.getRegisteredComponents(HaitiPersonAttributeTypeBundle.class).get(0));
		deployService.installBundle(Context.getRegisteredComponents(HaitiEncounterTypeBundle.class).get(0));
		deployService.installBundle(Context.getRegisteredComponents(HaitiAddressBundle.class).get(0));
	}
	
}
