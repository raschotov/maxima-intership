package school.maxima.maximadms.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import school.maxima.maximadms.dto.ContractorDto;

@Service
@AllArgsConstructor
@Slf4j
public class FnsService {

    @Value("${admin.secret.key}")
    private String secretKey;

    public boolean getContractorInnElseThrow(ContractorDto contractorDto) throws IOException {

        String urlProp = String.format(
            "https://api-fns.ru/api/innfl?fam=%s&nam=%s&otch=%s&bdate=%s&doctype=21s&docno=%s&key=%s",
            contractorDto.getSurName(),
            contractorDto.getFirstName(),
            contractorDto.getSurName(),
            contractorDto.getBDate(),
            contractorDto.getCredential().getPassport(),
            secretKey);
        final URL url = new URL(urlProp);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        if (makeJsonFromStream(con).contains("Информация об ИНН не найдена")) {
            return false;
        } else {
            String inn = makeInnFromJson(makeJsonFromStream(con));
            return inn.equals(contractorDto.getCredential().getInn());
        }
    }

    public String makeJsonFromStream(HttpURLConnection con) {

        try (final BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public String makeInnFromJson(String json) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            // convert JSON string to Map<String, List<Map<String, String>>>
            Map<String, List<Map<String, String>>> map = mapper.readValue(json,
                new TypeReference<>() {
                });
            return map.get("items").get(0).get("ИНН");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
