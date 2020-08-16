package com.example.outlooksample.api.response.calendars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value {
    @SerializedName("@odata.id")
    @Expose
    private String odataId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("changeKey")
    @Expose
    private String changeKey;
    @SerializedName("canShare")
    @Expose
    private Boolean canShare;
    @SerializedName("canViewPrivateItems")
    @Expose
    private Boolean canViewPrivateItems;
    @SerializedName("canEdit")
    @Expose
    private Boolean canEdit;
    @SerializedName("allowedOnlineMeetingProviders")
    @Expose
    private List<String> allowedOnlineMeetingProviders = null;
    @SerializedName("defaultOnlineMeetingProvider")
    @Expose
    private String defaultOnlineMeetingProvider;
    @SerializedName("isTallyingResponses")
    @Expose
    private Boolean isTallyingResponses;
    @SerializedName("isRemovable")
    @Expose
    private Boolean isRemovable;
    @SerializedName("owner")
    @Expose
    private Owner owner;

    public String getOdataId() {
        return odataId;
    }

    public void setOdataId(String odataId) {
        this.odataId = odataId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChangeKey() {
        return changeKey;
    }

    public void setChangeKey(String changeKey) {
        this.changeKey = changeKey;
    }

    public Boolean getCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public Boolean getCanViewPrivateItems() {
        return canViewPrivateItems;
    }

    public void setCanViewPrivateItems(Boolean canViewPrivateItems) {
        this.canViewPrivateItems = canViewPrivateItems;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public List<String> getAllowedOnlineMeetingProviders() {
        return allowedOnlineMeetingProviders;
    }

    public void setAllowedOnlineMeetingProviders(List<String> allowedOnlineMeetingProviders) {
        this.allowedOnlineMeetingProviders = allowedOnlineMeetingProviders;
    }

    public String getDefaultOnlineMeetingProvider() {
        return defaultOnlineMeetingProvider;
    }

    public void setDefaultOnlineMeetingProvider(String defaultOnlineMeetingProvider) {
        this.defaultOnlineMeetingProvider = defaultOnlineMeetingProvider;
    }

    public Boolean getIsTallyingResponses() {
        return isTallyingResponses;
    }

    public void setIsTallyingResponses(Boolean isTallyingResponses) {
        this.isTallyingResponses = isTallyingResponses;
    }

    public Boolean getIsRemovable() {
        return isRemovable;
    }

    public void setIsRemovable(Boolean isRemovable) {
        this.isRemovable = isRemovable;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
