package util;

import daoUtil.Query;

/**
 * Created with IntelliJ IDEA.
 * User: 戴虎
 * Date: 2018/08/22
 * Description:
 * Version: V1.0
 */
public class RecordLog {
    /**
     * 保存操作日志
     * @param operationLogInformation  当前操作
     * @param operationMan             操作人
     * @param operationType           操作类型
     * @param operationManId         操作人主键
     */
    public static void recordLog(String operationLogInformation,String operationMan,String operationType,String operationManId){
//        Log log = new Log();
//        log.setOperationLogInformation(operationLogInformation);
//        log.setOperationMan(operationMan);
//        log.setOperationType(operationType);
//        log.setOperationManId(operationManId);
//        new Query().insert(log);
    }
}
