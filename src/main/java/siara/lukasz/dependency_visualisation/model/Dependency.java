package siara.lukasz.dependency_visualisation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;

    @Override public String toString() {
        return String.format("Group ID: %s, Artifact ID: %s, Version: %s", this.groupId, this.artifactId, this.version);
    }
}
