package com.codbex.hyperion.integration.tests;

import org.eclipse.dirigible.integration.tests.api.SecurityIT;
import org.eclipse.dirigible.integration.tests.api.java.messaging.MessagingFacadeIT;
import org.eclipse.dirigible.integration.tests.ui.tests.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({//
        ApproveLeaveRequestBpmIT.class, //
        BPMStarterTemplateIT.class, //
        CreateNewProjectIT.class, //
        CsvimIT.class, //
        CustomSecurityIT.class, //
        DatabasePerspectiveIT.class, //
        DeclineLeaveRequestBpmIT.class, //
        GitPerspectiveIT.class, //
        HomepageRedirectIT.class, //
        MailIT.class, //
        MessagingFacadeIT.class, //
        SecurityIT.class, //
        TerminalIT.class//
})
public class DirigibileCommonTestSuiteIT {
}
