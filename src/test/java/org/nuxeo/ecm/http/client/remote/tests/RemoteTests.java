/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id: JOOoConvertPluginImpl.java 18651 2007-05-13 20:28:53Z sfermigier $
 */

package org.nuxeo.ecm.http.client.remote.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nuxeo.ecm.http.client.NuxeoServer;
import org.restlet.data.MediaType;
import org.restlet.resource.Representation;

import junit.framework.TestCase;

public class RemoteTests extends TestCase {

    public void XtestSimpleBA() throws IOException {
        NuxeoServer nxServer = new NuxeoServer("http://127.0.0.1:8080/nuxeo");

        nxServer.setAuthType(NuxeoServer.AUTH_TYPE_BASIC);
        nxServer.setBasicAuthentication("Administrator", "Administrator");

        List<String> pathParams = Arrays.asList("vocabulary", "country");

        Representation res = nxServer.doRestletGetCall(pathParams, null);

        System.out.println(res.getText());
        assertEquals(res.getMediaType().getName(), MediaType.TEXT_XML.getName());
    }

    public void XtestSimpleBAWithParams() throws IOException {
        NuxeoServer nxServer = new NuxeoServer("http://127.0.0.1:8080/nuxeo");

        nxServer.setAuthType(NuxeoServer.AUTH_TYPE_BASIC);
        nxServer.setBasicAuthentication("Administrator", "Administrator");

        List<String> pathParams = Arrays.asList("execQueryModel",
                "USER_DOCUMENTS");

        Map<String, String> queryParams = new HashMap<String, String>();

        queryParams.put("QP1", "$USER");
        queryParams.put("format", "JSON");
        Representation res = nxServer.doRestletGetCall(pathParams, queryParams);
        System.out.println(res.getText());
        assertEquals(res.getMediaType().getName(), MediaType.TEXT_PLAIN.getName());

        queryParams.put("format", "XML");
        res = nxServer.doRestletGetCall(pathParams, queryParams);
        System.out.println(res.getText());
        assertEquals(res.getMediaType().getName(), MediaType.TEXT_XML.getName());
    }

    public void testSimpleSecretWithParams() throws IOException {
        NuxeoServer nxServer = new NuxeoServer("http://127.0.0.1:8080/nuxeo");

        nxServer.setAuthType(NuxeoServer.AUTH_TYPE_SECRET);
        nxServer.setSharedSecretAuthentication("Administrator",
                "nuxeo5secretkey");

        List<String> pathParams = Arrays.asList("execQueryModel",
                "USER_DOCUMENTS");

        Map<String, String> queryParams = new HashMap<String, String>();

        queryParams.put("QP1", "$USER");
        queryParams.put("format", "JSON");
        Representation res = nxServer.doRestletGetCall(pathParams, queryParams);
        System.out.println(res.getText());
        assertEquals(res.getMediaType().getName(), MediaType.TEXT_PLAIN.getName());

        queryParams.put("format", "XML");
        res = nxServer.doRestletGetCall(pathParams, queryParams);
        System.out.println(res.getText());
        assertEquals(res.getMediaType().getName(), MediaType.TEXT_XML.getName());
    }

}
