package EduardoDesplanches.itaujava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> handleMalformedRequest(HttpMessageNotReadableException ex) {
        log.warn("JSON inválido na requisição o {}", ex.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Transação recusada: {}", ex.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleErroInesperado(Exception ex) {
        if (ex instanceof ErrorResponse erroDoSpring) {
            log.warn("Requisição inválida: {}", ex.getMessage());
            return ResponseEntity.status(erroDoSpring.getStatusCode()).build();
        }
        log.error("Erro Inesperado", ex);
        return ResponseEntity.internalServerError().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidacao(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> log.warn("Campo {} inválido: {}", e.getField(), e.getDefaultMessage()));
        return ResponseEntity.unprocessableEntity().build();
    }
}
