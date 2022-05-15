package sangwoo.naratmal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sangwoo.naratmal.model.dto.ApiResult;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<String>> validException(MethodArgumentNotValidException exception) {
        ApiResult<String> result = new ApiResult<>(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}