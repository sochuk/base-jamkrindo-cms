package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Where(clause = "deleted_at is NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users extends Base {

	@NotNull
	@Column(name = "name", length = 200)
	private String name;

	@NotNull
	@Column(name = "nik", unique = true, length = 18)
	private String nik;

	@NotNull
	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "remember_token", columnDefinition = "TEXT")
	private String rememberToken;

	@NotNull
	@Column(name = "email", unique = true, length = 200)
	private String email;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private Roles roles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "management_id")
	private HrisManagement hrisManagement;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id")
	private HrisDivision hrisDivision;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departement_id")
	private HrisDepartment hrisDepartment;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_departement_id")
	private HrisSubDepartment hrisSubDepartment;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private HrisSection hrisSection;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_section_id")
	private HrisSubSection hrisSubSection;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	private HrisPosition hrisPosition;

	@NotNull
	@Column(name = "grade_code", length = 50)
	private String gradeCode;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "functional_id")
	private HrisFunctional hrisFunctional;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	private HrisBranch hrisBranch;

	@NotNull
	@Column(name = "status", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean status;

	@NotNull
	@Column(name = "is_delete", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isDelete;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public HrisManagement getHrisManagement() {
		return hrisManagement;
	}

	public void setHrisManagement(HrisManagement hrisManagement) {
		this.hrisManagement = hrisManagement;
	}

	public HrisDivision getHrisDivision() {
		return hrisDivision;
	}

	public void setHrisDivision(HrisDivision hrisDivision) {
		this.hrisDivision = hrisDivision;
	}

	public HrisDepartment getHrisDepartment() {
		return hrisDepartment;
	}

	public void setHrisDepartment(HrisDepartment hrisDepartment) {
		this.hrisDepartment = hrisDepartment;
	}

	public HrisSubDepartment getHrisSubDepartment() {
		return hrisSubDepartment;
	}

	public void setHrisSubDepartment(HrisSubDepartment hrisSubDepartment) {
		this.hrisSubDepartment = hrisSubDepartment;
	}

	public HrisSection getHrisSection() {
		return hrisSection;
	}

	public void setHrisSection(HrisSection hrisSection) {
		this.hrisSection = hrisSection;
	}

	public HrisSubSection getHrisSubSection() {
		return hrisSubSection;
	}

	public void setHrisSubSection(HrisSubSection hrisSubSection) {
		this.hrisSubSection = hrisSubSection;
	}

	public HrisPosition getHrisPosition() {
		return hrisPosition;
	}

	public void setHrisPosition(HrisPosition hrisPosition) {
		this.hrisPosition = hrisPosition;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public HrisFunctional getHrisFunctional() {
		return hrisFunctional;
	}

	public void setHrisFunctional(HrisFunctional hrisFunctional) {
		this.hrisFunctional = hrisFunctional;
	}

	public HrisBranch getHrisBranch() {
		return hrisBranch;
	}

	public void setHrisBranch(HrisBranch hrisBranch) {
		this.hrisBranch = hrisBranch;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		status = status;
	}

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}
}

