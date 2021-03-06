/**
 * Copyright (c) Codice Foundation
 * <p/>
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 **/

package org.codice.ddf.spatial.ogc.csw.catalog.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.path.Path;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.StaxReader;

public class TestXstreamPathConverter {

    private static final String GML_XML =
            "<gml:Polygon id=\"p1\" xmlns:gml=\"http://www.opengis.net/gml\">\n"
                    + "    <gml:exterior>\n" + "        " + "<gml:LinearRing>\n"
                    + "            <gml:pos>-180.000000 90.000000</gml:pos>\n"
                    + "            <gml:pos>-180.000000 -90.0000000</gml:pos>\n"
                    + "            <gml:pos>180.000000 -90.000000</gml:pos>\n"
                    + "            <gml:pos>180.000000 90.0000000 </gml:pos>\n"
                    + "            <gml:pos>180.000000 90.000000 </gml:pos>\n"
                    + "        </gml:LinearRing>\n" + "    " + "</gml:exterior>\n"
                    + "</gml:Polygon>" + "";

    private static final Path POLYGON_POS_PATH = new Path("/Polygon/exterior/LinearRing/pos");

    private static final Path BAD_PATH = new Path("/Polygon/a/b/c");

    private static final Path POLYGON_GML_ID_PATH = new Path("/Polygon/@id");

    private static final String GML_NAMESPACE = "";

    private XStream xstream;

    private DataHolder argumentHolder;

    @Before
    public void setup() {

        QNameMap qmap = new QNameMap();
        qmap.setDefaultNamespace(GML_NAMESPACE);
        qmap.setDefaultPrefix("");
        StaxDriver staxDriver = new StaxDriver(qmap);

        xstream = new XStream(staxDriver);
        xstream.setClassLoader(this.getClass()
                .getClassLoader());
        XstreamPathConverter converter = new XstreamPathConverter();
        xstream.registerConverter(converter);
        xstream.alias("Polygon", XstreamPathValueTracker.class);
        argumentHolder = xstream.newDataHolder();

        Set<Path> paths = new LinkedHashSet<>();
        paths.addAll(Arrays.asList(POLYGON_POS_PATH, BAD_PATH, POLYGON_GML_ID_PATH));
        argumentHolder.put(XstreamPathConverter.PATH_KEY, paths);

    }

    @Test
    public void testAttributeValid() throws XMLStreamException {
        XMLStreamReader streamReader = XMLInputFactory.newInstance()
                .createXMLStreamReader(new StringReader(GML_XML));
        HierarchicalStreamReader reader = new StaxReader(new QNameMap(), streamReader);
        XstreamPathValueTracker pathValueTracker = (XstreamPathValueTracker) xstream.unmarshal(
                reader,
                null,
                argumentHolder);

        assertThat(pathValueTracker.getFirstValue(POLYGON_GML_ID_PATH), is("p1"));

    }

    @Test
    public void testGetFirstNodeValue() throws XMLStreamException {
        XMLStreamReader streamReader = XMLInputFactory.newInstance()
                .createXMLStreamReader(new StringReader(GML_XML));
        HierarchicalStreamReader reader = new StaxReader(new QNameMap(), streamReader);
        XstreamPathValueTracker pathValueTracker = (XstreamPathValueTracker) xstream.unmarshal(
                reader,
                null,
                argumentHolder);
        assertThat(pathValueTracker.getFirstValue(POLYGON_POS_PATH), is("-180.000000 90.000000"));

    }

    @Test
    public void testBadPath() throws XMLStreamException {
        XMLStreamReader streamReader = XMLInputFactory.newInstance()
                .createXMLStreamReader(new StringReader(GML_XML));
        HierarchicalStreamReader reader = new StaxReader(new QNameMap(), streamReader);
        XstreamPathValueTracker pathValueTracker = (XstreamPathValueTracker) xstream.unmarshal(
                reader,
                null,
                argumentHolder);
        assertThat(pathValueTracker.getFirstValue(BAD_PATH), nullValue());

    }

    @Test
    public void testPathEquality() {
        XstreamPathConverter converter = new XstreamPathConverter();
        Path path1 = new Path("/a/b/c");
        Path path2 = new Path("/a[1]/b[1]/c[1]");
        assertThat(converter.doBasicPathsMatch(path1, path2), is(true));
        assertThat(converter.doBasicPathsMatch(path2, path1), is(true));
        assertThat(converter.doBasicPathsMatch(path1, path1), is(true));
        assertThat(converter.doBasicPathsMatch(path2, path2), is(true));
    }

}
