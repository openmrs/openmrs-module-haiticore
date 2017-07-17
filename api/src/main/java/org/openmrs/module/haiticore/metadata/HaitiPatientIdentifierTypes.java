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

import org.openmrs.module.metadatadeploy.descriptor.PatientIdentifierTypeDescriptor;

/**
 * Constants for all defined patient identifier types
 */
public class HaitiPatientIdentifierTypes {

	public static PatientIdentifierTypeDescriptor BIOMETRIC_REF_NUMBER = new PatientIdentifierTypeDescriptor() {
		public String uuid() { return "e26ca279-8f57-44a5-9ed8-8cc16e90e559"; }
		public String name() { return "Biometrics Reference Code"; }
		public String description() { return "Code referencing a patient's record in an external biometrics system"; }
	};

	public static PatientIdentifierTypeDescriptor SEDISH_MPI_ECID = new PatientIdentifierTypeDescriptor() {
		public String uuid() { return "f54ed6b9-f5b9-4fd5-a588-8f7561a78401"; }
		public String name() { return "ECID"; }
		public String description() { return "Code referencing a patient's record in the SEDISH Master Person Index (Enterprise Client ID)"; }
	};

}