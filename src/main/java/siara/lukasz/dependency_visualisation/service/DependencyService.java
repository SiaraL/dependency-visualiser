package siara.lukasz.dependency_visualisation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import siara.lukasz.dependency_visualisation.model.Dependency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DependencyService {

    private final CsvService csvService;
    private final PdfService pdfService;

    public List<Dependency> getDependencies() {
        return parsePomFile();
    }

    public byte[] getDependenciesCsv() {
        return csvService.convertDtoToCsv(parsePomFile(), Dependency.class);
    }

    public byte[] getDependenciesPdf() {
        return pdfService.convertDtoToPdf(parsePomFile(), Dependency.class);
    }

    private List<Dependency> parsePomFile() {
        List<Dependency> dependencies = new ArrayList<>();
        try {
            File file = new File("pom.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("dependency");

            for (int i = 0; i < nList.getLength(); i++) {
                Element eElement = (Element) nList.item(i);
                String groupId = eElement.getElementsByTagName("groupId").item(0).getTextContent();
                String artifactId = eElement.getElementsByTagName("artifactId").item(0).getTextContent();
                String version = eElement.getElementsByTagName("version").item(0) != null ? eElement.getElementsByTagName("version").item(0).getTextContent() : "N/A";
                dependencies.add(new Dependency(groupId, artifactId, version));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependencies;
    }
}
