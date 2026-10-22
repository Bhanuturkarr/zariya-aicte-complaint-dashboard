package org.zariya.casework;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity @Table(name="complaints", indexes={@Index(name="idx_complaint_institution",columnList="institution_id"),@Index(name="idx_complaint_status",columnList="status")})
public class Complaint {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="case_ref",nullable=false,unique=true) private String caseRef;
    @Column(name="institution_id",nullable=false) private String institutionId;
    @Column(nullable=false) private String institution;
    @Column(nullable=false) private String title;
    @Column(nullable=false) private String bureau;
    @Column(nullable=false) private String category;
    @Column(nullable=false) private String priority;
    @Column(nullable=false) private String status;
    private String officer;
    private LocalDate dueDate;
    protected Complaint() {}
    public Complaint(String caseRef,String institutionId,String institution,String title,String bureau,String category,String priority,String status,String officer,LocalDate dueDate){this.caseRef=caseRef;this.institutionId=institutionId;this.institution=institution;this.title=title;this.bureau=bureau;this.category=category;this.priority=priority;this.status=status;this.officer=officer;this.dueDate=dueDate;}
    public Long getId(){return id;} public String getCaseRef(){return caseRef;} public String getInstitutionId(){return institutionId;} public String getInstitution(){return institution;} public String getTitle(){return title;} public String getBureau(){return bureau;} public String getCategory(){return category;} public String getPriority(){return priority;} public String getStatus(){return status;} public String getOfficer(){return officer;} public LocalDate getDueDate(){return dueDate;}
}
