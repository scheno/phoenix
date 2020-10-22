package com.schening.phoenix.pattern.factory.factorymethod;

import com.schening.phoenix.pattern.factory.ICourse;
import com.schening.phoenix.pattern.factory.PythonCourse;

/**
 * Created by Tom.
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
