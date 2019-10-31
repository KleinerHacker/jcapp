package org.pcsoft.framework.jcapp.type;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JCBoundsTest {

    @Test
    public void testSizeToPoints() {
        final JCBounds bounds = JCBounds.createWithSize(1, 1, 10, 5);
        Assert.assertEquals(11, bounds.getRight());
        Assert.assertEquals(6, bounds.getBottom());
        Assert.assertEquals(new JCPoint(11, 6), bounds.getRightBottom());
    }

    @Test
    public void testPointsToSize() {
        final JCBounds bounds = JCBounds.createWithPoints(1, 1, 11, 6);
        Assert.assertEquals(10, bounds.getWidth());
        Assert.assertEquals(5, bounds.getHeight());
        Assert.assertEquals(new JCSize(10, 5), bounds.getSize());
    }

    @Test
    public void testSizeToCenter() {
        final JCBounds bounds = JCBounds.createWithSize(3, 4, 10, 5);
        Assert.assertEquals(8, bounds.getCenterX());
        Assert.assertEquals(6, bounds.getCenterY());
        Assert.assertEquals(new JCPoint(8, 6), bounds.getCenter());
    }

    @Test
    public void testCenterToSize() {
        final JCBounds bounds = JCBounds.createWithCenter(6, 5, 10, 5);
        Assert.assertEquals(1, bounds.getLeft());
        Assert.assertEquals(3, bounds.getTop());
        Assert.assertEquals(new JCPoint(1, 3), bounds.getLeftTop());
    }

}