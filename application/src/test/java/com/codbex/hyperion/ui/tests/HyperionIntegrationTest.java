package com.codbex.hyperion.ui.tests;

import org.eclipse.dirigible.tests.UserInterfaceIntegrationTest;
import org.springframework.context.annotation.Import;

@Import(TestConfigurations.class)
public abstract class HyperionIntegrationTest extends UserInterfaceIntegrationTest {
}
