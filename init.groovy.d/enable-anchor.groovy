/* 
Anchor Engine configuration
Author: Ansil H
Email ID: ansilh@gmail.com

Required environmental variables :- 

ANCHOR_ADMIN_USERNAME=admin
ANCHOR_ADMIN_PASSWORD=$(kubectl get secret --namespace default anchore-demo-anchore-engine -o jsonpath="{.data.ANCHORE_ADMIN_PASSWORD}" | base64 --decode; echo)
ANCHOR_ENGINE_URL=http://anchore-demo-anchore-engine-api.default.svc.cluster.local:8228/v1/

*/
import jenkins.*
import hudson.*
import jenkins.model.Jenkins
import com.anchore.jenkins.plugins.anchore.*

println "--> Configuring Anchor"

def adminUsername = System.getenv("ANCHOR_ADMIN_USERNAME")
def adminPassword = System.getenv("ANCHOR_ADMIN_PASSWORD")
def engineURL = System.getenv("ANCHOR_ENGINE_URL")
assert adminUsername != null : "No ANCHOR_ADMIN_USERNAME env var provided, but required"
assert adminPassword != null : "No ANCHOR_ADMIN_PASSWORD env var provided, but required"
assert engineURL != null : "No ANCHOR_ENGINE_URL env var provided, but required"

def a = Jenkins.instance.getDescriptor("com.anchore.jenkins.plugins.anchore.AnchoreBuilder");
a.setEngineurl(engineURL)
a.setEngineuser(adminUsername)
a.setEnginepass(hudson.util.Secret.fromString(adminPassword))
a.save()