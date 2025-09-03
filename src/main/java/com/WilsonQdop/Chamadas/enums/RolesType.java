package com.WilsonQdop.Chamadas.enums;

public enum RolesType {
    USER(3),
    ADM(1),
    TECH(2);

    long roleId;

    RolesType(long roleId) {
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
