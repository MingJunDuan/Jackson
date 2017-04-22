package com.mjduan.project.model.comman;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by duan on 2017/4/22.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    protected Name name;
    protected Address address;
    @JsonProperty("sex")
    protected Gender gender;

    public static enum Gender implements IEnum<String> {
        MAN("M"), WOMAN("W");

        private String value;

        Gender(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public Gender ofValue(String value) {
            Gender[] values = values();
            for (Gender gender : values) {
                if (gender.value.equals(value)) {
                    return gender;
                }
            }
            return null;
        }
    }
}
