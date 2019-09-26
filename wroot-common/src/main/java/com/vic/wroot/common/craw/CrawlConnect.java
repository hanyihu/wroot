package com.vic.wroot.common.craw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP请求工具类
 * @author VIC
 *
 */
public class CrawlConnect {
	
    private final Logger logger = LoggerFactory.getLogger(CrawlConnect.class);
    
    private Connection connection;
    /**
     * 必定先调用这个方法
     * @param url
     * @return
     */
    public CrawlConnect(Connection connection){
        this.connection = connection;
    }
    
    public CrawlConnect url(String url){
        connection.url(url);
        return this;
    }
    public CrawlConnect url(URL url){
        connection.url(url);
        return this;
    }
    public CrawlConnect cookie(String name, String value){
        connection.cookie(name, value);
        return this;
    }
    public CrawlConnect cookie(Map<String, String> cookies){
        connection.cookies(cookies);
        return this;
    }
    public CrawlConnect data(String... keyvals){
        connection.data(keyvals);
        return this;
    }
    public CrawlConnect data(String key, String value ) {
        connection.data(key, value);
        return this;
    }
    /**
     * 1.8.x之后的功能
     * @return
     */
    public CrawlConnect data(String key, String filename, InputStream in){
        connection.data(key, filename, in);
        return this;
    }
    public Connection.Response execute() throws IOException{
        return connection.execute();
    }
    public CrawlConnect followRedirects(boolean followRedirects){
        connection.followRedirects(followRedirects);
        return this;
    }
    //get
    public Document getDocument() throws IOException {
        return connection.get();
    }
    public String getHtml() throws IOException{
        return this.getDocument().html();
    }
    public String getBodyText() throws IOException{
        return this.getDocument().body().text();
    }
    public CrawlConnect header(String key, String value) {
        connection.header(key, value);
        return this;
    }
    public CrawlConnect maxBodySize(int bytes) {
        connection.maxBodySize(bytes);
        return this;
    }
    public CrawlConnect method(Connection.Method method){
        connection.method(method);
        return this;
    }
    //Provide an alternate parser to use when parsing the response to a Document. 
    public CrawlConnect parser(Parser parser){
        connection.parser(parser);
        return this;
    }
    //post
    public Document postDocument() throws IOException{
        return connection.post();
    }
    public String postHtml() throws IOException{
        return this.postDocument().html();
    }
    public String postBodyText() throws IOException{
        return this.postDocument().body().text();
    }
    //Sets the default post data character set for x-www-form-urlencoded post data 
    public CrawlConnect postDataCharset(String charset) {
        connection.postDataCharset(charset);
        return this;
    }
    public Connection.Request request(){
        return connection.request();
    }
    public Connection.Response response(){
        return connection.response();
    }
    //Disable/enable TSL certificates validation for HTTPS requests. 
    public  CrawlConnect validateTLSCertificates(boolean value) {
        connection.validateTLSCertificates(value);
        return this;
    }
    /**
     * 下载文件到本地
     * @param path
     * @param fileName
     * @throws IOException 
     */
    public void downFile(String path, String fileName) throws IOException{
    	logger.info("下载文件 到本地{}{}", path, fileName);
        Response response = this.execute();
        File file = getFileByPathAndName(path, fileName);
        FileOutputStream out = new FileOutputStream(file);
        out.write(response.bodyAsBytes());
        out.close();
    }
    
    
    private File getFileByPathAndName(String path, String fileName){
    	File dir = new File(path);
    	if(!dir.exists()) {
    		dir.mkdirs();
    	}
    	return new File(path, fileName);
    }
}