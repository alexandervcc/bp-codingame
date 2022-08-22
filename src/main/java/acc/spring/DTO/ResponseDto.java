package acc.spring.DTO;

import java.util.List;
import java.util.Map;

public class ResponseDto {
    public String title;
    
    public Object data;
    public List<?> dataList;
    
    public String error;
    public Map<?, ?> errorsList;
}
