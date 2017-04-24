/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.haiticore.metadata;

import org.openmrs.module.emrapi.EmrApiConstants;
import org.openmrs.module.metadatadeploy.descriptor.EncounterTypeDescriptor;

/**
 * Constants for all defined encounter types
 */
public class HaitiEncounterTypes {

	public static EncounterTypeDescriptor PATIENT_REGISTRATION  = new EncounterTypeDescriptor() {
		public String uuid() { return "873f968a-73a8-4f9c-ac78-9f4778b751b6"; }
		public String name() { return "Enregistrement de patient"; }
		public String description() { return "Patient registration -- normally a new patient"; }
	};

}