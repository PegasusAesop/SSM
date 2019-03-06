package com.pegasus.utils;

public class FileNameUtils {

	 /**
	  * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
    	
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    public static String getPrefix(String fileName) {
    	
    	return fileName.substring(0,fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
    	
        return UUIDUtils.getUUID() +"_"
        		+FileNameUtils.getPrefix(fileOriginName) 
        		+ FileNameUtils.getSuffix(fileOriginName);
    }

}
