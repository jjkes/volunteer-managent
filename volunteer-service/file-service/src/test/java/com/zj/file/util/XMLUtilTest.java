package com.zj.file.util;

import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Test;

public class XMLUtilTest extends TestCase {

    @Test
    public void testDiv(){
        Document root = DocumentHelper.createDocument();
        Element div = DocumentHelper.createElement("div");

        Element span = DocumentHelper.createElement("span");

        div.add(span);
        span.addText("dsafsdf");
        div.addAttribute("style","position:absolute;");
        root.add(div);
        System.err.println(root.asXML());
        Assert.assertEquals(1,1);
    }

}