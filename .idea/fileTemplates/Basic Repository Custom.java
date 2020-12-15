#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

#parse("File Header.java")
public interface ${MODEL_NAME}RepositoryCustom {
}

