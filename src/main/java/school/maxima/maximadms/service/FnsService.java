package school.maxima.maximadms.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import school.maxima.maximadms.dto.ContractorDto;

@Service
@AllArgsConstructor
@Slf4j
public class FnsService {

    @Value("${admin.secret.key}")
    private String secretKey;

    public boolean getContractorInn(ContractorDto contractorDto) {

        String urlPropTemplate = "https://api-fns.ru/api/innfl?fam=%s&nam=%s&otch=%s&bdate=%s&doctype=21s&docno=%s&key=%s";
        String urlProp = String.format(urlPropTemplate,
            contractorDto.getSurName(),
            contractorDto.getFirstName(),
            contractorDto.getSurName(),
            contractorDto.getBDate(),
            contractorDto.getCredential().getPassport(),
            secretKey);

        final RestTemplate restTemplate = new RestTemplate();
        final String innFns = restTemplate.getForObject(urlProp, String.class);

        assert innFns != null;
        if (innFns.contains("Информация об ИНН не найдена")) {
            return false;
        } else {
            String inn = makeInnFromJson(innFns);
            return inn.equals(contractorDto.getCredential().getInn());
        }
    }

    private String makeInnFromJson(String json) {

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
