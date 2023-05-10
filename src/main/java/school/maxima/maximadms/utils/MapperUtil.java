package school.maxima.maximadms.utils;

import java.util.List;
import java.util.function.Function;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).toList();
    }

    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
