package com.iotanesia.jamkrindocms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="permission")
@Where(clause = "deleted_at is NULL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends Base{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_position_id")
    private RolesPosition rolesPosition;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MasterMenu masterMenu;

    public RolesPosition getRolesPosition() {
        return rolesPosition;
    }

    public void setRolesPosition(RolesPosition rolesPosition) {
        this.rolesPosition = rolesPosition;
    }

    public MasterMenu getMasterMenu() {
        return masterMenu;
    }

    public void setMasterMenu(MasterMenu masterMenu) {
        this.masterMenu = masterMenu;
    }
}

