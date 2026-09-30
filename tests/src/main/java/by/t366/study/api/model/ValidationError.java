package by.t366.study.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

// Элемент массива detail из ответа FastAPI при ошибке валидации (422).
// loc - массив смешанных типов: ["body", "firstName"] или ["body", 0],
// поэтому List<Object>. ctx - произвольный объект {"min_length": 1}.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationError {

    private List<Object> loc;
    private String msg;
    private String type;
    private String input;
    private Map<String, Object> ctx;
}

