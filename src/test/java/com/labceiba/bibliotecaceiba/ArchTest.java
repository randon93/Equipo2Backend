package com.labceiba.bibliotecaceiba;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.labceiba.bibliotecaceiba");

        noClasses()
            .that()
                .resideInAnyPackage("com.labceiba.bibliotecaceiba.service..")
            .or()
                .resideInAnyPackage("com.labceiba.bibliotecaceiba.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.labceiba.bibliotecaceiba.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
