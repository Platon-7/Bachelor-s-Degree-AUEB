package com.r4;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {

    Course course = new Course("Techno");
    Project project = new Project(course,3,"06/06/2021");

    @Test
    public void getMaxNumber(){
        Assert.assertEquals(3,project.getMaxNumber());
    }
    @Test
    public void getDeadline(){
        Assert.assertEquals("06/06/2021",project.getDeadline());
    }
}
