package com.iotanesia.jamkrindocms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hris_sub_section")
@Where(clause = "deleted_at is null")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrisSubSection extends Base {

    @NotNull
    @Column( nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(nullable = false, length = 500)
    private String name;

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
}