#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#parse("File Header.java")
public class ${EXCEPTION_NAME}Exception extends RuntimeException {
    public ${EXCEPTION_NAME}Exception(String message) {
        super(message);
    }
}

