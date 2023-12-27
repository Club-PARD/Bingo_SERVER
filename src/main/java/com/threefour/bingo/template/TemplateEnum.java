package com.threefour.bingo.template;

public enum TemplateEnum {

    KPT(1, "KPT"),
    Ls4(2, "4Ls"),
    F5(3, "F5");

    private final int templateId;
    private final String TemplateName;

    TemplateEnum(int templateId, String templateName) {
        this.templateId = templateId;
        this.TemplateName = templateName;
    }
}
