/*
 * Copyright (c) 2022 codbex or an codbex affiliate company and contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-FileCopyrightText: 2022 codbex or an codbex affiliate company and contributors
 * SPDX-License-Identifier: EPL-2.0
 */
package com.codbex.hyperion.integration.tests;

import org.eclipse.dirigible.tests.framework.browser.HtmlElementType;
import org.eclipse.dirigible.tests.framework.ide.IDE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class HomePageIT extends HyperionIntegrationTest {

    @Autowired
    private IDE ide;

    @Test
    void testOpenHomepage() {
        ide.openHomePage();

        browser.assertElementExistsByTypeAndText(HtmlElementType.SPAN, "Hyperion");
        browser.assertElementExistsByTypeAndText(HtmlElementType.HEADER3, "Welcome to Hyperion");
    }
}
