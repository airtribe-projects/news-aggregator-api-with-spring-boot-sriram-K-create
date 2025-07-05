@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body("Invalid input: " + errorMsg);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOthers(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
    }
}
