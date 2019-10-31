package org.pcsoft.framework.jcapp.type;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JCPointTest {

    @Test
    public void add() {
        final JCPoint point = new JCPoint(10, 5).add(new JCPoint(3, 5));
        Assert.assertEquals(13, point.getX());
        Assert.assertEquals(10, point.getY());
        Assert.assertEquals(new JCPoint(13, 10), point);
    }

    @Test
    public void sub() {
        final JCPoint point = new JCPoint(10, 5).sub(new JCPoint(3, 5));
        Assert.assertEquals(7, point.getX());
        Assert.assertEquals(0, point.getY());
        Assert.assertEquals(new JCPoint(7, 0), point);
    }
}