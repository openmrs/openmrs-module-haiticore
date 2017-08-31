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

	public static PatientIdentifierTypeDescriptor NIF_ID = new PatientIdentifierTypeDescriptor() {
		public String uuid() { return "e797f826-8e8f-11e7-bb31-be2e44b06b34"; }
		public String name() { return "Numéro d'identité fiscale (NIF)"; }
		public String description() { return "The Haiti Tax Identification Number (NIF) is issued by the Direction Générale des Impôts (DGI) from 1987 and which has 10 digits. Any natural or legal person, whatever his legal form or his personality, must take this number."; }
	};

	public static PatientIdentifierTypeDescriptor CIN_ID = new PatientIdentifierTypeDescriptor() {
		public String uuid() { return "e797face-8e8f-11e7-bb31-be2e44b06b34"; }
		public String name() { return "Carte d'identification nationale"; }
		public String description() { return "The Haiti national identification card (carte d'identification nationale) was supposed to replace the fiscal identity card.  Created 2005."; }
	};

}