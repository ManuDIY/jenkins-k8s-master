/* https://support.cloudbees.com/hc/en-us/articles/234709648-Disable-Jenkins-CLI
*/
import jenkins.model.Jenkins
jenkins.model.Jenkins.instance.getDescriptor("jenkins.CLI").get().setEnabled(false)
Jenkins.instance.save()