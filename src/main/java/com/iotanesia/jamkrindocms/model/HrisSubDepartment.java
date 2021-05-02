package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hris_sub_department")
@Where(clause = "deleted_at is NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrisSubDepartment extends Base {

    @NotNull
    @Column(name = "code", length = 10)
    private String code;

    @NotNull
    @Column(name = "name", length = 255)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private HrisDepartment hrisDepartment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "management_id")
    private HrisManagement hrisManagement;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private HrisDivision hrisDivision;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HrisDepartment getHrisDepartment() {
        return hrisDepartment;
    }

    public void setHrisDepartment(HrisDepartment hrisDepartment) {
        this.hrisDepartment = hrisDepartment;
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
}
