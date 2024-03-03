package com.hibernatedirty.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.hibernatedirty.annotations.IgnoreDirtyProperty;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "tutorials")
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
public class Tutorial extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "title")
  String title;

  @Column(name = "description")
  String description;

  @IgnoreDirtyProperty
  @Column(name = "published")
  Boolean published;
  
  @CreationTimestamp
 LocalDateTime createdDate;
  
  @UpdateTimestamp
  LocalDateTime updateDate;
  

  public Tutorial(String title, String description, boolean published,String auditUser) {
	    this.title = title;
	    this.description = description;
	    this.published = published;
	    this.auditUser = auditUser;
	  }
  
}
