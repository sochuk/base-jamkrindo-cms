package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hris_position")
@Where(clause = "deleted_at is null")
@AllArgsConstructor
@NoArgsConstructor
public class HrisPosition extends Base {

    @NotNull
    @Column(name = "code", length = 100)
    private String code;

    @NotNull
    @Column(name = "name", length = 100)
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
