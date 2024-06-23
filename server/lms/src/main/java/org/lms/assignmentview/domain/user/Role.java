package org.lms.assignmentview.domain.user;

public enum Role {
    Student,
    Staff;

    public boolean isStaff() {
        return this == Staff;
    }
}
