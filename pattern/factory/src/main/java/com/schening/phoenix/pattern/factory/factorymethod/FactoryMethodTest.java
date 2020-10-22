package com.schening.phoenix.pattern.factory.factorymethod;

import com.schening.phoenix.pattern.factory.ICourse;

/**
 * Created by Tom.
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        ICourseFactory factory = new PythonCourseFactory();
        ICourse course = factory.create();
        course.record();

        factory = new JavaCourseFactory();
        course = factory.create();
        course.record();

    }

}
