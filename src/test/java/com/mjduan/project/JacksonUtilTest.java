package com.mjduan.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjduan.project.model.SchoolClass;
import com.mjduan.project.model.Student;
import com.mjduan.project.model.comman.Address;
import com.mjduan.project.model.comman.Name;
import com.mjduan.project.model.comman.Person;
import com.mjduan.project.util.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by duan on 2017/4/22.
 */
public class JacksonUtilTest {
    private static final Logger LOG = Logger.getLogger(JacksonUtilTest.class.getCanonicalName());

    @Test
    public void testSerSerialize(){
        SchoolClass schoolClass = getSchoolClass();
        String jsonStr = JacksonUtil.toJsonStr(schoolClass);

        String expectedStr = "{\"name\":\"class1\",\"students\":[{\"id\":1,\"name\":{\"firstName\":\"MingJun\",\"lastName\":\"Duan\"},\"address\":{\"postalCode\":\"400450\",\"street\":\"street1\"},\"sex\":\"M\"},{\"id\":1,\"name\":{\"firstName\":\"MingJun\",\"lastName\":\"Duan\"},\"address\":{\"postalCode\":\"400450\",\"street\":\"street1\"},\"sex\":\"M\"}]}";
        Assertions.assertEquals(jsonStr,expectedStr);
    }

    @Test
    public void testDeserialize(){
        String expectedStr = "{\"name\":\"class1\",\"unknown_key\":\"unknown_value\",\"students\":[{\"id\":1,\"name\":{\"firstName\":\"MingJun\",\"lastName\":\"Duan\"},\"address\":{\"postalCode\":\"400450\",\"street\":\"street1\"},\"sex\":\"M\"},{\"id\":1,\"name\":{\"firstName\":\"MingJun\",\"lastName\":\"Duan\"},\"address\":{\"postalCode\":\"400450\",\"street\":\"street1\"},\"sex\":\"M\"}]}";
        SchoolClass schoolClass = JacksonUtil.toObject(expectedStr, SchoolClass.class);
        Assertions.assertEquals("class1",schoolClass.getName());
        Assertions.assertEquals(2,schoolClass.getStudents().size());
        Assertions.assertEquals(Person.Gender.MAN,schoolClass.getStudents().get(0).getGender());
    }

    @Test
    public void testSerializeIncludeNonEmpty() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        Name name = new Name("", "lastname");
        String jsonStr = objectMapper.writeValueAsString(name);
        //there is no firstName field, because the value of firstName is ""
        LOG.info(jsonStr);
    }

    @Test
    public void testTransformJsonStrToList(){
        List<Name> names = Arrays.asList(new Name("firstName", "lastName"), new Name("firstName", "lastName"));
        String jsonStr = JacksonUtil.toJsonStr(names);
        LOG.info(jsonStr);

        List<Name> nameList = JacksonUtil.toList(jsonStr, Name.class);
        Assertions.assertEquals(2,nameList.size());
        Assertions.assertEquals("firstName",nameList.get(0).getFirstName());
        Assertions.assertEquals("lastName",nameList.get(0).getLastName());
    }

    private SchoolClass getSchoolClass(){
        return new SchoolClass("class1", Arrays.asList(getStudent(), getStudent()));
    }

    private Student getStudent(){
        Name name = new Name().builder().firstName("MingJun").lastName("Duan").build();

        Student student = new Student();
        student.setId(1);
        student.setName(name);
        student.setAddress(getAddress());
        student.setGender(Person.Gender.MAN);
        return student;
    }

    private Address getAddress(){
        return new Address().builder().postalCode("400450").street("street1").build();
    }

}
