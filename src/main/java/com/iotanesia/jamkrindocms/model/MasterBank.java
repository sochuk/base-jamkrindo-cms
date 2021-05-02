package com.iotanesia.jamkrindocms.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class MasterBank extends Base{

    @NotNull
    @Column(name = "code", unique = true, length = 50)
    private String code;

    @NotNull
    @Column(name = "name", length = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
