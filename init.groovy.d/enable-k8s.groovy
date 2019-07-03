import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import org.csanchez.jenkins.plugins.kubernetes.*
import org.csanchez.jenkins.plugins.kubernetes.volumes.workspace.EmptyDirWorkspaceVolume
import org.csanchez.jenkins.plugins.kubernetes.volumes.HostPathVolume
 
//since kubernetes-1.0
//import org.csanchez.jenkins.plugins.kubernetes.model.KeyValueEnvVar
import org.csanchez.jenkins.plugins.kubernetes.PodEnvVar
 
//change after testing
//ConfigObject conf = new ConfigSlurper().parse(new File(System.getenv("JENKINS_HOME") + '/jenkins_config/kubernetes.txt').text)
 
def kc
try {
    println("Configuring k8s")
    if (Jenkins.instance.clouds) {
        kc = Jenkins.instance.clouds.get(0)
        println "cloud found: ${Jenkins.instance.clouds}"
    } else {
        kc = new KubernetesCloud('Kubernetes')
        Jenkins.instance.clouds.add(kc)
        println "cloud added: ${Jenkins.instance.clouds}"
    }
 
    kc.setContainerCapStr('500')
    kc.setServerUrl('')
    kc.setSkipTlsVerify(true)
    kc.setNamespace('default')
    kc.setJenkinsUrl('http://jenkins')
//    kc.setCredentialsId('') <<-- WE don't need it as we are running inside k8s
    kc.setRetentionTimeout(5)
    //since kubernetes-1.0
//    kc.setConnectTimeout()
    kc.setReadTimeout(0)
    //since kubernetes-1.0
//    kc.setMaxRequestsPerHostStr(conf.kubernetes.maxRequestsPerHostStr)
 
//    kc = null
    println("Configuring k8s completed")
}
finally {
    //if we don't null kc, jenkins will try to serialise k8s objects and that will fail, so we won't see actual error
    kc = null
}