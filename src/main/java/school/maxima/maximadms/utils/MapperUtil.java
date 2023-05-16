package school.maxima.maximadms.utils;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Setter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MapperUtil {

    private static ModelMapper mapper;

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> mapper.map(element, targetClass))
            .toList();
    }

    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
