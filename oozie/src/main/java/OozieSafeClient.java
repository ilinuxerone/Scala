import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.oozie.client.AuthOozieClient;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.client.WorkflowJob.Status;

/**
 * Created by Administrator on 2017/5/12.
 */
public class OozieSafeClient {
    AuthOozieClient oozieClient = null;

    public OozieSafeClient(String oozieURLstr, boolean useCache, String cacheFilePath) throws MalformedURLException {

        // oozieURLstr: http://localhost:11000/oozie
        // useCache: "true" to make use of existing cache for authentication.
        // caheFilePath: if file doesn't exist in default user home need to set
        // the path.

        URL oozieUrl = new URL(oozieURLstr);
        if (useCache == true) {
            // To instruct Oozie client to use cached token for kerberos
            // authentication
            System.setProperty("oozie.auth.token.cache", "true");
        }

        if (cacheFilePath != null) {
            System.setProperty("user.home", cacheFilePath);
        }

        oozieClient = new AuthOozieClient(oozieUrl.toString());
    }

    public void submitOozieJob(Map<String, String> oozieConfigMap) {

        Properties conf = oozieClient.createConfiguration();

        for (Map.Entry<String, String> entry : oozieConfigMap.entrySet()) {
            conf.put(entry.getKey(), entry.getValue());
        }

        if (oozieClient != null) {

            try {
                String jobId = oozieClient.run(conf);
                while (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING) {
                    System.out.println("Submitted Oozie wokflow ID : {" + jobId + "]}");
                    Thread.sleep(10000);
                }
                if (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.SUCCEEDED) {

                    System.out.println("Workflow job completed !");

                } else {

                    System.out.println("Workflow job Failed !");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unable to initialize oozie client");
            System.exit(1);
        }
    }

    public static void main(String args[]) throws MalformedURLException {
        // Prepare a map to pass all necessary configurations to oozie job
        Map<String, String> oozieConfigMap = new HashMap<String, String>();
        oozieConfigMap.put("oozie.wf.application.path", "/user/home/oozie/workflow/");
        new OozieSafeClient("http://localhost:11000/oozie", true, null).submitOozieJob(oozieConfigMap);
    }

}
