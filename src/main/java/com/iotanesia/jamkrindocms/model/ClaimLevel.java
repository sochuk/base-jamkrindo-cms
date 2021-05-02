package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "claim_level")
@Where(clause = "deleted_at is NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimLevel extends Base {

    @NotNull
    @Column(nullable=false, unique=true)
    private String name;

    @NotNull
    @Column(nullable=false, name = "range_min")
    private BigDecimal rangeMin;

    @NotNull
    @Column(nullable=false, name = "range_max")
    private BigDecimal rangeMax;

    @NotNull
    @Column(nullable=false)
    private Integer level;

    @NotNull
    @Column(nullable=false)
    private String status;

    @Column(length = 5000)
    private String description;


}
