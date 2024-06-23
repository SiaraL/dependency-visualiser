package siara.lukasz.dependency_visualisation.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import siara.lukasz.dependency_visualisation.exception.CsvException;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("prototype")
public class CsvService {
    public byte[] convertDtoToCsv(List input, Class clazz) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(clazz).withHeader();
            mapper.writer(schema).writeValue(out, input);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CsvException("Cannot create csv file.");
        }
    }
}
