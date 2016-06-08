//package com.rslakra.bos;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//
//public class OrganizationUnit extends BusinessObject implements Serializable {
//
//	/** <code>serialVersionUID</code> */
//	private static final long serialVersionUID = 1L;
//
//	public String name;
//	public long establishedOn;
//	public String phoneNo;
//	public String abbreviation;
//	public String tableName;
//	public String organisationCode;
//	public long ddno;
//
//	// public Entity entity;
//	public OrganizationUnit parentOrganisationUnit;
//	public Address primaryAddress;
//
//	public String organisationUnitName;
//	public String emailId;
//	public Collection contactPersons;
//
//	public String identification;
//	public String code;
//
//	public String code2;
//
//	public Date createdOn;
//	public String createdBy;
//	public String createdByHost;
//	public String updatedByHost;
//	public Date updatedOn;
//	public String updatedBy;
//
//	private transient static HashMap<String, String> pluralProperties;
//
//	static {
//		if (pluralProperties == null) {
//			pluralProperties = new HashMap();
//		}
//		pluralProperties.put("contactPersons", "contactPerson");
//	}
//
//	/**
//	 * Returns the id.
//	 * 
//	 * @return
//	 */
//	public String getId() {
//		return id;
//	}
//
//	/**
//	 * The id to be set.
//	 * 
//	 * @param id
//	 */
//	public void setId(String id) {
//		if (this.id != id) {
//			String oldId = this.id;
//			this.id = id;
//			firePropertyChange("id", oldId, id);
//		}
//	}
//
//	public static Object getSingularPropertyName(String pluralPropertyName) {
//		if (pluralProperties == null || pluralPropertyName == null)
//			throw new IllegalArgumentException("Either PluralPropertyHashMap or pluralPropertyName is null");
//		Object singularPropertyName = pluralProperties.get(pluralPropertyName);
//		return singularPropertyName;
//	}
//
//	public String getCode2() {
//		return code2;
//	}
//
//	/**
//	 * @param newCode2
//	 */
//	public void setCode2(String newCode2) {
//		try {
//			if (this.code2 != newCode2) {
//				String oldCode2 = this.code2;
//				this.code2 = newCode2;
//				firePropertyChange("code2", (oldCode2), (newCode2));
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setCode2");
//		}
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	/**
//	 * @param newCode
//	 */
//	public void setCode(String newCode) {
//		if (this.code != newCode) {
//			String oldCode = this.code;
//			this.code = newCode;
//			firePropertyChange("code", (oldCode), (newCode));
//		}
//	}
//
//	Collection getContactPersons() {
//		try {
//			if (contactPersons == null)
//				contactPersons = new LinkedList<>();
//			return contactPersons;
//	}
//
//	Iterator<E> getIteratorContactPersons() {
//		if (contactPersons == null) {
//			contactPersons = new LinkedList();
//		}
//		return contactPersons.iterator();
//	}
//
//	/**
//	 * @param newContactPersons
//	 */
//	void setContactPersons(Collection newContactPersons) {
//		try {
//			if (this.contactPersons != newContactPersons) {
//				Collection oldValue = this.contactPersons;
//				this.contactPersons = newContactPersons;
//				firePropertyChange("contactPersons", oldValue, this.contactPersons);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setContactPersons");
//		}
//	}
//
//	/**
//	 * @param newContactPerson
//	 */
//	void addContactPerson(ContactPerson newContactPerson) {
//		try {
//			if (newContactPerson == null)
//				return;
//			if (this.contactPersons == null)
//				this.contactPersons = new LinkedList();
//			if (!this.contactPersons.contains(newContactPerson)) {
//				this.contactPersons.add(newContactPerson);
//				newContactPerson.setContactOf(this);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.addContactPerson");
//		}
//	}
//
//	/**
//	 * @param oldContactPerson
//	 */
//	void removeContactPerson(ContactPerson oldContactPerson) {
//		try {
//			if (oldContactPerson == null)
//				return;
//			if (this.contactPersons != null)
//				if (this.contactPersons.remove(oldContactPerson)) {
//					oldContactPerson.setContactOf((OrganisationUnit) null);
//				}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.removeContactPerson");
//		}
//	}
//
//	void setContactPerson(int i, ContactPerson newContactPerson) {
//		try {
//			ContactPerson oldContactPerson = (ContactPerson) ((List) this.contactPersons).get(i);
//			((List) this.contactPersons).set(i, newContactPerson);
//			if (oldContactPerson != null) {
//				oldContactPerson.setContactOf((OrganisationUnit) null);
//			}
//			if (newContactPerson != null)
//				newContactPerson.setContactOf(this);
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setContactPerson");
//		}
//	}
//
//	ContactPerson getContactPerson(int i) {
//		return (ContactPerson) ((List) this.contactPersons).get(i);
//	}
//
//	void addContactPerson(int i, ContactPerson newContactPerson) {
//		try {
//			((List) this.contactPersons).add(i, null);
//			setContactPerson(i, newContactPerson);
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.addContactPerson");
//		}
//	}
//
//	void removeContactPerson(int i) {
//		try {
//			setContactPerson(i, null);
//			((List) this.contactPersons).remove(i);
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.removeContactPerson");
//		}
//	}
//
//	public String getIdentification() {
//		return identification;
//	}
//
//	/**
//	 * @param newIdentification
//	 */
//	public void setIdentification(String newIdentification) {
//		try {
//			if (this.identification == newIdentification)
//				return;
//			String oldIdentification = this.identification;
//			this.identification = newIdentification;
//			firePropertyChange("identification", (oldIdentification), (newIdentification));
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setIdentification");
//		}
//	}
//
//	public String getEmailId() {
//		return emailId;
//	}
//
//	/**
//	 * @param newEmailId
//	 */
//	public void setEmailId(String newEmailId) {
//		try {
//			if (this.emailId == newEmailId)
//				return;
//			String oldEmailId = this.emailId;
//			this.emailId = newEmailId;
//			firePropertyChange("emailId", (oldEmailId), (newEmailId));
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setEmailId");
//		}
//	}
//
//	public String getOrganisationUnitName() {
//		return organisationUnitName;
//	}
//
//	/**
//	 * @param newOrganisationUnitName
//	 */
//	public void setOrganisationUnitName(String newOrganisationUnitName) {
//		try {
//			if (this.organisationUnitName != newOrganisationUnitName) {
//				String oldOrganisationUnitName = this.organisationUnitName;
//				this.organisationUnitName = newOrganisationUnitName;
//				firePropertyChange("organisationUnitName", (oldOrganisationUnitName), (newOrganisationUnitName));
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setOrganisationUnitName");
//		}
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param newName
//	 */
//	public void setName(String newName) {
//		try {
//			if (this.name != newName) {
//				String oldName = this.name;
//				this.name = newName;
//				firePropertyChange("name", (oldName), (newName));
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setName");
//		}
//	}
//
//	public Date getDateOfOpening() {
//		return dateOfOpening;
//	}
//
//	/**
//	 * @param newDateOfOpening
//	 */
//	public void setDateOfOpening(Date newDateOfOpening) {
//		try {
//			if (this.dateOfOpening != newDateOfOpening) {
//				Date oldDateOfOpening = this.dateOfOpening;
//				this.dateOfOpening = newDateOfOpening;
//				firePropertyChange("dateOfOpening", (oldDateOfOpening), (newDateOfOpening));
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setDateOfOpening");
//		}
//	}
//
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//
//	/**
//	 * @param newPhoneNo
//	 */
//	public void setPhoneNo(String newPhoneNo) {
//		try {
//			if (this.phoneNo == newPhoneNo)
//				return;
//			String oldPhoneNo = this.phoneNo;
//			this.phoneNo = newPhoneNo;
//			firePropertyChange("phoneNo", (oldPhoneNo), (newPhoneNo));
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setPhoneNo");
//		}
//	}
//
//	public String getShortName() {
//		return shortName;
//	}
//
//	/**
//	 * @param newShortName
//	 */
//	public void setShortName(String newShortName) {
//		try {
//			if (this.shortName != newShortName) {
//				String oldShortName = this.shortName;
//				this.shortName = newShortName;
//				firePropertyChange("shortName", (oldShortName), (newShortName));
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setShortName");
//		}
//	}
//
//	public String getTableName() {
//		return tableName;
//	}
//
//	/**
//	 * @param newTableName
//	 */
//	public void setTableName(String newTableName) {
//		if (this.tableName != newTableName) {
//			String oldTableName = this.tableName;
//			this.tableName = newTableName;
//			firePropertyChange("tableName", (oldTableName), (newTableName));
//		}
//	}
//
//	public String getOrganisationCode() {
//		return organisationCode;
//	}
//
//	/**
//	 * @param newOrganisationCode
//	 */
//	public void setOrganisationCode(String newOrganisationCode) {
//		if (this.organisationCode != newOrganisationCode) {
//			String oldOrganisationCode = this.organisationCode;
//			this.organisationCode = newOrganisationCode;
//			firePropertyChange("organisationCode", (oldOrganisationCode), (newOrganisationCode));
//		}
//	}
//
//	public long getDdno() {
//		return ddno;
//	}
//
//	/**
//	 * @param newDdno
//	 */
//	public void setDdno(long newDdno) {
//		try {
//			if (this.ddno == newDdno)
//				return;
//			long oldDdno = this.ddno;
//			this.ddno = newDdno;
//			firePropertyChange("ddno", new Long(oldDdno), new Long(newDdno));
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setDdno");
//		}
//	}
//
//	public Entity getEntity() {
//		return entity;
//	}
//
//	/**
//	 * @param newEntity
//	 */
//	public void setEntity(Entity newEntity) {
//		try {
//			if (this.entity != newEntity)
//
//			{
//				Entity oldEntity = this.entity;
//				this.entity = newEntity;
//				D.outP("oldEntity", oldEntity);
//				D.outP("entity", entity);
//
//				firePropertyChange("entity", oldEntity, newEntity);
//			}
//		} finally {
//			Debugger.D.pop("Vines.OrganisationUnit.setEntity");
//		}
//	}
//
//	public OrganisationUnit getControllingOrganisationUnit() {
//		return controllingOrganisationUnit;
//	}
//
//	/**
//	 * @param newControllingOrganisationUnit
//	 */
//	public void setControllingOrganisationUnit(OrganisationUnit newControllingOrganisationUnit) {
//		try {
//			if (this.controllingOrganisationUnit != newControllingOrganisationUnit) {
//				OrganisationUnit oldControllingOrganisationUnit = this.controllingOrganisationUnit;
//				this.controllingOrganisationUnit = newControllingOrganisationUnit;
//
//				firePropertyChange("controllingOrganisationUnit", oldControllingOrganisationUnit,
//						newControllingOrganisationUnit);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setControllingOrganisationUnit");
//		}
//	}
//
//	public Address getPrimaryAddress() {
//		return primaryAddress;
//	}
//
//	/**
//	 * @param newPrimaryAddress
//	 */
//	public void setPrimaryAddress(Address newPrimaryAddress) {
//		try {
//			if (this.primaryAddress != newPrimaryAddress) {
//				Address oldPrimaryAddress = this.primaryAddress;
//				this.primaryAddress = newPrimaryAddress;
//
//				firePropertyChange("primaryAddress", oldPrimaryAddress, newPrimaryAddress);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setPrimaryAddress");
//		}
//	}
//
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	public Date getCreatedOn() {
//		return createdOn;
//	}
//
//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public Date getUpdatedOn() {
//		return updatedOn;
//	}
//
//	public void setCreatedBy(String newCreatedBy) {
//		try {
//			if (this.createdBy != newCreatedBy) {
//				String oldCreatedBy = this.createdBy;
//				this.createdBy = newCreatedBy;
//
//				firePropertyChange("createdBy", oldCreatedBy, newCreatedBy);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setCreatedBy");
//		}
//	}
//
//	public void setCreatedOn(Date newCreatedOn) {
//		try {
//			if (this.createdOn != newCreatedOn) {
//				Date oldCreatedOn = this.createdOn;
//				this.createdOn = newCreatedOn;
//
//				firePropertyChange("createdOn", oldCreatedOn, newCreatedOn);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setCreatedOn");
//		}
//
//	}
//
//	public void setUpdatedBy(String newUpdatedBy) {
//		if (this.updatedBy != newUpdatedBy) {
//			String oldUpdatedBy = this.updatedBy;
//			this.updatedBy = newUpdatedBy;
//
//			firePropertyChange("updatedBy", oldUpdatedBy, newUpdatedBy);
//		}
//	}
//
//	public void setUpdatedOn(Date newUpdatedOn) {
//		try {
//			if (this.updatedOn != newUpdatedOn) {
//				Date oldUpdatedOn = this.updatedOn;
//				this.updatedOn = newUpdatedOn;
//
//				firePropertyChange("updatedOn", oldUpdatedOn, newUpdatedOn);
//			}
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setUpdatedOn");
//		}
//
//	}
//
//	public String getCreatedByHost() {
//		return createdByHost;
//	}
//
//	public void setCreatedByHost(String createdByHost) {
//		try {
//			this.createdByHost = createdByHost;
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setCreatedByHost");
//		}
//	}
//
//	public String getUpdatedByHost() {
//		return updatedByHost;
//	}
//
//	public void setUpdatedByHost(String updatedByHost) {
//		try {
//			this.updatedByHost = updatedByHost;
//		} finally {
//			// Debugger.D.pop("Vines.OrganisationUnit.setUpdatedByHost");
//		}
//	}
//
//}
