package com.rslakra.appsuite.jdk.enums;

import com.rslakra.appsuite.core.BeanUtils;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 11/4/21 12:52 PM
 */
public class EnumMapper<E extends Enum<E>> {

    private static final String OTHERS = String.valueOf(UUID.randomUUID());
    private final Map<String, E> mapEnums;
    private final Class<E> type;

    /**
     * @param type
     */
    private EnumMapper(final Class<E> type) {
        this.type = type;
        this.mapEnums = new HashMap<>(type.getEnumConstants().length);
    }

    /**
     * @return
     */
    public int size() {
        return this.mapEnums.size();
    }

    /**
     * @param key
     * @return
     */
    public final E toEnum(final String key) {
        if (mapEnums.containsKey(key)) {
            return mapEnums.get(key);
        } else if (BeanUtils.isEmpty(key)) {
            return null;
        } else if (mapEnums.containsKey(OTHERS)) {
            return mapEnums.get(OTHERS);
        }

        throw new IllegalStateException("Unmapped value " + key + " of type " + type.getSimpleName());
    }

    /**
     * @param object
     * @return
     */
    public final String from(final Object object) {
        return mapEnums.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), object)).findFirst().get()
                .getKey();
    }

    /**
     * @return
     */
    public final EnumPropertyEditor<E> getEditor() {
        return new EnumPropertyEditor<>(this);
    }

    /**
     * @param type
     * @return
     */
    public static <E> Builder newBuilder(final Class<E> type) {
        return new Builder(type);
    }

    /**
     * @param <E>
     */
    public static final class Builder<E extends Enum<E>> {

        private final EnumMapper<E> enumMapper;

        /**
         * @param type
         */
        private Builder(final Class<E> type) {
            this.enumMapper = new EnumMapper<>(type);
        }

        /**
         * @param key
         * @param value
         * @return
         */
        public Builder<E> map(final String key, E value) {
            this.enumMapper.mapEnums.put(key, value);
            return this;
        }

        /**
         * @param value
         * @return
         */
        public Builder<E> mapOthers(final E value) {
            return map(EnumMapper.OTHERS, value);
        }

        public EnumMapper<E> build() {
            return this.enumMapper;
        }

        public EnumPropertyEditor<E> getEditor() {
            return new EnumPropertyEditor<>(this.enumMapper);
        }
    }

    public static final class EnumPropertyEditor<E extends Enum<E>> extends PropertyEditorSupport {

        private final EnumMapper<E> enumMapper;

        /**
         * @param enumMapper
         */
        private EnumPropertyEditor(final EnumMapper<E> enumMapper) {
            this.enumMapper = enumMapper;
        }

        /**
         * @param key
         * @throws IllegalArgumentException
         */
        @Override
        public void setAsText(final String key) throws IllegalArgumentException {
            setValue(enumMapper.toEnum(key));
        }
    }
}
