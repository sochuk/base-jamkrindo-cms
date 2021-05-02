package com.iotanesia.jamkrindocms.constant;

public class RolesTypeConstant {
    private RolesTypeConstant() {

    }

    public enum RolesType {
        SUPER_ADMIN("ROLE_SUPER_ADMIN"),
        ADMIN("ROLE_ADMIN"),
        MITRA("ROLE_MITRA"),
        CLAIM_PERSON("ROLE_CLAIM_PERSON"),
        APPROVAL("ROLE_APPROVAL");

        private String value;

        RolesType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
