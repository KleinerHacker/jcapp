package org.pcsoft.framework.jcapp.type;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JCSizeTest {

    @Test
    public void add() {
        final JCSize size = new JCSize(10, 5).add(new JCSize(5, 3));
        Assert.assertEquals(15, size.getWidth());
        Assert.assertEquals(8, size.getHeight());
        Assert.assertEquals(new JCSize(15, 8), size);
    }

    @Test
    public void sub() {
        final JCSize size = new JCSize(10, 5).sub(new JCSize(5, 3));
        Assert.assertEquals(5, size.getWidth());
        Assert.assertEquals(2, size.getHeight());
        Assert.assertEquals(new JCSize(5, 2), size);
    }
}