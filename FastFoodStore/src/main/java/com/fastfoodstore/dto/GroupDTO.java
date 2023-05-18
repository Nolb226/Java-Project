package com.fastfoodstore.dto;

public class GroupDTO {
    private String groupCode;
    private String groupName;
    private String groupIcon;
    private String IN_groupCode;
    private boolean inMenu;

    public GroupDTO() {

    }

    public GroupDTO(String groupCode, String groupName, String groupIcon, String IN_groupCode, boolean inMenu) {
        this.IN_groupCode = IN_groupCode;
        this.groupCode = groupCode;
        this.groupIcon = groupIcon;
        this.groupName = groupName;
        this.inMenu = inMenu;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setIN_groupCode(String iN_groupCode) {
        IN_groupCode = iN_groupCode;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getIN_groupCode() {
        return IN_groupCode;
    }

    public boolean getInMenu() {
        return inMenu;
    }
    
}
