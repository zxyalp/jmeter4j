package com.jmter4j;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.File;
import java.io.IOException;


/**
 * Created by summe on 2017/9/6.
 */
public class WriteHelper implements JavaSamplerClient{
    private SampleResult results;
    private String fileParent;
    private String fileName;
    private String text;

    public Arguments getDefaultParameters(){
        Arguments params = new Arguments();
        params.addArgument("fileParent","D:\\tmp\\");
        params.addArgument("fileName","resp.txt");
        params.addArgument("context","test context.");

        return params;
    }

    public void setupTest(JavaSamplerContext javaSamplerContext) {

    }

    public SampleResult runTest(JavaSamplerContext context) {
        fileParent = context.getParameter("fileParent");
        fileName = context.getParameter("fileName");
        text =    context.getParameter("context");

        results = new SampleResult();

        results.sampleStart();

        try {

            FileHelper.randomWrite(fileParent, fileName, text);

        } catch (IOException e) {
            e.printStackTrace();
        }

        results.sampleEnd();

        return results;
    }

    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

   public static void main(String[] args){

   }
}
