package siara.lukasz.dependency_visualisation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import siara.lukasz.dependency_visualisation.controller.DependencyController;
import siara.lukasz.dependency_visualisation.model.Dependency;
import siara.lukasz.dependency_visualisation.service.DependencyService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(DependencyController.class)
public class DependencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DependencyService dependencyService;

    @Test
    public void testGetDependencies() throws Exception {
        // Given
        List<Dependency> dependencies = Arrays.asList(
                new Dependency("abc.com", "artifact1", "1.0.0"),
                new Dependency("xyz.org", "artifact2", "2.0.0")
                                                     );

        // When
        when(dependencyService.getDependencies()).thenReturn(dependencies);

        // Then
        MvcResult result = mockMvc.perform(get("/api/dependencies"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        assertThat(jsonResponse).contains("groupId", "artifactId", "version");
    }
}
