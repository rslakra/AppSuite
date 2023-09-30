package com.rslakra.appsuite.adtech.entity;

/**
 * @author Rohtash Lakra
 * @created 3/30/20 4:07 PM
 */
public class GeoInfo {

    private Long woeId;
    private Long parentWoeId;
    private WoeIdType type;
    private String name;

    public GeoInfo() {
    }

    public GeoInfo(Long woeId, Long parentWoeId, WoeIdType type, String name) {
        this.woeId = woeId;
        this.parentWoeId = parentWoeId;
        this.type = type;
        this.name = name;
    }

    public Long getWoeId() {
        return woeId;
    }

    public void setWoeId(Long woeId) {
        this.woeId = woeId;
    }

    public Long getParentWoeId() {
        return parentWoeId;
    }

    public void setParentWoeId(Long parentWoeId) {
        this.parentWoeId = parentWoeId;
    }

    public WoeIdType getType() {
        return type;
    }

    public void setType(WoeIdType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        GeoInfo geoInfo = ((GeoInfo) object);
        if (!getWoeId().equals(geoInfo.getWoeId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (getWoeId() ^ (getWoeId() >>> 32));
    }
}
