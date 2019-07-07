#!groovy

/*
Enable Kubernetes plugin along with Jenkins URL for slaves 
*/
import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*
import jenkins.model.Jenkins

def j = Jenkins.getInstance()

def k = new KubernetesCloud(
  'Kubernetes',
  null,
  '',
  'default',
  'http://jenkins',
  '10', 0, 0, 5
)
k.setSkipTlsVerify(true)

j.clouds.replace(k)
j.save()
