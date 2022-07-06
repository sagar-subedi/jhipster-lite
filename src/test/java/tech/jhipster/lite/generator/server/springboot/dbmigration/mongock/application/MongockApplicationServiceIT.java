package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application;

import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
class MongockApplicationServiceIT {

  @Autowired
  private MongockApplicationService mongockApplicationService;

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    // Given
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(TestFileUtils.tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    project.addConfig(BASE_NAME, "foo");

    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    // When
    mongockApplicationService.init(project);

    // Then
    assertDependencies(project);
    assertConfigAndChangeLogFiles(project);
    assertTestFiles(project);
    assertProperties(project);
  }
}
