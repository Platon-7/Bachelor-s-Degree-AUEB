package com.r4;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

    Student student = new Student("Cristiano","Ronaldo","p3180068","cr7forthewin","cr7@gmail.com",null,null);
    Account account = new Account(student);

    @Test
    public void getEmail(){
        Assert.assertEquals("cr7@gmail.com",account.getEmail());
    }
    @Test
    public void getPassword(){
        Assert.assertEquals("cr7forthewin",account.getPassword());
    }
    @Test
    public void getAM(){
        Assert.assertEquals("p3180068",account.getAM());
    }
    @Test
    public void setPassword(){
        account.setPassword("messiisbetter");
        Assert.assertEquals("messiisbetter",account.getPassword());
    }
}