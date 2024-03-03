package com.hibernatedirty.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
public class AuditEntity {

	protected String auditUser;
		
	@UpdateTimestamp
	protected LocalDateTime auditTimeStamp;

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public LocalDateTime getAuditTimeStamp() {
		return auditTimeStamp;
	}

	public void setAuditTimeStamp(LocalDateTime auditTimeStamp) {
		this.auditTimeStamp = auditTimeStamp;
	}
	
	
}
