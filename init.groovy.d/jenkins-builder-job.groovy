import jenkins.*
import hudson.*
import jenkins.model.Jenkins
import hudson.plugins.git.*;

def scm = new GitSCM("https://github.com/ansilh/jenkins-k8s-master.git")
scm.branches = [new BranchSpec("*/master")];

def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, "Jenkinsfile")

def parent = Jenkins.instance
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, "Jenkins-k8s-builder")
job.definition = flowDefinition

parent.reload()