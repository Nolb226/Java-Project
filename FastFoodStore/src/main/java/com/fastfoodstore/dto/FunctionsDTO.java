package com.fastfoodstore.dto;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FunctionsDTO {
    private String functionCode;
    private String functionName;
    private String functionIcon;

    public FunctionsDTO() {

    }

    public FunctionsDTO(String functionCode, String functionName, String functionIcon) {
        this.functionCode = functionCode;
        this.functionName = functionName;
        this.functionIcon = functionIcon;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setFunctionIcon(String functionIcon) {
        this.functionIcon = functionIcon;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getFunctionIcon() {
        return functionIcon;
    }

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource(functionIcon));
    }
}
