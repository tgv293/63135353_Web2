package vn.gvt.ENote.Models;

public enum UserRoleEnum {
    USER("User", 1),
    ADMIN("Admin", 2);

    private int roleId;
    private String roleName;

    UserRoleEnum(String roleName, int id) {
        this.roleName = roleName;
        this.roleId = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRoleEnum getRoleByName(String roleName) {
        if ("Admin".equals(roleName)) {
            return ADMIN;
        } else {
            return USER;
        }
    }

    public static UserRoleEnum getRoleById(int roleId) {
        switch (roleId) {
            case 2: {
                return ADMIN;
            }
            default: {
                return USER;
            }
        }
    }
}
