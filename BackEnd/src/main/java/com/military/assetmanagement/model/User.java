
package com.military.assetmanagement.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private int baseId;

    public User(int id, String username, String password, String role, int baseId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.baseId = baseId;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public int getBaseId() { return baseId; }
}
