package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Entity
@Table(name = "global_params")
@Where(clause = "deleted_at is NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalParams extends Base {

    @NotNull
    @Column(name = "type", length = 50)
    private String type;

    @NotNull
    @Column(name = "code", length = 50)
    private String code;

    @NotNull
    @Column(name = "value", length = 50)
    private String value;

    @Column(name = "number_code", length = 50, nullable = true)
    private String number_code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNumber_code() {
        return number_code;
    }

    public void setNumber_code(String number_code) {
        this.number_code = number_code;
    }
}
