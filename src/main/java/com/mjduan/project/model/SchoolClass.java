package com.mjduan.project.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

/**
 * Created by duan on 2017/4/22.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchoolClass {
    //school class name
    String name;
    List<Student> students = Collections.EMPTY_LIST;
}
