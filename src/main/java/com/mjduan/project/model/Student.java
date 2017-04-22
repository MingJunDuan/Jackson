package com.mjduan.project.model;

import com.mjduan.project.model.comman.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Created by duan on 2017/4/22.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    private int id;
}
