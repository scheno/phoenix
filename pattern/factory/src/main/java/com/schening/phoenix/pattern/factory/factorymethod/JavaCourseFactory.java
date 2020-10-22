package com.schening.phoenix.pattern.factory.factorymethod;

import com.schening.phoenix.pattern.factory.ICourse;
import com.schening.phoenix.pattern.factory.JavaCourse;

/**
 * Created by Tom.
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
