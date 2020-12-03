package com.gxw.store.project.user.dto;


import javax.validation.constraints.NotBlank;

public class NameParam {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
