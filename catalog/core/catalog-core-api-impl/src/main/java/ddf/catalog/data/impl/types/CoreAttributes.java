/**
 * Copyright (c) Codice Foundation
 * <p>
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details. A copy of the GNU Lesser General Public License
 * is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package ddf.catalog.data.impl.types;

import java.util.HashSet;
import java.util.Set;

import ddf.catalog.data.AttributeDescriptor;
import ddf.catalog.data.MetacardType;
import ddf.catalog.data.impl.AttributeDescriptorImpl;
import ddf.catalog.data.impl.BasicTypes;
import ddf.catalog.data.types.Core;

/**
 * This class provides attributes that reflect core attributes expected to be relevant to all metacard types
 */
public class CoreAttributes implements Core, MetacardType {
    private static final Set<AttributeDescriptor> DESCRIPTORS = new HashSet<>();

    private static final String NAME = "core";

    static {
        DESCRIPTORS.add(new AttributeDescriptorImpl(CHECKSUM,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(CHECKSUM_ALGORITHM,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(CREATED,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.DATE_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(DESCRIPTION,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(EXPIRATION,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.DATE_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(ID,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(LANGUAGE,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(LOCATION,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.GEO_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METADATA,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.XML_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(MODIFIED,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.DATE_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(DERIVED_RESOURCE_DOWNLOAD_URL,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(DERIVED_RESOURCE_URI,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(RESOURCE_DOWNLOAD_URL,
                true /* indexed */,
                false /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(RESOURCE_SIZE,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(RESOURCE_URI,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(SOURCE_ID,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(THUMBNAIL,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.BINARY_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(TITLE,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                false /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METACARD_CREATED,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.DATE_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METACARD_MODIFIED,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                false /* multivalued */,
                BasicTypes.DATE_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METACARD_OWNER,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.STRING_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METACARD_TAGS,
                true /* indexed */,
                true /* stored */,
                false /* tokenized */,
                true /* multivalued */,
                BasicTypes.OBJECT_TYPE));
        DESCRIPTORS.add(new AttributeDescriptorImpl(METACARD_PERMISSIONS,
                true /* indexed */,
                true /* stored */,
                true /* tokenized */,
                true /* multivalued */,
                BasicTypes.XML_TYPE));
    }

    @Override
    public Set<AttributeDescriptor> getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public AttributeDescriptor getAttributeDescriptor(String name) {
        for (AttributeDescriptor attributeDescriptor : DESCRIPTORS) {
            if (attributeDescriptor.getName()
                    .equals(name)) {
                return attributeDescriptor;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
